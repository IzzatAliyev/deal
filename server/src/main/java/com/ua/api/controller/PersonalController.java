package com.ua.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.PersonalRequestDto;
import com.ua.api.dto.response.entity.PersonalResponseDto;
import com.ua.facade.PersonalFacade;

import java.util.List;

@RestController
@RequestMapping("/api/personals")
public class PersonalController {

    private final PersonalFacade personalFacade;

    public PersonalController(PersonalFacade personalFacade) {
        this.personalFacade = personalFacade;
    }

    @GetMapping
    public ResponseEntity<List<PersonalResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(personalFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(personalFacade.count(request));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Boolean> updatePersonal(@PathVariable String email, @RequestBody PersonalRequestDto personalRequestDto) {
        personalFacade.update(personalRequestDto, email);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{email}")
    public ResponseEntity<PersonalResponseDto> details(@PathVariable String email) {
        return ResponseEntity.ok(personalFacade.findByEmail(email));
    }
}
