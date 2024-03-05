package com.example.demo.fact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class recommend_drug_List {
    @JsonProperty("recommend_drug")
    List<String>recommend_drug_list;
}
