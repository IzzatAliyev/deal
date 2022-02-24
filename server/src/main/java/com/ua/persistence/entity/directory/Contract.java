package com.ua.persistence.entity.directory;

import com.ua.persistence.entity.BaseEntity;
import com.ua.persistence.type.ContractType;

import javax.persistence.*;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {

    private String name;

    @OneToOne
    private Department department;

    @OneToOne
    private Dealer dealer;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type")
    private ContractType contractType;

    public Contract() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
}
