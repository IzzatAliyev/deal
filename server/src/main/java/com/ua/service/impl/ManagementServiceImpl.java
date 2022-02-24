package com.ua.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ua.exception.IncorrectInputData;
import com.ua.logger.LoggerLevel;
import com.ua.logger.LoggerService;
import com.ua.persistence.crud.CrudRepositoryHelper;
import com.ua.persistence.entity.directory.Management;
import com.ua.persistence.repository.ManagementRepository;
import com.ua.service.ManagementService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ManagementServiceImpl implements ManagementService {

    private final CrudRepositoryHelper<Management, ManagementRepository> repositoryHelper;
    private final ManagementRepository managementRepository;
    private final LoggerService loggerService;

    public ManagementServiceImpl(CrudRepositoryHelper<Management, ManagementRepository> repositoryHelper,
                                        ManagementRepository managementRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.managementRepository = managementRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional()
    public void create(Management entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(managementRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void update(Management entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(managementRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + Management.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(managementRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Management.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Management> findById(Long id) {
        return repositoryHelper.findById(managementRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Management> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(managementRepository, parameterMap, Management.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(managementRepository, parameterMap, Management.class);
    }

    private void checkInputDataOnValid(Management entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
