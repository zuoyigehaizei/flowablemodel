package com.example.flowableactivity.service;

import com.example.flowableactivity.model.WorkflowStepReqDTO;
import com.example.flowableactivity.model.entity.WorkflowStepInfo;

import java.util.List;

public interface WorkflowStepService {

    /**
     * 添加工作流步骤
     *
     * @return
     */
    Integer addWorkflowStep(String stepName, String stepDesc, Integer type, Integer workflowId);

    /**
     * 设置工作流步骤
     *
     * @param workflowStepReqDTO
     * @return
     */
    boolean setWorkflowStep(WorkflowStepReqDTO workflowStepReqDTO);

    /**
     * 查询步骤列表
     *
     * @param activityId
     * @return
     */
    List<WorkflowStepInfo> getWorkflowStepLists(Integer activityId);
}
