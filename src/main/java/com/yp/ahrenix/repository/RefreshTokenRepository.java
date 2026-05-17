package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.RefreshToken;
import com.yp.ahrenix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

}