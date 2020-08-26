package com.lonffy.mjob.service;

import com.lonffy.mjob.entity.SysTask;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lonffy.mjob.vo.SysTaskVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lonffy
 * @since 2020-08-20
 */
public interface SysTaskService extends IService<SysTask> {
     void updateTask(SysTaskVo sysTaskVo);
     void deleteTask(String taskName,String groupName);
}
