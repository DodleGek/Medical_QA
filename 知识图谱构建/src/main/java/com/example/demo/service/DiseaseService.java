package com.example.demo.service;



import com.example.demo.dao.DiseaseReposity;
import com.example.demo.dao.SymptomRepository;
import com.example.demo.fact.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {
    @Autowired
    private DiseaseReposity diseaseReposity;
    @Autowired
    private SymptomRepository symptomRepository;

    public void savediseaseNode(Disease disease) {
        diseaseReposity.save(disease);
    }
    public Disease findByName(String name) {
       return diseaseReposity.findByName(name);
    }
}

