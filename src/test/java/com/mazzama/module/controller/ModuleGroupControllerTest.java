package com.mazzama.module.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mazzama.module.domain.Module;
import com.mazzama.module.domain.ModuleGroup;
import com.mazzama.module.dto.ModuleGroupDto;
import com.mazzama.module.dto.ModulesDto;
import com.mazzama.module.service.ModuleGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = ModuleGroupController.class)
@ActiveProfiles("TEST")
class ModuleGroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModuleGroupService moduleGroupService;

    List<ModuleGroup> moduleGroups = new ArrayList<>();
    List<Module> modules = new ArrayList<>();
    

    @BeforeEach
    void setUp() {
        Module promo = new Module();
        promo.setName("Promo");

        Module category = new Module();
        category.setName("Category");

        Module flashSale = new Module();
        flashSale.setName("Flash Sale");

        Module history = new Module();
        history.setName("History");

        Module news = new Module();
        news.setName("News");

        modules = Arrays.asList(promo, category, flashSale, history, news);

        Long index = 1L;
        for (Module module : modules) {
            ModuleGroup moduleGroup = new ModuleGroup();
            moduleGroup.setOrders(index);
            moduleGroup.setModule(module);
            moduleGroups.add(moduleGroup);
            index++;
        }
    }

    @Test
    void whenInputUserIdValid_thenReturns200() throws Exception {
        Long userId = 1L;
        ModulesDto result = new ModulesDto();
        result.setModules(convertToDtoUtil(moduleGroups));

        when(moduleGroupService.findModuleGroupByUserId(userId)).thenReturn(result);

        MockHttpServletResponse response = mockMvc
                .perform(get("/api/modulegroup/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    private List<ModuleGroupDto> convertToDtoUtil(List<ModuleGroup> moduleGroups) {
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