package com.sk.order.domain.usecase;

import java.util.UUID;

public interface OrderPayUsecase {
	
	void payOrder(UUID orderId);

}
