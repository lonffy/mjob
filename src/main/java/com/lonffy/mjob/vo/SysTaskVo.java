package com.lonffy.mjob.vo;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 类中的@Data代替了get set方法
 * </p>
 *
 * @author lonffy
 * @since 2020-08-20
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysTaskVo {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 执行类
     */
    private String taskClass;

    /**
     * 任务说明
     */
    private String note;

    /**
     * 定时规则
     */
    private String cron;

    /**
     * 执行参数
     */
    private String execParams;

    /**
     * 是否允许并发，0(false)：不允许 1（true）：允许
     */
    private Boolean concurrent;

    /**
     * 修改人
     */
    private Long modifier;

}
