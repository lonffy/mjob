package com.lonffy.mjob.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
public class SysTask extends Model<SysTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 任务组
     */
    private String taskGroup;

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
     * 执行时间
     */
    private Date execDate;

    /**
     * 执行结果（成功:1、失败:0)
     */
    private Boolean execResult;

    /**
     * 是否允许并发，0(false)：不允许 1（true）：允许
     */
    private Boolean concurrent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Long modifier;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
