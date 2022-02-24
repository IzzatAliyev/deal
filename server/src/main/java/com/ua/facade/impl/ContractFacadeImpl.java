package com.ua.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.ContractRequestDto;
import com.ua.api.dto.response.entity.ContractResponseDto;
import com.ua.facade.ContractFacade;
import com.ua.persistence.entity.directory.Contract;
import com.ua.service.ContractService;
import com.ua.service.DepartmentService;
import com.ua.service.DealerService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContractFacadeImpl implements ContractFacade {

    private final ContractService contractService;
    private final DepartmentService departmentService;
    private final DealerService dealerService;

    public ContractFacadeImpl(ContractService contractService, DepartmentService departmentService, DealerService dealerService) {
        this.contractService = contractService;
        this.departmentService = departmentService;
        this.dealerService = dealerService;
    }

    @Override
    public void create(ContractRequestDto contractRequestDto) {
        Contract contract = new Contract();
        contract.setName(contractRequestDto.getName());
        contract.setContractType(contractRequestDto.getContractType());
        contract.setDepartment(departmentService.findById(contractRequestDto.getDepartmentId()).get());
        contract.setDealer(dealerService.findById(contractRequestDto.getDealerId()).get());
        contractService.create(contract);
    }

    @Override
    public void update(ContractRequestDto contractRequestDto, Long id) {
        Contract contract = contractService.findById(id).get();
        contract.setUpdated(new Date());
        contract.setName(contractRequestDto.getName());
        contract.setContractType(contractRequestDto.getContractType());
        contract.setDepartment(departmentService.findById(contractRequestDto.getDepartmentId()).get());
        contract.setDealer(dealerService.findById(contractRequestDto.getDealerId()).get());
        contractService.update(contract);
    }

    @Override
    public void delete(Long id) {
        contractService.delete(id);
    }

    @Override
    public ContractResponseDto findById(Long id) {
        return new ContractResponseDto(contractService.findById(id).get());
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return contractService.count(parameterMap);
    }

    @Override
    public List<ContractResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Contract> all = contractService.findAll(parameterMap);
        List<ContractResponseDto> items = all.stream().map(ContractResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
