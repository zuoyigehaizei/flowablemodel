package com.example.flowableactivity.mapper;


import com.example.flowableactivity.model.entity.WorkflowConditionInfo;

import java.util.List;
import java.util.Map;

public interface WorkflowConditionMapper {

	int add(WorkflowConditionInfo entity);

	int edit(WorkflowConditionInfo entity);

	int del(int id);

	WorkflowConditionInfo getByID(int id);

	int getsCount(Map<String, Object> maps);

	List<WorkflowConditionInfo> gets(Map<String, Object> maps);

}