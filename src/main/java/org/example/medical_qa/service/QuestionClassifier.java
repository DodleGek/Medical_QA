package org.example.medical_qa.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface QuestionClassifier {
    Map<String, Object> classify(String question);
}
