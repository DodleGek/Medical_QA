package com.example.demo.fact;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.core.schema.Id;


@Data
    @NodeEntity(label = "Recommend_food")
    public class Recommend_eat {
        @Id
        @GeneratedValue
        private Long id;
        @Property(name="name")
        private  String name;
    }

