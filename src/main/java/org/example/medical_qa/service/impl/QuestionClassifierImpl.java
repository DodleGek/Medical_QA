package org.example.medical_qa.service.impl;


import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie;
import org.example.medical_qa.service.QuestionClassifier;
import org.example.medical_qa.service.TextFileReader;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class QuestionClassifierImpl implements QuestionClassifier {

    // 医疗特征词文件路径
    private static final String CHECK_FILE_PATH = "src\\main\\resources\\static\\check.txt";
    private static final String DENY_FILE_PATH = "src\\main\\resources\\static\\deny.txt";
    private static final String DEPARTMENT_FILE_PATH = "src\\main\\resources\\static\\department.txt";
    private static final String DISEASE_FILE_PATH = "src\\main\\resources\\static\\disease.txt";
    private static final String DRUG_FILE_PATH = "src\\main\\resources\\static\\drug.txt";
    private static final String FOOD_FILE_PATH = "src\\main\\resources\\static\\food.txt";
    private static final String PRODUCER_FILE_PATH = "src\\main\\resources\\static\\producer.txt";
    private static final String SYMPTOM_FILE_PATH = "src\\main\\resources\\static\\symptom.txt";

    // 读取文件的工具类
    private final TextFileReader textFileReader = new TextFileReader();
    // 医疗特征词——类型 字典
    private final Map<String, List<String>> wordTypeDict = new HashMap<>();
    // ac树输入数据
    private final TreeMap<String, String> map = new TreeMap<>();
    // ac树实例
    private AhoCorasickDoubleArrayTrie<String> acdat;

    // 医疗特征词
    private List<String> checkWord;
    private List<String> departmentWord;
    private List<String> diseaseWord;
    private List<String> drugWord;
    private List<String> foodWord;
    private List<String> producerWord;
    private List<String> symptomWord;
    private List<String> denyWord;

    // 问句疑问词
    private List<String> symptomQwds;
    private List<String> causeQwds;
    private List<String> acompanyQwds;
    private List<String> foodQwds;
    private List<String> drugQwds;
    private List<String> preventQwds;
    private List<String> lasttimeQwds;
    private List<String> curewayQwds;
    private List<String> cureprobQwds;
    private List<String> easygetQwds;
    private List<String> checkQwds;
    private List<String> belongQwds;
    private List<String> cureQwds;

    public QuestionClassifierImpl() {
        loadWordTypeDict();
        buildAcdat();
        loadQuestion();
    }


    /**
     * 问句分类
     *
     * @param question 问题
     * @return 问题分类结果
     */
    @Override
    public Map<String, Object> classify(String question) {
        Map<String, Object> data = new HashMap<>();
        Map<String, List<String>> medicalWords = checkMedical(question);

        if (medicalWords.isEmpty()) {
            return data;
        }

        List<String> types = new ArrayList<>();
        medicalWords.values().forEach(types::addAll);

        List<String> questionTypes = new ArrayList<>();

        // 问题分类逻辑...
        if (qWord(question, symptomQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseSymptom");
        }
        if (qWord(question, symptomQwds) && types.contains("Symptom")) {
            questionTypes.add("SymptomDisease");
        }
        if (qWord(question, causeQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseCause");
        }
        if (qWord(question, acompanyQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseAccompany");
        }
        if (qWord(question, foodQwds) && types.contains("Disease")) {
            if (qWord(question, denyWord)) {
                questionTypes.add("DiseaseNotFood");
            } else {
                questionTypes.add("DiseaseFood");
            }
        }
        if (qWord(question, drugQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseDrug");
        }
        if (qWord(question, cureQwds) && types.contains("Drug")) {
            questionTypes.add("DrugDisease");
        }
        if (qWord(question, checkQwds) && types.contains("Drug")) {
            questionTypes.add("DrugProducer");
        }
        if (qWord(question, checkQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseCheck");
        }
        if (qWord(question, preventQwds) && types.contains("Disease")) {
            questionTypes.add("DiseasePrevent");
        }
        if (qWord(question, lasttimeQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseLasttime");
        }
        if (qWord(question, curewayQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseCureway");
        }
        if (qWord(question, cureprobQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseCureprob");
        }
        if (qWord(question, easygetQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseEasyget");
        }
        if (qWord(question, belongQwds) && types.contains("Disease")) {
            questionTypes.add("DiseaseBelong");
        }
        if (qWord(question, belongQwds) && types.contains("Producer")) {
            questionTypes.add("ProducedBy");
        }
        // 如果没有分类到具体的问题类型，那么就按照疾病描述来回答
        if (questionTypes.isEmpty() && types.contains("Disease")) {
            questionTypes.add("DiseaseDesc");
        }
        // 如果没有分类到具体的问题类型，那么就按照症状描述来回答
        if (questionTypes.isEmpty() && types.contains("Symptom")) {
            questionTypes.add("SymptomDisease");
        }


        // 将问题分类结果存储在Map中
        data.put("args", medicalWords);
        data.put("question_types", questionTypes);
        return data;
    }

    /**
     * 加载问句疑问词
     */

    private void loadQuestion() {
        // 初始化问句疑问词成员变量
        this.symptomQwds = Arrays.asList("症状", "表征", "现象", "症候", "表现");
        this.causeQwds = Arrays.asList("原因", "成因", "为什么", "怎么会", "怎样才", "咋样才", "怎样会", "如何会", "为啥", "为何", "如何才会", "怎么才会", "会导致", "会造成");
        this.acompanyQwds = Arrays.asList("并发症", "并发", "一起发生", "一并发生", "一起出现", "一并出现", "一同发生", "一同出现", "伴随发生", "伴随", "共现");
        this.foodQwds = Arrays.asList("饮食", "饮用", "吃", "食", "伙食", "膳食", "喝", "菜", "忌口", "补品", "保健品", "食谱", "菜谱", "食用", "食物", "补品");
        this.drugQwds = Arrays.asList("药", "药品", "用药", "胶囊", "口服液", "炎片");
        this.preventQwds = Arrays.asList("预防", "防范", "抵制", "抵御", "防止", "躲避", "逃避", "避开", "免得", "逃开", "避开", "避掉", "躲开", "躲掉", "绕开", "怎样才能不", "怎么才能不", "咋样才能不", "咋才能不", "如何才能不", "怎样才不", "怎么才不", "咋样才不", "咋才不", "如何才不", "怎样才可以不", "怎么才可以不", "咋样才可以不", "咋才可以不", "如何可以不", "怎样才可不", "怎么才可不", "咋样才可不", "咋才可不", "如何可不");
        this.lasttimeQwds = Arrays.asList("周期", "多久", "多长时间", "多少时间", "几天", "几年", "多少天", "多少小时", "几个小时", "多少年");
        this.curewayQwds = Arrays.asList("怎么治疗", "如何医治", "怎么医治", "怎么治", "怎么医", "如何治", "医治方式", "疗法", "咋治", "怎么办", "咋办", "咋治");
        this.cureprobQwds = Arrays.asList("多大概率能治好", "多大几率能治好", "治好希望大么", "几率", "几成", "比例", "可能性", "能治", "可治", "可以治", "可以医");
        this.easygetQwds = Arrays.asList("易感人群", "容易感染", "易发人群", "什么人", "哪些人", "感染", "染上", "得上");
        this.checkQwds = Arrays.asList("检查", "检查项目", "查出", "检查", "测出", "试出");
        this.belongQwds = Arrays.asList("属于什么科", "属于", "什么科", "科室");
        this.cureQwds = Arrays.asList("治疗什么", "治啥", "治疗啥", "医治啥", "治愈啥", "主治啥", "主治什么", "有什么用", "有何用", "用处", "用途", "有什么好处", "有什么益处", "有何益处", "用来", "用来做啥", "用来作甚", "需要", "要");
    }

    /**
     * 加载医疗特征词——类型
     */
    private void loadWordTypeDict() {
        checkWord = textFileReader.readTextFile(CHECK_FILE_PATH);
        departmentWord = textFileReader.readTextFile(DEPARTMENT_FILE_PATH);
        diseaseWord = textFileReader.readTextFile(DISEASE_FILE_PATH);
        drugWord = textFileReader.readTextFile(DRUG_FILE_PATH);
        foodWord = textFileReader.readTextFile(FOOD_FILE_PATH);
        producerWord = textFileReader.readTextFile(PRODUCER_FILE_PATH);
        symptomWord = textFileReader.readTextFile(SYMPTOM_FILE_PATH);
        denyWord = textFileReader.readTextFile(DENY_FILE_PATH);

        addWordsToDict(checkWord, "Check");
        addWordsToDict(departmentWord, "Department");
        addWordsToDict(diseaseWord, "Disease");
        addWordsToDict(drugWord, "Drug");
        addWordsToDict(foodWord, "Food");
        addWordsToDict(producerWord, "Producer");
        addWordsToDict(symptomWord, "Symptom");
    }

    private void addWordsToDict(List<String> words, String type) {
        for (String word : words) {
            wordTypeDict.computeIfAbsent(word, k -> new ArrayList<>()).add(type);
        }
    }

    /**
     * 构建ac自动机
     */

    private void buildAcdat() {

        // Add words from different lists to the map
        featureWord(checkWord);
        featureWord(departmentWord);
        featureWord(diseaseWord);
        featureWord(drugWord);
        featureWord(foodWord);
        featureWord(producerWord);
        featureWord(symptomWord);

        acdat = new AhoCorasickDoubleArrayTrie<>();
        acdat.build(map);
    }

    public void featureWord(List<String> word) {
        for (String key : word) {
            map.put(key, key);
        }
    }


    /**
     * 判断问题中是否包含特定的疑问词
     *
     * @param question 问题
     * @param Qwds     疑问词列表
     * @return 是否包含
     */

    private boolean qWord(String question, List<String> Qwds) {
        for (String qwds : Qwds) {
            if (question.contains(qwds)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查问题中是否包含医疗特征词
     *
     * @param question 问题
     * @return 包含的医疗特征词
     */

    public Map<String, List<String>> checkMedical(String question) {
        List<String> regionWds = new ArrayList<>();
        // 迭代 AC 树并提取词语
        acdat.parseText(question, (begin, end, value) -> {
            // 将匹配到的值直接添加到列表中
            regionWds.add(value);
        });

        // 去除重复的词语
        Set<String> stopWds = new HashSet<>();
        for (String wd1 : regionWds) {
            for (String wd2 : regionWds) {
                if (wd2.contains(wd1) && !wd1.equals(wd2)) {
                    stopWds.add(wd1);
                }
            }
        }
        regionWds.removeAll(stopWds);

        // 创建最终的字典
        Map<String, List<String>> finalDict = new HashMap<>();
        for (String word : regionWds) {
            finalDict.put(word, wordTypeDict.get(word));
        }

        return finalDict;
    }

}


