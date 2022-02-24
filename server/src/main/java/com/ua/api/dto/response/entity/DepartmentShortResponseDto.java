package com.ua.api.dto.response.entity;

import com.ua.persistence.entity.directory.Department;

public class DepartmentShortResponseDto {

    private Long id;
    private String name;

    public DepartmentShortResponseDto() {

    }

    public DepartmentShortResponseDto(Department department) {
        setId(department.getId());
        this.name = department.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
