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
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymptomList {
        private List<String> symptom;
    }

