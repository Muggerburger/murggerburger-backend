package com.kakao.app.Service;

import com.kakao.app.Entity.UserEntity;
import com.kakao.app.Repository.UserRepository;
import com.kakao.app.dto.RankRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankService {

    private final UserRepository userRepository;

    public UserEntity save(RankRequest rankRequest){
        return userRepository.save(rankRequest.toEntity());
    }

    public UserEntity updateTime(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
