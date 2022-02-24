package com.ua.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.DepartmentRequestDto;
import com.ua.api.dto.response.entity.DepartmentResponseDto;
import com.ua.facade.DepartmentFacade;
import com.ua.persistence.entity.directory.Department;
import com.ua.service.ManagementService;
import com.ua.service.DepartmentService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentFacadeImpl implements DepartmentFacade {

    private final ManagementService managementService;
    private final DepartmentService departmentService;

    public DepartmentFacadeImpl(DepartmentService departmentService, ManagementService managementService) {
        this.departmentService = departmentService;
        this.managementService = managementService;
    }

    @Override
    public void create(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setName(departmentRequestDto.getName());
        department.setDepartmentType(departmentRequestDto.getDepartmentType());
        department.setManagements(departmentRequestDto.getManagementIds().stream()
                .filter(managementId -> managementId != null)
                .map(managementId -> managementService.findById(managementId).get())
                .collect(Collectors.toSet()));
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequestDto departmentRequestDto, Long id) {
        Department department = departmentService.findById(id).get();
        department.setUpdated(new Date());
        department.setName(departmentRequestDto.getName());
        department.setDepartmentType(departmentRequestDto.getDepartmentType());
        department.setManagements(departmentRequestDto.getManagementIds().stream()
                .filter(managementId -> managementId != null)
                .map(managementId -> managementService.findById(managementId).get())
                .collect(Collectors.toSet()));
        departmentService.update(department);
    }

    @Override
    public void delete(Long id) {
        departmentService.delete(id);
    }

    @Override
    public DepartmentResponseDto findById(Long id) {
        return new DepartmentResponseDto(departmentService.findById(id).get());
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return departmentService.count(parameterMap);
    }

    @Override
    public List<DepartmentResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Department> all = departmentService.findAll(parameterMap);
        List<DepartmentResponseDto> items = all.stream().map(DepartmentResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
