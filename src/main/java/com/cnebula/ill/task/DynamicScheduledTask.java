package com.cnebula.ill.task;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Lazy(false)
//@Component
//@EnableScheduling
public class DynamicScheduledTask implements SchedulingConfigurer {

    /**
     *  通过自动注入启动任务调度
     *
     *     @Autowired
     *    DynamicScheduledTask dynamicScheduledTask;
     *
     */

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_CRON = "*/2 * * * * ?";
    private String cron = DEFAULT_CRON;

    /**
     * 获取任务执行规则
     * @return
     */
    public String getCron() {
        return cron;
    }

    /**
     * 设置任务执行规则
     * @param cron
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    @Bean()
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(20);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //用于设置定时任务线程数，默认不设置的话为单线程
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                // 任务逻辑
                logger.info("*************^^^^^^^^^^^^==============dynamicCronTask is running...");
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 任务触发，可修改任务的执行周期
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                logger.info(nextExec.toString());
                return nextExec;
            }
        });
    }
}