package com.example.demo.dao;

import com.example.demo.fact.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentReposity extends Neo4jRepository<Department,Long> {
}
