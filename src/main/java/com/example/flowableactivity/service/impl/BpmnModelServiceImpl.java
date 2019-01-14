package com.example.flowableactivity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.flowableactivity.constant.WorkflowStepTypeEnum;
import com.example.flowableactivity.mapper.WorkflowConditionMapper;
import com.example.flowableactivity.mapper.WorkflowMapper;
import com.example.flowableactivity.mapper.WorkflowStepMapper;
import com.example.flowableactivity.model.entity.WorkflowConditionInfo;
import com.example.flowableactivity.model.entity.WorkflowInfo;
import com.example.flowableactivity.model.entity.WorkflowStepInfo;
import com.example.flowableactivity.service.BpmnModelService;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.*;

@Service
public class BpmnModelServiceImpl implements BpmnModelService {

    @Autowired
    private WorkflowMapper workflowMapper;

    @Autowired
    private WorkflowConditionMapper workflowConditionMapper;

    @Autowired
    private WorkflowStepMapper workflowStepMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;


    @Override
    public BpmnModel bpmnAssembly(Integer workflowId) {
        try {
            BpmnModel bpmnModel = new BpmnModel();
            //获取工作流对象
            WorkflowInfo workflow = workflowMapper.getByID(workflowId);
            Process process = new Process();
            //指定key
            process.setId("process" + workflowId);
            process.setName(workflow.getFlowname());
            //获取活动下所有步骤
            Map<String, Object> map = new HashMap<>();
            map.put("flowid", workflowId);
            List<WorkflowStepInfo> steps = workflowStepMapper.gets(map);
            Map<Object, WorkflowStepInfo> workflowstep = new HashMap<>();
            //添加节点到bpmnmodel（排序type asc）
            for (WorkflowStepInfo w : steps) {
                if (w.getType() != WorkflowStepTypeEnum.START.toValue()) {
                    //将步骤存入集合
                    workflowstep.put(w.getId(), w);
                }
                if (w.getType() == WorkflowStepTypeEnum.START.toValue()) {
                    //开始步骤存入集合
                    workflowstep.put("start", w);
                    process.addFlowElement(createStartEvent(w));
                } else if (w.getType() == WorkflowStepTypeEnum.FINISH.toValue()) {
                    process.addFlowElement(createEndEvent(w));
                } else if (w.getType() == WorkflowStepTypeEnum.COUNTERSIGN.toValue()) {
                    continue;
                } else {
                    process.addFlowElement(createUserTask("_" + w.getId(), w.getName(), "", w.getAuditname()));
                }
            }
            //获取开始节点(type=1)
            WorkflowStepInfo workflowStepInfo = workflowstep.get("start");
            Assert.notNull(workflowStepInfo, "开始步骤为空");
            //存入当前开始节点到list
            List<WorkflowStepInfo> list = new ArrayList<>();
            list.add(workflowStepInfo);
            //定义集合记录存入的节点
            List<WorkflowStepInfo> jilu = new ArrayList<>();
            long l = System.currentTimeMillis();
            for (int i = 0; i < steps.size(); i++) {
                if (steps.get(i).getType() == WorkflowStepTypeEnum.FINISH.toValue()) {
                    break;
                }
                //根据集合中当前节点，获取下一节点，并连线
                int size = list.size();
                for (int x = 0; x < size; x++) {
                    WorkflowStepInfo wf = list.get(0);
                    String nextstepid = wf.getNextstepid();
                    if (nextstepid == null) {
                        list.remove(0);
                        continue;
                    }
                    String[] split = nextstepid.split(",");
                    for (int j = 0; j < split.length; j++) {
                        //获取下一节点
                        WorkflowStepInfo step = workflowstep.get(Integer.parseInt(split[j]));
                        WorkflowConditionInfo byID = null;
                        //判断节点是否是会签节点
                        if (step.getType() == WorkflowStepTypeEnum.COUNTERSIGN.toValue()) {
                            //如果是会签根据当前节点信息生成
                            process.addFlowElement(createUserTask1("_" + step.getId(), "huiqian", "", ""));
                            if (step.getConditionid() != null) {
                                //获取节点条件，添加
                                String conditionid = step.getConditionid();
                                JSONObject jsonObject = JSON.parseObject(conditionid);
                                Integer integer = (Integer) jsonObject.get(wf.getId());
                                if (integer == null) {
                                    process.addFlowElement(createSequenceFlow("_" + wf.getId(), "_" + step.getId(), "_" + wf.getId() + "_" + step.getId() + "ffsdf", "", ""));
                                } else {
                                    byID = workflowConditionMapper.getByID(integer);
                                    //连线
                                    process.addFlowElement(createSequenceFlow("_" + wf.getId(), "_" + step.getId(), "_" + wf.getId() + "_" + step.getId() + "ffsdf", "", byID.getConditionexpression()));
                                }
                            } else {
                                //无条件连线
                                process.addFlowElement(createSequenceFlow("_" + wf.getId(), "_" + step.getId(), "_" + wf.getId() + "_" + step.getId() + "ffsdf", "", ""));
                            }
                        } else {
                            //判断节点条件
                            int i1 = new Random().nextInt(100);
                            if (step.getConditionid() != null) {
                                //获取节点条件，添加
                                String conditionid = step.getConditionid();
                                JSONObject jsonObject = JSON.parseObject(conditionid);
                                Integer integer = (Integer) jsonObject.get(wf.getId());
                                if (integer == null) {
                                    process.addFlowElement(createSequenceFlow("_" + wf.getId(), "_" + step.getId(), "_" + wf.getId() + "_" + step.getId() + i1, "", ""));
                                } else {
                                    byID = workflowConditionMapper.getByID(integer);
                                    process.addFlowElement(createSequenceFlow("_" + wf.getId(), "_" + step.getId(), "_" + wf.getId() + "_" + step.getId() + i1, "", byID.getConditionexpression()));
                                }
                            } else {
                                //无条件连线
                                process.addFlowElement(createSequenceFlow("_" + wf.getId(), "_" + step.getId(), "_" + wf.getId() + "_" + step.getId() + i1, "", ""));
                            }
                        }
                        //重复节点，过滤掉
                        if (!jilu.contains(step)) {
                            list.add(step);
                        }
                        jilu.add(step);
                    }
                    list.remove(wf);
                }
            }
            long l1 = System.currentTimeMillis();
            System.out.println("用时时间是" + (l1 - l));
            bpmnModel.addProcess(process);
            return bpmnModel;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("流程模型添加失败");
        }
    }

