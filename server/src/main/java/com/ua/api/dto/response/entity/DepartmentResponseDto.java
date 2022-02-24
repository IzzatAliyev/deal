package com.ua.api.dto.response.entity;

import org.apache.commons.collections4.CollectionUtils;
import com.ua.api.dto.response.ResponseDto;
import com.ua.persistence.entity.directory.Department;
import com.ua.persistence.type.DepartmentType;

import java.util.stream.Collectors;

public class DepartmentResponseDto extends ResponseDto {

    private String name;
    private DepartmentType departmentType;
    private ManagementShortResponseDto[] managements;

    public DepartmentResponseDto() {

    }

    public DepartmentResponseDto(Department department) {
        setId(department.getId());
        setCreated(department.getCreated());
        setUpdated(department.getUpdated());
        setDeletionMark(department.getDeletionMark());
        this.name = department.getName();
        this.departmentType = department.getDepartmentType();
        if (CollectionUtils.isNotEmpty(department.getManagements())){
            this.managements = department.getManagements()
                    .stream().map(ManagementShortResponseDto::new).collect(Collectors.toList()).toArray(new ManagementShortResponseDto[0]);
        } else {
            this.managements = new ManagementShortResponseDto[0];
        }
    }

    public ManagementShortResponseDto[] getManagements() {
        return managements;
    }

    public void setManagements(ManagementShortResponseDto[] managements) {
        this.managements = managements;
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
