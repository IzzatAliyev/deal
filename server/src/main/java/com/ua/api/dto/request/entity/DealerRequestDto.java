package com.ua.api.dto.request.entity;

import com.ua.api.dto.request.RequestDto;
import com.ua.persistence.type.DealerType;

public class DealerRequestDto extends RequestDto {

    private String name;
    private String inn;
    private DealerType dealerType;

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
