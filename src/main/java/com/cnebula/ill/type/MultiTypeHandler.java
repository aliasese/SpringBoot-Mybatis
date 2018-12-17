package com.cnebula.ill.type;

import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;

public class MultiTypeHandler<T> extends EnumTypeHandler {


    public MultiTypeHandler(Class<T> type) {
        super(type);
    }
}
