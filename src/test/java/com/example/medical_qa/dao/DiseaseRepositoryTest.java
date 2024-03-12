package com.example.medical_qa.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataNeo4jTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = Neo4jAutoConfiguration.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DiseaseRepositoryTest {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Test
    void testFindSymptomByName() {
        String diseaseName = "感冒";
        List<String> expectedSymptoms = List.of("发热", "咳嗽", "流感");

        List<String> actualSymptoms = diseaseRepository.findSymptomByName(diseaseName);

        assertEquals(expectedSymptoms.size(), actualSymptoms.size());
        assertEquals(expectedSymptoms, actualSymptoms);
    }

    // 编写其他测试方法来测试其他查询方法
}
