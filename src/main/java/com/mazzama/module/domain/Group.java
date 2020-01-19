package com.mazzama.module.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
//@Builder
@Getter
@Setter
@Table(name = "group")
public class Group extends BaseEntity {

    public Group() {}
    private String name;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<User> users;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ModuleGroup> moduleGroups;
}
