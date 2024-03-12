package com.example.medical_qa.controller;

import com.example.medical_qa.dto.MedicalQuestionRequest;
import com.example.medical_qa.service.MedicalQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medical")
public class MedicalQaController {

    @Autowired
    private MedicalQaService medicalQaService;

    @PostMapping("/qa")
    public ResponseEntity<List<String>> submitMedicalQuestion(@RequestBody MedicalQuestionRequest request) {
        try {
            // 委托给服务层处理业务逻辑
            List<String> answers = medicalQaService.processMedicalQuestion(request.getQuestion());
            int limit = 5;
            if (answers.size() > limit) {
                answers = answers.subList(0, limit); // 如果答案数量超过限制，截取前5个
            }

            return ResponseEntity.ok(answers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
