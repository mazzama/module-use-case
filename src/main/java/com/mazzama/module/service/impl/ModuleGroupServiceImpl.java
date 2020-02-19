package com.mazzama.module.service.impl;

import com.mazzama.module.domain.Group;
import com.mazzama.module.domain.ModuleGroup;
import com.mazzama.module.domain.User;
import com.mazzama.module.dto.ModuleGroupDto;
import com.mazzama.module.dto.ModulesDto;
import com.mazzama.module.repository.ModuleGroupRepository;
import com.mazzama.module.repository.UserRepository;
import com.mazzama.module.service.ModuleGroupService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleGroupServiceImpl implements ModuleGroupService {

    private static final Logger LOG = LoggerFactory.getLogger(ModuleGroupServiceImpl.class);

    @Autowired
    private ModuleGroupRepository moduleGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ModulesDto findAllModuleGroup() {
        LOG.info("I am here");

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
            Group userGroup = userOptional.get().getGroups();

            List<ModuleGroup> moduleGroups = moduleGroupRepository.findAllByGroupsId(userGroup.getId());
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
                dto.setModuleOrder(moduleGroup.getOrders());
                temporaryModuleGroup.add(dto);
            }
        }
        return temporaryModuleGroup;
    }
}