    @Override
    public InputStream getworkflowDiagram(String activtiyKey, String version) {
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionVersion(Integer.valueOf(version)).processDefinitionKey(activtiyKey).singleResult();
            //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
            InputStream in = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator()
                    .generateDiagram(bpmnModel, "png",
                            new ArrayList<>(), new ArrayList<>(), "宋体", "宋体", "宋体", null, 1.0, false);
            return in;
        }catch (Exception e){
            throw new RuntimeException("获取流程图失败");
        }
    }

    //任务节点-组
    protected UserTask createGroupTask(String id, String name, String candidateGroup) {
        List<String> candidateGroups = new ArrayList<String>();
        candidateGroups.add(candidateGroup);
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setCandidateGroups(candidateGroups);
        return userTask;
    }

    //任务节点-用户
    protected UserTask createUserTask(String id, String name, String userPkno, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }

    //会签
    protected UserTask createUserTask1(String id, String name, String userPkno, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee("${leader}");
        MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = new MultiInstanceLoopCharacteristics();
        multiInstanceLoopCharacteristics.setSequential(false);
        multiInstanceLoopCharacteristics.setInputDataItem("leaderList");
        multiInstanceLoopCharacteristics.setElementVariable("leader");
        userTask.setLoopCharacteristics(multiInstanceLoopCharacteristics);
        return userTask;
    }


    //任务节点-锁定者
    protected UserTask createAssigneeTask(String id, String name, String assignee) {
        UserTask userTask = new UserTask();
        userTask.setName(name);
        userTask.setId(id);
        userTask.setAssignee(assignee);
        return userTask;
    }

    /*连线*/
    protected SequenceFlow createSequenceFlow(String from, String to, String id, String name, String conditionExpression) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        flow.setName(name);
        flow.setId(id);
        if (StringUtils.isNotEmpty(conditionExpression)) {
            flow.setConditionExpression(conditionExpression);
        }
        return flow;
    }

    //排他网关
    protected ExclusiveGateway createExclusiveGateway(String id, String name) {
        ExclusiveGateway exclusiveGateway = new ExclusiveGateway();
        exclusiveGateway.setId(id);
        exclusiveGateway.setName(name);
        return exclusiveGateway;
    }

    //并行网关
    protected ParallelGateway createParallelGateway(String id, String name) {
        ParallelGateway gateway = new ParallelGateway();
        gateway.setId(id);
        gateway.setName(name);
        return gateway;
    }

    //开始节点
    protected StartEvent createStartEvent(WorkflowStepInfo workflowStepInfo) {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("_" + workflowStepInfo.getId());
        return startEvent;
    }

    /*结束节点*/
    protected EndEvent createEndEvent(WorkflowStepInfo workflowStepInfo) {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("_" + workflowStepInfo.getId());
        return endEvent;
    }
}
