package com.sk.integration;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sk.product.adapter.persistence.ProductInMemoryAdapter;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.entity.Product.Category;
import com.sk.product.domain.usecase.ProductService;
import com.sk.product.domain.usecase.ProductUsecase;

public class OrderIntegrationTest {
	
	private ProductUsecase productUsecase;

	@BeforeEach
	public void init() {

		productUsecase = new ProductService(new ProductInMemoryAdapter());
		
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
	}
	
	@Test
	void order() throws Exception {
		//제품등록
		productUsecase.findAll();
		//제품조회
		//주문생성
		//주문결제
		//배달완료
	}

}
