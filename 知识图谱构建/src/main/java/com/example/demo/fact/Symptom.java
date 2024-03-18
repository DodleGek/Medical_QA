package com.example.demo.fact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.core.schema.Id;


import java.util.List;

@Data
@NodeEntity(label = "Symptom")
public class Symptom {
    @Property(name="name")
    private String  name;
    @Id
    @GeneratedValue
    @Property(name="id")
    private Long id;
}
