package com.example.demo.service;

import com.example.demo.dao.FoodReposity;
import com.example.demo.fact.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    @Autowired
    private FoodReposity foodReposity;
    public void sava(Food food){
        foodReposity.save(food);
    }
}
