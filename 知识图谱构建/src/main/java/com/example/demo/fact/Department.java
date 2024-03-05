package com.example.demo.fact;


import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.core.schema.Id;

@Data
@NodeEntity(label = "department")
public class Department {
    @Property(name="name")
    private String name;
    @Id
    private Long id;
}
