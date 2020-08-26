package com.lonffy.mjob.service.impl;

import com.lonffy.mjob.dto.QuartzJob;
import com.lonffy.mjob.service.QuartzJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    @Autowired
    private Scheduler scheduler;

    @Override
    public void addQuartzJob(QuartzJob quartzJob) throws SchedulerException, ClassNotFoundException {
        final String taskName = quartzJob.getTaskName();
        final String taskGroup = quartzJob.getTaskGroup();
        JobKey jobKey = JobKey.jobKey(taskName,taskGroup);

        Class classz = Class.forName(quartzJob.getTaskClass());

        final JobDetail jobDetail = JobBuilder.newJob(classz).withIdentity(jobKey).withDescription(quartzJob.getNote()).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCron());
        TriggerKey triggerKey = TriggerKey.triggerKey(taskName,taskGroup);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();

    }

    @Override
    public void excuteJob(String taskName, String taskGroup) throws SchedulerException {
        scheduler.triggerJob(JobKey.jobKey(taskName,taskGroup));
    }

    @Override
    public void pauseJob(String taskName, String taskGroup) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(taskName,taskGroup));
    }

    @Override
    public void deleteJob(String taskName, String taskGroup) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(taskName,taskGroup));
        scheduler.unscheduleJob(TriggerKey.triggerKey(taskName,taskGroup));
        scheduler.deleteJob(JobKey.jobKey(taskName,taskGroup));
    }

    @Override
    public void resumeJob(String taskName, String taskGroup) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(taskName,taskGroup));
    }

    @PostConstruct
    public void startScheduler(){
        try {
            scheduler.start();
            System.out.println("111111111111111111111");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
