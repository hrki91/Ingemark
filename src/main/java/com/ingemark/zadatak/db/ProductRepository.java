package com.ingemark.zadatak.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ingemark.zadatak.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByCode(String code);
}
