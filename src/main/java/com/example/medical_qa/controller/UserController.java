//package com.example.medical_qa.controller;
//
//import com.example.medical_qa.dto.UserRequest;
////import com.example.medical_qa.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
//        // 检查是否存在相同的账户
//        if (userService.userExists(userRequest.getUserid())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("该账户已存在");
//        }
//
//        // 注册用户
//        userService.registerUser(userRequest.getUserid(), userRequest.getPassword());
//        return ResponseEntity.status(HttpStatus.CREATED).body("用户注册成功");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
//        // 验证用户并获取用户令牌
//        String token = userService.authenticateUser(userRequest.getUserid(), userRequest.getPassword());
//
//        if (token != null) {
//            return ResponseEntity.ok(token);
//        } else {
//            // 验证失败，返回相应的错误消息
//            if (userService.userExists(userRequest.getUserid())) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("该账户不存在");
//            }
//        }
//    }
//}
