package com.ljx.hfgsjt.message.controller;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;


/**
 * 自动发送短信
 */
public class SendMessageQuartz extends QuartzJobBean {
    @Autowired
    private SendMessageController sendMessageController;
    @Autowired
    private MessageController messageController;
    @Value("${message.dx_switch}")
    private String dx_switch;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(dx_switch.equals("1")){
            //messageController.getDxDfsListAndSend();
            sendMessageController.getDxDfsListAndSend();
        }
    }
}
