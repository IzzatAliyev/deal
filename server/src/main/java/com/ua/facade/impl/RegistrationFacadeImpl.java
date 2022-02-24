package com.ua.facade.impl;

import org.springframework.stereotype.Service;
import com.ua.api.dto.request.auth.SignUpRequest;
import com.ua.facade.RegistrationFacade;
import com.ua.persistence.entity.user.Personal;
import com.ua.service.PersonalService;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final PersonalService personalService;

    public RegistrationFacadeImpl(PersonalService personalService) {
        this.personalService = personalService;
    }

    @Override
    public void registration(SignUpRequest signUpRequest) {
        Personal personal = new Personal();
        personal.setEmail(signUpRequest.getEmail());
        personal.setPassword(signUpRequest.getPassword());
        personal.setFirstName(signUpRequest.getFirstName());
        personal.setLastName(signUpRequest.getLastName());
        personalService.create(personal);
    }
}
