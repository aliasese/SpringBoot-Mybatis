package com.cnebula.ill.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonProperty;
import org.postgresql.util.PGobject;

@Setter
@Getter
public class Tenant {
    private String id;
    @JsonProperty("tenant")
    private Object tenant;
    private String regdate;
    private String udpdate;
}
