package com.example.flowableactivity.mapper;


import com.example.flowableactivity.model.WorkflowDTO;
import com.example.flowableactivity.model.entity.WorkflowInfo;

import java.util.List;
import java.util.Map;

public interface WorkflowMapper {

	int add(WorkflowInfo entity);

	int edit(WorkflowInfo entity);

	int del(int id);

	WorkflowInfo getByID(int id);

	int getsCount(Map<String, Object> maps);

	List<WorkflowInfo> gets(Map<String, Object> maps);

	/**
	 * 获取当前工作流最新版本
	 * @param id
	 * @return
	 */
    WorkflowDTO getWorkflowDto(int id);

	/**
	 * 查询工作流历史版本
	 * @param map
	 * @return
	 */
	List<WorkflowDTO> getActivityHistory(Map<String, Object> map);
}