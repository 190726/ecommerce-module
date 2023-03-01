package com.sk.product.adapter.persistence;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductPersistencePort{
	
	private final ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		ProductEntity entity = new ProductEntity(product.getId(), product.getName()
				, product.getPrice(), product.getStockAmount(), product.getCategory());
		productRepository.save(entity);
		return product;
	}

	@Override
	public Optional<Product> findBy(UUID id) {
		Optional<ProductEntity> entity = productRepository.findById(id);
		return entity.map(p -> Product.builder()
				.id(p.getId())
				.name(p.getName())
				.price(p.getPrice())
				.stockAmount(p.getStockAmount())
				.category(p.getCategory()).build()
				);
	}

	@Override
	public List<Product> findAll() {
		List<ProductEntity> entities = productRepository.findAll();
		return entities.stream().map(p -> Product.builder()
				.id(p.getId())
				.name(p.getName())
				.price(p.getPrice())
				.stockAmount(p.getStockAmount())
				.category(p.getCategory()).build()
				).collect(toList());
	}
}