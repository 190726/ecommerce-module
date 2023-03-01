package com.sk.product.domain.usecase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sk.product.domain.entity.Product;

public interface ProductUsecase {

	void register(Product product);

	Optional<Product> findBy(UUID id);

	List<Product> findAll();

} 