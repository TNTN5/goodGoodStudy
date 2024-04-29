package com.apollo.cms.demo.job;

import com.apollo.cms.demo.service.ItemService;
import com.apollo.cms.demo.util.WebSocketUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.altitude.cms.quartz.annotation.CronExp;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
@Component
@DisallowConcurrentExecution
@CronExp(id = 1, cron = "0/3 * * * * ?")
public class ItemJob implements Job {
    @Autowired
    private ItemService itemService;
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        WebSocketUtils.sendMessage(itemService.toData());
    }
}
