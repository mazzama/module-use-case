package com.mazzama.module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ModulesDto {

    @JsonProperty("modules")
    private List<ModuleGroupDto> modules;
}
