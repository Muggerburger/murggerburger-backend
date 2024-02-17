package com.kakao.app.service;

import com.kakao.app.entity.UserEntity;
import com.kakao.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public UserEntity save(String nickname) {


        com.kakao.app.entity.UserEntity saved = userRepository.save(new com.kakao.app.entity.UserEntity(nickname, null));

        return saved;
    }
}
// localhost:8080/user?userId=1 GET

// POST localhost:8080/user

//body @

//
