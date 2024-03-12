package com.example.demo.service;

import com.example.demo.dao.AcompanyReposity;
import com.example.demo.fact.Acompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcompanyService {
    @Autowired
    private AcompanyReposity acompanyReposity;
    public void save(Acompany acompany){
        acompanyReposity.save(acompany);
    }
}
