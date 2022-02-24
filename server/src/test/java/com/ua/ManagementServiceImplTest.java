package com.ua;

import com.ua.persistence.entity.directory.Department;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ua.exception.EntityNotFoundException;
import com.ua.exception.IncorrectInputData;
import com.ua.persistence.entity.directory.Management;
import com.ua.persistence.type.DepartmentType;
import com.ua.service.ManagementService;
import com.ua.service.DepartmentService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ManagementServiceImplTest {

    @Autowired
    private ManagementService managementService;

    @Autowired
    private DepartmentService departmentService;

    private Map<String, String[]> parameterMap;
    private final int ITEMS_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEMS_SIZE; i++) {
            Management management = GenerationUtil.generateManagement(GenerationUtil.NAME_MANAGEMENT + i);
            managementService.create(management);
        }

        Assertions.assertEquals(ITEMS_SIZE, managementService.findAll(parameterMap).size());
    }

    @Order(1)
    @Test
    void shouldBeCreateManagementWhenNameIsNotEmpty() {
        Management management = new Management();
        management.setName("New management");
        managementService.create(management);

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(2)
    @Test
    void shouldBeReturnExceptionWhenCreatedManagementWhenNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Management management = new Management();
            managementService.create(management);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE + 1);
    }

    @Order(3)
    @Test
    void shouldBeUpdateManagementWhenManagementExistInDB() {
        Management management = managementService.findAll(parameterMap).stream().findAny().get();
        String newName = GenerationUtil.NAME_MANAGEMENT + GenerationUtil.NAME_MANAGEMENT;
        management.setName(newName);
        managementService.update(management);
        Management managementById = managementService.findById(management.getId()).get();

        Assertions.assertEquals(managementById.getName(), newName);

        Department department = new Department();
        department.setDepartmentType(DepartmentType.CORP);
        department.setName("department");
        departmentService.create(department);
        Set<Department> departments = new HashSet<>();
        departments.add(department);
        management.setDepartments(departments);
        managementService.update(management);

        managementById = managementService.findById(management.getId()).get();

        Assertions.assertEquals(managementById.getDepartments().size(), departments.size());

    }

    @Order(4)
    @Test
    void shouldBeReturnExceptionWhenUpdateManagementWhenManagementNameIsEmpty() {
        Exception exception = assertThrows(IncorrectInputData.class, () -> {
            Management management = managementService.findAll(parameterMap).stream().findAny().get();
            management.setName("");
            managementService.update(management);
        });
    }

    @Order(5)
    @Test
    public void shouldBeDeleteManagementWhenManagementIsExist() {
        Long id = managementService.findAll(parameterMap).stream().findFirst().get().getId();
        managementService.delete(id);
        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(6)
    @Test
    public void shouldBeReturnExceptionWhenDeleteManagementWhichIsNotExist() {
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            managementService.delete(100L);
        });

        verifyItemsWhenItemsIsNotEmpty(ITEMS_SIZE);
    }

    @Order(7)
    @Test
    public void shouldBeEmptyListWhenAllDataIsClear() {
        managementService.findAll(parameterMap).forEach(management -> managementService.delete(management.getId()));
        departmentService.findAll(parameterMap).forEach(department -> departmentService.delete(department.getId()));

        Assertions.assertEquals(0, managementService.findAll(parameterMap).size());
    }

    private void verifyItemsWhenItemsIsNotEmpty(int size) {
        List<Management> items = managementService.findAll(parameterMap);

        Assertions.assertTrue(items.size() != 0);
        Assertions.assertEquals(size, items.size());
    }
}
