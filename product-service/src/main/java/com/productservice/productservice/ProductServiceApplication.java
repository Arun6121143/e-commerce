package com.productservice.productservice;

import com.productservice.productservice.product.Product;
import com.productservice.productservice.product.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
@EnableEurekaServer
public class ProductServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

//	@Autowired
//	private ProductRepository productRepository;
//	@PostConstruct
//	public void addProducts(){
//		for(int i=0;i<50;i++){
//			Product product = new Product(UUID.randomUUID(),"T-SHIRT",5,300+i);
//			productRepository.save(product);
//		}
// }
}