package com.sk.app.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;
import com.sk.order.domain.usecase.OrderPayUsecase;
import com.sk.order.domain.usecase.OrderPlaceUsecase;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductUsecase;

@RestController
public class OrderProductController {
	
	@Autowired private ProductUsecase productUsecase;
	@Autowired private OrderPlaceUsecase orderPlaceUsecase;
	@Autowired private OrderPayUsecase orderPayUsecase;
	
	@GetMapping("/products")
	public List<Product> products(){
		return productUsecase.findAll();
	}
	
	@GetMapping("/order")
	public Order order(@RequestParam("id") String id, @RequestParam("amount") Long amount) {
	
		Product product = productUsecase.findBy(id)
			.orElseThrow(() -> new IllegalStateException("해당 상품이 없습니다."));
		//OrderItem 생성
		OrderItem orderItem = OrderItem.builder()
				.productId(product.getId())
				.name(product.getName())
				.amount(amount)
				.price(product.getPrice())
				.build();
		Order placeOrder = orderPlaceUsecase.placeOrder(List.of(orderItem));
		Order payedOrder = orderPayUsecase.payOrder(placeOrder.getId());
		return payedOrder;
	}

}
