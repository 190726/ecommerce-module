package com.sk.order.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

public class OrderService {

	private OrderPersistencePort orderPersistencePort;

	public OrderService(OrderPersistencePort orderPersistencePort) {
		this.orderPersistencePort = orderPersistencePort;
	}

	public Order placeOrder(List<OrderItem> list) {
		Assert.notEmpty(list, "1개 이상의 아이템이 있어야 합니다.");
		Order order = new Order();
		order.items(list);
		return orderPersistencePort.place(order);
	}

	public Order getOrder(UUID id) {
		return orderPersistencePort.findBy(id);
	}
}
