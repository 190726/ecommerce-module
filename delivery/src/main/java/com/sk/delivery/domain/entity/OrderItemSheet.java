package com.sk.delivery.usecase;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class OrderItemSheet {

	private String productName;
	private BigDecimal productPrice;
	private int amount;
}
