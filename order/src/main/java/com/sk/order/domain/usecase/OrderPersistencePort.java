package com.sk.order.domain.usecase;

import java.util.UUID;

import com.sk.order.domain.entity.Order;

public interface OrderPersistencePort {

	Order place(Order order);

	Order findBy(UUID id);
	
}
