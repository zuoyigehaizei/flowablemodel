package com.example.flowableactivity.service;

import org.flowable.task.api.Task;

import java.util.List;

public interface WorkflowRevolveService {

    /**
     * 启动流程process
     * @param orderId
     * @param money
     * @param key
     */
    void addProcess(String orderId, Integer money, String key);

    /**
     * 审核通过
     * @param taskId
     */
    void auditApply(String taskId);

    /**
     * 拒绝审核
     * @param taskId
     */
    void auditReject(String taskId);

    /**
     * 获取审批管理列表
     * @param userId
     * @return
     */
    List<Task> getAuditList(String userId);
}
