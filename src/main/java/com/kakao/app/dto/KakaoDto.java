package com.kakao.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class KakaoDto {
    private String nickname;
    private String accessToken;

    public KakaoDto(String nickname){
        this.nickname = nickname;
    }
}
