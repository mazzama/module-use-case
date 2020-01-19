package com.mazzama.module.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    public User() {}
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id")
    private Group groups;
}
