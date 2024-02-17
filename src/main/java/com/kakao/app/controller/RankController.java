package com.kakao.app.controller;

import com.kakao.app.entity.UserEntity;
import com.kakao.app.repository.UserRepository;
import com.kakao.app.service.RankService;
import com.kakao.app.dto.RankRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // HTTP Response body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;
    private final UserRepository userRepository;


    @CrossOrigin
    @PostMapping("/gameover")
    public ResponseEntity<UserEntity> addRank(@RequestBody RankRequest rankRequest) {
        log.info("request addRank");
        return rankService.addOrUpdateRank(rankRequest);
    }

    @CrossOrigin
    @GetMapping("/ranking")
    public ResponseEntity<List<UserEntity>> getAllUsersOrderByTimeAsc() {
        // 시간순으로 모든 사용자를 가져와서 반환합니다.
        List<UserEntity> users = userRepository.findAllByOrderByTimeAsc();
        return ResponseEntity.ok(users);
    }
}
