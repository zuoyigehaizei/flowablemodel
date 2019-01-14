package com.example.flowableactivity.controller;


import com.example.flowableactivity.model.ResultInfo;
import com.example.flowableactivity.service.WorkflowRevolveService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class WorkflowRevolveController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkflowRevolveService workflowRevolveService;

    //启动流程
    @PostMapping(value = "startProcess")
    @ResponseBody
    public ResultInfo<Object> addProcess(String orderId, Integer money, String key) {
        workflowRevolveService.addProcess(orderId, money, key);
        return new ResultInfo<>(ResultInfo.codeSuccessfully);
    }

    //审核通过(审核通过后查询是否存有流程实例，如何存在流程结束修改订单状态)
    @PutMapping(value = "auditApply")
    public ResultInfo<Object> auditApply(String taskId) {
        workflowRevolveService.auditApply(taskId);
        return new ResultInfo<>(ResultInfo.codeSuccessfully);
    }

    //审核拒绝(拒绝，修改订单状态)
    @ResponseBody
    @PutMapping(value = "auditReject")
    public ResultInfo<Object> auditReject(String taskId) {
        workflowRevolveService.auditReject(taskId);
        return new ResultInfo<>(ResultInfo.codeSuccessfully);
    }

    //获取审批管理列表
    @GetMapping(value = "/lists")
    @ResponseBody
    public ResultInfo<Object> auditList(String userId) {
        List<Task> tasks = workflowRevolveService.getAuditList(userId);
        return new ResultInfo<>(ResultInfo.codeSuccessfully, "", tasks.toString());
    }
}
