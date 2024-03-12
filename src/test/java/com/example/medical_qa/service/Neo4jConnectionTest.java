package com.example.medical_qa.service;

import org.neo4j.driver.Record;
import org.neo4j.driver.*;

public class Neo4jConnectionTest {

    public static void main(String[] args) {
        // Neo4j 连接信息
        String uri = "bolt://192.168.10.8:7687";
        String username = "neo4j";
        String password = "Lhyu100218..";

        // 创建驱动程序
        try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password))) {
            // 创建会话
            try (Session session = driver.session()) {
                // 运行一个简单的查询来测试连接
                Result result = session.run("RETURN 1 AS number");
                // 处理查询结果
                while (result.hasNext()) {
                    Record record = result.next();
                    int number = record.get("number").asInt();
                    System.out.println("查询结果：" + number);
                }
            }
        } catch (Exception e) {
            // 处理连接异常
            System.err.println("连接到 Neo4j 数据库时出现异常：" + e.getMessage());
            e.printStackTrace();
        }
    }
}