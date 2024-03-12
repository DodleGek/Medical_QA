//package com.example.medical_qa.service;
//
//
//import com.example.medical_qa.entity.ConversationSession;
//
//import java.util.List;
//
///**
// * ConversationService 接口定义了用于管理用户对话会话的方法。
// */
//public interface ConversationService {
//
//    /**
//     * 打开一个对话会话，为用户创建新的对话组或时间线。
//     *
//     * @param userId 用户的唯一标识符。
//     * @param sessionId 对话会话的唯一标识符，用于标识不同的对话组或时间线。
//     */
//    public void openSession(String userId, String sessionId);
//
//    /**
//     * 关闭当前对话会话，保存当前对话组的状态。
//     *
//     * @param userId 用户的唯一标识符。
//     */
//    public void closeSession(String userId);
//
//    /**
//     * 将一个对话会话添加到用户的对话历史中。
//     *
//     * @param userId 用户的唯一标识符。
//     * @param conversation 包含对话组信息和对话列表的对话会话。
//     */
//    public void addConversation(String userId, ConversationSession conversation);
//
//    /**
//     * 获取用户的对话历史记录，返回用户之前的所有对话会话。
//     *
//     * @param userId 用户的唯一标识符。
//     * @return 包含用户所有对话会话的列表。
//     */
//    public List<ConversationSession> getUserConversationHistory(String userId);
//}
//
