package com.ringo.config;

import com.ringo.jobs.FirstJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
        return JobBuilder.newJob(FirstJob.class).storeDurably().build();
    }

    @Bean
    public Trigger trigger(){
        SimpleScheduleBuilder simpleScheduleBuilder=SimpleScheduleBuilder.simpleSchedule()
                //每秒执行一次
                .withIntervalInSeconds(1)
                //永远的执行下去
                .repeatForever()
                ;
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(simpleScheduleBuilder)
                .forJob(jobDetail())
                .build();
    }
    @Bean
    public Trigger trigger2(){
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger2","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                //每5s执行一次
                .forJob(jobDetail())
                .build();
    }
}
