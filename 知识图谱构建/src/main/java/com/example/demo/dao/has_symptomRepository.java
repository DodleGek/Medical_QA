package com.example.demo.dao;


import com.example.demo.fact.Disease;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface has_symptomRepository extends Neo4jRepository<Disease,Long> {

    @Query("MATCH (a:Disease {name: $name})with a ORDER BY a.name LIMIT 1 " +
            " MATCH (b:Symptom )where b.name=$symptomname with a,b order by b.symptom limit 1 CREATE (a)-[r:HAS_SYMPTOM]->(b) RETURN r")
    public void createRelation(@Param("name") String diseaseName, @Param("symptomname") String symptomname);
}
