package com.sk.delivery.domain.usecase;

import org.springframework.stereotype.Service;

import com.sk.delivery.domain.entity.OrderSheet;

@Service
public class DeliveryService implements DeliveryDispachUsecase {

	@Override
	public OrderSheet dispatchDelivery(OrderSheet orderSheet) {
		orderSheet.completeDelivary();
		return orderSheet;
	}
}