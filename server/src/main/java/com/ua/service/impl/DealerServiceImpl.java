package com.ua.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ua.exception.IncorrectInputData;
import com.ua.logger.LoggerLevel;
import com.ua.logger.LoggerService;
import com.ua.persistence.crud.CrudRepositoryHelper;
import com.ua.persistence.entity.directory.Dealer;
import com.ua.persistence.repository.DealerRepository;
import com.ua.service.ContractService;
import com.ua.service.DealerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    private final CrudRepositoryHelper<Dealer, DealerRepository> repositoryHelper;
    private final DealerRepository dealerRepository;
    private final ContractService contractService;
    private final LoggerService loggerService;

    public DealerServiceImpl(CrudRepositoryHelper<Dealer, DealerRepository> repositoryHelper,
                                   DealerRepository dealerRepository,
                                   ContractService contractService, LoggerService loggerService) {
        this.repositoryHelper = repositoryHelper;
        this.dealerRepository = dealerRepository;
        this.contractService = contractService;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional()
    public void create(Dealer entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: create");
        repositoryHelper.create(dealerRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: create; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void update(Dealer entity) {
        checkInputDataOnValid(entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: start; operation: update; id = " + entity.getId());
        repositoryHelper.update(dealerRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "object: " + entity.getClass().getSimpleName() + "; stage: finish; operation: update; id = " + entity.getId());
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        Map<String, String[]> params = new HashMap<>();
        params.put("dealer", new String[]{String.valueOf(id)});
        long countContracts =  contractService.count(params);
        if (countContracts != 0) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + Dealer.class.getSimpleName() + "; operation: update/new; id = " + id + "; problem = " + "With this dealer exist " + countContracts + " contracts you can not delete this dealer");
            throw new IncorrectInputData("With this dealer exist " + countContracts + " contracts you can not delete this dealer");
        }
        loggerService.commit(LoggerLevel.WARN, "object: " + Dealer.class.getSimpleName() + "; stage: start; operation: delete; id = " + id);
        repositoryHelper.delete(dealerRepository, id);
        loggerService.commit(LoggerLevel.WARN, "object: " + Dealer.class.getSimpleName() + "; stage: finish; operation: delete; id = " + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dealer> findById(Long id) {
        return repositoryHelper.findById(dealerRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dealer> findAll(Map<String, String[]> parameterMap) {
        return repositoryHelper.findAll(dealerRepository, parameterMap, Dealer.class);
    }

    @Override
    @Transactional(readOnly = true)
    public long count(Map<String, String[]> parameterMap) {
        return repositoryHelper.count(dealerRepository, parameterMap, Dealer.class);
    }

    private void checkInputDataOnValid(Dealer entity) {
        if (!innIsValid(entity.getInn())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "inn is not valid");
            throw new IncorrectInputData("inn is not valid");
        }
        if (!nameIsValid(entity.getName())) {
            loggerService.commit(LoggerLevel.ERROR, "object: " + entity.getClass().getSimpleName() + "; operation: update/new; id = " + entity.getId() + "; problem = " + "name is not valid");
            throw new IncorrectInputData("name is not valid");
        }
    }

    private boolean innIsValid(String inn) {
        return inn.matches("[0-9]+") && inn.length() == 12;
    }

    private boolean nameIsValid(String name) {
        return StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name);
    }
}
