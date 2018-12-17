package com.cnebula.ill.mapper;

import com.cnebula.ill.dto.BaseData;
import com.cnebula.ill.type.GenericTypeHandler;
import com.cnebula.ill.type.MyTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

//@Mapper
public interface DataMapper {

    @Select(value = "SELECT * FROM manage_tenant WHERE id = #{id}")
    @Results({
            @Result(column = "tenant", javaType = Object.class, property = "tenant", typeHandler = MyTypeHandler.class),
            @Result(column = "id", javaType = String.class, property = "id")
    })
    public BaseData findById(@Param(value = "id") String id);

    @Update(value = "UPDATE ${schema}.manage_tenant SET tenant = jsonb_set(tenant,'{tenant_status}', '${tenant_status}')  WHERE id = '${id}'")
    public Integer update(@Param(value = "schema") String schema, @Param(value = "tenant_status") Integer tenant_status, @Param(value = "id") String id);
}
