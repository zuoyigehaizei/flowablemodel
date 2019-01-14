package com.example.flowableactivity.mapper;


import com.example.flowableactivity.model.entity.WorkflowStepInfo;

import java.util.List;
import java.util.Map;

public interface WorkflowStepMapper {

	int add(WorkflowStepInfo entity);

	int edit(WorkflowStepInfo entity);

	int del(int id);

	WorkflowStepInfo getByID(int id);

	int getsCount(Map<String, Object> maps);

	List<WorkflowStepInfo> gets(Map<String, Object> maps);

}