package com.lonffy.mjob.service.impl;

import com.lonffy.mjob.dto.QuartzJob;
import com.lonffy.mjob.entity.SysTask;
import com.lonffy.mjob.mapper.SysTaskMapper;
import com.lonffy.mjob.service.QuartzJobService;
import com.lonffy.mjob.service.SysTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lonffy.mjob.vo.SysTaskVo;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lonffy
 * @since 2020-08-20
 */
@Service
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTask> implements SysTaskService {

    @Autowired
    private QuartzJobService quartzJobService;

    @Override
    public void deleteTask(String taskName, String groupName) {
        SysTaskMapper baseMapper = this.getBaseMapper();
        Map<String,Object> map = new HashMap<>();
        map.put("task_name",taskName);
        map.put("task_group",groupName);

        baseMapper.deleteByMap(map);
        try {
            quartzJobService.deleteJob(taskName,groupName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTask(SysTaskVo sysTaskVo) {
        SysTask sysTask = this.getById(sysTaskVo.getId());

        if(sysTask!=null){
            BeanUtils.copyProperties(sysTaskVo,sysTask);
            sysTask.setUpdateTime(new Date());
            this.updateById(sysTask);
            try {
                quartzJobService.deleteJob(sysTask.getTaskName(),sysTask.getTaskGroup());
                QuartzJob quartzJob2 = new QuartzJob();
                quartzJob2.setTaskId(sysTask.getId());
                quartzJob2.setTaskName(sysTask.getTaskName());
                quartzJob2.setTaskGroup(sysTask.getTaskGroup());
                quartzJob2.setCron(sysTask.getCron());
                quartzJob2.setNote(sysTask.getNote());
                quartzJob2.setTaskClass(sysTask.getTaskClass());
                quartzJobService.addQuartzJob(quartzJob2);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }








    }
}
