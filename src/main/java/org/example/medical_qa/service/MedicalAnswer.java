package org.example.medical_qa.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MedicalAnswer {
    List<String> retrieveInformation(List<Map<String, Object>> cyphers);
}
