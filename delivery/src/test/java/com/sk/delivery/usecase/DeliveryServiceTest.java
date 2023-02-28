package com.sk.delivery.usecase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryServiceTest {
	

	private DeliveryService deliveryService;
	
	@BeforeEach
	void init() {
		deliveryService = new DeliveryService();
	}

	@Test
	void deliveryPrepareTest() throws Exception {
		List<OrderItemSheet> itemSheets = new ArrayList<>();
		OrderItemSheet orderItemSheet = OrderItemSheet.builder().productName("상품명").amount(2).productPrice(BigDecimal.ONE).build();
		itemSheets.add(orderItemSheet);
		
		OrderSheet orderSheet = OrderSheet.builder()
				.orderId("id")
				.receiver("sangkook")
				.orderItemSheets(itemSheets)
				.totalPrice(BigDecimal.TEN)
				.build();
		OrderSheet dispatchDelivery = deliveryService.dispatchDelivery(orderSheet);
		Assertions.assertThat(dispatchDelivery.getOrderId()).isEqualTo(orderSheet.getOrderId());
	}
}