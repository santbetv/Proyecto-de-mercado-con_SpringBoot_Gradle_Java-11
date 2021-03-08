package com.santbetv.developermercado.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.santbetv.developermercado.domain.Product;
import com.santbetv.developermercado.domain.repository.ProductRepository;

@Service
public class ProductService {
	
    
    private ProductRepository productRepository;
    
    
    @Autowired
    public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        try {
            productRepository.delete(productId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
