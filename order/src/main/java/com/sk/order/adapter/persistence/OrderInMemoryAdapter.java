package com.sk.order.adapter.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.sk.order.domain.entity.Order;
import com.sk.order.domain.usecase.OrderPersistencePort;

@Repository
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