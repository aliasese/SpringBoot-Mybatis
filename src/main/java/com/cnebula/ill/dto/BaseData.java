package com.cnebula.ill.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseData implements Serializable {
    private static final long serialVersionUID = -8368249512731638115L;

    public String id;

    //@JsonInclude
    public Object tenant;

    public String regdate;

    public String udpdate;

    //public Integer totalRecord;

}
