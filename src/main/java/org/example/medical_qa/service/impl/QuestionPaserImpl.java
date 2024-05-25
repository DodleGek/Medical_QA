package org.example.medical_qa.service.impl;


import org.example.medical_qa.dao.DiseaseRepository;
import org.example.medical_qa.entity.node.Disease;
import org.example.medical_qa.service.QuestionPaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionPaserImpl implements QuestionPaser {
    private final DiseaseRepository diseaseRepository;
    @Autowired
    public QuestionPaserImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<Map<String, Object>> paser(Map<String, Object> intent) {
        // 医疗特征词
        Map<String, List<String>> args = (Map<String, List<String>>) intent.get("args");
        Map<String, List<String>> entityDict = buildEntityDict(args);
        List<String> questionTypes = (List<String>) intent.get("question_types");
        Map<String, Object> intermediateResult = new HashMap<>(); // 中间结果
        List<Map<String, Object>> finalResult = new ArrayList<>(); // 最终结果

        for (String questionType : questionTypes) {
            List<Object> answer = new ArrayList<>(); // 查询答案
            intermediateResult.put("questionType", questionType);
            if (Objects.equals(questionType, "DiseaseSymptom")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseAccompany")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseNotFood")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseFood")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseDrug")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseCheck")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseBelong")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseCause")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseasePrevent")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseLasttime")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseCureprob")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseCureway")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseEasyget")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DiseaseDesc")) {
                answer = searchGraph(questionType, entityDict.get("Disease"));
            } else if (Objects.equals(questionType, "DrugProducer")) {
                answer = searchGraph(questionType, entityDict.get("Drug"));
            } else if (Objects.equals(questionType, "DrugDisease")) {
                answer = searchGraph(questionType, entityDict.get("Drug"));
            } else if (Objects.equals(questionType, "PRODUCED_BY")) {
                answer = searchGraph(questionType, entityDict.get("Producer"));
            } else if (Objects.equals(questionType, "SymptomDisease")) {
                answer = searchGraph(questionType, entityDict.get("Symptom"));
            }

            if (answer != null && !answer.isEmpty()) {
                intermediateResult.put("answer", answer);
            }
            finalResult.add(intermediateResult);
        }
        return finalResult;
    }


    public List<Object> searchGraph(String questionType, List<String> entities) {
        List<Object> answer = new ArrayList<>();

        // 如果实体列表为空，直接返回空列表
        if (entities == null || entities.isEmpty()) {
            return answer;
        }

        // 遍历每个实体，根据问题类型生成相应的 SQL 查询
        for (String entity : entities) {
            switch (questionType) {
                // 查询疾病的原因
                case "DiseaseCause":
                    answer.addAll(diseaseRepository.findCauseByName(entity));
                    break;
                // 查询疾病的防御措施
                case "DiseasePrevent":
                    answer.addAll(diseaseRepository.findPreventByName(entity));
                    break;
                // 查询疾病的持续时间
                case "DiseaseLasttime":
                    answer.addAll(diseaseRepository.findCureLastTimeByName(entity));
                    break;
                // 查询疾病的治愈概率
                case "DiseaseCureprob":
                    answer.addAll(diseaseRepository.findCuredProbByName(entity));
                    break;
                // 查询疾病的治疗方式
                case "DiseaseCureway":
                    answer.addAll(diseaseRepository.findCureWayByName(entity));
                    break;
                // 查询疾病的易发人群
                case "DiseaseEasyget":
                    answer.addAll(diseaseRepository.findEasyGetByName(entity));
                    break;
                // 查询疾病的相关介绍
                case "DiseaseDesc":
                    Disease diseaseByName = diseaseRepository.findDiseaseByName(entity);
                    List<String> desc = new ArrayList<>();
                    desc.add(diseaseByName.getName());
                    desc.add(diseaseByName.getDesc());
                    answer.add(desc);
                    break;
            }
        }

        return answer;
    }


    public Map<String, List<String>> buildEntityDict(Map<String, List<String>> args) {
        Map<String, List<String>> entityDict = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : args.entrySet()) {
            String arg = entry.getKey();
            List<String> types = entry.getValue();
            for (String type : types) {
                if (!entityDict.containsKey(arg)) {
                    entityDict.put(type, Collections.singletonList(arg));
                } else {
                    entityDict.get(type).add(arg);
                }
            }
        }
        return entityDict;
    }
}
