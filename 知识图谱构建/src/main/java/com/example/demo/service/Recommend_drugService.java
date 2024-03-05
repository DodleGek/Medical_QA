package com.example.demo.service;

import com.example.demo.dao.Recommend_drugReposity;
import com.example.demo.fact.Recommend_drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Recommend_drugService {
    @Autowired
    Recommend_drugReposity recommendDrugReposity;
    public void sava(Recommend_drug drug){
        recommendDrugReposity.save(drug);
    }
}
