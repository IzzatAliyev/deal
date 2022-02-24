package com.ua.service;

import com.ua.persistence.entity.user.Personal;

import java.util.Optional;

public interface PersonalService extends  BaseService<Personal> {
    Optional<Personal> findByEmail(String email);
}
