package com.example.demo.dao;

import com.example.demo.fact.Recommend_eat;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Recommend_eatReposity extends Neo4jRepository<Recommend_eat,Long> {

}
