package com.sk.product.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.PRIVATE)
public class Product {
	
	public enum Category{
		ELECTRIC, FOOD, ETC;
	}

	private UUID id;
	private String name;
	private BigDecimal price;
	private Long stockAmount;
	private Category category;

	@Builder
	public Product(String name, BigDecimal price, Long stockAmount, Category category) {
		this(UUID.randomUUID(), name, price, stockAmount, category);
	}
	
	@Builder
	public Product(UUID id, String name, BigDecimal price, Long stockAmount, Category category) {
		Assert.hasText(name, "상품명은 필수 입니다.");
		Assert.isTrue(Objects.requireNonNull(price)
				.compareTo(BigDecimal.ZERO) > 0, "가격은 0보다 커야 합니다.");
		Assert.isTrue(stockAmount > 0, "재고량은 0 이상이어야 합니다.");
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockAmount = stockAmount;
		this.category = category;
	}

}