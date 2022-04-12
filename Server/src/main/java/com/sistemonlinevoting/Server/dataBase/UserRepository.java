package com.sistemonlinevoting.Server.dataBase;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    User findByusername(String username);
    User findByid(Long id);
}
