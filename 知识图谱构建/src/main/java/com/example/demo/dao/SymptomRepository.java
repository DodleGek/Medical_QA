package com.example.demo.dao;


import com.example.demo.fact.Symptom;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends Neo4jRepository<Symptom,Long> {
@Query("MATCH (n:Symptom) RETURN n")
    public List<Symptom> findall();


}
