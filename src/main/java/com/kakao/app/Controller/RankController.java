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

        //Optional<UserEntity> userEntity = Optional.of(new UserEntity()); //예시
        //Optional 쓰면 UserEntity 값이 null인지 아닌지 확인 가능
        Optional<UserEntity> userEntity = userRepository.findByNickname(rankRequest.getNickname()); //rankRequest에서 id만 확인해서 그 레코드를 가져옴(원래 있던 레코드)

        //DB에 기존 nickname이 없는 경우
        if (!userEntity.isPresent()) {
            UserEntity savedUser = rankService.save(rankRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedUser);
        } else { //DB에 기존 nickname이 있는 경우
            UserEntity existingUser = userEntity.get(); //기존에 있던 레코드를 가져와서 existingUser에 저장
            if (rankRequest.getTime() < existingUser.getTime()) {
                existingUser.setTime(rankRequest.getTime());
                UserEntity updatedUser = rankService.updateTime(existingUser);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(updatedUser);
            } else {
                // 기존 시간 값이 더 작을 경우에는 업데이트하지 않고 기존 값을 반환
                return ResponseEntity.status(HttpStatus.OK)
                        .body(existingUser);
            }
        }
    }
    @GetMapping("/api/users")
    public ResponseEntity<List<UserEntity>> getAllUsersOrderByTimeAsc() {
        // 시간순으로 모든 사용자를 가져와서 반환합니다.
        List<UserEntity> users = userRepository.findAllByOrderByTimeAsc();
        return ResponseEntity.ok(users);
    }
}
