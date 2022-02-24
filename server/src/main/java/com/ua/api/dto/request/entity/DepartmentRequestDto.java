package com.ua.api.dto.request.entity;

import com.ua.api.dto.request.RequestDto;
import com.ua.persistence.type.DepartmentType;

import java.util.List;

public class DepartmentRequestDto extends RequestDto {

    private String name;
    private DepartmentType departmentType;
    private List<Long> managementIds;

    public List<Long> getManagementIds() {
        return managementIds;
    }

    public void setManagementIds(List<Long> managementIds) {
        this.managementIds = managementIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }
}
