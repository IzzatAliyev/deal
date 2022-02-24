package com.ua.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ua.exception.IncorrectInputData;
import com.ua.logger.LoggerLevel;
import com.ua.logger.LoggerService;
import com.ua.persistence.crud.CrudRepositoryHelper;
import com.ua.persistence.entity.directory.Department;
import com.ua.persistence.repository.DepartmentRepository;
import com.ua.service.ContractService;
import com.ua.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final CrudRepositoryHelper<Department, DepartmentRepository> repositoryHelper;
    private final DepartmentRepository departmentRepository;
    private final ContractService contractService;
    private final LoggerService loggerService;

    public DepartmentServiceImpl(CrudRepositoryHelper<Department, DepartmentRepository> repositoryHelper,
                              DepartmentRepository departmentRepository,
                              ContractService contractService, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.departmentRepository = departmentRepository;
        this.contractService = contractService;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional
    public void create(Department entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(departmentRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void update(Department entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(departmentRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Map<String, String[]> params = new HashMap<>();
        params.put("department", new String[]{String.valueOf(id)});
        long countContracts = contractService.count(params);
        if (countContracts != 0) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + Department.class.getSimpleName() + "; operation: delete; id = " + id + "; problem = " + "With this department exist " + countContracts + " contracts you can not delete this department");
            throw new IncorrectInputData("With this department exist " + countContracts + " contracts you can not delete this department");
        }
        loggerService.commit(LoggerLevel.WARN, "object: " + Department.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(departmentRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Department.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findById(Long id) {
        return repositoryHelper.findById(departmentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(departmentRepository, parameterMap, Department.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(departmentRepository, parameterMap, Department.class);
    }

    private void checkInputDataOnValid(Department entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
