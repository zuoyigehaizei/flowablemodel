package com.example.flowableactivity.model;

public class WorkflowStepReqDTO {

    //工作流id
    private Integer workflowId;

    //步骤id
    private Integer stepId;

    //步骤执行下一步id   字符串，分隔
    private String nextStepId;

    //审核通过执行步骤id
    private Integer successStepId;

    //审核不通过执行步骤id
    private Integer faildStepId;

    //条件正确判定步骤（逗号关联，左边条件值，右边步骤）
    private String successCondition;

    //条件不正确判定步骤（逗号关联，左边条件值，右边步骤）
    private String faileCondition;

    //步骤角色id (,号分隔)
    private String roleIds;

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Integer workflowId) {
        this.workflowId = workflowId;
    }


    public String getNextStepId() {
        return nextStepId;
    }

    public void setNextStepId(String nextStepId) {
        this.nextStepId = nextStepId;
    }

    public Integer getSuccessStepId() {
        return successStepId;
    }

    public String getSuccessCondition() {
        return successCondition;
    }

    public void setSuccessCondition(String successCondition) {
        this.successCondition = successCondition;
    }

    public String getFaileCondition() {
        return faileCondition;
    }

    public void setFaileCondition(String faileCondition) {
        this.faileCondition = faileCondition;
    }

    public void setSuccessStepId(Integer successStepId) {
        this.successStepId = successStepId;
    }

    public Integer getFaildStepId() {
        return faildStepId;
    }

    public void setFaildStepId(Integer faildStepId) {
        this.faildStepId = faildStepId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
