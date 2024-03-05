package com.example.demo.dao;

import com.example.demo.fact.Productor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductorReposity extends Neo4jRepository<Productor,Long> {
}
