package com.sk.order.usecase;

import java.util.UUID;

public interface OrderPersistencePort {

	Order place(Order order);

	Order findBy(UUID id);
	
}
