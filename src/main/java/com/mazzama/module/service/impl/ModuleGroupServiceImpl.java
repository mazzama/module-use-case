package com.mazzama.module.service.impl;

import com.mazzama.module.domain.Group;
import com.mazzama.module.domain.ModuleGroup;
import com.mazzama.module.domain.User;
import com.mazzama.module.dto.ModuleGroupDto;
import com.mazzama.module.dto.ModulesDto;
import com.mazzama.module.repository.ModuleGroupRepository;
import com.mazzama.module.repository.UserRepository;
import com.mazzama.module.service.ModuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleGroupServiceImpl implements ModuleGroupService {

    @Autowired
    private ModuleGroupRepository moduleGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ModulesDto findAllModuleGroup() {
        List<ModuleGroup> moduleGroups = moduleGroupRepository.findAll();
        List<ModuleGroupDto> moduleGroupDtos = convertToDto(moduleGroups);
        ModulesDto result = new ModulesDto();
        result.setModules(moduleGroupDtos);
        return result;
    }

    @Override
    public ModulesDto findModuleGroupByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<ModuleGroupDto> moduleGroupDtos;

        if (userOptional.isPresent()) {
            Group userGroup = userOptional.get().getGroup();

            List<ModuleGroup> moduleGroups = moduleGroupRepository.findAllByGroupId(userGroup.getId());
            moduleGroupDtos = convertToDto(moduleGroups);
        } else {
            throw new EntityNotFoundException("Tidak ditemukan");
        }

        ModulesDto result = new ModulesDto();
        result.setModules(moduleGroupDtos);
        return result;
    }

    private List<ModuleGroupDto> convertToDto(List<ModuleGroup> moduleGroups) {
        List<ModuleGroupDto> temporaryModuleGroup = new ArrayList<>();
        if (moduleGroups.size() > 0) {
            for (ModuleGroup moduleGroup: moduleGroups) {
                ModuleGroupDto dto = new ModuleGroupDto();
                dto.setModuleName(moduleGroup.getModule().getName());
                dto.setModuleOrder(moduleGroup.getOrder());
                temporaryModuleGroup.add(dto);
            }
        }
        return temporaryModuleGroup;
    }
}
