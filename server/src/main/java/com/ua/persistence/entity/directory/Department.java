package com.ua.persistence.entity.directory;

import com.ua.persistence.entity.BaseEntity;
import com.ua.persistence.type.DepartmentType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "department_type")
    private DepartmentType departmentType;

    @ManyToMany
    @JoinTable(
            name = "department_management",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "management_id"))
    private Set<Management> managements;

    public Department() {
        super();
    }

    public Set<Management> getManagements() {
        return managements;
    }

    public void setManagements(Set<Management> managements) {
        this.managements = managements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }
}
