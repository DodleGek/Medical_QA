package com.example.demo.service;

import com.example.demo.dao.Recommend_eatReposity;
import com.example.demo.fact.Recommend_eat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Recommend_eatService {
    @Autowired
    private Recommend_eatReposity recommendEatReposity;
    public void sava(Recommend_eat eat){
        recommendEatReposity.save(eat);
    }
}
