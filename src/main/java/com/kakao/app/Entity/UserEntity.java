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
    private Long id;
    private String nickname;
    private Long time;

    @Builder
    public UserEntity(String nickname, Long time){
        this.nickname=nickname;
        this.time=time;
    }

    public void setTime(Long time) {
        this.time=time;
    }
}
