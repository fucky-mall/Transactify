package com.example.service;

import com.example.Utils;
import com.example.dto.GetUserResponse;
import com.example.model.User;
import com.example.repository.UserCacheRepository;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    public static final String USER_CREATION_TOPIC = "user_created";
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCacheRepository userCacheRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public void createUser(User user) throws JsonProcessingException {
        userRepository.save(user);
        JSONObject userObject = new JSONObject();
        userObject.put("phone" , user.getPhone());
        userObject.put("name", user.getName());
        userObject.put("email", user.getEmail());
        kafkaTemplate.send(USER_CREATION_TOPIC, objectMapper.writeValueAsString(userObject));
    }

    public GetUserResponse get(Integer userId) throws Exception {
        User user = userCacheRepository.get(userId);
        if(user != null) {
            return Utils.convertToUserResponse(user);
        }

        user = userRepository.findById(userId).orElseThrow(() -> new Exception());
        userCacheRepository.set(user);
        return Utils.convertToUserResponse(user);
    }
}
