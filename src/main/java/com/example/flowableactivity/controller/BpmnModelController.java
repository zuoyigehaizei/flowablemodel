package com.example.flowableactivity.controller;


import com.example.flowableactivity.model.ResultInfo;
import com.example.flowableactivity.service.BpmnModelService;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.model.*;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BpmnModelController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private BpmnModelService bpmnModelService;

    /**
     * 部署工作流
     */
    @PostMapping("bpmnAssembly/{activityId}")
    public ResultInfo<Object> addBpmnModel(@PathVariable Integer activityId) {
       try {
           BpmnModel bpmnModel = bpmnModelService.bpmnAssembly(activityId);
//        //验证bpmnmodel
           ProcessValidator defaultProcessValidator = new ProcessValidatorFactory().createDefaultProcessValidator();
           List<ValidationError> validate = defaultProcessValidator.validate(bpmnModel);
           if(validate.size() > 0){
               throw new RuntimeException("流程有误，请检查后重试");
           }
           // 流程定义的分类
           String filename = "activity" + activityId + "bpmn20.xml";
           //生成自动布局
           new BpmnAutoLayout(bpmnModel).execute();
           DeploymentBuilder deploymentBuilder = repositoryService
                   .createDeployment()
                   .addBpmnModel(filename, bpmnModel);
           // 部署
           Deployment deploy = deploymentBuilder.deploy();
           return new ResultInfo<>(ResultInfo.codeSuccessfully, "部署成功", deploy);
       }catch (Exception e){
           e.printStackTrace();
           return new ResultInfo<>(ResultInfo.codeFailure, e.getMessage(), "部署失败");
       }
    }

}
