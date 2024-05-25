package org.example.medical_qa.service.impl;


import org.example.medical_qa.service.MedicalAnswer;
import org.example.medical_qa.service.MedicalQaService;
import org.example.medical_qa.service.QuestionClassifier;
import org.example.medical_qa.service.QuestionPaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MedicalQaServiceImpl 类是医疗问答服务的实现，用于处理用户的医疗问题。
 */
@Service
public class MedicalQaServiceImpl implements MedicalQaService {


    @Autowired
    private QuestionClassifier questionClassifier;

    @Autowired
    private QuestionPaser questionPaser;

    @Autowired
    private MedicalAnswer medicalAnswer;

    /**
     * 处理用户提出的医疗问题。
     *
     * @param question 用户提出的医疗问题。
     * @return 根据问题分析得到的意图，从医疗数据库中检索相关信息的答案。
     */
    @Override
    public List<String> processMedicalQuestion(String question) {
        List<String> qa = new ArrayList<>();
        qa.add("抱歉，暂时无法回答您的问题");
        // 分析用户问题，获取用户提问的主题
        Map<String, Object> intent = questionClassifier.classify(question);

        if (intent == null || intent.isEmpty()) {
            // 无法识别用户提问的主题
            return qa;
        }
        // 解析用户问题，查询数据库
        List<Map<String, Object>> result = questionPaser.paser(intent);

        // 套模板，拼接答案
        return medicalAnswer.retrieveInformation(result);
    }
}



