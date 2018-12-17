package com.cnebula.ill.controller;

import com.cnebula.ill.pojo.Tenant;
import com.cnebula.ill.service.QueryDataService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/query")
public class BusinessController {

    private static final Logger log = LoggerFactory.getLogger(BusinessController.class);


    @Autowired
    QueryDataService queryDataService;

    //@RequestMapping("/get/{id}")
    @GetMapping("/get/{id}")
    //@Scheduled(cron = "*/2 * * * * ?")
    //@Scheduled(fixedDelay = 5000)
    public Object getData(@PathVariable(value = "id") String id) {
        log.info(id);
        Object oneById = queryDataService.getOneById(id);
        System.out.println(oneById);
        return oneById;
    }

    @GetMapping("/get/my/{id}")
    public Object getMyData(@PathVariable(value = "id") String id) {
        log.info(id);
        Object oneById = queryDataService.getDataById(id);
        System.out.println(oneById);
        return oneById;
    }

    @GetMapping("/pagequery")
    @ResponseBody
    public PageInfo<Object> pageQuery(int currentPage, int pageSize){
        return queryDataService.findItemByPage(currentPage, pageSize);
    }

    @PostMapping("/insert")
    @ResponseBody
    public Integer insert(@RequestBody(required = true) List<Tenant> tenants){
        System.out.println(tenants);
        return queryDataService.insertTenant(tenants);
    }

    @GetMapping("/update")
    @ResponseBody
    public Integer update(String schema, Integer tenant_status, String id){
        System.out.println(schema);
        System.out.println(tenant_status);
        System.out.println(id);
        return queryDataService.update(schema, tenant_status, id);
    }

    @PostMapping("/update")
    @ResponseBody
    public Integer update(@RequestBody Tenant tenant){
        System.out.println(tenant);
        return queryDataService.updateTenant(tenant);
    }
}
