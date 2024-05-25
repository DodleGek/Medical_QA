package org.example.medical_qa.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface QuestionPaser {
    List<Map<String, Object>> paser(Map<String, Object> intent);
}
