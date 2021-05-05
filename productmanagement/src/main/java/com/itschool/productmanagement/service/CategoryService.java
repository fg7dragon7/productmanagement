package com.itschool.productmanagement.service;

import com.itschool.productmanagement.entities.CategoryModel;
import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.exception.CategoryNameException;
import com.itschool.productmanagement.exception.ProductDescriptionException;
import com.itschool.productmanagement.exception.ProductNameException;
import com.itschool.productmanagement.exception.ProductPriceException;
import com.itschool.productmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> displayCategories(){
        List<CategoryModel> categoryModelList = categoryRepository.findAll();
        System.out.println("Displaying products");
        return categoryModelList;
    }

    public void addCategory(CategoryModel categoryModel){

        if (categoryModel.getCategory().length() <= 3){
            RuntimeException exception = new CategoryNameException("Numele Categoriei este prea scurt");
            throw exception;
        }else {
            categoryRepository.save(categoryModel);
        }
    }

}
