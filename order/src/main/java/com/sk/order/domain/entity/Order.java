package com.sk.order.domain.entity;

import static java.time.LocalDateTime.now;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sk.order.domain.usecase.DeliveryDesk;

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
	
	private Order() {
	}
	
	public static Order createEmptyOrder() {
		Order order = new Order();
		order.setId(UUID.randomUUID());
		order.setTotalPrice(BigDecimal.ZERO);
		order.setOrderStatus(OrderStatus.ORDERED);
		order.setLastModified(now());
		return order;
	}

	public Order placed(List<OrderItem> list) {
		orderItems.addAll(list);
		return this;
	}

	public Order payed(DeliveryDesk deliveryDesk) {
		deliveryDesk.dispatch(this);
		orderStatus = OrderStatus.PAYED;
		lastModified = now();
		return this;
	}
}