package com.ua.api.dto.response.entity;

import com.ua.persistence.entity.directory.Management;

public class ManagementShortResponseDto {

    private Long id;
    private String name;

    public ManagementShortResponseDto() {

    }

    public ManagementShortResponseDto(Management management) {
        setId(management.getId());
        this.name = management.getName();
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
