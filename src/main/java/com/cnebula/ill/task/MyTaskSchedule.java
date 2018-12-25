package com.cnebula.ill.task;

import javafx.fxml.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
//@Component
//@EnableScheduling
public class MyTaskSchedule implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(MyTaskSchedule.class);
    /**
     * 每月月初执行一次
     */
    @Scheduled(cron="*/2 * * * * ?")
    @Transactional
    public void funcReportInit(){
        log.info("---------------- 定时任务 ----------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.funcReportInit();
    }
}
