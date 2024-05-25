package org.example.medical_qa.service.impl;

import org.example.medical_qa.service.MedicalAnswer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MedicalAnswerImpl implements MedicalAnswer {
    @Override
    public List<String> retrieveInformation(List<Map<String, Object>> result) {
        List<String> finalAnswer = new ArrayList<>();
        for (Map<String, Object> intermediateResult : result) {
            String questionType = (String) intermediateResult.get("questionType");
            List<Object> answer= (List<Object>) intermediateResult.get("answer");
            if (answer.isEmpty()) {
                finalAnswer.add("抱歉，暂时无法回答您的问题");
                continue;
            }
            switch (questionType) {
                  case "DiseaseSymptom" -> {List<String> desc = new ArrayList<>();
                      for (Object objects : answer) {
                          desc = (List<String>) objects;
                      }
                      System.out.println(desc.get(0));}
//                case "DiseaseSymptom" -> finalAnswer.add(answer.get(0)[0] + "的症状包括：" + answer.get(0)[2]);
//                case "DiseaseAccompany" ->
//                        finalAnswer.add(answer.get(0)[0] + "可能伴有：" + answer.get(0)[2] + "等症状");
//                case "DiseaseNotFood" ->
//                        finalAnswer.add("患有" + answer.get(0)[0] + "的人最好不要吃：" + answer.get(0)[2]);
//                case "DiseaseFood" -> {
//                    for (Object[] objects : answer) {
//                        if (objects[1].equals("RECOMMENDED_FOOD")) {
//                            finalAnswer.add(answer.get(0)[0] + "推荐食谱包括有：" + objects[2]);
//                        } else if (objects[1].equals("DO_FOOD")) {
//                            finalAnswer.add(answer.get(0)[0] + "宜食的食物包括有：" + objects[2]);
//                        }
//                    }
//                }
//                case "DiseaseDrug" -> {
//                    for (Object[] objects : answer) {
//                        if (objects[1].equals("COMMON_DRUG")) {
//                            finalAnswer.add(answer.get(0)[0] + "通常的使用的药品包括：" + objects[2]);
//                        } else if (objects[1].equals("RECOMMEND_DRUG")) {
//                            finalAnswer.add(objects[2].toString());
//                        }
//                    }
//                }
//                case "DiseaseCheck" ->
//                        finalAnswer.add(answer.get(0)[0] + "通常可以通过以下方式检查出来：" + answer.get(0)[2]);
//                case "DiseaseBelong" -> finalAnswer.add("患有" + answer.get(0)[0] + "应去医院挂：" + answer.get(0)[2]);
//                case "DiseaseCause" -> finalAnswer.add(answer.get(0)[0] + "可能的成因有：" + answer.get(0)[1]);
//                case "DiseasePrevent" -> finalAnswer.add(answer.get(0)[0] + "的预防措施包括：" + answer.get(0)[1]);
//                case "DiseaseLasttime" ->
//                        finalAnswer.add(answer.get(0)[0] + "治疗可能持续的周期为：" + answer.get(0)[1]);
//                case "DiseaseCureprob" -> finalAnswer.add(answer.get(0)[0] + "可以尝试如下治疗：" + answer.get(0)[1]);
//                case "DiseaseCureWay" ->
//                        finalAnswer.add(answer.get(0)[0] + "治愈的概率为（仅供参考）：" + answer.get(0)[1]);
//                case "DiseaseEasyget" -> finalAnswer.add(answer.get(0)[0] + "的易感人群包括：" + answer.get(0)[1]);
                case "DiseaseDesc" -> {
                    List<String> desc = new ArrayList<>();
                    for (Object objects : answer) {
                        desc = (List<String>) objects;
                    }
                    System.out.println(desc.get(0));
                System.out.println(desc.get(1));
                    finalAnswer.add(desc.get(0) + "的简要描述:" + desc.get(1).replace("\\\"", "\"").replace("\\'", "'"));
                }

//                case "DrugDisease" ->
//                        finalAnswer.add("！请注意药物仅供推荐，服用药物请咨询医师\n" + answer.get(0)[0] + "主治的疾病有：" + answer.get(0)[2]);
//                case "DrugProducer" -> finalAnswer.add(answer.get(0)[0] + "的生产厂商是：" + answer.get(0)[2]);
//                case "ProducedBy" -> finalAnswer.add(answer.get(0)[0] + "生产的药物有：" + answer.get(0)[2]);
//                case "SymptomDisease" ->
//                        finalAnswer.add("症状" + answer.get(0)[0] + "可能染上的疾病有：" + answer.get(0)[2]);
            }
        }
        return finalAnswer;
    }
}
