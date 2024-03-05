package com.example.demo.dao;


import com.example.demo.fact.Acompany;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcompanyReposity extends Neo4jRepository<Acompany,Long> {
}
