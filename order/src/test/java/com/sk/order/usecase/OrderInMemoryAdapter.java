package com.sk.order.usecase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderInMemoryAdapter implements OrderPersistencePort {
	
	Map<String, Order> persistenceMap = new HashMap<>();

	@Override
	public Order place(Order order) {
		persistenceMap.putIfAbsent(order.getId().toString(), order);
		return order;
	}

	@Override
	public Order findBy(UUID id) {
		return persistenceMap.get(id.toString());
	}
}