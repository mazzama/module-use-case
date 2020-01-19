package com.mazzama.module;

import com.mazzama.module.domain.Module;
import com.mazzama.module.repository.ModuleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class ModuleApplication {

    @Autowired
    private ModuleRepository moduleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ModuleApplication.class, args);
    }

    @PostConstruct
    public void prepareData() {
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
    }
}
