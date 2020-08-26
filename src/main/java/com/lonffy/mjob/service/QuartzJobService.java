package com.lonffy.mjob.service;

import com.lonffy.mjob.dto.QuartzJob;
import org.quartz.SchedulerException;

public interface QuartzJobService {
    void addQuartzJob(QuartzJob quartzJob) throws SchedulerException, ClassNotFoundException;
    void excuteJob(String taskName, String taskGroup) throws SchedulerException;
    void pauseJob(String taskName, String taskGroup) throws SchedulerException;
    void deleteJob(String taskName, String taskGroup) throws SchedulerException;
    void resumeJob(String taskName, String taskGroup) throws SchedulerException;

}
