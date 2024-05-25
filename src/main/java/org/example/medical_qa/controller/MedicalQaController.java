package org.example.medical_qa.controller;

import org.example.medical_qa.dto.Result;
import org.example.medical_qa.dto.SearchKey;
import org.example.medical_qa.service.MedicalQaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/medical")
public class MedicalQaController {

    @Autowired
    private MedicalQaService medicalQaService;

    @PostMapping("/qa") //  医疗问题请求路径
    public Result<List<String>> submitMedicalQuestion(@RequestBody SearchKey searchKey) {
        try {
            List<String> answers = medicalQaService.processMedicalQuestion(searchKey.getQuestion());// 接受数据库返回的答案
            int limit = 5; // 进行显示数据限制，截取前5个
            if (answers.size() > limit) {
                answers = answers.subList(0, limit);
            }
            return Result.success(answers); // 传递json数据
        } catch (Exception e) {
            return Result.fail(e.getMessage());
        }
    }
}
