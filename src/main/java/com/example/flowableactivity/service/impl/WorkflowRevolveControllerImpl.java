package com.example.flowableactivity.service.impl;

import com.example.flowableactivity.service.WorkflowRevolveService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;

@Service
public class WorkflowRevolveControllerImpl implements WorkflowRevolveService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void addProcess(String orderId, Integer money, String key) {
        //启动流程(提交申请订单变量（money,orderId）)
        HashMap<String, Object> map = new HashMap<>();
        map.put("money", money);
        //todo  获取流程会签表情审核人，添加到流动变量中
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("aaa");
//        strings.add("bbb");
//        strings.add("ccc");
//        map.put("leaderList", strings);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, orderId,map);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
    }

    @Override
    public void auditApply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        Assert.notNull(task,"流程不存在");
        String processInstanceId = task.getProcessInstanceId();
        if(task.getTaskDefinitionKey().contains("huiqian")){
            //会签节点
            taskService.complete(taskId);
            //todo  存储会签意见信息
        }else{
            //通过审核
            HashMap<String, Object> map = new HashMap<>();
            map.put("outcome", "通过");
            taskService.complete(taskId, map);
            //todo 存储审核通过意见
        }
        //判断当前节点是否是最后节点，如何是修改订单状态
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(pi == null){
            //todo 修改订单状态为通过
        }
    }

    @Override
    public void auditReject(String taskId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "拒绝");
        taskService.complete(taskId, map);
        //todo 存储审核拒绝意见
        //todo 修改订单状态为通过  结束工作流
    }

    @Override
    public List<Task> getAuditList(String userId) {
        //todo 分页
        return taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
    }
}
