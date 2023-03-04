package com.sk.app.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sk.app.service.OrderProductService;
import com.sk.delivery.domain.entity.OrderSheet;
import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;
import com.sk.order.domain.usecase.OrderPayUsecase;
import com.sk.order.domain.usecase.OrderPlaceUsecase;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductUsecase;

@RestController
public class OrderProductController {
	
	@Autowired OrderProductService orderProductService;
	
	
	@GetMapping("/products")
	public List<Product> products(){
		return orderProductService.allProducts();
	}
	
	@GetMapping("/order")
	public Order order(@RequestParam("id") String id, @RequestParam("amount") Long amount) {
		Order payedOrder = orderProductService.orderPlaced(id, amount);
		return payedOrder;
	}
	
	@GetMapping("/pay")
	public OrderSheet pay(@RequestParam("orderId") String orderId) {
		return orderProductService.orderPayed(orderId);
	}
}