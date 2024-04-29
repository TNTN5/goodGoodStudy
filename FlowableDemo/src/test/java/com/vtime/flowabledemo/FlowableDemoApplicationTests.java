package com.vtime.flowabledemo;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
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
public class FlowableDemoApplicationTests {
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;


    //    流程部署操作
    @Test
    public void deployFlow() {
//        System.out.println(processEngine);
//        RepositoryService repositoryService =processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("process/01/HolidayDemo1.bpmn20.xml")
                .name("候选人案例")
                .deploy();
        System.out.println("deploy.getId() = " + deploy.getId());
    }

    //开始流程
    @Test
    public void startFlow() {
//        在流程定义表里动态维护
        String processId = "HolidayDemo1:1:a650777e-fc6d-11ee-bed3-00ff21492263";
        String processKey = "Example02";  //注意保证唯一
//        在启动流程实例的时候我们就可以绑定对应的表达式的值
        Map<String, Object> variables = new HashMap<>();
        variables.put("candidate1", "zhangsan");
        variables.put("candidate2", "lisi");
        variables.put("candidate3", "wangwu");
//        根据流程id启动实例
        runtimeService.startProcessInstanceById(processId, variables);
//        根据流程定义key启动流程实例   //设置的变量是全局变量
//        ProcessInstance processInstance = runtimeService
//                .startProcessInstanceByKey(processKey,variables);
    }

    //    流程启动后还可以绑定对应的流程变量
    @Test
    public void setVariable() {
        String executionId = "e135a984-fbca-11ee-a450-00ff21492263";
        runtimeService.setVariable(executionId, "var4", "var4");
//        runtimeService设置局部变量
        runtimeService.setVariableLocal(executionId, "varLocal1", "localTest1");

        String taskId = "e13c122c-fbca-11ee-a450-00ff21492263";
        taskService.setVariable(taskId, "taskVar1", "taskVar1");
//        runtimeService设置局部变量
        taskService.setVariableLocal(taskId, "taskVarLocal1", "taskLocalTest1");
    }

    //    获取定义的变量
    @Test
    public void getVariables() {
        String executionId = "e135a984-fbca-11ee-a450-00ff21492263";
        Map<String, Object> variables = runtimeService.getVariables(executionId);
        System.out.println(variables);

        String taskId = "e13c122c-fbca-11ee-a450-00ff21492263";
        taskService.getVariables(taskId);
        System.out.println(variables);
    }

    //根据用户查询代办任务
    @Test
    public void findFlow() {
//        任务实例通过taskservice实现
//        获取到act_ru_task中assignee字段是zhangsan的字段
        List<Task> list = taskService.createTaskQuery().taskAssignee("zhangsan").list();
    }

    @Test
    public void climTask() {
      /*
        根据候选人查询待办
        候选人需要通过拾取操作把候选人变为审批人
        多个候选人只有一个可以变为审批人
        审批人如果不想审批了，可以归还，从审批人变为候选人
        */
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("vtime1").list();

        for (Task task : list) {
//            拾取操作
            taskService.claim(task.getId(), "vtime1");

        }
    }

    @Test
    public void unclimTask() {
      /*
        根据候选人查询待办
        候选人需要通过拾取操作把候选人变为审批人
        多个候选人只有一个可以变为审批人
        审批人如果不想审批了，可以归还，从审批人变为候选人
        */
        List<Task> list = taskService.createTaskQuery().taskAssignee("vtime1").list();

        for (Task task : list) {
//            拾取操作
            System.out.println( "--归还操作--");
            taskService.unclaim(task.getId());

        }
    }

/*
* 指派操作
* */
    @Test
    public void setAssignee() {
        List<Task> list = taskService.createTaskQuery().taskAssignee("vtime1").list();

        for (Task task : list) {
//            拾取操作
            System.out.println( "--指派--");
            taskService.setAssignee(task.getId(),"vtime666");

        }
    }

    //任务审批
    @Test
    public void completeTask() {
        /*
        Map<String, Object> variables = new HashMap<>();
        variables.put("taskVar1", "taskVar1");
//        完成任务审批，根据任务id，绑定对应的表达式的值
//        taskService.complete("fda74f9a-fba5-11ee-97b7-00ff21492263",variables);
        taskService.complete("9a90c465-fbcd-11ee-b591-00ff21492263");
        */
        Map<String,Object> variables=new HashMap<>();
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("zhangsan")   //根据候选人查询
                .list();
        variables.put("candidate4", "vtime1");
        variables.put("candidate5", "vtime2");
        for (Task task : list) {
            taskService.complete(task.getId(),variables);
        }
        taskService.complete("fd921fe5-fc71-11ee-9a0a-00ff21492263");
    }

    //    流程挂起和激活
    @Test
    public void susendedActivity() {
        String processDefinitionId = "FirstFlow:1:7a705e93-faf5-11ee-a96f-00ff21492263";
//        流程的挂起和激活--->流程的定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
//        获取当前流程定义的状态
        boolean suspended = processDefinition.isSuspended();
        if (suspended) {
//            激活
            repositoryService.activateProcessDefinitionById(processDefinitionId);
        } else {
//            挂起
            repositoryService.suspendProcessDefinitionById(processDefinitionId);
        }
    }

    //    挂起流程实例
    @Test
    public void suspendInstance() {
        String taskId = "28d5cb6a-faf7-11ee-8479-00ff21492263";
//        挂起流程实例
        runtimeService.suspendProcessInstanceById(taskId);
//        激活流程实例
//        runtimeService.activateProcessInstanceById(taskId);
    }


}
