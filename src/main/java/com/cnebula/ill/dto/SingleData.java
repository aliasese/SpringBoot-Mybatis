package com.cnebula.ill.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SingleData implements Serializable {

    public Object id;

    public SingleData() {
    }

    public SingleData(Integer id) {
        this.id = id;
    }

    public String toString() {
        return this.id + "";
    }
}
