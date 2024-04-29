package com.vtime.flowabledemo;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.junit.Test;

public class FlowableTest1 {

    private final String url = "jdbc:mysql://localhost:3306/flowable-learn?serverTimezone=UTC&nullCatalogMeansCurrent=true";

    /**
     * 部署流程到数据库中
     * 在非spring环境下的应用
     */
    @Test
    public void deloyFlow() {

//        流程引擎配置对象  关联相关数据源
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl(url)
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        获取流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
//        部署流程需要获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
//                一次部署操作可以部署多个流程定义
                .addClasspathResource("process/01/FirstFlow.bpmn20.xml")
                .name("第一个流程图")
                .deploy();  //部署的方法
        System.out.println("deploy.getId() = " + deploy.getId());
    }
}
