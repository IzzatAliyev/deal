package com.ua.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.DepartmentRequestDto;
import com.ua.api.dto.response.entity.DepartmentResponseDto;
import com.ua.facade.DepartmentFacade;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController {

    private final DepartmentFacade departmentFacade;

    public DepartmentController(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(departmentFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(departmentFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentFacade.create(departmentRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequestDto departmentRequestDto) {
        departmentFacade.update(departmentRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        departmentFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(departmentFacade.findById(id));
    }
}
