package com.dreadfiles.springboot.repository;

import com.dreadfiles.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}