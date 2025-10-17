package com.dreadfiles.springboot.controller;

import com.dreadfiles.springboot.dto.ProductDTO;
import com.dreadfiles.springboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> productDTOList = this.productService.findAll();
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Integer id) {
        Optional<ProductDTO> productDTO = this.productService.findById(id);
        if (productDTO.isPresent()) {
            return ResponseEntity.ok(productDTO.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = this.productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @Valid @RequestBody ProductDTO productDTO) {
        Optional<ProductDTO> updatedProduct = this.productService.update(id, productDTO);
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok(updatedProduct.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (this.productService.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
