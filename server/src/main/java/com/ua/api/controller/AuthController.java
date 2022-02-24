package com.ua.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ua.api.dto.request.auth.LoginRequest;
import com.ua.api.dto.request.auth.SignUpRequest;
import com.ua.api.dto.response.auth.MessageResponse;
import com.ua.facade.AuthenticationFacade;
import com.ua.facade.RegistrationFacade;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrationFacade registrationFacade;
    private final AuthenticationFacade authenticationFacade;

    public AuthController(RegistrationFacade registrationFacade, AuthenticationFacade authenticationFacade) {
        this.registrationFacade = registrationFacade;
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationFacade.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signUpRequest) {
        registrationFacade.registration(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User signuped successfully!"));
    }
}
