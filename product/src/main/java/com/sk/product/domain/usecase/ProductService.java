package com.sk.product.domain.usecase;

import java.util.UUID;

import com.sk.product.domain.entity.Product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService implements ProductUsecase {
	
	private final ProductPersistencePort productPersistencePort;

	@Override
	public void register(Product product) {
		productPersistencePort.save(product);
	}

	@Override
	public Product findBy(UUID id) {
		return productPersistencePort.findBy(id);
	}

}
