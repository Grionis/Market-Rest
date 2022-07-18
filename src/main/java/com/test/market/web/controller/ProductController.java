package com.test.market.web.controller;

import com.test.market.domain.Product;
import com.test.market.domain.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("/Product/allProd")
    public List<Product> getAll(){
        return productServices.getAll();
    }

    @GetMapping("/Product/prodId={productId}")
    public Optional<Product> getProduct(@PathVariable("productId") int productId){
        return productServices.getProduct(productId);
    }

    @GetMapping("/Category/cateId={categoryId}")
    public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productServices.getByCategory(categoryId);
    }

    @PostMapping("/Product/save")
    public Product save(@RequestBody Product product){
        return productServices.save(product);
    }

    @DeleteMapping("/Product/deleteId={productId}")
    public boolean delete(@PathVariable("productId") int productId){
        return productServices.delete(productId);
    }

}
