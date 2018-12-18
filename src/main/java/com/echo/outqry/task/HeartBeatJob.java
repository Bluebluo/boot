package com.echo.outqry.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HeartBeatJob extends QuartzJobBean {

    private static final Logger Log = LoggerFactory.getLogger(HeartBeatJob.class);
    private int count;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.count++;
        Log.info("{} hi ,i am still alive", this.count);
        jobExecutionContext.getJobDetail().getJobDataMap().put("count", this.count);
    }

    public void setCount(int count){
        this.count = count;
    }
}
