package com.sk.delivery.domain.usecase;

import com.sk.delivery.domain.entity.OrderSheet;

public class DeliveryService implements DeliveryDispachUsecase {

	@Override
	public OrderSheet dispatchDelivery(OrderSheet orderSheet) {
		orderSheet.completeDelivary();
		return orderSheet;
	}
}