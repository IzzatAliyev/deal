package com.ua.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ua.persistence.type.ContractType;
import com.ua.persistence.type.DepartmentType;
import com.ua.persistence.type.DealerType;
import com.ua.persistence.type.RoleType;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypesController extends BaseController {


    @GetMapping("/departmentTypes")
    public ResponseEntity<List<DepartmentType>> findAllDepartmentTypes() {
        return ResponseEntity.ok(Arrays.stream(DepartmentType.values()).toList());
    }

    @GetMapping("/dealerTypes")
    public ResponseEntity<List<DealerType>> findAllDealerTypes() {
        return ResponseEntity.ok(Arrays.stream(DealerType.values()).toList());
    }

    @GetMapping("/contractTypes")
    public ResponseEntity<List<ContractType>> findAllContractTypes() {
        return ResponseEntity.ok(Arrays.stream(ContractType.values()).toList());
    }

    @GetMapping("/roleTypes")
    public ResponseEntity<List<RoleType>> findAllRoleTypes() {
        return ResponseEntity.ok(Arrays.stream(RoleType.values()).toList());
    }

}
