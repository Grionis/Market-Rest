package com.test.market.web.controller;

import com.test.market.domain.Category;
import com.test.market.domain.Product;
import com.test.market.domain.service.ProductServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @GetMapping("/Product/allProd")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code = 200,message = "OK")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("/Product/prodId={productId}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "8") @PathVariable("productId") int productId){
        return productServices.getProduct(productId)
                .map( product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/Category/cateId={categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        List<Product> products = productServices.getByCategory(categoryId).orElse(null);

        return products != null && !products.isEmpty() ?
                new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Product/save")
    public ResponseEntity<Product> save(@RequestBody Product product){

        return new ResponseEntity<>(productServices.save(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/Product/deleteId={productId}")
    public ResponseEntity delete(@PathVariable("productId") int productId){
        if(productServices.delete(productId)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
