package org.example.medical_qa.dao;

import org.example.medical_qa.entity.node.Disease;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends Neo4jRepository<Disease, Long> {
    // 根据疾病名称查询疾病的症状
    @Query("MATCH (m:Disease)-[r:HAS_SYMPTOM]->(n:Symptom) WHERE m.name = $name RETURN m.name, r.name, n.name")
    List<String> findSymptomByName(@Param("name") String name);

    // 根据疾病名称查询疾病的并发症
    @Query("MATCH (m:Disease)-[r:ACCOMPANY_WITH]->(n:Disease) WHERE m.name = $name RETURN m.name, r.name, n.name ")
    List<String> findAccompanyDiseaseByName(@Param("name") String name);

    // 根据疾病名称查询疾病的常用药品
    @Query("MATCH (m:Disease)-[r:COMMON_DRUG]->(n:Drug) WHERE m.name = $name RETURN m.name, r.name, n.name " +
            "UNION ALL " +
            "MATCH (m:Disease)-[r:RECOMMEND_DRUG]->(n:Drug) WHERE m.name = $name RETURN m.name, r.name, n.name")
    List<Object[]> findCommonAndRecommendedDrugs(@Param("name") String name);


    // 根据疾病名称查询疾病的推荐食物
    @Query("MATCH (m:Disease)-[r:RECOMMENDED_FOOD]->(n:Food) WHERE m.name = $name RETURN m.name, r.name, n.name " +
            "UNION ALL " +
            "MATCH (m:Disease)-[r:DO_FOOD]->(n:Food) WHERE m.name = $name RETURN m.name, r.name, n.name")
    List<Object[]> findRecommendAndDoDrugs(@Param("name") String name);

    // 根据疾病名称查询疾病的忌口食物
    @Query("MATCH (m:Disease)-[r:AVOIDED_FOOD]->(n:Food) WHERE m.name = $name  RETURN m.name, r.name, n.name")
    List<String> findAvoidedFoodByName(@Param("name") String name);

    // 根据疾病名称查询疾病的检查项目
    @Query("MATCH (m:Disease)-[r:NEED_CHECK]->(n:Check) WHERE m.name = $name RETURN m.name, r.name, n.name")
    List<String> findCheckByName(@Param("name") String name);


    // 根据疾病名称查询疾病的所属科室
    @Query("MATCH (m:Disease)-[r:BELONGS_TO]->(n:Department) WHERE m.name = $name RETURN m.name, r.name, n.name")
    List<String> findDepartmentByName(@Param("name") String name);

    // 根据疾病名称查询疾病的病因
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d.name,d.cause")
    List<String> findCauseByName(@Param("name") String name);

    // 根据疾病名称查询疾病的防御措施
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d.name,d.prevent")
    List<String> findPreventByName(@Param("name") String name);

    // 根据疾病名称查询疾病的持续时间
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d.name,d.cureLastTime")
    List<String> findCureLastTimeByName(@Param("name") String name);

    // 根据疾病名称查询疾病的治愈概率
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d.name,d.curedProb")
    List<String> findCuredProbByName(@Param("name") String name);

    // 根据疾病名称查询疾病的治疗方式
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d.name,d.cureWay")
    List<String> findCureWayByName(@Param("name") String name);

    // 根据疾病名称查询疾病的易感人群
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d.name,d.easyGet")
    List<String> findEasyGetByName(@Param("name") String name);

    // 根据疾病名称查询疾病的描述
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d limit 1")
    Disease findDiseaseByName(@Param("name") String name);
}
