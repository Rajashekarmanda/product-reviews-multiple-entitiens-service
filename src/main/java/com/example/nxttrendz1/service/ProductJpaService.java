package com.example.nxttrendz1.service;

import com.example.nxttrendz1.model.Product;
import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ProductJpaService implements ProductRepository {
	@Autowired
	ProductJpaRepository productJpaRepository;

	@Override
	public ArrayList<Product> getProducts() {
		List<Product> productsList = productJpaRepository.findAll();
		ArrayList<Product> products = new ArrayList<>(productsList);
		return products;
	}

	@Override
	public Product getProduct(int productId) {
		try {
			Product product = productJpaRepository.findById(productId).get();
			return product;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Product addProduct(Product product) {
		try {
			productJpaRepository.save(product);
			return product;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Product updateProduct(int productId, Product product) {
		try {
			Product productFound = productJpaRepository.findById(productId).get();
			if (product.getProductName() != null) {
				productFound.setProductName(product.getProductName());
			}
			productJpaRepository.save(productFound);
			return productFound;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid productId");
		}
	}

	@Override
	public void deleteProduct(int productId) {
		try {
			productJpaRepository.deleteById(productId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid productId");
		}

	}

}