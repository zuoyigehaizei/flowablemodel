package com.example.flowableactivity.controller;

import com.example.flowableactivity.model.ResultInfo;
import com.example.flowableactivity.model.WorkflowStepReqDTO;
import com.example.flowableactivity.model.entity.WorkflowStepInfo;
import com.example.flowableactivity.service.WorkflowStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workflowStep")
public class WorkflowStepController {

    @Autowired
    private WorkflowStepService workflowStepService;

    //添加步骤(步骤名称，步骤描述，步骤类型)
    @PostMapping
    public ResultInfo<Object> addWorkflowStep(@RequestParam String stepName, @RequestParam String stepDesc, @RequestParam Integer type, @RequestParam Integer workflowId) {
        Integer b = workflowStepService.addWorkflowStep(stepName, stepDesc, type, workflowId);
        if (b > 0) {
            return new ResultInfo<>(ResultInfo.codeSuccessfully);
        }
        return new ResultInfo<>(ResultInfo.codeFailure, "添加失败", "");
    }

    //设置步骤
    @PutMapping("/setWorkflowStep")
    public ResultInfo<Object> setWorkflowStep(WorkflowStepReqDTO workflowStepReqDTO) {
        boolean b = workflowStepService.setWorkflowStep(workflowStepReqDTO);
        if (b) {
            return new ResultInfo<>(ResultInfo.codeSuccessfully);
        }
        return new ResultInfo<>(ResultInfo.codeFailure, "设置失败", "");
    }

    //显示步骤列表
    @GetMapping("/workflowStepLists")
    public ResultInfo<Object> getWorkflowStepLists(@RequestParam Integer activityId) {
        List<WorkflowStepInfo> list = workflowStepService.getWorkflowStepLists(activityId);
        return new ResultInfo<>(ResultInfo.codeSuccessfully, "", list);
    }

    //修改步骤

    //删除步骤

    //回显步骤
}
