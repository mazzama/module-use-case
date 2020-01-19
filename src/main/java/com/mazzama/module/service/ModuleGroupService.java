package com.mazzama.module.service;

import com.mazzama.module.dto.ModulesDto;

public interface ModuleGroupService {
    ModulesDto findAllModuleGroup();

    ModulesDto findModuleGroupByUserId(Long userId);
}
