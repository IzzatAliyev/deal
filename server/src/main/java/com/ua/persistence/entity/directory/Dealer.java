package com.ua.persistence.entity.directory;

import com.ua.persistence.entity.BaseEntity;
import com.ua.persistence.type.DealerType;

import javax.persistence.*;

@Entity
@Table(name = "dealers")
public class Dealer extends BaseEntity {

    private String name;

    private String inn;

    @Enumerated(EnumType.STRING)
    @Column(name = "dealer_type")
    private DealerType dealerType;

    public Dealer() {
        super();
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
