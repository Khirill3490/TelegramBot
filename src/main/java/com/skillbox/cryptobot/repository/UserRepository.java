package com.skillbox.cryptobot.repository;

import com.skillbox.cryptobot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserUUID(long uuid);

}
