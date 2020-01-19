package com.mazzama.module.service.impl;

import com.mazzama.module.domain.Module;
import com.mazzama.module.repository.ModuleRepository;
import com.mazzama.module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModuleRepository categoryRepository;

    @Override
    public List<Module> findAllCategories() {
        return categoryRepository.findAll();
    }
}
