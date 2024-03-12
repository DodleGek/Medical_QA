package com.example.demo.fact;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

@NodeEntity(label = "acompany")
public class Acompany {
    @Property(name="acompany")
   private List<String>  acompany;
  @Id
  @GeneratedValue
   private Long id;



}
