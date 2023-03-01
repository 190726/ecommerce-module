package com.sk.delivery.domain.entity;

import static org.springframework.util.Assert.hasText;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notEmpty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.PRIVATE)
public class OrderSheet {
	
	enum DeliveryStatus{
		PREPARED,COMPLETED;
	}
	
	private String orderId;
	private String receiver;
	private List<OrderItemSheet> orderItemSheets = new ArrayList<>();
	private DeliveryStatus deliveryStatus;
	private BigDecimal totalPrice;
	
	@Builder
	public OrderSheet(String orderId, String receiver, List<OrderItemSheet> orderItemSheets
			, BigDecimal totalPrice) {
		
		validate(orderId, receiver, orderItemSheets, totalPrice);
		
		this.orderId = orderId;
		this.receiver = receiver;
		this.orderItemSheets = new ArrayList<>(orderItemSheets);
		this.totalPrice = totalPrice;
	}

	private void validate(String orderId, String receiver, List<OrderItemSheet> orderItemSheets,
			BigDecimal totalPrice) {
		hasText(orderId, "주문번호는 필수입니다.");
		hasText(receiver, "배송받는 사람은 필수입니다.");
		//noNullElements(orderItemSheets, "주문아이템이 null이면 안됩니다.");
		notEmpty(orderItemSheets, "주문아이템이 null이면 안됩니다.");
		isTrue(totalPrice.compareTo(BigDecimal.ZERO) > 0, "주문금액은 0원이상이어야 합니다.");
	}

	public void completeDelivary() {
		deliveryStatus = DeliveryStatus.COMPLETED;
	}
}