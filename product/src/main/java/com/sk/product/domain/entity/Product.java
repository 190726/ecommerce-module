package com.sk.product.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.PRIVATE)
public class Product {

	private UUID id;
	private String name;
	private BigDecimal price;

	public Product(UUID id, String name, BigDecimal price) {
		Assert.hasText(name, "상품명은 필수 입니다.");
		Assert.isTrue(price.compareTo(BigDecimal.ZERO) > 0, "가격은 0보다 커야 합니다.");
		this.id = id;
		this.name = name;
		this.price = price;
	}
}