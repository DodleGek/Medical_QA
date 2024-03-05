package com.example.demo;


import com.example.demo.dao.belong_toReposity;
import com.example.demo.dao.has_Recommend_drugReposity;
import com.example.demo.fact.*;

import com.example.demo.service.*;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@SpringBootTest
class Demo10ApplicationTests {

    @Autowired
    private SymptomService symptomService;
    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private has_SymptomService has_symptomService;
    @Autowired
    private Recommend_drugService recommendDrugService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private AcompanyService acompanyService;
    @Autowired
    private CheckService checkService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
            private ProductorService productorService;
    @Autowired
            private belong_toReposity belong;
    @Autowired
            private has_Recommend_drugReposity hasRecommendDrugReposity;
    ObjectMapper objectMapper = new ObjectMapper();
    File jsonFile = new File("D:\\idea workspace\\知识图谱构建\\src\\main\\resources\\medical.json");

    @Test
    //添加疾病节点
    public void diseasesave() {
        try {
            List<Disease> diseases = objectMapper.readValue(jsonFile, new TypeReference<List<Disease>>() {
            });
            for (Disease disease : diseases) {
                diseaseService.savediseaseNode(disease);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加症状节点
    @Test
    public void symptomsave() {
        try {
            List<SymptomList> symptomList = objectMapper.readValue(jsonFile, new TypeReference<List<SymptomList>>() {
            });
            for (SymptomList symptom_of_list : symptomList) {
                List<String> symptom_name=symptom_of_list.getSymptom();
                for(String name:symptom_name){
                    Symptom symptom=new Symptom();
                    symptom.setName(name);
                    symptomService.savesymptomNode(symptom);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* 添加症状和疾病关系节点*/

    @Test
    public void contextLoads() {

        try {
            List<DiseaseAll> diseaseAlls = objectMapper.readValue(jsonFile, new TypeReference<List<DiseaseAll>>() {
            });
            // 遍历实体列表并处理每个实体
            for ( DiseaseAll diseaseAll: diseaseAlls) {
                String name=diseaseAll.getName();
                List<String> symptom=diseaseAll.getSymptom();
                List<String> emptyList = new ArrayList<>();
                if(!Objects.equals(symptom, emptyList)) {
                    for (String symptom_name : symptom) {
                        has_symptomService.create(name, symptom_name);
                    }
                }
                }

        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
//添加特效药节点
    @Test
    public void sava_recommed_drug() {
        try {
            List<recommend_drug_List> drugs_list = objectMapper.readValue(jsonFile, new TypeReference<List<recommend_drug_List>>() {
            });
            for (recommend_drug_List drugList : drugs_list) {
                for(String name:drugList.getRecommend_drug_list()){
                    Recommend_drug recommend_drug=new Recommend_drug();
                    recommend_drug.setName(name);
                    recommendDrugService.sava(recommend_drug);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//添加疾病和所有特效药的关系节点
    @Test
    public void create_hasRecommend() {
        try {
            List<DiseaseAll> diseaseAlls = objectMapper.readValue(jsonFile, new TypeReference<List<DiseaseAll>>() {
            });
            // 遍历实体列表并处理每个实体
            for (DiseaseAll diseaseAll : diseaseAlls) {
                String name = diseaseAll.getName();
                List<String> recommend_druglist = diseaseAll.getRecommend_drug();
                List<String> emptyList = new ArrayList<>();
                if (!Objects.equals(recommend_druglist, emptyList)) {
                    for (String recommend_name : recommend_druglist) {
                        hasRecommendDrugReposity

                                .create(name, recommend_name);
                    }
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void savafood() {
        try {
            List<Food> foods = objectMapper.readValue(jsonFile, new TypeReference<List<Food>>() {
            });
            for (Food food : foods) {
                foodService.sava(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void savaacopany() {
        try {
            List<Acompany> acompanies = objectMapper.readValue(jsonFile, new TypeReference<List<Acompany>>() {
            });
            for (Acompany acompany : acompanies) {
                acompanyService.save(acompany);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void savacheck() {
        try {
            List<Check> checks = objectMapper.readValue(jsonFile, new TypeReference<List<Check>>() {
            });
            for (Check check : checks) {
                checkService.sava(check);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void savadepartment() {
        try {
            List<DepartmentList> departments = objectMapper.readValue(jsonFile, new TypeReference<List<DepartmentList>>() {
            });
            for (DepartmentList department_of_list : departments) {
                for(String department_name:department_of_list.getCure_department()){
                    Department d=new Department();
                    d.setName(department_name);
                    departmentService.sava(d);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //添加疾病和所属治疗科室的关系
@Test
public void createbelongto(){
    try {
        List<DiseaseAll> diseaseAlls = objectMapper.readValue(jsonFile, new TypeReference<List<DiseaseAll>>() {
        });
        // 遍历实体列表并处理每个实体
        for ( DiseaseAll diseaseAll: diseaseAlls) {
            String name=diseaseAll.getName();
            List<String> department=diseaseAll.getCure_department();
            List<String> emptyList = new ArrayList<>();
            if(!Objects.equals(department, emptyList)) {
                for (String depatment_name : department) {
                   belong.create(name, depatment_name);
                }
            }
        }

    } catch (
            IOException e) {
        e.printStackTrace();
    }
}
    @Test
    public void savaproductor() {
        try {
            List<Productor>productors =objectMapper.readValue(jsonFile, new TypeReference<List<Productor>>() {});
            for (Productor productor:productors) {
              productorService.save(productor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void query(){
      Disease d=diseaseService.findByName("百日咳");
      d.setName("hahaha");
      diseaseService.savediseaseNode(d);
    }
}
