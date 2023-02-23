package com.application.letsbuy.internal.repositories;

import com.application.letsbuy.internal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findById(Long id);
     Optional<User> findByName(String name);
     Optional<User> findByEmail(String email);
}
