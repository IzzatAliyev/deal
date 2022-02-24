package com.ua.persistence.entity.directory;

import com.ua.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "managements")
public class Management extends BaseEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "department_management",
            joinColumns = @JoinColumn(name = "management_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id"))
    private Set<Department> departments;

    public Management() {
        super();
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
