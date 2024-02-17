package com.kakao.app.Controller;

import com.kakao.app.Entity.UserEntity;
import com.kakao.app.Repository.UserRepository;
import com.kakao.app.Service.RankService;
import com.kakao.app.dto.RankRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController // HTTP Response body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequiredArgsConstructor
public class RankController {

    private final RankService rankService;
    private final UserRepository userRepository;

    @PostMapping("/api/gameover")
    public ResponseEntity<UserEntity> addRank(@RequestBody RankRequest rankRequest) {
        return rankService.addOrUpdateRank(rankRequest);
    }

    @GetMapping("/api/ranking")
    public ResponseEntity<List<UserEntity>> getAllUsersOrderByTimeAsc() {
        // 시간순으로 모든 사용자를 가져와서 반환합니다.
        List<UserEntity> users = userRepository.findAllByOrderByTimeAsc();
        return ResponseEntity.ok(users);
    }
}
