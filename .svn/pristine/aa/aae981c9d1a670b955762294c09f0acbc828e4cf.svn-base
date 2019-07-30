package com.ljx.hfgsjt.message.controller;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Value("${message.timer}")
    private int timer;

    @Bean
    public JobDetail teatQuartzDetail(){
        return JobBuilder.newJob(SendMessageQuartz.class).withIdentity("SendMessageQuartz").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(timer)  //设置时间周期单位秒
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail())
                .withIdentity("SendMessageQuartz")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
