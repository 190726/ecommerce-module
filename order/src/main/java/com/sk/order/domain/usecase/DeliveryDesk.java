package com.sk.order.domain.usecase;

import com.sk.order.domain.entity.Order;

public interface DeliveryDesk {

	void dispatch(Order order);

}
