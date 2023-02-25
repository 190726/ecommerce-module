package com.sk.product.domain.usecase;

import java.util.UUID;

import com.sk.product.domain.entity.Product;

public interface ProductPersistencePort {

	Product save(Product product);

	Product findBy(UUID id);

}
