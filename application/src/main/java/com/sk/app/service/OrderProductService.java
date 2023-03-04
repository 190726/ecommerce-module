package com.sk.app.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.delivery.domain.entity.OrderItemSheet;
import com.sk.delivery.domain.entity.OrderSheet;
import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;
import com.sk.order.domain.usecase.OrderPayUsecase;
import com.sk.order.domain.usecase.OrderPlaceUsecase;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductUsecase;

@Service
public class OrderProductService {
	
	@Autowired private ProductUsecase productUsecase;
	@Autowired private OrderPlaceUsecase orderPlaceUsecase;
	@Autowired private OrderPayUsecase orderPayUsecase;
	
	public Order orderPlaced(String id, Long amount) {
		Product product = productUsecase.findBy(UUID.fromString(id))
			.orElseThrow(() -> new IllegalStateException("해당 상품이 없습니다."));
		//OrderItem 생성
		OrderItem orderItem = OrderItem.builder()
				.productId(product.getId().toString())
				.name(product.getName())
				.amount(amount)
				.price(product.getPrice())
				.build();
		Order placeOrder = orderPlaceUsecase.placeOrder(List.of(orderItem));
		
		return placeOrder;
	}

	public List<Product> allProducts() {
		return productUsecase.findAll();
	}
	
	public OrderSheet orderPayed(String orderId) {
		Order payedOrder = orderPayUsecase.payOrder(UUID.fromString(orderId));
		return mappedOrderSheet(payedOrder);
	}

	private OrderSheet mappedOrderSheet(Order payedOrder) {
		return OrderSheet.builder()
				.orderId(payedOrder.getId().toString())
				.receiver("경기도 남양주시 다산동")
				.totalPrice(payedOrder.getTotalPrice())
				.orderItemSheets(mappedOrderItemSheet(payedOrder.getOrderItems()))
			.build();
	}

	private List<OrderItemSheet> mappedOrderItemSheet(List<OrderItem> orderItems) {
		return orderItems.stream()
			.map(o -> OrderItemSheet.builder()
						.productName(o.getName())
						.amount((int) o.getAmount())
						.productPrice(o.getPrice())
						.build())
			.collect(toList());
	}

}
