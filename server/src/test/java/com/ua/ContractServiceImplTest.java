package com.ua;

import com.ua.persistence.entity.directory.Contract;
import com.ua.persistence.entity.directory.Department;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ua.exception.EntityNotFoundException;
import com.ua.exception.IncorrectInputData;
import com.ua.persistence.entity.directory.Dealer;
import com.ua.persistence.type.ContractType;
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
public class ContractServiceImplTest {

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
            Department department = GenerationUtil.generateDepartment(GenerationUtil.NAME_DEPARTMENT + i);
            departmentService.create(department);
            Dealer dealer = GenerationUtil.generateDealer(GenerationUtil.NAME_DEALER + 1, "11111111111" + i, DealerType.CLIENT);
            dealerService.create(dealer);
            Contract contract = GenerationUtil.generateContract(GenerationUtil.NAME_CONTRACT + 1, department, dealer);
            contractService.create(contract);
        }

        Assertions.assertEquals(ITEMS_SIZE, contractService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateContractWhenNameIsNotEmpty() {
        Contract contract = new Contract();
        contract.setName("New contract");
        contract.setDepartment(departmentService.findAll(parameterMap).stream().findAny().get());
        contract.setDealer(dealerService.findAll(parameterMap).stream().findAny().get());
        contractService.create(contract);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedContractWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Contract contract = GenerationUtil.generateContract(GenerationUtil.NAME_CONTRACT + 1,
                    departmentService.findAll(parameterMap).stream().findAny().get(),
                    dealerService.findAll(parameterMap).stream().findAny().get());
            contract.setName("");
            contractService.create(contract);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateContractWhenContractExistInDB() {
        Contract contract = contractService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_CONTRACT + GenerationUtil.NAME_CONTRACT;
        contract.setName(newName);
        contract.setContractType(ContractType.CLIENT_CONTRACT);
        contractService.update(contract);
        Contract contractById = contractService.findById(contract.getId()).get();

        Assertions.assertEquals(contractById.getName(), newName);
        Assertions.assertEquals(contractById.getContractType(), ContractType.CLIENT_CONTRACT);

        contract.setContractType(ContractType.SUPPLIER_CONTRACT);
        contractService.update(contract);
        contractById = contractService.findById(contract.getId()).get();

        Assertions.assertEquals(contractById.getContractType(), ContractType.SUPPLIER_CONTRACT);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateContractWhenContractNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Contract contract = contractService.findAll(parameterMap).stream().findAny().get();
            contract.setName("");
            contractService.update(contract);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteContractWhenContractIsExist() {
        Contract contract = contractService.findAll(parameterMap).stream().findAny().get();
        contractService.delete(contract.getId());
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteContractAndItDoesNotIsExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            contractService.delete(100L);
        });
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        contractService.findAll(parameterMap).forEach(contract -> contractService.delete(contract.getId()));
        departmentService.findAll(parameterMap).forEach(department -> departmentService.delete(department.getId()));
        dealerService.findAll(parameterMap).forEach(dealer -> dealerService.delete(dealer.getId()));

        Assertions.assertEquals(0, contractService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Contract> items = contractService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
