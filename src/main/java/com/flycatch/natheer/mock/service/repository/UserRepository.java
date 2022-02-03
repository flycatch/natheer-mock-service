package com.flycatch.natheer.mock.service.repository;

import com.flycatch.natheer.mock.service.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Object> findByUsername(String username);
}
