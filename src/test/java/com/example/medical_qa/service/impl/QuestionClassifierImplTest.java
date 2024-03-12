package com.example.medical_qa.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class QuestionClassifierImplTest {

    private QuestionClassifierImpl questionClassifier;

    @BeforeEach
    void setUp() {
        questionClassifier = new QuestionClassifierImpl();
    }

    @Test
    void testClassify() {
        // 输入测试问题
        String question ="我感觉头疼，是不是得了感冒的表现？";
        System.out.println("我感觉头疼，是不是得了感冒的表现？");
        // 调用classify方法进行分类
        Map<String, Object> result = questionClassifier.classify(question);

        // 验证分类结果是否符合预期
        Assertions.assertNotNull(result);
        System.out.println(result);
        Map<String,List<String>> args = (Map<String,List<String>>) result.get("args");
        System.out.println(args);
        List<String> questionTypes = (List<String>) result.get("question_types");

        // 验证是否成功从问题中提取了医疗特征词
        Assertions.assertFalse(args.isEmpty());

        // 验证问题类型是否正确
        Assertions.assertTrue(questionTypes.contains("DiseaseSymptom"));
    }
}
