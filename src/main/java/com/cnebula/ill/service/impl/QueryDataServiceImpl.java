package com.cnebula.ill.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.cnebula.ill.mapper.DataMapper;
import com.cnebula.ill.mybatis.MyDataMapper;
import com.cnebula.ill.pojo.Tenant;
import com.cnebula.ill.service.QueryDataService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QueryDataServiceImpl implements QueryDataService {

    @Autowired
    DataMapper dataMapper;

    @Autowired(required = true)
    @Resource
    MyDataMapper myDataMapper;

    @Override
    public Object getOneById(String id) {
        Object byId = dataMapper.findById(id);
        return byId;
    }

    @Override
    public Object getDataById(String id) {
        Object byId = myDataMapper.findDataById(id);
        return byId;
    }

    @Override
    public PageInfo<Object> findItemByPage(int currentPage, int pageSize) {

        return PageHelper.startPage(currentPage, pageSize, "regdate DESC").doSelectPageInfo(() -> myDataMapper.findByPage(currentPage, pageSize));

        /*PageHelper.startPage(currentPage, pageSize, "regdate DESC");
        List<Object> objectList = myDataMapper.findByPage(currentPage, pageSize);
        PageInfo<Object> objectPageInfo = new PageInfo<>(objectList);
        return objectPageInfo;*/
    }

    @Override
    public Integer insertTenant(List<Tenant> tenants) {
        return myDataMapper.insertTenant(tenants);
    }

    @Override
    public Integer update(String schema, Integer tenant_status, String id) {
        return dataMapper.update(schema, tenant_status, id);
    }

    @Override
    public Integer updateTenant(Tenant tenant) {
        return myDataMapper.updateTenant(tenant);
    }

    @Override
    public Integer updateTenantStatus(String id, Integer status) {
        return myDataMapper.updateTenantStatus(id, status);
    }


}
