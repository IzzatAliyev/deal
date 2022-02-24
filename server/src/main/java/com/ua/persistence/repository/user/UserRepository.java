package com.ua.persistence.repository.user;

import org.springframework.stereotype.Repository;
import com.ua.persistence.entity.user.User;
import com.ua.persistence.repository.AbstractRepository;

import java.util.Optional;

@Repository
public interface UserRepository <ENTITY extends User> extends AbstractRepository<ENTITY> {

    Optional<ENTITY> findByEmail(String email);
    boolean existsByEmail(String email);
}
