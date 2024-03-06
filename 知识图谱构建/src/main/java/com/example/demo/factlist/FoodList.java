package com.example.demo.factlist;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodList {
    @JsonProperty("not_eat")
    private List<String> not_eat;
    @JsonProperty("recommend_eat")
    private List<String> recommend_eat;
}
