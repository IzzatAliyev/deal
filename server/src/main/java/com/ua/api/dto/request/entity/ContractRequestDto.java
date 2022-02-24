package com.ua.api.dto.request.entity;

import com.ua.api.dto.request.RequestDto;
import com.ua.persistence.type.ContractType;

public class ContractRequestDto extends RequestDto {

    private String name;
    private long departmentId;
    private long dealerId;
    private ContractType contractType;

    public String getName() {
        return name;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
}
