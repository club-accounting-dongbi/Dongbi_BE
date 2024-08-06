package com.dongbi.projectDongbi.domain.blacklistedToken.repository;

import com.dongbi.projectDongbi.domain.blacklistedToken.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
    boolean existsByToken (String token);
    void deleteByToken (String token);
}
