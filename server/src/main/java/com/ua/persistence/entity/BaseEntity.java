package com.ua.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "deletion_mark")
    private Boolean deletionMark;

    public BaseEntity() {
        this.created = new Date();
        this.updated = new Date();
        this.deletionMark = false;
    }
}
