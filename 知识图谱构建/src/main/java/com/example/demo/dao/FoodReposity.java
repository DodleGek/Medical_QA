package com.example.demo.dao;

import com.example.demo.fact.Food;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodReposity extends Neo4jRepository<Food,Long> {

}
