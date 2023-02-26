package com.sk.order.usecase;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//1.주문할 상품과 수량값으로 주문아이템을 생성하여 주문에 담고 저장한다.
//2.결재를 진행후 완료되면 배송을 시작한다.
public class OrderServiceTest {
	
	private OrderPersistencePort orderPersistencePort;
	private OrderService orderService;

	@BeforeEach
	void setUp() {
		orderPersistencePort = new OrderPersistencePort() {

			@Override
			public Order place(Order order) {
				return order;
			}
		};
		orderService = new OrderService(orderPersistencePort);
	}
	
	@Test
	void placeOrderTest() throws Exception {
		
		UUID productId = UUID.randomUUID();
		String name = "상품명";
		long amount = 10L;
		BigDecimal price = BigDecimal.TEN;
		OrderItem orderItem = new OrderItem(productId, name, amount, price);
		Order order = orderService.placeOrder(List.of(orderItem));
		Assertions.assertThat(order.getOrderItems()).hasSizeGreaterThan(0);
	}
}