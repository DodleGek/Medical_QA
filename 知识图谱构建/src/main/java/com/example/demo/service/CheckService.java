package com.example.demo.service;

import com.example.demo.dao.CheckReposity;
import com.example.demo.fact.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckService {
    @Autowired
    CheckReposity checkReposity;
    public void sava(Check check){
        checkReposity.save(check);
    }
}
