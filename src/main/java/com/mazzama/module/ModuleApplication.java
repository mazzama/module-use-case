package com.mazzama.module;

import com.mazzama.module.domain.Group;
import com.mazzama.module.domain.Module;
import com.mazzama.module.domain.ModuleGroup;
import com.mazzama.module.domain.User;
import com.mazzama.module.repository.GroupRepository;
import com.mazzama.module.repository.ModuleGroupRepository;
import com.mazzama.module.repository.ModuleRepository;
import com.mazzama.module.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Slf4j
public class ModuleApplication {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ModuleGroupRepository moduleGroupRepository;

    public static void main(String[] args) {
        SpringApplication.run(ModuleApplication.class, args);
    }

    @PostConstruct
    public void populateData() {
        log.info("Preparing data set");
        if (moduleRepository.findAll().size() == 0) {
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

            List<Module> modules = new ArrayList<>();
            modules.add(promo);
            modules.add(category);
            modules.add(flashSale);
            modules.add(history);
            modules.add(news);

            log.info("Saving all data");
            moduleRepository.saveAll(modules);
        } else {
            log.info("No data saved");
        }

        List<Group> groups = groupRepository.findAll();
        if (groups.size() == 0) {
            Group newcomerUserGroup = new Group();
            newcomerUserGroup.setName("Newcomer");

            Group oldUserGroup = new Group();
            oldUserGroup.setName("Old Group");

            User user1 = new User();
            user1.setName("Azzam");
            user1.setGroup(oldUserGroup);

            User user2 = new User();
            user2.setName("Abdul");
            user2.setGroup(newcomerUserGroup);

            newcomerUserGroup.getUsers().add(user1);
            oldUserGroup.getUsers().add(user2);
            log.info("Saving new user group");
            groupRepository.saveAll(Arrays.asList(oldUserGroup, newcomerUserGroup));
        }

        if (groups.size() > 0 && moduleGroupRepository.findAll().size() == 0) {
            List<Module> modules = moduleRepository.findAll();

            for (Group group: groups) {
                for (int i=0; i < modules.size(); i++) {
                    ModuleGroup moduleGroup = new ModuleGroup();
                    moduleGroup.setGroup(group);
                    if (group.getName().equalsIgnoreCase("Old Group")) {
                        moduleGroup.setModule(modules.get(i));
                        log.info("Module name: {}", i);
                    } else {
                        moduleGroup.setModule(modules.get(modules.size() - 1 - i));
                        log.info("Module name: {}", modules.size() - 1 - i);
                    }
                    moduleGroup.setOrder(Long.valueOf(i + 1));
                    log.info("Save new modulegroup with group: {} and order {}", group.getName(), moduleGroup.getOrder());
                    moduleGroupRepository.save(moduleGroup);
                }
            }
        }
    }


}
