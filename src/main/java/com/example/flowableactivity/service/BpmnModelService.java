package com.example.flowableactivity.service;

import org.flowable.bpmn.model.BpmnModel;

import java.io.InputStream;

public interface BpmnModelService {

    /**
     * 组装工作流对象
     * @return
     */
    BpmnModel bpmnAssembly(Integer activity);

    /**
     * 获取工作流版本流程图
     * @param activtiyKey
     * @param version
     */
    InputStream getworkflowDiagram(String activtiyKey, String version);
}
