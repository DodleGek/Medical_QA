package com.example.demo.dao;

import com.example.demo.fact.Recommend_drug;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface has_Recommend_drugReposity extends Neo4jRepository<Recommend_drug,Long> {
    @Query("match (a:Disease) where a.name=$disease_name with a order by a.name limit 1 " +
            " match (b:Recommend_drug) where b.name=$Recommend_name with a,b order by b.name limit 1" +
            " create (a)-[r:has_Recommend_drug]->(b) return r")
    public void create(@Param("disease_name") String disease_name,@Param("Recommend_name") String Recommend_name);
}
