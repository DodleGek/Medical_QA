package com.example.demo.service;

import com.example.demo.dao.not_eatReposity;
import com.example.demo.fact.not_eat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class not_eatService {
    @Autowired
    private not_eatReposity notEatReposity;
    public void sava(not_eat not){
        notEatReposity.save(not);
    }
}
