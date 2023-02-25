package com.sk.product.adapter.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductPersistencePort;

public class ProductPersistenceAdapter implements ProductPersistencePort {
	
	Map<String, Product> persistenceMap = new HashMap<>();

	@Override
	public Product save(Product product) {
		return persistenceMap.put(product.getId().toString(), product);
	}

	@Override
	public Product findBy(UUID id) {
		return persistenceMap.get(id.toString());
	}
}
