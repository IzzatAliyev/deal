package com.ua.api.dto.response.entity;

import com.ua.api.dto.response.ResponseDto;
import com.ua.persistence.entity.directory.Dealer;
import com.ua.persistence.type.DealerType;

public class DealerResponseDto extends ResponseDto {

    private String name;
    private String inn;
    private DealerType dealerType;
    private long countOfContracts;

    public DealerResponseDto() {
    }

    public DealerResponseDto(Dealer dealer) {
        setId(dealer.getId());
        setCreated(dealer.getCreated());
        setUpdated(dealer.getUpdated());
        setDeletionMark(dealer.getDeletionMark());
        this.name = dealer.getName();
        this.inn = dealer.getInn();
        this.dealerType = dealer.getDealerType();
    }

    public long getCountOfContracts() {
        return countOfContracts;
    }

    public void setCountOfContracts(long countOfContracts) {
        this.countOfContracts = countOfContracts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public DealerType getDealerType() {
        return dealerType;
    }

    public void setDealerType(DealerType dealerType) {
        this.dealerType = dealerType;
    }
}
