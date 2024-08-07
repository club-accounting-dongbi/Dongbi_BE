package com.dongbi.projectDongbi.domain.refreshToken.repository;

import com.dongbi.projectDongbi.domain.refreshToken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByUserId(Long userId);
    boolean existsByToken(String token);
    void deleteByToken(String token);
}
