package com.sk.product;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductUsecase;

@SpringBootTest
public class ProductJpaTest {
	
	@Autowired
	ProductUsecase productUsecase;
	
	@Transactional
	@Test
	void productRegister() throws Exception {
		//given
		Product product = Product.builder().name("test").price(BigDecimal.ONE).stockAmount(10L).build();
		//when
		productUsecase.register(product);
		Optional<Product> findProduct = productUsecase.findBy(product.getId().toString());
		//then
		Assertions.assertThat(product.getName()).isEqualTo(findProduct.get().getName());
	}
}