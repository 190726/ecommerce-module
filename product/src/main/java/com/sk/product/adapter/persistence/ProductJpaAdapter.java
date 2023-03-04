package com.sk.product.adapter.persistence;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.util.comparator.Comparators;

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
	public Optional<Product> findBy(String id) {
		Optional<ProductEntity> entity = productRepository.findById(id.toString());
		System.out.println(entity.get());
		System.out.println(id.toString());
		return entity.map(p -> {return new Product(p.getId(), p.getName(), p.getPrice(), p.getStockAmount(), p.getCategory());}
				);
	}

	@Override
	public List<Product> findAll() {
		List<ProductEntity> entities = productRepository.findAll();
		System.out.println("-----productEntity-----");
		entities.forEach(System.out::println);
		 List<Product> products = entities.stream().map(p -> {
			 					return new Product(p.getId(), p.getName(), p.getPrice(), p.getStockAmount(), p.getCategory());
		 					}
				).sorted(Comparator.comparing(Product::getName))
				.collect(toList());
		 System.out.println("----productMapped----");
		 products.forEach(System.out::println);
		return products;
	}
}