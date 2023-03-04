package com.sk.product.adapter.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductPersistencePort;

public class ProductInMemoryAdapter implements ProductPersistencePort {
	
	Map<String, Product> persistenceMap = new HashMap<>();

	@Override
	public Product save(Product product) {
		return persistenceMap.put(product.getId().toString(), product);
	}

	@Override
	public Optional<Product> findBy(String id) {
		return Optional.ofNullable(persistenceMap.get(id.toString()));
	}

	@Override
	public List<Product> findAll() {
		return new ArrayList<>(persistenceMap.values());
	}
}