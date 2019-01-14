package com.example.flowableactivity.service;

import com.example.flowableactivity.model.entity.WorkflowConditionInfo;

import java.util.List;

public interface WorkflowConditionService {

    /**
     * 根据类型和value查询条件
     * @param type
     * @param conditionvalue
     * @return
     */
    List<WorkflowConditionInfo> selectWorkflowCondition(int type, String conditionvalue);

    /**
     * 添加条件
     * @param key
     * @param value
     * @param type
     * @return
     */
    Integer addWorkflowCondition(String key, String value,Integer type);
}
