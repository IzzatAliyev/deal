package com.ua.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.DealerRequestDto;
import com.ua.api.dto.response.entity.DealerResponseDto;
import com.ua.facade.DealerFacade;

import java.util.List;

@RestController
@RequestMapping("/api/dealers")
public class DealerController extends BaseController {

    private final DealerFacade dealerFacade;

    public DealerController(DealerFacade dealerFacade) {
        this.dealerFacade = dealerFacade;
    }

    @GetMapping
    public ResponseEntity<List<DealerResponseDto>> findAll(WebRequest request) {
        return ResponseEntity.ok(dealerFacade.findAll(request));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(WebRequest request) {
        return ResponseEntity.ok(dealerFacade.count(request));
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createNewDealer(@RequestBody DealerRequestDto dealerRequestDto) {
        dealerFacade.create(dealerRequestDto);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateDealer(@PathVariable Long id, @RequestBody  DealerRequestDto dealerRequestDto) {
        dealerFacade.update(dealerRequestDto, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        dealerFacade.delete(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealerResponseDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(dealerFacade.findById(id));
    }
}
