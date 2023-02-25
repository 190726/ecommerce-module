package com.sk.product.domain;

import lombok.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sk.product.adapter.persistence.ProductPersistenceAdapter;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductPersistencePort;
import com.sk.product.domain.usecase.ProductService;
import com.sk.product.domain.usecase.ProductUsecase;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductServiceTest {

    private ProductUsecase productUsecase;
    private ProductPersistencePort port;
    
	@BeforeEach
    public void setup() {
		port = new ProductPersistenceAdapter();
		productUsecase = new ProductService(port);
    }
	
    @Test
    @DisplayName("상품 등록하기")
    public void addProductTest(){
        //given
        UUID id = UUID.randomUUID();
        String name = "실손보험";
		BigDecimal price = BigDecimal.TEN;
		
		Product product = new Product(id, name, price);
        //when
        productUsecase.register(product);
        Product find = productUsecase.findBy(id);
        //then
        Assertions.assertThat(find.getId()).isEqualTo(id);
    }
}