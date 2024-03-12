package com.example.demo.service;

import com.example.demo.dao.ProductorReposity;
import com.example.demo.fact.Productor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductorService {
    @Autowired
    private ProductorReposity productorReposity;
    public void save(Productor productor){
        productorReposity.save(productor);
    }
}
