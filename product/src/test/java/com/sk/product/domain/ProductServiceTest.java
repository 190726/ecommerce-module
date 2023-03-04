package com.sk.product.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sk.product.adapter.persistence.ProductInMemoryAdapter;
import com.sk.product.domain.entity.Product;
import com.sk.product.domain.entity.Product.Category;
import com.sk.product.domain.usecase.NotExistProductException;
import com.sk.product.domain.usecase.ProductPersistencePort;
import com.sk.product.domain.usecase.ProductService;
import com.sk.product.domain.usecase.ProductUsecase;

public class ProductServiceTest {

    private ProductUsecase productUsecase;
    private ProductPersistencePort port;
    
	@BeforeEach
    public void setup() {
		port = new ProductInMemoryAdapter();
		productUsecase = new ProductService(port);
    }
	
	@Test
	@DisplayName("상품ID자동생성")
	void makeProduct() throws Exception {
		Product build = Product.builder().name("test").price(BigDecimal.ONE).stockAmount(10L).build();
		Assertions.assertThat(build.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("상품 생성시 가격 오류")
	void makeProductErrorTest() throws Exception {
		assertThrows(IllegalArgumentException.class, ()
				-> new Product(UUID.randomUUID().toString(), "상품", new BigDecimal(-10), 10L, Category.ETC));
	}
	
    @Test
    @DisplayName("상품 등록하기")
    public void addProductTest(){
        //given
		UUID id = UUID.randomUUID();
        String name = "실손보험";
		BigDecimal price = BigDecimal.TEN;
		
		Product product = new Product(id.toString(), name, price, 10L, Category.ELECTRIC);
        //when
        productUsecase.register(product);
        Product find = productUsecase.findBy(id.toString())
        		.orElseThrow(()->new NotExistProductException(String.format("해당ID에 상품이 없습니다. ID: %s", id)));
        //then
        Assertions.assertThat(find.getId()).isEqualTo(id);
    }
}