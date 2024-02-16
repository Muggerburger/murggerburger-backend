package com.kakao.app.Repository;

import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
