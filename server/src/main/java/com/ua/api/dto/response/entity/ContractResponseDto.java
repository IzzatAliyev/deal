package com.ua.api.dto.response.entity;

import com.ua.api.dto.response.ResponseDto;
import com.ua.persistence.entity.directory.Contract;
import com.ua.persistence.entity.directory.Dealer;
import com.ua.persistence.type.ContractType;

public class ContractResponseDto extends ResponseDto {

    private String name;
    private DepartmentShortResponseDto department;
    private Dealer dealer;
    private ContractType contractType;

    public ContractResponseDto() {

    }

    public ContractResponseDto(Contract contract) {
        setId(contract.getId());
        setCreated(contract.getCreated());
        setUpdated(contract.getUpdated());
        setDeletionMark(contract.getDeletionMark());
        this.name = contract.getName();
        this.contractType = contract.getContractType();
        this.department = new DepartmentShortResponseDto(contract.getDepartment());
        this.dealer = contract.getDealer();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentShortResponseDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentShortResponseDto department) {
        this.department = department;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
}
