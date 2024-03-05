package com.example.demo.dao;


import com.example.demo.fact.Disease;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseReposity extends Neo4jRepository<Disease,Long> {
    @Query("MATCH (d:Disease) WHERE d.name = $name RETURN d ORDER BY d.name LIMIT 1")
    Disease findByName(@Param("name") String name);

}
