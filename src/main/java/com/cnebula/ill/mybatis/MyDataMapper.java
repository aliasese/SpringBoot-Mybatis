package com.cnebula.ill.mybatis;

import com.cnebula.ill.dto.BaseData;
import com.cnebula.ill.pojo.Tenant;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface MyDataMapper {

    public Object findDataById(@Param("id") String id);
    List<Object> findByPage(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    Integer insertTenant(@Param("tenants") List<Tenant> tenants);
    Integer updateTenant(@Param("tenant") Tenant tenant);
    Integer updateTenantStatus(@Param("id") String id, @Param("status") Integer status);
}
