package com.ua.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.ManagementRequestDto;
import com.ua.api.dto.response.entity.ManagementResponseDto;
import com.ua.facade.ManagementFacade;

import java.util.List;

@RestController
@RequestMapping("/api/managements")
public class ManagementController extends BaseController {

    private final ManagementFacade managementFacade;

    public ManagementController(ManagementFacade managementFacade) {
        this.managementFacade = managementFacade;
    }

    @GetMapping
    public ResponseEntity<List<ManagementResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(managementFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(managementFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewManagement(@RequestBody ManagementRequestDto managementRequestDto) {
        managementFacade.create(managementRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateManagement(@PathVariable Long id, @RequestBody ManagementRequestDto managementRequestDto) {
        managementFacade.update(managementRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        managementFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(managementFacade.findById(id));
    }
}
