package com.example.medical_qa.controller;

import com.example.medical_qa.dto.MedicalQuestionRequest;
import com.example.medical_qa.service.MedicalQaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MedicalQaControllerTest {

    @Autowired
    private MedicalQaController medicalQaController;

    @MockBean
    private MedicalQaService medicalQaService;

    @Test
    void submitMedicalQuestion_ValidQuestion_ReturnsResponseEntityWithAnswer() {
        // Arrange
        MedicalQuestionRequest request = new MedicalQuestionRequest();
        request.setQuestion("我感觉头疼，是不是得了感冒的表现？");
        String expectedAnswer = "这里是您的预期答案";

        // Mocking the service method behavior
        when(medicalQaService.processMedicalQuestion(request.getQuestion())).thenReturn(Collections.singletonList(expectedAnswer));

        // Act
        ResponseEntity<List<String>> responseEntity = medicalQaController.submitMedicalQuestion(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size()); // 确保返回的列表长度为 1
        assertEquals(expectedAnswer, responseEntity.getBody().get(0)); // 确保返回的答案与预期相符
    }
}
