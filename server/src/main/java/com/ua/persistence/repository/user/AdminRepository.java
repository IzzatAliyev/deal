package com.ua.persistence.repository.user;

import org.springframework.stereotype.Repository;
import com.ua.persistence.entity.user.Admin;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
