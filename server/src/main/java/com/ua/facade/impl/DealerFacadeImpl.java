package com.ua.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import com.ua.api.dto.request.entity.DealerRequestDto;
import com.ua.api.dto.response.entity.DealerResponseDto;
import com.ua.facade.DealerFacade;
import com.ua.persistence.entity.directory.Dealer;
import com.ua.service.DealerService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DealerFacadeImpl implements DealerFacade {

    private final DealerService dealerService;

    public DealerFacadeImpl(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @Override
    public void create(DealerRequestDto dealerRequestDto) {
        Dealer dealer = new Dealer();
        dealer.setName(dealerRequestDto.getName());
        dealer.setInn(dealerRequestDto.getInn());
        dealer.setDealerType(dealerRequestDto.getDealerType());
        dealerService.create(dealer);
    }

    @Override
    public void update(DealerRequestDto dealerRequestDto, Long id) {
        Dealer dealer = dealerService.findById(id).get();
        dealer.setUpdated(new Date());
        dealer.setName(dealerRequestDto.getName());
        dealer.setInn(dealerRequestDto.getInn());
        dealer.setDealerType(dealerRequestDto.getDealerType());
        dealerService.update(dealer);
    }

    @Override
    public void delete(Long id) {
        dealerService.delete(id);
    }

    @Override
    public DealerResponseDto findById(Long id) {
        return new DealerResponseDto(dealerService.findById(id).get());
    }

    @Override
    public long count(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return dealerService.count(parameterMap);
    }

    @Override
    public List<DealerResponseDto> findAll(WebRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<Dealer> all = dealerService.findAll(parameterMap);
        List<DealerResponseDto> items = all.stream().map(DealerResponseDto::new).collect(Collectors.toList());
        return items;
    }
}
