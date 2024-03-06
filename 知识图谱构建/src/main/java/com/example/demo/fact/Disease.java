package com.example.demo.fact;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NodeEntity(label ="Disease")
@Data
public class Disease {
    @Property(name="name")
    private  String name;
    @Property(name="desc")
    private String desc;
    @Property(name="prevent")
    private  String prevent;
    @Property(name="cause")
    private String cause;
    @Property(name="cureLastTime")
    @JsonProperty("cure_lasttime")
    private String cureLastTime;
    @Property(name="cureProb")
    @JsonProperty("cured_prob")
    private String cureProb;
    @Property(name="cureWay")
    @JsonProperty("cure_way")
    private List<String> cureWay;
    @JsonProperty("easy_get")
    @Property(name="easyGet")
    private String easyGet;
    @Id
    @GeneratedValue
    private Long id;

}
