package com.cnebula.ill.service;

import com.cnebula.ill.pojo.Tenant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface QueryDataService {

    public Object getOneById(String id);
    public Object getDataById(String id);
    PageInfo<Object> findItemByPage(int currentPage, int pageSize);
    Integer insertTenant(List<Tenant> tenants);
    Integer update(String schema, Integer tenant_status, String id);
    Integer updateTenant(Tenant tenant);
    Integer updateTenantStatus(String id, Integer status);
}
