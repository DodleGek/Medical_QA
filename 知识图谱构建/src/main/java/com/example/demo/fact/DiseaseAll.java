package com.example.demo.fact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
/*所有的属性方便添加关系*/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiseaseAll {
    private  String name;
    private String desc;
    private  String prevent;
    private List<String> acompany;
    private List<String> category;
    private List<String> cure_department;
    private  List<String> recommend_drug;
    private  List<String> drug_detail;
    private List<String> do_eat;
    private List<String> not_eat;
    private List<String> recommend_eat;
    private String cause;
    private List<String>symptom;
}
