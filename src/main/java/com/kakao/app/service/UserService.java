package com.kakao.app.service;

import com.kakao.app.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public String save(String nickname) {


        com.kakao.app.Entity.UserEntity saved = userRepository.save(new com.kakao.app.Entity.UserEntity(nickname, null));

        return saved.getNickname();
    }
}
