package com.appjam.appjam.domain.auth.repository;

import com.appjam.appjam.domain.auth.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsUserByUserId(String userId);
}
