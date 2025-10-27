package com.dreadfiles.springboot.service;

import com.dreadfiles.springboot.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();

    Optional<ProductDTO> findById(Integer id);

    ProductDTO save(ProductDTO productDTO);

    Optional<ProductDTO> update(Integer id, ProductDTO productDTO);

    boolean deleteById(Integer id);

}