package com.dreadfiles.springboot.service;

import com.dreadfiles.springboot.dto.ProductDTO;
import com.dreadfiles.springboot.mapper.ProductMapper;
import com.dreadfiles.springboot.model.Product;
import com.dreadfiles.springboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> productList = this.productRepository.findAll();
        return this.productMapper.toDTOList(productList);
    }

    @Override
    public Optional<ProductDTO> findById(Integer id) {
        return this.productRepository.findById(id)
                .map(this.productMapper::toDTO);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = this.productMapper.toEntity(productDTO);
        Product savedProduct = this.productRepository.save(product);
        return this.productMapper.toDTO(savedProduct);
    }

    @Override
    public Optional<ProductDTO> update(Integer id, ProductDTO productDTO) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setDescription(productDTO.getDescription());
            Product updatedProduct = this.productRepository.save(existingProduct);
            return Optional.of(this.productMapper.toDTO(updatedProduct));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Integer id) {
        if (this.productRepository.existsById(id)) {
            this.productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}