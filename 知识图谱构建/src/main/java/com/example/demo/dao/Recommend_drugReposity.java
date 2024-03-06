package com.example.demo.dao;


import com.example.demo.fact.Recommend_drug;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Recommend_drugReposity extends Neo4jRepository<Recommend_drug,Long> {
}
