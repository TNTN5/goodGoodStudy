package com.vtime.flowabledemo;

import org.flowable.engine.*;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = FlowableDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FlowableDemoApplicationTests02 {
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


/*
* 用户维护
* */
    @Test
    public void createUser(){
        User user =identityService.newUser("zhangsna");
        user.setEmail("zhangsna@qq.com");
        user.setFirstName("zhang");
        user.setLastName("san");
        user.setPassword("123");
        identityService.saveUser(user);
    }

/*
* 用户组维护
* */
    @Test
    public void createGroup(){
        Group group=identityService.newGroup("xjb");
        group.setName("行政部");
        group.setType("type1");
        identityService.saveGroup(group);
    }

    /*
    * 维护用户和用户组的关系
    * */
    @Test
    public void createMemeberShip(){
//        查询对应的的用户组
        Group group=identityService.createGroupQuery().groupId("xsb").singleResult();
        List<User> users=identityService.createUserQuery().list();
        for (User user : users) {
            identityService.createMembership(user.getId(),group.getId());
        }
    }

    //    流程部署操作
    @Test
    public void deployFlow() {
//        System.out.println(processEngine);
//        RepositoryService repositoryService =processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/01/HolidayDemo2.bpmn20.xml")
                .name("候选人组案例")
                .deploy();
        System.out.println("deploy.getId() = " + deploy.getId());
    }

//    删除部署的流程
    @Test
    public  void  deleteDeployFlow(){
        repositoryService.deleteDeployment("8100e537-fc8a-11ee-83bb-00ff21492263",true);
    }

    //开始流程
    @Test
    public void startFlow() {
        String processId = "HolidayDemo2:1:8131922a-fc8a-11ee-83bb-00ff21492263";
        runtimeService.startProcessInstanceById(processId);
    }

//    查询候选任务
    @Test
    public void findCandidateTask(){
        Group group=identityService.createGroupQuery()
                .groupMember("zhangsna")
                .singleResult();
        System.out.println("group.getId() = " + group.getId());
        List<Task> list=taskService.createTaskQuery()
                .taskCandidateGroup(group.getId())
                .list();
        for (Task task : list) {
            System.out.println("task.getId() = " + task.getId());
        }
    }

    @Test
    public void cliamTask(){
        Group group=identityService.createGroupQuery()
                .groupMember("zhangsna")
                .singleResult();
        System.out.println("group.getId() = " + group.getId());
        List<Task> list=taskService.createTaskQuery()
                .taskCandidateGroup(group.getId())
                .list();
        for (Task task : list) {
            taskService.claim(task.getId(),"zhangsna");
        }
    }

    @Test
    public void completeTask(){
        taskService.complete("e0d3ec4e-fc8c-11ee-953e-00ff21492263");
    }

}
