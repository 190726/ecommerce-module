package com.sk.order.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

//1.주문할 상품과 수량값으로 주문아이템을 생성하여 주문에 담고 저장한다.
//2.결재를 진행후 완료되면 배송을 시작한다.
public class OrderServiceTest {
	
	private OrderService orderService = new OrderService();

	@Test
	void placeOrderTest() throws Exception {
		
		UUID productId = UUID.randomUUID();
		String name = "상품명";
		long amount = 10L;
		BigDecimal price = BigDecimal.TEN;
		OrderItem orderItem = new OrderItem(productId, name, amount, price);
		orderService.placeOrder(orderItem);
	}
}