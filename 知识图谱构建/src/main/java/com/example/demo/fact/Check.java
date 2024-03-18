package com.example.demo.fact;





import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;


import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NodeEntity(label = "category")
public class Check {
     @Property(name="category")
     private List<String> category;
     @Id
     private Long identity;

}
