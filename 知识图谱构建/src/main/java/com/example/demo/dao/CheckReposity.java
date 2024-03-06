package com.example.demo.dao;


import com.example.demo.fact.Check;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckReposity extends Neo4jRepository<Check,Long> {
}
