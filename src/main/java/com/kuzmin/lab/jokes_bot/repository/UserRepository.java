package com.kuzmin.lab.jokes_bot.repository;

import com.kuzmin.lab.jokes_bot.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String username);
}
