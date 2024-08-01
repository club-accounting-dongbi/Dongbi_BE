package com.dongbi.projectDongbi.domain.user.repository;

import com.dongbi.projectDongbi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
