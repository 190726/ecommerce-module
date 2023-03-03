package com.sk.app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.product.domain.entity.Product;
import com.sk.product.domain.usecase.ProductUsecase;

@RestController
public class OrderProductController {
	
	@Autowired private ProductUsecase productUsecase;
	
	@GetMapping("/products")
	public List<Product> products(){
		return productUsecase.findAll();
	}

}
