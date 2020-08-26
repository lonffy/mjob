package com.lonffy.mjob.controller;


import com.lonffy.mjob.common.RespResult;
import com.lonffy.mjob.dto.QuartzJob;
import com.lonffy.mjob.entity.SysTask;
import com.lonffy.mjob.service.QuartzJobService;
import com.lonffy.mjob.service.SysTaskService;
import com.lonffy.mjob.vo.SysTaskVo;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.acl.LastOwnerException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lonffy
 * @since 2020-08-20
 */
@RestController
@RequestMapping("/sys-task")
public class SysTaskController {

    @Autowired
    private SysTaskService sysTaskService;

    @Autowired
    private QuartzJobService quartzJobService;


    @RequestMapping("/addTask")
    public RespResult addSysTask(SysTask sysTask) throws SchedulerException, ClassNotFoundException {
        sysTask.setCreateTime(new Date());
        sysTask.setUpdateTime(new Date());
        sysTask.setExecDate(new Date());
        sysTaskService.save(sysTask);
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setTaskId(sysTask.getId());
        quartzJob.setTaskName(sysTask.getTaskName());
        quartzJob.setTaskGroup(sysTask.getTaskGroup());
        quartzJob.setCron(sysTask.getCron());
        quartzJob.setNote(sysTask.getNote());
        quartzJob.setTaskClass(sysTask.getTaskClass());

        quartzJobService.addQuartzJob(quartzJob);
        return  RespResult.success();
    }


    @RequestMapping("/updateTask")
    public RespResult updateSysTask(SysTaskVo sysTaskVo) throws SchedulerException, ClassNotFoundException {
        sysTaskService.updateTask(sysTaskVo);
        return  RespResult.success();
    }

    @RequestMapping("/excuteTask/{task_group}/{task_name}")
    public RespResult excuteTask(@PathVariable("task_group") String taskGroup, @PathVariable("task_name") String taskName) throws SchedulerException {
        quartzJobService.excuteJob(taskGroup,taskName);
        return  RespResult.success();
    }

    @RequestMapping("/resumeTask/{task_group}/{task_name}")
    public RespResult resumeTask(@PathVariable("task_group") String taskGroup, @PathVariable("task_name") String taskName) throws SchedulerException {
        quartzJobService.resumeJob(taskGroup,taskName);
        return  RespResult.success();
    }


    @RequestMapping("/pauseTask/{task_group}/{task_name}")
    public RespResult pauseTask(@PathVariable("task_group") String taskGroup, @PathVariable("task_name") String taskName) throws SchedulerException {
        quartzJobService.pauseJob(taskGroup,taskName);
        return  RespResult.success();
    }

    @RequestMapping("/deleteTask/{task_group}/{task_name}")
    public RespResult deleteTask(@PathVariable("task_group") String taskGroup, @PathVariable("task_name") String taskName) throws SchedulerException {
        sysTaskService.deleteTask(taskName,taskGroup);
        return  RespResult.success();
    }



}
