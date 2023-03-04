package com.sk.product.domain.usecase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sk.product.domain.entity.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUsecase {
	
	private final ProductPersistencePort productPersistencePort;

	@Override
	public void register(Product product) {
		productPersistencePort.save(product);
	}

	@Override
	public Optional<Product> findBy(UUID id) {
		return productPersistencePort.findBy(id);
	}

	@Override
	public List<Product> findAll() {
		return productPersistencePort.findAll();
	}

}
