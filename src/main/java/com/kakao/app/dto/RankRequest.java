package com.kakao.app.dto;

import com.kakao.app.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RankRequest {

    private String nickname;
    private Long time;

    @Builder
    public RankRequest(String nickname, Long time){
        this.nickname=nickname;
        this.time=time;
    }

    public UserEntity toEntity(){ //dto를 엔티티로 만들어주는 메서드
        return UserEntity.builder()
                .nickname(nickname)
                .time(time)
                .build();
    }
}
