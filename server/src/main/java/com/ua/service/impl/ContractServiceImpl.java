package com.ua.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ua.exception.IncorrectInputData;
import com.ua.logger.LoggerLevel;
import com.ua.logger.LoggerService;
import com.ua.persistence.crud.CrudRepositoryHelper;
import com.ua.persistence.entity.directory.Contract;
import com.ua.persistence.repository.ContractRepository;
import com.ua.service.ContractService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContractServiceImpl implements ContractService {

    private final CrudRepositoryHelper<Contract, ContractRepository> repositoryHelper;
    private final ContractRepository contractRepository;
    private final LoggerService loggerService;

    public ContractServiceImpl(CrudRepositoryHelper<Contract, ContractRepository> repositoryHelper,
                                ContractRepository contractRepository, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.contractRepository = contractRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional()
    public void create(Contract entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(contractRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void update(Contract entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(contractRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "object: " + Contract.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(contractRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Contract.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contract> findById(Long id) {
        return repositoryHelper.findById(contractRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contract> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(contractRepository, parameterMap, Contract.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(contractRepository, parameterMap, Contract.class);
    }

    private void checkInputDataOnValid(Contract entity) {
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
