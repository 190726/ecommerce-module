package com.sk.order.domain.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;

public class OrderService implements OrderGetUsecase, OrderPlaceUsecase, OrderPayUsecase{

	private OrderPersistencePort orderPersistencePort;

	public OrderService(OrderPersistencePort orderPersistencePort) {
		this.orderPersistencePort = orderPersistencePort;
	}

	@Override
	public Order placeOrder(List<OrderItem> list) {
		Assert.notEmpty(list, "1개 이상의 아이템이 있어야 합니다.");
		Order order = new Order();
		order.items(list);
		return orderPersistencePort.place(order);
	}

	@Override
	public Order getOrder(UUID id) {
		return orderPersistencePort.findBy(id);
	}

	@Override
	public void payOrder(UUID orderId) {
		throw new UnsupportedOperationException();
	}
}