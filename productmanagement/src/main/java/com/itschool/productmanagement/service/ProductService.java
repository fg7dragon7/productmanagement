package com.itschool.productmanagement.service;

import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.exception.ProductDescriptionException;
import com.itschool.productmanagement.exception.ProductNameException;
import com.itschool.productmanagement.exception.ProductPriceException;
import com.itschool.productmanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> displayProducts(){
        List<ProductModel> productModelList = productRepository.findAll();
        System.out.println("Displaying products");
        return productModelList;
    }

    public void addProduct(ProductModel productModel){

        if (productModel.getProductName().length() <= 3){
            RuntimeException exception = new ProductNameException("Numele Produsului este prea scurt");
            throw exception;
        }else if (productModel.getDescription().length() <= 5){
            RuntimeException exception = new ProductDescriptionException("Descrierea Produsului este prea scurta");
            throw exception;
        }else if (productModel.getPrice() <= 0){
            RuntimeException exception = new ProductPriceException("Pretul Produsului este prea mic");
            throw exception;
        }else{
            productRepository.save(productModel);
        }
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public ProductModel findById(int id){
        Optional<ProductModel> optionalAuthorModel = productRepository.findById(id);
        return optionalAuthorModel.get();
    }

    public void edit(ProductModel editedProduct) {
        productRepository.save(editedProduct);
    }
}
