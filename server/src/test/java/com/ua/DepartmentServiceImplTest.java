package com.ua;

import com.ua.persistence.entity.directory.Contract;
import com.ua.persistence.entity.directory.Department;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ua.exception.IncorrectInputData;
import com.ua.persistence.entity.directory.Dealer;
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
class DepartmentServiceImplTest {

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
        }

        Assertions.assertEquals(ITEMS_SIZE, departmentService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateDepartmentWhenNameIsNotEmpty() {
        Department department = new Department();
        department.setName("New department");
        departmentService.create(department);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedDepartmentWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Department department = new Department();
            departmentService.create(department);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateDepartmentWhenDepartmentExistInDB() {
        Department department = departmentService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_DEPARTMENT + GenerationUtil.NAME_DEPARTMENT;
        department.setName(newName);
        departmentService.update(department);
        Department departmentById = departmentService.findById(department.getId()).get();

        Assertions.assertEquals(departmentById.getName(), newName);
    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateDepartmentWhenDepartmentNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Department department = departmentService.findAll(parameterMap).stream().findAny().get();
            department.setName("");
            departmentService.update(department);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteDepartmentWhenDepartmentContractDoesNotExist() {
        Long id = departmentService.findAll(parameterMap).stream().findFirst().get().getId();
        departmentService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeNotDeleteDepartmentWhenDepartmentContractIsExist() {
        Department department = departmentService.findAll(parameterMap).stream().findAny().get();
        Dealer dealer = GenerationUtil.generateDealer(GenerationUtil.NAME_DEALER, "111111111111", DealerType.CLIENT);
        dealerService.create(dealer);
        Contract contract = GenerationUtil.generateContract(GenerationUtil.NAME_CONTRACT, department, dealer);
        contractService.create(contract);
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            departmentService.delete(department.getId());
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
        List<Department> items = departmentService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
