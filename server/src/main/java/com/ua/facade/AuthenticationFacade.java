package com.ua.facade;

import com.ua.api.dto.request.auth.LoginRequest;
import com.ua.api.dto.response.auth.SecurityResponse;

public interface AuthenticationFacade {

    SecurityResponse login(LoginRequest dto);
}
