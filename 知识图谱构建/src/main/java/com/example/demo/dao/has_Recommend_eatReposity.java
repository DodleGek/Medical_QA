package com.example.demo.dao;

import com.example.demo.fact.Recommend_eat;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface has_Recommend_eatReposity extends Neo4jRepository<Recommend_eat,Long> {
    @Query("match (a:Disease) where a.name=$diseasename with a order by a.name limit 1" +
            " match(b:Recommend_eat)where b.name=$Recommend_eatname with a,b order by b.name limit 1" +
            " create (a)-[r:has_Recommend_eat]->(b) return r")
    public void create(@Param("diseasename") String name,@Param("Recommend_eatname") String R_name);
}
