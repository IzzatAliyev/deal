package com.ua.facade;

import com.ua.api.dto.request.auth.SignUpRequest;

public interface RegistrationFacade {

    void registration(SignUpRequest dto);
}
