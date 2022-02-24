package com.ua.api.dto.request.entity;

import com.ua.api.dto.request.RequestDto;

import java.util.List;

public class ManagementRequestDto extends RequestDto {

    private String name;
    private List<Long> departmentIds;

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
