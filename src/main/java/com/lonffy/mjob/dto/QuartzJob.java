package com.lonffy.mjob.dto;

import lombok.Data;

import java.util.Map;

@Data
public class QuartzJob {
    private Long taskId;
    private String taskName;
    private String taskGroup;
    private String taskClass;
    private String note;
    private String cron;

    /**
     * 是否允许并发，0(false)：不允许 1（true）：允许
     */
    private Boolean concurrent;

    /**
     * 执行参数
     */
    private Map<String, Object> dataMap;
}
