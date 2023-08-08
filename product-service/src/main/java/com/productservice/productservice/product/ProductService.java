package com.productservice.productservice.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProducts(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProducts(UUID productId) {
        Product product = productRepository.findById(productId).get();
        product.setProductQuantity(product.getProductQuantity()-1);
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }
}