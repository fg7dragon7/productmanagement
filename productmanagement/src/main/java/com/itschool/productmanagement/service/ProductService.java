package com.itschool.productmanagement.service;

import com.itschool.productmanagement.entities.ProductModel;
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

    public void addProduct(ProductModel product){
        //We can validate firstName /  lastName

        productRepository.save(product);
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
