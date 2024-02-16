package com.kakao.app.service;

import com.kakao.app.entity.UserEntity;
import com.kakao.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public String save(String nickname) {


        UserEntity saved = userRepository.save(new UserEntity(nickname, null));

        return saved.getNickname();
    }
}
