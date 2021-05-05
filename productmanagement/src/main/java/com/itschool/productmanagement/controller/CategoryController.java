package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.exception.CategoryNameException;
import com.itschool.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "category")
    public String displayCategories(Model model){
        List<CategoryModel> categoryModelList = categoryService.displayCategories();
        model.addAttribute("categories", categoryModelList);
        return "categories";
    }

    @GetMapping(path = "add-category")
    public String viewCategoryPage(Model model){
        model.addAttribute("newCategory", new CategoryModel());
        return "category-add";
    }

    @GetMapping(path = "category-add")
    public String addCategory(@ModelAttribute CategoryModel newCategory){
        System.out.println("Add Category ->" + newCategory.getId() + " " + newCategory.getCategory());
           categoryService.addCategory(newCategory);
           return "redirect:/categories";
        }

}
