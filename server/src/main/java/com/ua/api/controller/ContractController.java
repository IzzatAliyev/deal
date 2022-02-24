package com.ua.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.ContractRequestDto;
import com.ua.api.dto.response.entity.ContractResponseDto;
import com.ua.facade.ContractFacade;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController extends BaseController {

    private final ContractFacade contractFacade;

    public ContractController(ContractFacade contractFacade) {
        this.contractFacade = contractFacade;
    }

    @GetMapping
    public ResponseEntity<List<ContractResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(contractFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(contractFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewContract(@RequestBody ContractRequestDto contractRequestDto) {
        contractFacade.create(contractRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateContract(@PathVariable Long id, @RequestBody ContractRequestDto contractRequestDto) {
        contractFacade.update(contractRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteContract(@PathVariable Long id) {
        contractFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(contractFacade.findById(id));
    }
}
