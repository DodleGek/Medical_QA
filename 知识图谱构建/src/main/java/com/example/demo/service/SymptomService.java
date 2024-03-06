package com.example.demo.service;

import com.example.demo.dao.SymptomRepository;
import com.example.demo.fact.Symptom;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SymptomService {
    @Autowired
    private SymptomRepository symptomRepository;
    public void savesymptomNode(Symptom symptom){
        symptomRepository.save(symptom);
    }
//    public Symptom findBylistsymptom(List<String> listsymptoms){
//        List<Symptom> symptoms= (List<Symptom>) symptomRepository.findall();
//        for(Symptom symptom:symptoms){
//            if (symptom.getSymptom().equals(listsymptoms) ){
//                return  symptom;
//            }
//        }
//        return null;
//    }
}
