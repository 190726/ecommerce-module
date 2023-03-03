package com.sk;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sk.product.domain.entity.Product;
import com.sk.product.domain.entity.Product.Category;
import com.sk.product.domain.usecase.ProductUsecase;

@SpringBootApplication
public class Main {
	
	@Autowired ProductUsecase productUsecase;
	
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    
    @Bean
    public ApplicationRunner applicationRunner() {
    	return args -> {
    		Product product1 = Product.builder().name("상품1").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
    		Product product2 = Product.builder().name("상품2").price(BigDecimal.TEN).stockAmount(100L).category(Category.ELECTRIC).build();
    		Product product3 = Product.builder().name("상품3").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
    		Product product4 = Product.builder().name("상품4").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
    		Product product5 = Product.builder().name("상품5").price(BigDecimal.ONE).stockAmount(100L).category(Category.FOOD).build();
    		Product product6 = Product.builder().name("상품6").price(BigDecimal.ONE).stockAmount(100L).category(Category.FOOD).build();
    		Product product7 = Product.builder().name("상품7").price(BigDecimal.TEN).stockAmount(100L).category(Category.ETC).build();
    		Product product8 = Product.builder().name("상품8").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
    		Product product9 = Product.builder().name("상품9").price(BigDecimal.TEN).stockAmount(100L).category(Category.FOOD).build();
            //when
            productUsecase.register(product1);
            productUsecase.register(product2);
            productUsecase.register(product3);
            productUsecase.register(product4);
            productUsecase.register(product5);
            productUsecase.register(product6);
            productUsecase.register(product7);
            productUsecase.register(product8);
            productUsecase.register(product9);
    	};
    }

}