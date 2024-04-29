package com.quartz.scheduler;

import com.quartz.job.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
//  创建JobDetail实例，并与job类绑定
        JobDetail jobDetail= JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1","group1")
                .build();
//  构建trigger实例
        Trigger trigger= TriggerBuilder.newTrigger().withIdentity("trigger1","triggerGroup1")
                .startNow()         //立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)   //3秒执行一次
                        .repeatForever())   //一直执行
                .build();
//  创建scheduler并执行
        Scheduler scheduler=new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("------------scheduler  start !---------------");
        scheduler.start();
//  1分钟后睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("------------scheduler  shutdown !---------------");

    }
}
