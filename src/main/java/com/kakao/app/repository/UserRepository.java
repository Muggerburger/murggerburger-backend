package com.kakao.app.repository;

import com.kakao.app.entity.UserEntity;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
