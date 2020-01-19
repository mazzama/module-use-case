package com.mazzama.module.controller;

import com.mazzama.module.domain.ModuleGroup;
import com.mazzama.module.dto.ModuleGroupDto;
import com.mazzama.module.dto.ModulesDto;
import com.mazzama.module.service.ModuleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/modulegroup")
public class ModuleGroupController {

    @Autowired
    private ModuleGroupService moduleGroupService;

    @GetMapping
    public ResponseEntity<ModulesDto> findAllModuleGroup() {
        return ResponseEntity.ok(moduleGroupService.findAllModuleGroup());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ModulesDto> findModuleGroupsByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(moduleGroupService.findModuleGroupByUserId(userId));
    }
}
