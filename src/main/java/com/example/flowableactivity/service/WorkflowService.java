package com.example.flowableactivity.service;

import com.example.flowableactivity.model.PageInfo;
import com.example.flowableactivity.model.WorkflowDTO;
import com.example.flowableactivity.model.entity.WorkflowInfo;

import java.util.List;

public interface WorkflowService {

    /**
     * 添加工作流
     * @param workflowName
     * @param workflowDesc
     * @return
     */
    Integer addWorkflow(String workflowName, String workflowDesc);

    /**
     * 根据机构id,获取工作流列表
     * @param organizationalid
     * @return
     */
    PageInfo<List<WorkflowDTO>> getWorkflowListByOrganizationalId(Integer organizationalid, Integer pageNum, Integer pageSize);

    /**
     * 获取工作流版本列表
     * @param activtiyKey
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<List<WorkflowDTO>> getActivityHistory(String activtiyKey, Integer pageNum, Integer pageSize);
}
