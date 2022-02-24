package com.ua.persistence.repository.user;

import org.springframework.stereotype.Repository;
import com.ua.persistence.entity.user.Personal;

@Repository
public interface PersonalRepository  extends UserRepository<Personal> {
}
