package com.mazzama.module.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group extends BaseEntity {

    public Group() {}
    private String name;

    @OneToMany(
            mappedBy = "groups",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(
            mappedBy = "groups",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ModuleGroup> moduleGroups = new ArrayList<>();
}
