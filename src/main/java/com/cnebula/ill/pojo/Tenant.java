package com.cnebula.ill.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.postgresql.util.PGobject;

@Setter
@Getter
public class Tenant {
    private String id;
    private PGobject tenant;
    private String regdate;
    private String udpdate;
}
