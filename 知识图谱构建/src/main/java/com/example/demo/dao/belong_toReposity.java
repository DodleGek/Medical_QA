package com.example.demo.dao;

import com.example.demo.fact.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface belong_toReposity extends Neo4jRepository<Department,Long> {
    @Query("match (a:Disease) where a.name=$disease_name with a order by a.name limit 1" +
            " match (b:Department) where b.name=$departmentname with a,b order by b.name limit 1" +
            " create  (a)-[r:belong_to]->(b) return r")
    public void create(@Param("disease_name") String diseasename,@Param("departmentname") String departmenname);
}
