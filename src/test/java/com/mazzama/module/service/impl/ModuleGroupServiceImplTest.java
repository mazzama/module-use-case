package com.mazzama.module.service.impl;

import com.mazzama.module.domain.Group;
import com.mazzama.module.domain.Module;
import com.mazzama.module.domain.ModuleGroup;
import com.mazzama.module.domain.User;
import com.mazzama.module.dto.ModulesDto;
import com.mazzama.module.repository.ModuleGroupRepository;
import com.mazzama.module.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("TEST")
class ModuleGroupServiceImplTest {

    @InjectMocks
    private ModuleGroupServiceImpl moduleGroupService;

    @Mock
    private ModuleGroupRepository moduleGroupRepository;

    @Mock
    private UserRepository userRepository;

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
    public void whenFindModuleGroupByUserId_thenReturnModuleGroupList() {
        Long userId = 1L;
        Long groupId = 1L;

        Group group = new Group();
        group.setId(groupId);
        User user = new User();
        user.setId(userId);
        user.setName("Azzam");
        user.setGroups(group);
        Optional userOptional = Optional.of(user);

        doReturn(userOptional).when(userRepository).findById(userId);
        doReturn(moduleGroups).when(moduleGroupRepository).findAllByGroupsId(groupId);
        ModulesDto actual = moduleGroupService.findModuleGroupByUserId(1L);

        assertNotNull(actual);
        assertEquals(actual.getModules().size(), moduleGroups.size());
    }

    @Test()
    public void whenFindModuleGroupByUserIdNotFound_thenReturnException() {
        Long userId = 10L;
        Optional userOptional = Optional.empty();

        doReturn(userOptional).when(userRepository).findById(userId);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            moduleGroupService.findModuleGroupByUserId(userId);
        });

        String expectedMessage = "Tidak ditemukan";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}