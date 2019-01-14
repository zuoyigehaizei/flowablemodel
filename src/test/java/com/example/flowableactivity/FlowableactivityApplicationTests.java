package com.example.flowableactivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowableactivityApplicationTests {

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void contextLoads() {

//        Map<String, String> map = new HashMap<>();
//        map.put("11","ddd");
//        map.put("22","ccc");
//        map.put("33","aaa");
//        String s = JSON.toJSONString(map);
//        Map map1 = JSON.parseObject(s, HashMap.class);
//        Map<String,String> map2 = JSON.parseObject(s, Map.class);

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId("7581e1ea-156f-11e9-8bda-d4bed9c6d93b").singleResult();
        ProcessInstance p2 = runtimeService.createProcessInstanceQuery().processInstanceId("process1:1:bb8dc3e2-13b1-11e9-b7a1-d4bed9c6d93b").singleResult();
        ProcessInstance p3 = runtimeService.createProcessInstanceQuery().processInstanceId("8164cd18-154e-11e9-9c7e-d4bed9c6d93b").singleResult();
        ProcessInstance p4 = runtimeService.createProcessInstanceQuery().processInstanceId("75827e35-156f-11e9-8bda-d4bed9c6d93b").singleResult();


    }

}

