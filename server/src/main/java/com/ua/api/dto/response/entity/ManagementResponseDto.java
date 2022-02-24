package com.ua.api.dto.response.entity;

import org.apache.commons.collections4.CollectionUtils;
import com.ua.api.dto.response.ResponseDto;
import com.ua.persistence.entity.directory.Management;

import java.util.stream.Collectors;

public class ManagementResponseDto extends ResponseDto {

    private String name;
    private DepartmentShortResponseDto[] departments;

    public ManagementResponseDto(Management management) {
        setId(management.getId());
        setCreated(management.getCreated());
        setUpdated(management.getUpdated());
        setDeletionMark(management.getDeletionMark());
        this.name = management.getName();
        if (CollectionUtils.isNotEmpty(management.getDepartments())){
            this.departments = management.getDepartments()
                    .stream().map(DepartmentShortResponseDto::new).collect(Collectors.toList()).toArray(new DepartmentShortResponseDto[0]);
        } else {
            this.departments = new DepartmentShortResponseDto[0];
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentShortResponseDto[] getDepartments() {
        return departments;
    }

    public void setDepartments(DepartmentShortResponseDto[] departments) {
        this.departments = departments;
    }
}
