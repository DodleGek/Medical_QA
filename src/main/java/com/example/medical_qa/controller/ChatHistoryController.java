//package com.example.medical_qa.controller;
//
////import com.example.medical_qa.entity.ConversationSession;
//import com.example.medical_qa.service.ChatHistoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/chat/history")
//public class ChatHistoryController {
//
//    @Autowired
//    private ChatHistoryService chatHistoryService;
//
//    @GetMapping("/getUserSession/{userId}")
//    public ResponseEntity<ConversationSession> getUserSession(@PathVariable String userId) {
//        try {
//            ConversationSession userSession = chatHistoryService.getUserSession(userId);
//
//            if (userSession != null) {
//                return ResponseEntity.ok(userSession);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//}
