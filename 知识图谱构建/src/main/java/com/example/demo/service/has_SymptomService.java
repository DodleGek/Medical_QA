package com.example.demo.service;
import com.example.demo.dao.has_symptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class has_SymptomService {
    @Autowired
    private has_symptomRepository has_symptomReposity;



    public void create(String name,String symptomname){
          has_symptomReposity.createRelation(name,symptomname);
    }
}
