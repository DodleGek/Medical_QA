package org.example.medical_qa.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalQaService {
    List<String> processMedicalQuestion(String question);
}

