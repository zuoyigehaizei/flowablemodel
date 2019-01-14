package com.example.flowableactivity.service.impl;

import com.example.flowableactivity.mapper.WorkflowMapper;
import com.example.flowableactivity.model.PageInfo;
import com.example.flowableactivity.model.WorkflowDTO;
import com.example.flowableactivity.model.entity.WorkflowInfo;
import com.example.flowableactivity.service.WorkflowService;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    private WorkflowMapper workflowMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public Integer addWorkflow(String workflowName, String workflowDesc) {
        WorkflowInfo workflowInfo = new WorkflowInfo();
        workflowInfo.setFlowstatus(0);
        workflowInfo.setFlowname(workflowName);
        workflowInfo.setFlowdesc(workflowDesc);
        workflowInfo.setCreatetime(new Date());
        //todo  添加organizationalid  机构id
        return workflowMapper.add(workflowInfo);
    }

    @Override
    public PageInfo<List<WorkflowDTO>> getWorkflowListByOrganizationalId(Integer organizationalid, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("organizationalid", organizationalid);
        int recordcount = 0;
        if(pageSize > 0){
            recordcount = workflowMapper.getsCount(map);
            map.put("page", pageNum);
            map.put("pagesize", pageSize);
        }
        List<WorkflowInfo> workflowInfos = workflowMapper.gets(map);
        List<WorkflowDTO> list = new ArrayList<>();
        for (WorkflowInfo workflowInfo : workflowInfos){
            WorkflowDTO workflowDTO = workflowMapper.getWorkflowDto(workflowInfo.getId());
            list.add(workflowDTO);
        }
        PageInfo<List<WorkflowDTO>> pageInfo = new PageInfo<>(pageSize,recordcount,pageNum);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public PageInfo<List<WorkflowDTO>> getActivityHistory(String activtiyKey, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityKey", activtiyKey);
        int recordcount = 0;
        if(pageSize > 0){
            recordcount = (int) repositoryService.createProcessDefinitionQuery().processDefinitionKey(activtiyKey).count();
            map.put("page", pageNum);
            map.put("pagesize", pageSize);
        }
        List<WorkflowDTO> list = workflowMapper.getActivityHistory(map);
        PageInfo<List<WorkflowDTO>> pageInfo = new PageInfo<>(pageSize, recordcount, pageNum);
        pageInfo.setRows(list);
        return pageInfo;
    }


}
