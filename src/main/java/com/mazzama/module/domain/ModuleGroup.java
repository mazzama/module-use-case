package com.mazzama.module.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "module_group")
public class ModuleGroup extends BaseEntity {

    public ModuleGroup() {}
    private Long orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id", nullable = false)
    private Group groups;
}
