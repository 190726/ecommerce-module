package com.sk.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sk.product.adapter.persistence.ProductJpaAdapter;
import com.sk.product.adapter.persistence.ProductRepository;
import com.sk.product.domain.usecase.ProductPersistencePort;
import com.sk.product.domain.usecase.ProductService;
import com.sk.product.domain.usecase.ProductUsecase;

@Configuration
public class ProductContextConfiguration {
	
	@Bean
	public ProductUsecase productUsecase(ProductPersistencePort port) {
		return new ProductService(port);
	}
	
	@Bean
	public ProductPersistencePort productPersistencePort(ProductRepository repository) {
		return new ProductJpaAdapter(repository);
	}
}