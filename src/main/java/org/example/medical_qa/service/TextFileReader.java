package org.example.medical_qa.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {

    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件路径
     * @return 文件内容
     */
    public List<String> readTextFile(String filePath) {
        List<String> content = new ArrayList<>();

        try {
            // 使用 FileInputStream 直接指定文件路径获取 InputStream
            FileInputStream inputStream = new FileInputStream(filePath);

            // 使用 InputStreamReader 和 BufferedReader 读取文件内容，并指定字符编码为 UTF-8
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.add(line.trim());
                }
            }
        } catch (IOException e) {
            logger.error("读取文件内容失败", e); // 读取文件失败的日志记录
        }

        return content;
    }
}


