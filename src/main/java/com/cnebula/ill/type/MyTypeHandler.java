package com.cnebula.ill.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.*;

@MappedTypes(Object.class)
//@MappedJdbcTypes(JdbcType.TIME)
public class MyTypeHandler extends BaseTypeHandler<Object> {

    private static final PGobject P_GOBJECT = new PGobject();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        P_GOBJECT.setType("jsonb");
        try {
            P_GOBJECT.setValue((objectMapper.writeValueAsString(o)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setObject(i, P_GOBJECT);
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        if (resultSet.getString(s) != null) {
            try {
                return objectMapper.readValue(resultSet.getString(s), Object.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        if (resultSet.getString(i) != null) {
            try {
                return objectMapper.readValue(resultSet.getString(i), Object.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        if (callableStatement.getString(i) != null) {
            try {
                return objectMapper.readValue(callableStatement.getString(i), Object.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
