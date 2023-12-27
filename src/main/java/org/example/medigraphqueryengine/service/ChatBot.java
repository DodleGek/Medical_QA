package org.example.medigraphqueryengine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatBot {

    private final QuestionClassifier classifier;
    private final QuestionParser parser;
    private final AnswerSearcher searcher;

    @Autowired
    public ChatBot(QuestionClassifier classifier, QuestionParser parser, AnswerSearcher searcher) {
        this.classifier = classifier;
        this.parser = parser;
        this.searcher = searcher;
    }

    public String chatMain(String sent) {
        String answer = "抱歉，我暂时无法回答您的问题。您可以访问 https://github.com/DodleGek 获取更多帮助。祝您身体健康！";
        //用于对用户的问题进行分类，返回分类结果
        String resClassify = classifier.classify(sent).toString();
        if (resClassify == null) {
            return answer;
        }
        //用于解析分类后的问题
        String resSql = parser.parserMain(resClassify);
        //用于根据解析后的问题搜索答案
        List<String> finalAnswers = searcher.searchMain(resSql);
        if (finalAnswers == null || finalAnswers.isEmpty()) {
            return answer;
        } else {
            return String.join("\n", finalAnswers);
        }
    }
}
