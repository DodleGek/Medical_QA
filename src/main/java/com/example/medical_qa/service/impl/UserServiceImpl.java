//package com.example.medical_qa.service.impl;
//
//import com.example.medical_qa.entity.User;
//import com.example.medical_qa.dao.UserRepository;
//import com.example.medical_qa.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void registerUser(String userId, String password) {
//        User newUser = new User();
//        newUser.setUserId(userId);
//        newUser.setPassword(password);
//        userRepository.save(newUser);
//    }
//
//    @Override
//    public String authenticateUser(String userId, String password) {
//        Optional<User> userOptional = userRepository.findByUserId(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            if (password.equals(user.getPassword())) {
//                // 密码匹配，返回用户令牌
//                return generateUserToken(user);
//            }
//        }
//
//        // 密码错误或用户不存在
//        return null;
//    }
//
//    @Override
//    public boolean userExists(String userId) {
//        return userRepository.findByUserId(userId).isPresent();
//    }
//
//    // 省略其他可能需要的方法
//
//    private String generateUserToken(User user) {
//        // 实现生成用户令牌的逻辑，可以使用JWT或其他方式
//        // 返回用户令牌的字符串
//        return "user_token";
//    }
//}
