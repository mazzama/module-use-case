package com.mazzama.module.domain;

import lombok.*;

import javax.persistence.*;

@Entity
//@Builder
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    public User() {}
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}