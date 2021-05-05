package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "category")
    public String displayCategories(Model model){
        List<CategoryModel> categoryModelList = categoryService.displayCategories();
        model.addAttribute("name", "Afisare category");
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
        System.out.println("Add Category ->" + newCategory.getId() + " " + newCategory.getName());
           categoryService.addCategory(newCategory);
           return "redirect:/categories";
        }

    @GetMapping(path = "edit-category")
    public String viewCategoryPage(@RequestParam int id, Model model) {
        CategoryModel foundCategory =  categoryService.findById(id);
        model.addAttribute("editCategory", foundCategory);
        return "category-edit";
    }


    @GetMapping("category-edit")
    public String editCategory(@ModelAttribute CategoryModel editedCategory){
        categoryService.edit(editedCategory);
        return  "redirect:/category";
    }

    @GetMapping(path = "deleteById")
    public String deleteById(@RequestParam("id") int id) {
        System.out.println("Deleting category with id:" + id);
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }

    @GetMapping(path = "findById")
    public String findById(@RequestParam("id") int id, Model model) {
        System.out.println("Searching for category: id:" + id);
        CategoryModel categoryModel = categoryService.findById(id);
        model.addAttribute("foundCategory", categoryModel);
        return "view-category";
    }

}
