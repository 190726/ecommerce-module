package com.sk.delivery.domain.usecase;

import com.sk.delivery.domain.entity.OrderSheet;

public interface DeliveryDispachUsecase {
	public OrderSheet dispatchDelivery(OrderSheet orderSheet);
}