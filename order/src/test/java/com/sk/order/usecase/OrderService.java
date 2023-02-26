package com.sk.order.usecase;

import java.util.List;

public class OrderService {

	private OrderPersistencePort orderPersistencePort;

	public OrderService(OrderPersistencePort orderPersistencePort) {
		this.orderPersistencePort = orderPersistencePort;
	}

	public Order placeOrder(List<OrderItem> list) {
		Order order = new Order();
		order.items(list);
		return orderPersistencePort.place(order);
	}
}
