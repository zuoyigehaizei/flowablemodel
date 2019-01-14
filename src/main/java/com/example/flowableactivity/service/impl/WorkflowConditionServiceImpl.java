package com.example.flowableactivity.service.impl;

import com.example.flowableactivity.mapper.WorkflowConditionMapper;
import com.example.flowableactivity.model.entity.WorkflowConditionInfo;
import com.example.flowableactivity.service.WorkflowConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkflowConditionServiceImpl implements WorkflowConditionService {

    @Autowired
    private WorkflowConditionMapper workflowConditionMapper;

    @Override
    public List<WorkflowConditionInfo> selectWorkflowCondition(int type, String conditionvalue) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        if(conditionvalue != null){
            map.put("conditionvalue", conditionvalue);
        }
        return workflowConditionMapper.gets(map);
    }

    @Override
    public Integer addWorkflowCondition(String key, String value,Integer type) {
        WorkflowConditionInfo workflowConditionInfo = new WorkflowConditionInfo();
        workflowConditionInfo.setConditinokey(key);
        workflowConditionInfo.setConditionvalue(value);
        workflowConditionInfo.setCreatetime(new Date());
        if(type == 0){
            workflowConditionInfo.setConditionexpression("${"+key+"=="+value+"}");
        }else{
            workflowConditionInfo.setConditionexpression("${"+key + value+"}");
        }
        workflowConditionInfo.setType(type);
        workflowConditionMapper.add(workflowConditionInfo);
        return workflowConditionInfo.getId();
    }
}
