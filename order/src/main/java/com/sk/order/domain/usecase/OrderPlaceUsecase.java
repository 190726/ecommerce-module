package com.sk.order.domain.usecase;

import java.util.List;

import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;

public interface OrderPlaceUsecase {
	
	Order placeOrder(List<OrderItem> list);

}
