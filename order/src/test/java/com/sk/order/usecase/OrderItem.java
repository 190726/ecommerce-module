package com.sk.order.usecase;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.PRIVATE)
public class OrderItem {

	private UUID productId;
	private String name;
	private long amount;
	private BigDecimal price;

	@Builder
	public OrderItem(UUID productId, String name, long amount, BigDecimal price) {
		Assert.hasText(name, "상품명은 필수입니다");
		Assert.isTrue(amount > 0, "수량은 0개 이상이어야 합니다");
		Assert.isTrue(price != null, "가격은 필수입니다.");
		this.productId = productId;
		this.name = name;
		this.amount = amount;
		this.price = price;
	}
}