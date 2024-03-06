package com.example.demo;


import com.example.demo.dao.belong_toReposity;
import com.example.demo.dao.has_Recommend_drugReposity;
import com.example.demo.dao.has_Recommend_eatReposity;
import com.example.demo.dao.has_not_eatReposity;
import com.example.demo.fact.*;

import com.example.demo.factlist.*;
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
    @Autowired
            private Recommend_eatService recommendEatService;
    @Autowired
            private  not_eatService not_eatService;
    @Autowired
            private has_Recommend_eatReposity hasRecommendEatReposity;
    @Autowired
            private has_not_eatReposity has_not_eatReposity;
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
    //推荐食物节点
    @Test
    public void savaRecommend_eat() {
        try {
            List<FoodList> foodLists = objectMapper.readValue(jsonFile, new TypeReference<List<FoodList>>() {});
            List<String> empty=new ArrayList<>();
            FoodList f=new FoodList();
            for (FoodList foodList : foodLists) {
                if (!Objects.equals(foodList, f)) {
                    if (!Objects.equals(empty, foodList.getRecommend_eat())) {
                        for (String name : foodList.getRecommend_eat()) {
                            Recommend_eat eat = new Recommend_eat();
                            eat.setName(name);
                            recommendEatService.sava(eat);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //创建疾病和建议吃的食物的关系节点
    @Test
    public void create_hasRecommend_eat() {
        try {
            List<DiseaseAll> diseaseAlls = objectMapper.readValue(jsonFile, new TypeReference<List<DiseaseAll>>() {
            });
            // 遍历实体列表并处理每个实体
            for (DiseaseAll diseaseAll : diseaseAlls) {
                String name = diseaseAll.getName();
                List<String> recommend_foodlist = diseaseAll.getRecommend_eat();
                List<String> emptyList = new ArrayList<>();
                if (!Objects.equals(recommend_foodlist, null)) {
                    for (String recommend_name : recommend_foodlist) {
                        hasRecommendEatReposity.create(name, recommend_name);
                    }
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //不能吃的食物节点
    @Test
    public void saveNot_eat() {

        try {
            List<FoodList> foodLists = objectMapper.readValue(jsonFile, new TypeReference<List<FoodList>>() {});
            List<String> empty = new ArrayList<>();
            FoodList f = new FoodList();
            for (FoodList foodList : foodLists) {
                if (!Objects.equals(f, foodList)) {
                    if (!Objects.equals(empty, foodList.getNot_eat()))
                        for (String name : foodList.getNot_eat()) {
                            not_eat not = new not_eat();
                            not.setName(name);
                            not_eatService.sava(not);
                        }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    //添加疾病和不能吃的食物的关系节点
    @Test
    public void create_hasnot_eat() {
        try {
            List<DiseaseAll> diseaseAlls = objectMapper.readValue(jsonFile, new TypeReference<List<DiseaseAll>>() {
            });
            // 遍历实体列表并处理每个实体
            for (DiseaseAll diseaseAll : diseaseAlls) {
                String name = diseaseAll.getName();
                List<String> not_foodlist = diseaseAll.getNot_eat();
                if (!Objects.equals(not_foodlist, null)) {
                    for (String not_name : not_foodlist) {
                        has_not_eatReposity.create(name, not_name);
                    }
                }
            }

        }
        catch (Exception e){
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

}
