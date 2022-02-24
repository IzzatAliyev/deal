package com.ua.facade;

import com.ua.api.dto.request.entity.PersonalRequestDto;
import com.ua.api.dto.response.entity.PersonalResponseDto;

public interface PersonalFacade extends BaseFacade<PersonalRequestDto, PersonalResponseDto>{
    PersonalResponseDto findByEmail(String email);
    void update(PersonalRequestDto req, String email);
}
