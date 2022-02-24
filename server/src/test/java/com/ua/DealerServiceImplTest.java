package com.ua;

import com.ua.persistence.entity.directory.Dealer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ua.exception.IncorrectInputData;
import com.ua.persistence.entity.directory.Contract;
import com.ua.persistence.entity.directory.Department;
import com.ua.persistence.type.DealerType;
import com.ua.service.ContractService;
import com.ua.service.DepartmentService;
import com.ua.service.DealerService;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class DealerServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private ContractService contractService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Dealer dealer = GenerationUtil.generateDealer(GenerationUtil.NAME_DEALER + i, "11111111111" + i, DealerType.CLIENT);
            dealerService.create(dealer);
        }

        Assertions.assertEquals(ITEMS_SIZE, dealerService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateDealerWhenNameIsNotEmpty() {
        Dealer dealer = new Dealer();
        dealer.setName("New dealer");
        dealer.setDealerType(DealerType.CLIENT);
        dealer.setInn("123333333333");
        dealerService.create(dealer);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedDealerWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Dealer dealer = new Dealer();
            dealer.setDealerType(DealerType.CLIENT);
            dealer.setInn("123333333333");
            dealerService.create(dealer);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateDealerWhenDealerExistInDB() {
        Dealer dealer = dealerService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_DEALER + GenerationUtil.NAME_DEALER;
        dealer.setName(newName);
        dealerService.update(dealer);
        Dealer dealerById = dealerService.findById(dealer.getId()).get();

        Assertions.assertEquals(dealerById.getName(), newName);

        dealer.setDealerType(DealerType.SUPPLIER);
        dealerService.update(dealer);
        dealerById = dealerService.findById(dealer.getId()).get();

        Assertions.assertEquals(dealerById.getDealerType(), DealerType.SUPPLIER);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateDealerWhenDealerNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Dealer dealer = dealerService.findAll(parameterMap).stream().findAny().get();
            dealer.setName("");
            dealerService.update(dealer);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteDealerWhenContractDoesNotExist() {
        Long id = dealerService.findAll(parameterMap).stream().findFirst().get().getId();
        dealerService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeNotDeleteDealerWhenContractIsExist() {
        Department department = GenerationUtil.generateDepartment(GenerationUtil.NAME_DEPARTMENT);
        departmentService.create(department);
        Dealer dealer = dealerService.findAll(parameterMap).stream().findAny().get();
        Contract contract = GenerationUtil.generateContract(GenerationUtil.NAME_CONTRACT, department, dealer);
        contractService.create(contract);
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            dealerService.delete(dealer.getId());
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        contractService.findAll(parameterMap).forEach(contract -> contractService.delete(contract.getId()));
        departmentService.findAll(parameterMap).forEach(department -> departmentService.delete(department.getId()));
        dealerService.findAll(parameterMap).forEach(dealer -> dealerService.delete(dealer.getId()));

        Assertions.assertEquals(0, departmentService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Dealer> items = dealerService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
