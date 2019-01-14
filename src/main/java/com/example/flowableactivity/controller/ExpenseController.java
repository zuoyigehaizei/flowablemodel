package com.example.flowableactivity.controller;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "expense")
public class ExpenseController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;


    @PostMapping(value = "add")
    @ResponseBody
    public String addExpense(String userId, Integer money, String key) {
        if (true) {
            throw new RuntimeException("启动流程失败");
        }
        //启动流程(提交申请订单)
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("money", money);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");
        map.put("leaderList", strings);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, "orderId",map);
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
        return "提交成功.流程Id为：" + processInstance.getId();
    }


    /**
     * 会签节点通过
     */
    @PutMapping(value = "huiqian")
    @ResponseBody
    public String huiqian(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        taskService.complete(taskId);
        return "processed ok!";
    }


    /**
     * 获取审批管理列表
     */
    @GetMapping(value = "/list")
    public Object list(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        return tasks.toString();
    }

    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    @PutMapping(value = "apply")
    @ResponseBody
    public String apply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        taskService.complete(taskId, map);
        return "processed ok!";
    }


    /**
     * 拒绝
     */
    @ResponseBody
    @PutMapping(value = "reject")
    public String reject(String taskId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "不通过");
        taskService.complete(taskId, map);
        //结束工作流
        taseCancel();
        return "success";
    }

    //取消订单
    public void taseCancel() {
        //查询所有订单
        List<Task> orderCancel = taskService.createTaskQuery().taskDefinitionKey("orderCancel").list();
        //取消
        for (Task task : orderCancel) {
            taskService.complete(task.getId());
        }
    }


    /**
     * 生成流程图
     *
     * @param processId 任务ID
     */
//    @GetMapping(value = "processDiagram")
//    public void genProcessDiagram01(HttpServletResponse httpServletResponse, String processId) throws Exception {
//        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
//        List<String> flows = new ArrayList<>();
//        //获取流程图
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(processId);
////        InputStream in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
////                .generateDiagram(bpmnModel, "png", new ArrayList<>(),
////                        flows, "宋体","宋体","宋体", null, 1.0);
//        InputStream in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
//                .generateDiagram(bpmnModel, "png",
//                        new ArrayList<>(),flows, "宋体","宋体","宋体",null,1.0, false);
//        OutputStream out = null;
//        byte[] buf = new byte[1024];
//        int legth = 0;
//        try {
//            out = httpServletResponse.getOutputStream();
//            while ((legth = in.read(buf)) != -1) {
//                out.write(buf, 0, legth);
//            }
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
//    }
    @GetMapping(value = "processDiagram")
    public void genProcessDiagram01(HttpServletResponse httpServletResponse, String processId) throws Exception {
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        List<String> flows = new ArrayList<>();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processId);
//        InputStream in = repositoryService.getResourceAsStream(processId, "abbpmn20.xml");
        InputStream in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
                .generateDiagram(bpmnModel, "png",
                        new ArrayList<>(), flows, "宋体", "宋体", "宋体", null, 1.0, false);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
