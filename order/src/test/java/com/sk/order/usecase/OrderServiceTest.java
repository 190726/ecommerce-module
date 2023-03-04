package com.sk.order.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sk.order.adapter.persistence.OrderInMemoryAdapter;
import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;
import com.sk.order.domain.usecase.DeliveryDesk;
import com.sk.order.domain.usecase.OrderPersistencePort;
import com.sk.order.domain.usecase.OrderService;

//1.주문할 상품과 수량값으로 주문아이템을 생성하여 주문에 담고 저장한다.
//2.결재를 진행후 완료되면 배송을 시작한다.
public class OrderServiceTest {
	
	private OrderPersistencePort orderPersistencePort;
	private OrderService orderService;
	private DeliveryDesk deliveryDesk;

	@BeforeEach
	void setUp() {
		deliveryDesk = new DeliveryDesk() {
			
			@Override
			public void dispatch(Order order) {
				//TODO:주문배달처리
			}
		};
		orderPersistencePort = new OrderInMemoryAdapter();
		orderService = new OrderService(orderPersistencePort, deliveryDesk);
	}
	
	Order orderStub() {
		UUID productId = UUID.randomUUID();
		String name = "상품명";
		long amount = 10L;
		BigDecimal price = BigDecimal.TEN;
		OrderItem orderItem = new OrderItem(productId.toString(), name, amount, price);
		Order order = orderService.placeOrder(List.of(orderItem));
		return order;
	}
	
	@Test
	@DisplayName("상품아이템으로 주문생성하기")
	void placeOneItemOrderTest() throws Exception {
		
		UUID productId = UUID.randomUUID();
		String name = "상품명";
		long amount = 10L;
		BigDecimal price = BigDecimal.TEN;
		OrderItem orderItem = new OrderItem(productId.toString(), name, amount, price);
		Order order = orderService.placeOrder(List.of(orderItem));
		assertThat(order.getOrderItems()).hasSizeGreaterThan(0);
	}
	
	@Test
	@DisplayName("주문번호로 주문가져오기")
	void getOrderTest() throws Exception {
		Order orderStub = orderStub();
		Order findOrder = orderService.getOrder(orderStub.getId());
		assertThat(findOrder.getId()).isEqualTo(orderStub.getId());
	}
	
	@Test
	@DisplayName("생성된 주문에 대해 결제하기")
	void payedOrderTest() throws Exception {
		UUID id = orderStub().getId();
		Order payOrder = orderService.payOrder(id);
		assertThat(id).isEqualTo(payOrder.getId());
	}
}