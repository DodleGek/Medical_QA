package com.example.demo.fact;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@NodeEntity(label = "not_eat")
public class not_eat {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "name")
    private String name;
}
