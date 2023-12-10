package org.example.medigraphqueryengine.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MedicalGraphRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalGraphRepository.class); // 日志记录
    private final Neo4jClient neo4jClient;

    @Autowired
    //spring boot自动配置neo4jClient
    public MedicalGraphRepository(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    /**
     * 向neo4j中创建节点
     */
    public void createNode(String label, Set<String> nodes) {
        int count = 0;
        for (String nodeName : nodes) {
            neo4jClient.query("CREATE (n:" + label + " {name: $name}) RETURN n")
                    .bindAll(Map.of("name", nodeName))
                    .run();
            count++;
            LOGGER.info("Created node: {} of {}", count, nodes.size());
        }
    }

    /**
     * 向neo4j中创建疾病包含的属性
     */
    public void createDiseasesNodes(List<Map<String, String>> diseaseInfos) {
        int count = 0;
        for (Map<String, String> diseaseDict : diseaseInfos) {
            neo4jClient.query("CREATE (n:Disease {name: $name, desc: $desc, prevent: $prevent, cause: $cause, easy_get: $easy_get, cure_lasttime: $cure_lasttime, cure_department: $cure_department, cure_way: $cure_way, cured_prob: $cured_prob}) RETURN n")
                    .bindAll(Map.of("name", diseaseDict.get("name"), "desc", diseaseDict.get("desc"), "prevent", diseaseDict.get("prevent"), "cause", diseaseDict.get("cause"), "easy_get", diseaseDict.get("easy_get"), "cure_lasttime", diseaseDict.get("cure_lasttime"), "cure_department", diseaseDict.get("cure_department"), "cure_way", diseaseDict.get("cure_way"), "cured_prob", diseaseDict.get("cured_prob")))
                    .run();
            count++;
            LOGGER.info("Created disease node: {} of {}", count, diseaseInfos.size());
        }
    }

    /**
     * 向neo4j中创建节点关系
     */
    public void createRelationship(String startNode, String endNode, List<List<String>> edges, String relType, String relName) {
        int count = 0;
        Set<String> setEdges = new HashSet<>();
        for (List<String> edge : edges) {
            setEdges.add(String.join("###", edge));
        }
        int all = setEdges.size(); //获取总的关系数量
        for (String edge : setEdges) {
            String[] nodes = edge.split("###");
            String node1 = nodes[0];
            String node2 = nodes[nodes.length - 1]; // 获取最后一个节点
            String query = String.format("MATCH (p:%s),(q:%s) WHERE p.name='%s' AND q.name='%s' CREATE (p)-[rel:%s{name:'%s'}]->(q)",
                    startNode, endNode, node1, node2, relType, relName);
            neo4jClient.query(query).run();
            count++;
            LOGGER.info("Created relationship: {} of {}", count, all);
        }
    }

    /**
     * 创建知识图谱所需要的数据结构
     */
    public Map<String, Set<String>> initializeNode() {
        return Map.of(
                // 创建节点数据结构
                "drugs", new HashSet<>(), // 药品
                "foods", new HashSet<>(), // 食物
                "checks", new HashSet<>(), // 检查
                "departments", new HashSet<>(), // 科室
                "producers", new HashSet<>(), // 药品生产商
                "diseases", new HashSet<>(), // 疾病
                "symptoms", new HashSet<>(), // 症状
                "doctors", new HashSet<>() // 医生
        );
    }

    public Map<String, List<Map<String, Object>>> initializeRels() {
        return Map.ofEntries(
                // 创建关系数据结构
                Map.entry("DEPARTMENT_BELONGS_TO", new ArrayList<>()), // 科室-科室关系
                Map.entry("NO_EAT", new ArrayList<>()), // 疾病-忌吃食物关系
                Map.entry("DO_EAT", new ArrayList<>()), // 疾病-宜吃食物关系
                Map.entry("RECOMMAND_EAT", new ArrayList<>()), // 疾病-推荐吃食物关系
                Map.entry("COMMON_DRUG", new ArrayList<>()), // 疾病-通用药品关系
                Map.entry("RECOMMAND_DRUG", new ArrayList<>()), // 疾病-热门药品关系
                Map.entry("NEED_CHECK", new ArrayList<>()), // 疾病-检查关系
                Map.entry("DRUGS_OF", new ArrayList<>()), // 药品-产生关系
                Map.entry("DOCTOR_BELONGS_TO", new ArrayList<>()), // 医生-科室关系
                Map.entry("HAS_SYMPTOM", new ArrayList<>()), // 疾病-症状关系
                Map.entry("ACOMPANY_WITH", new ArrayList<>()), // 疾病-并发关系
                Map.entry("DISEASE_BELONGS_TO", new ArrayList<>()) // 疾病-科室关系
        );
    }

}