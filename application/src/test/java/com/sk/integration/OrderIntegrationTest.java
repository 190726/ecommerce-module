package com.sk.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sk.order.adapter.persistence.OrderInMemoryAdapter;
import com.sk.order.domain.entity.Order;
import com.sk.order.domain.entity.OrderItem;
import com.sk.order.domain.usecase.DeliveryDesk;
import com.sk.order.domain.usecase.OrderService;
import com.sk.product.adapter.persistence.ProductInMemoryAdapter;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.entity.Product.Category;
import com.sk.product.domain.usecase.ProductService;
import com.sk.product.domain.usecase.ProductUsecase;

public class OrderIntegrationTest {
	
	private ProductUsecase productUsecase;
	private OrderService orderservice;

	@BeforeEach
	public void init() {

		productUsecase = new ProductService(new ProductInMemoryAdapter());
		orderservice = new OrderService(
				new OrderInMemoryAdapter(), new DeliveryDesk() {
					@Override
					public void dispatch(Order order) {
						System.out.println(String.format("order completed %s", order.getTotalPrice().toPlainString()));
					}
				});
		
		Product product1 = Product.builder().name("상품1").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
		Product product2 = Product.builder().name("상품2").price(BigDecimal.TEN).stockAmount(100L).category(Category.ELECTRIC).build();
		Product product3 = Product.builder().name("상품3").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
		Product product4 = Product.builder().name("상품4").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
		Product product5 = Product.builder().name("상품5").price(BigDecimal.ONE).stockAmount(100L).category(Category.FOOD).build();
		Product product6 = Product.builder().name("상품6").price(BigDecimal.ONE).stockAmount(100L).category(Category.FOOD).build();
		Product product7 = Product.builder().name("상품7").price(BigDecimal.TEN).stockAmount(100L).category(Category.ETC).build();
		Product product8 = Product.builder().name("상품8").price(BigDecimal.ONE).stockAmount(100L).category(Category.ELECTRIC).build();
		Product product9 = Product.builder().name("상품9").price(BigDecimal.TEN).stockAmount(100L).category(Category.FOOD).build();
        //when
        productUsecase.register(product1);
        productUsecase.register(product2);
        productUsecase.register(product3);
        productUsecase.register(product4);
        productUsecase.register(product5);
        productUsecase.register(product6);
        productUsecase.register(product7);
        productUsecase.register(product8);
        productUsecase.register(product9);
	}
	
	@Test
	void order() throws Exception {
		//제품등록
		List<Product> findAll = productUsecase.findAll();
		//제품조회
		Product findFirst = findAll.stream()
			.filter(p -> p.getCategory()==Category.FOOD)
			.findFirst()
			.orElseThrow();
		System.out.println(String.format("product find : %s", findFirst));
		//Product to OrderItem mapping
		OrderItem orderItem = OrderItem.builder()
			.productId(findFirst.getId().toString())
			.name(findFirst.getName())
			.amount(5)
			.price(findFirst.getPrice())
			.build();
		//주문생성
		Order placeOrder = orderservice.placeOrder(List.of(orderItem));
		System.out.println(String.format("order placed : %s", placeOrder));
		//주문결제
		Order payedOrder = orderservice.payOrder(placeOrder.getId());
		//배달완료
		assertThat(payedOrder.getOrderStatus().getCode())
				.isEqualTo("payed");
	}

}
