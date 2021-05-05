package com.itschool.productmanagement.controller;

import com.itschool.productmanagement.entities.ProductModel;
import com.itschool.productmanagement.exception.ProductDescriptionException;
import com.itschool.productmanagement.exception.ProductNameException;
import com.itschool.productmanagement.exception.ProductPriceException;
import com.itschool.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "product")
    public String displayProducts(Model model) {
        List<ProductModel> productModelList = productService.displayProducts();
        model.addAttribute("name", "Afisare produse");
        model.addAttribute("products", productModelList);
        return "products";
    }

    @GetMapping(path = "add-product")
    public String viewProductPage(Model model) {
        model.addAttribute("newProduct", new ProductModel());
        return "product-add";
    }

    @GetMapping(path = "edit-product")
    public String viewProductPage(@RequestParam int id, Model model) {
        ProductModel foundProduct =  productService.findById(id);
        model.addAttribute("editProduct", foundProduct);
        return "product-edit";
    }


    @GetMapping("product-edit")
    public String editProduct(@ModelAttribute ProductModel editedProduct){
        productService.edit(editedProduct);
        return  "redirect:/product";
    }


    @GetMapping(path = "product-add")
    public String addProduct(@ModelAttribute ProductModel newProduct,Model model) {
        System.out.println("Add product ->" + newProduct.getProductName() + " " + newProduct.getDescription() +
                " " + newProduct.getPrice());
        try {
            productService.addProduct(newProduct);
            return "redirect:/product";
        }catch (ProductNameException productNameException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessage", productNameException.getMessage());
            return "product-add";
        }catch (ProductDescriptionException productDescriptionException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessage", productDescriptionException.getMessage());
            return "product-add";
        }catch (ProductPriceException productPriceException) {
            model.addAttribute("newProduct", newProduct);
            model.addAttribute("errorMessage", productPriceException.getMessage());
            return "product-add";
        }
    }

    @GetMapping(path = "deleteById")
    public String deleteById(@RequestParam("id") int id) {
        System.out.println("Deleting product with id:" + id);
        productService.deleteProduct(id);
        return "redirect:/product";
    }

    @GetMapping(path = "findById")
    public String findById(@RequestParam("id") int id, Model model) {
        System.out.println("Searching for product: id:" + id);
        ProductModel productModel = productService.findById(id);
        model.addAttribute("foundProduct", productModel);
        return "view-product";
    }


}
