package com.mazzama.module.domain;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
//@Builder
@Table(name = "module")
public class Module extends BaseEntity {

    public Module() {}

    private String name;

    @OneToMany(
            mappedBy = "module",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ModuleGroup> moduleGroups;

}
