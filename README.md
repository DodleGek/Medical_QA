# Medical_QA - 开源医疗问答系统

欢迎来到 Medical_QA 项目，这是一个基于 Spring Boot 的医疗问答系统。本项目旨在为医疗领域提供高效、准确的问答服务。

## 项目依赖

本项目依赖于以下技术栈：

- Java 17
- Spring Boot 3.3.0
- Neo4j 图数据库
- Lombok 1.18.30
- aho-corasick-double-array-trie 1.2.3
- Spring Boot Actuator

## 如何运行

1. 进入项目目录：
   ```bash
   cd Medical_QA
   
2. 使用 Maven 构建项目：
   ```bash
   mvn clean install

3. 运行项目：
   ```bash
   mvn spring-boot:run
   
4. 或者，您可以直接运行打包后的 jar 文件：
   ```bash
   java -jar target/Medical_QA-0.0.1-SNAPSHOT.jar
   
5. 访问项目：默认情况下，项目将运行在 http://localhost:8080。

