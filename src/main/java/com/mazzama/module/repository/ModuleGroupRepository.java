package com.mazzama.module.repository;

import com.mazzama.module.domain.ModuleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleGroupRepository extends JpaRepository<ModuleGroup, Long> {
    List<ModuleGroup> findAllByGroupsId(Long groupId);
}
