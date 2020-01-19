package com.mazzama.module.domain;

import lombok.*;

import javax.persistence.*;

@Entity
//@Builder
@Getter
@Setter
@Table(name = "module_group")
public class ModuleGroup extends BaseEntity {

    public ModuleGroup() {}
    private Long order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}
