package com.ua.facade.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.ua.api.dto.request.auth.LoginRequest;
import com.ua.api.dto.response.auth.SecurityResponse;
import com.ua.facade.AuthenticationFacade;
import com.ua.service.AuthenticationService;
import com.ua.util.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationService authenticationService;
    private final SecurityUtil securityUtil;

    public AuthenticationFacadeImpl(AuthenticationService authenticationService, SecurityUtil securityUtil) {
        this.authenticationService = authenticationService;
        this.securityUtil = securityUtil;
    }

    @Override
    public SecurityResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
        String jwt = securityUtil.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new SecurityResponse(jwt,
                userDetails.getUsername(),
                roles);
    }
}
