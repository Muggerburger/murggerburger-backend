package com.kakao.app.Entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // user_id
    private String nickname;
    private Long time;

    @Builder
    public UserEntity(Long userId, String nickname, Long time){
        this.userId=userId;
        this.nickname=nickname;
        this.time=time;
    }

    public void setTime(Long time) {
    }
}
