package com.sk.order.domain.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.util.Assert;

import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;

public class OrderService implements OrderGetUsecase, OrderPlaceUsecase, OrderPayUsecase{

	private final OrderPersistencePort orderPersistencePort;
	private final DeliveryDesk deliveryDesk;

	public OrderService(OrderPersistencePort orderPersistencePort, DeliveryDesk deliveryDesk) {
		this.orderPersistencePort = orderPersistencePort;
		this.deliveryDesk = deliveryDesk;
	}

	@Override
	public Order placeOrder(List<OrderItem> list) {
		Assert.notEmpty(list, "1개 이상의 아이템이 있어야 합니다.");
		return orderPersistencePort.place(
					Order.createEmptyOrder().placed(list)
				);
	}

	@Override
	public Order getOrder(UUID id) {
		return orderPersistencePort.findBy(id);
	}

	@Override
	public Order payOrder(UUID orderId) {
		return getOrder(orderId).payed(deliveryDesk);
	}
}