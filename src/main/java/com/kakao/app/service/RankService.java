package com.kakao.app.service;

import com.kakao.app.entity.UserEntity;
import com.kakao.app.repository.UserRepository;
import com.kakao.app.dto.RankRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankService {

    private final UserRepository userRepository;

    public ResponseEntity<UserEntity> addOrUpdateRank(RankRequest rankRequest) {
        Optional<UserEntity> userEntityOptional = userRepository.findByNickname(rankRequest.getNickname());

        if (!userEntityOptional.isPresent()) {
            UserEntity savedUser = save(rankRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } else {
            UserEntity existingUser = userEntityOptional.get();
            if (rankRequest.getTime() < existingUser.getTime()) {
                existingUser.setTime(rankRequest.getTime());
                UserEntity updatedUser = updateTime(existingUser);
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(existingUser);
            }
        }
    }

    private UserEntity save(RankRequest rankRequest) {
        return userRepository.save(rankRequest.toEntity());
    }

    private UserEntity updateTime(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
