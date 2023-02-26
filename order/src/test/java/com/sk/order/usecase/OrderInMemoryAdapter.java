package com.sk.order.usecase;

import java.util.HashMap;
import java.util.Map;

public class OrderInMemoryAdapter implements OrderPersistencePort {
	
	Map<String, Order> persistenceMap = new HashMap<>();

	@Override
	public Order place(Order order) {
		persistenceMap.putIfAbsent(order.getId().toString(), order);
		return order;
	}
}