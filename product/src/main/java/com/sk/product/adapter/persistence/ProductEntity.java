package com.sk.product.adapter.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sk.product.domain.entity.Product.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
@Entity
public class ProductEntity {
	
	@Id
	@Column(columnDefinition = "uuid")
	private UUID id;
	
	private String name;
	
	@Column(precision =10, scale = 2)
	private BigDecimal price;
	
	@Column(name = "STOCK_AMOUNT")
	private Long stockAmount;
	
	@Enumerated(EnumType.STRING)
	private Category category;
}