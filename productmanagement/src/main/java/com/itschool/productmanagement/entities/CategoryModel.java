package com.itschool.productmanagement.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<ProductModel> productModelList;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProductModel> getProductModelList() {
        return productModelList;
    }

    public void setProductModelList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
