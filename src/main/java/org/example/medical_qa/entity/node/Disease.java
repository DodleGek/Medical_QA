package org.example.medical_qa.entity.node;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;


@Node("Disease")
@Data
public class Disease {
    @Id
    @GeneratedValue
    private Long id;
    private String cause;
    private String name;
    private String prevent;
    private String desc;
    private List<String> cure_department;
    private String cure_lasttime;
    private List<String> cure_way;
    private String cured_prob;
    private String easy_get;
}
