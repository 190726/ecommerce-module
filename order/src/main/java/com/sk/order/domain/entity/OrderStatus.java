package com.sk.order.domain.entity;

import lombok.Getter;

@Getter
public enum OrderStatus {
	
	ORDERED("ordered", "주문중"),
    PAYED("payed", "결제됨"),
    RECEIVED("deliveried", "배달됨"),
    CANCELED("canceled", "취소됨 ");

    private final String code;
    private final String text;

    OrderStatus(String code, String text) {
        this.code = code;
        this.text = text;
    }
}
