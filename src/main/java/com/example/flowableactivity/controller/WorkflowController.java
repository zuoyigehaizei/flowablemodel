package com.example.flowableactivity.controller;

import com.example.flowableactivity.model.PageInfo;
import com.example.flowableactivity.model.ResultInfo;
import com.example.flowableactivity.model.WorkflowDTO;
import com.example.flowableactivity.service.BpmnModelService;
import com.example.flowableactivity.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {

    Logger logger = LoggerFactory.getLogger(WorkflowController.class);

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BpmnModelService bpmnModelService;

    //添加工作流(工作流名称，描述，)
    @PostMapping
    public ResultInfo<Object> addWorkflow(@RequestParam String workflowName,@RequestParam String workflowDesc) {
        Integer b = workflowService.addWorkflow(workflowName,workflowDesc);
        if(b <= 0){
            return new ResultInfo<>(ResultInfo.codeFailure,"添加工作流失败",null);
        }
        return new ResultInfo<>(ResultInfo.codeSuccessfully);
    }

    //todo 修改工作流

    //todo 回显工作流

    //查看工作流列表
    @GetMapping
    public ResultInfo<Object> getWorkflowList(@RequestParam Integer organizationalid,@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        PageInfo<List<WorkflowDTO>> list = workflowService.getWorkflowListByOrganizationalId(organizationalid,pageNum,pageSize);
        return new ResultInfo<>(list);
    }


    //根据activtiyKey和版本查看工作流流程图
    @GetMapping("/diagram")
    public void getWorkflowDiagram(HttpServletResponse response, @RequestParam String activtiyKey, @RequestParam String version) throws IOException {
        InputStream in = bpmnModelService.getworkflowDiagram(activtiyKey, version);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = response.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    //获取工作流历史版本列表
    @GetMapping("/activityHistory")
    public ResultInfo<Object> getActivityHistory(@RequestParam String activtiyKey,@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        PageInfo<List<WorkflowDTO>> pageInfo = workflowService.getActivityHistory(activtiyKey, pageNum, pageSize);
        return new ResultInfo<>(pageInfo);
    }

}
