package com.mazzama.module.controller;

import com.mazzama.module.domain.Module;
import com.mazzama.module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Module>> findAllCategories() {
        return ResponseEntity.ok(categoryService.findAllCategories());
    }
}
