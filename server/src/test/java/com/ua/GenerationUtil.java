package com.ua;

import com.ua.persistence.entity.directory.*;
import com.ua.persistence.entity.user.Personal;
import com.ua.persistence.type.ContractType;
import com.ua.persistence.type.DepartmentType;
import com.ua.persistence.type.DealerType;
import com.ua.persistence.type.RoleType;

import java.util.Date;

public final class GenerationUtil {

    public static final String NAME_DEPARTMENT = "test department";
    public static final String NAME_DEALER = "test dealer";
    public static final String NAME_CONTRACT = "test contract";
    public static final String NAME_MANAGEMENT = "test management";

    private GenerationUtil() {
    }

    public static Personal generatePersonal(String firstName, String lastName, String email, String password) {
        Personal personal = new Personal();
        personal.setBirthDay(new Date());
        personal.setRoleType(RoleType.ROLE_SELLER_MANAGER);
        personal.setEnabled(true);
        personal.setLastName(lastName);
        personal.setFirstName(firstName);
        personal.setEmail(email);
        personal.setPassword(password);
        return personal;
    }

    public static Management generateManagement(String name) {
        Management management = new Management();
        management.setName(name);
        return management;
    }

    public static Department generateDepartment(String name) {
        Department department = new Department();
        department.setName(name);
        department.setDepartmentType(DepartmentType.CORP);
        return department;
    }

    public static Dealer generateDealer(String name, String inn, DealerType dealerType) {
        Dealer dealer = new Dealer();
        dealer.setName(name);
        dealer.setInn(inn);
        dealer.setDealerType(dealerType);
        return dealer;
    }

    public static Contract generateContract(String name, Department department, Dealer dealer) {
        Contract contract = new Contract();
        contract.setName(name);
        contract.setDepartment(department);
        contract.setDealer(dealer);
        if (dealer.getDealerType() == DealerType.CLIENT) {
            contract.setContractType(ContractType.CLIENT_CONTRACT);
        } else {
            contract.setContractType(ContractType.SUPPLIER_CONTRACT);
        }
        return contract;
    }
}
