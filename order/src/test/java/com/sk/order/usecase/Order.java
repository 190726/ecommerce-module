package com.sk.order.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.PRIVATE)
public class Order {
	
	private UUID id;
	
	private List<OrderItem> orderItems = new ArrayList<>();
	
	private BigDecimal totalPrice;
	
	private OrderStatus orderStatus;
	
	private LocalDateTime lastModified;
	
	public Order() {
		this.id = UUID.randomUUID();
		this.totalPrice = BigDecimal.ZERO;
		this.orderStatus = OrderStatus.ORDERED;
		this.lastModified = LocalDateTime.now();
	}

	public void items(List<OrderItem> list) {
		orderItems.addAll(list);
	}
}