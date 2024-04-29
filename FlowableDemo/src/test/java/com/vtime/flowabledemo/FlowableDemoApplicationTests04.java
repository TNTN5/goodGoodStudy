package com.vtime.flowabledemo;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/*
 *
 * 网关
 * */
@SpringBootTest(classes = FlowableDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FlowableDemoApplicationTests04 {
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    IdentityService identityService;


    //    流程部署操作
    @Test
    public void deployFlow() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/01/HolidayDemo4.bpmn20.xml")
                .name("并行网关")
                .deploy();
        System.out.println("deploy.getId() = " + deploy.getId());
    }

    @Test
    public void deleteDeployment(){
        repositoryService.deleteDeployment("45d8fd80-fc91-11ee-89dd-00ff21492263",true);
    }

    //开始流程
    @Test
    public void startFlow() {
//        在流程定义表里动态维护
        String processId = "HolidayDemo4:1:bd91ea80-fc94-11ee-92ad-00ff21492263";
//        根据流程id启动实例
        runtimeService.startProcessInstanceById(processId);
    }


    @Test
    public void completeTask() {
        String taskId = "51cc63e2-fc95-11ee-86c0-00ff21492263";
        taskService.complete(taskId);
    }

}
