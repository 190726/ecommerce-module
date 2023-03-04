package com.sk.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.delivery.domain.usecase.DeliveryDispachUsecase;
import com.sk.order.domain.entity.Order;
import com.sk.order.domain.usecase.DeliveryDesk;

@Service
public class DeliveryDeskService implements DeliveryDesk{

	@Autowired DeliveryDispachUsecase deliveryDispachUsecase;
	
	@Override
	public void dispatch(Order order) {
		System.out.println("order dispach not implements");
	}
}