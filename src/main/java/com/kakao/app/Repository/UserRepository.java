package com.kakao.app.Repository;

import com.kakao.app.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(Long userId);
    List<UserEntity> findAllByOrderByTimeAsc();
}
