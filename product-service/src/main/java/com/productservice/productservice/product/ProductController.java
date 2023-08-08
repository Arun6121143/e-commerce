package com.productservice.productservice.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // only admin can access this url
    @PostMapping("/save")
    public ResponseEntity<Product>saveProducts(@RequestBody  Product product){
       Product savedProduct = productService.saveProducts(product);
       return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }

    //accessible by both user and admin
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
//    @GetMapping("/getProductsUsers")
//    public UserProducts getProducts(){
//        UserProducts userProducts = new UserProducts();
//        userProducts.setAllProducts(productService.getAllProducts());
//        return userProducts;
//    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId){
        Product product = productService.getProducts(productId);
        return product;
    }
}