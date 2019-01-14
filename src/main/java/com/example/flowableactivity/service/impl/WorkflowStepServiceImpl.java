package com.example.flowableactivity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.flowableactivity.constant.WorkflowStepTypeEnum;
import com.example.flowableactivity.mapper.WorkflowStepMapper;
import com.example.flowableactivity.model.WorkflowStepReqDTO;
import com.example.flowableactivity.model.entity.WorkflowConditionInfo;
import com.example.flowableactivity.model.entity.WorkflowStepInfo;
import com.example.flowableactivity.service.WorkflowConditionService;
import com.example.flowableactivity.service.WorkflowStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkflowStepServiceImpl implements WorkflowStepService {

    @Autowired
    private WorkflowStepMapper workflowStepMapper;

    @Autowired
    private WorkflowConditionService workflowConditionService;

    @Override
    public Integer addWorkflowStep(String stepName, String stepDesc, Integer type, Integer workflowId) {
        WorkflowStepInfo workflowStepInfo = new WorkflowStepInfo();
        workflowStepInfo.setCreatetime(new Date());
        workflowStepInfo.setName(stepName);
        workflowStepInfo.setStepdesc(stepDesc);
        workflowStepInfo.setType(type);
        workflowStepInfo.setFlowid(workflowId);
        //todo 添加后int类型默认值0
        int add = workflowStepMapper.add(workflowStepInfo);
        return add;
    }

    @Override
    public boolean setWorkflowStep(WorkflowStepReqDTO workflowStepReqDTO) {
        try {
            //获取当前步骤
            WorkflowStepInfo step = workflowStepMapper.getByID(workflowStepReqDTO.getStepId());
            //判断步骤类型
            int type = step.getType();
            String roleIds = workflowStepReqDTO.getRoleIds();
            if (type == WorkflowStepTypeEnum.AUDIT_DETERMINE.toValue()) {
                //审核通过执行步骤（只能有一个id）
                List<WorkflowConditionInfo> list = workflowConditionService.selectWorkflowCondition(0, null);
                for (WorkflowConditionInfo workflowConditionInfo : list) {
                    Integer workflowConditionId = workflowConditionInfo.getId();
                    if (workflowConditionInfo.getConditionvalue().equals("通过")) {
                        setWorkflowCondition(workflowStepReqDTO.getSuccessStepId(), step.getId(), workflowConditionId);
                    } else {
                        setWorkflowCondition(workflowStepReqDTO.getFaildStepId(), step.getId(), workflowConditionId);
                    }
                    //设置步骤条件关联
                    step.setNextstepid(workflowStepReqDTO.getSuccessStepId() + "," + workflowStepReqDTO.getFaildStepId());
                }
            } else if (type == WorkflowStepTypeEnum.CONDITION_DETERMINE.toValue()) {
                //条件判定
                //添加条件到条件表 左边条件值，右边步骤
                String successCondition = workflowStepReqDTO.getSuccessCondition();
                conditionAudit(successCondition.split(","), step.getId());
                String faileCondition = workflowStepReqDTO.getFaileCondition();
                conditionAudit(faileCondition.split(","), step.getId());
                //设置步骤下一步
                step.setNextstepid(workflowStepReqDTO.getSuccessCondition() + "," + workflowStepReqDTO.getFaileCondition());
            } else if (type == WorkflowStepTypeEnum.COUNTERSIGN.toValue()) {
                //会签类型
//            step.setAuditid(roleIds);
                //todo   根据角色id查询角色名称添加
                step.setNextstepid(workflowStepReqDTO.getNextStepId());
            } else {
                //普通类型
                step.setNextstepid(workflowStepReqDTO.getNextStepId());
            }
            //角色
            step.setAuditid(roleIds);
            //更新时间
            step.setUpdatetime(new Date());
            workflowStepMapper.edit(step);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("步骤设置失败");
        }
    }

    @Override
    public List<WorkflowStepInfo> getWorkflowStepLists(Integer activityId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("flowid", activityId);
        return workflowStepMapper.gets(map);
    }


    private void setWorkflowCondition(Integer conditionStepId, int stepId, Integer workflowConditionId) {
        WorkflowStepInfo step = workflowStepMapper.getByID(conditionStepId);
        Assert.notNull(step, "审核下一步为空");
        String conditionid = step.getConditionid();
        Map<Integer, Integer> map;
        if (conditionid != null) {
            map = JSONObject.parseObject(conditionid, HashMap.class);
        } else {
            map = new HashMap<>();
        }
        map.put(stepId, workflowConditionId);
        step.setConditionid(JSON.toJSONString(map));
        workflowStepMapper.edit(step);
    }

    private void conditionAudit(String[] split, Integer stepId) {
        Integer conditionId = workflowConditionService.addWorkflowCondition("money", split[0], 1);
        //设置步骤条件json
        setWorkflowCondition(Integer.valueOf(split[1]), stepId, conditionId);
    }

}
