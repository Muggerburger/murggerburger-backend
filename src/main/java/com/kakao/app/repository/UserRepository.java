package com.kakao.app.repository;

import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
