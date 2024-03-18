package com.example.demo.fact;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

@NodeEntity(label = "cause")
public class Productor {
    @Property(name="cause")
    private String cause;
    @Id
    @GeneratedValue
    private Long identity;

}
