package com.ua.facade;

import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.RequestDto;
import com.ua.api.dto.response.ResponseDto;

import java.util.List;

public interface BaseFacade <REQ extends RequestDto, RES extends ResponseDto> {

    void create(REQ req);
    void update(REQ req, Long id);
    void delete(Long id);
    RES findById(Long id);
    List<RES> findAll(WebRequest request);
    long count(WebRequest request);
}
