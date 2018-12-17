package com.cnebula.ill.type;

import com.cnebula.ill.dto.BaseData;
import com.cnebula.ill.dto.SingleData;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericTypeHandler<E extends Object> extends BaseTypeHandler<E> {

    private Class<E> type;
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    public GenericTypeHandler(Class<E> type) {
        if (type == null) throw new IllegalArgumentException("Type argument(genericity) cannot be null");
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName), type);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }

    private String toJson(E object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private E toObject(String content, Class<?> clazz) {
        if (content != null && !content.isEmpty()) {
            try {
                return (E) mapper.readValue(content, clazz);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    /*public static void main(String [] args) throws IOException {
        *//*SingleData singleData = mapper.readValue("{\"id\":\"ffg13444444dddd\"}", SingleData.class);
        System.out.println(singleData);*//*
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        Object weqwe2121 = objectMapper.writeValueAsString(new SingleData());
        System.out.println(weqwe2121);
    }*/
}
