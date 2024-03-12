package com.example.medical_qa.dao;

import com.example.medical_qa.entity.Disease;
import com.example.medical_qa.entity.Symptom;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface SymptomRepository extends Neo4jRepository<Symptom, Long> {

    // 根据疾病名称查询疾病
    @Query("MATCH (m:Disease)-[r:has_symptom]->(n:Symptom) WHERE n.name = $name RETURN m.name, r.name, n.name")
    List<Disease> findDiseaseBySymptomName(@Param("name")String name);
}
