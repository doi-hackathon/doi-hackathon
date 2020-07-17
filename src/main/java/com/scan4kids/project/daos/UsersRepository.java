package com.scan4kids.project.daos;

import com.scan4kids.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
