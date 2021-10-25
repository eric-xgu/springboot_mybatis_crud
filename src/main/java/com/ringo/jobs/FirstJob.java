package com.ringo.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstJob implements Job {
    private static Logger logger= LoggerFactory.getLogger(FirstJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TriggerKey triggerKey=jobExecutionContext.getTrigger().getKey();
        logger.info("触发器："+triggerKey.getName()+"所属组:"+triggerKey.getGroup()+simpleDateFormat.format(new Date())+"Quartz");
    }
}
