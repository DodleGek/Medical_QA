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
@NodeEntity(label = "food")
public class Food {
    @Property(name="do_eat")
    private List<String> do_eat;
    @Property(name="not_eat")
    private List<String> not_eat;
    @Property(name="recommend_eat")
    private List<String> recommend_eat;
    @Id
    @GeneratedValue
    private Long identity;

}
