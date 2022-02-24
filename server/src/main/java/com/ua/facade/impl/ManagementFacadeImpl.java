package com.ua.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.ManagementRequestDto;
import com.ua.api.dto.response.entity.ManagementResponseDto;
import com.ua.facade.ManagementFacade;
import com.ua.persistence.entity.directory.Management;
import com.ua.service.ManagementService;
import com.ua.service.DepartmentService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ManagementFacadeImpl implements ManagementFacade {

    private final ManagementService managementService;
    private final DepartmentService departmentService;

    public ManagementFacadeImpl(ManagementService managementService, DepartmentService departmentService) {
        this.managementService = managementService;
        this.departmentService = departmentService;
    }

    @Override
    public void create(ManagementRequestDto managementRequestDto) {
        Management management = new Management();
        management.setName(managementRequestDto.getName());
        management.setDepartments(managementRequestDto.getDepartmentIds().stream()
                .filter(departmentId -> departmentId != null)
                .map(departmentId -> departmentService.findById(departmentId).get())
                .collect(Collectors.toSet()));
        managementService.create(management);
    }

    @Override
    public void update(ManagementRequestDto managementRequestDto, Long id) {
        Management management = managementService.findById(id).get();
        management.setUpdated(new Date());
        management.setName(managementRequestDto.getName());
        management.setDepartments(managementRequestDto.getDepartmentIds().stream()
                .filter(departmentId -> departmentId != null)
                .map(departmentId -> departmentService.findById(departmentId).get())
                .collect(Collectors.toSet()));
        managementService.update(management);
    }

    @Override
    public void delete(Long id) {
        managementService.delete(id);
    }

    @Override
    public ManagementResponseDto findById(Long id) {
        return new ManagementResponseDto(managementService.findById(id).get());
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return managementService.count(parameterMap);
    }

    @Override
    public List<ManagementResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Management> all = managementService.findAll(parameterMap);
        List<ManagementResponseDto> items = all.stream().map(ManagementResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
