package com.sk.product.domain.usecase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sk.product.domain.entity.Product;

public interface ProductPersistencePort {

	Product save(Product product);

	Optional<Product> findBy(String id);

	List<Product> findAll();

}
