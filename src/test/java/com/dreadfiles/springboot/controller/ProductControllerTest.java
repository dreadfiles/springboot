package com.dreadfiles.springboot.controller;

import com.dreadfiles.springboot.dto.ProductDTO;
import com.dreadfiles.springboot.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDTO productDTO;

    @BeforeEach
    void setup() {
        productDTO = createProductDTO();
    }

    private ProductDTO createProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1);
        dto.setName("Guitar");
        dto.setDescription("Electric Guitar");
        dto.setValue(1000);
        return dto;
    }

    @Test
    void shouldReturnAllProducts() {
        when(productService.findAll()).thenReturn(List.of(productDTO));

        ResponseEntity<List<ProductDTO>> response = productController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productService).findAll();
    }

    @Test
    void shouldReturnProductById() {
        when(productService.findById(1)).thenReturn(Optional.of(productDTO));

        ResponseEntity<ProductDTO> response = productController.getById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productService).findById(1);
    }

    @Test
    void shouldNotReturnProductByIdWhenNotFound() {
        when(productService.findById(2)).thenReturn(Optional.empty());

        ResponseEntity<ProductDTO> response = productController.getById(2);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();

        verify(productService).findById(2);
    }

    @Test
    void shouldCreateProduct() {
        when(productService.save(productDTO)).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.create(productDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productService).save(productDTO);
    }

    @Test
    void shouldUpdateProduct() {
        when(productService.update(1, productDTO)).thenReturn(Optional.of(productDTO));

        ResponseEntity<ProductDTO> response = productController.update(1, productDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productService).update(1, productDTO);
    }

    @Test
    void shouldNotUpdateProductWhenNotFound() {
        when(productService.update(2, productDTO)).thenReturn(Optional.empty());

        ResponseEntity<ProductDTO> response = productController.update(2, productDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();

        verify(productService).update(2, productDTO);
    }

    @Test
    void shouldDeleteProduct() {
        when(productService.deleteById(1)).thenReturn(true);

        ResponseEntity<Void> response = productController.delete(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();

        verify(productService).deleteById(1);
    }

    @Test
    void shouldNotDeleteProductWhenNotFound() {
        when(productService.deleteById(2)).thenReturn(false);

        ResponseEntity<Void> response = productController.delete(2);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();

        verify(productService).deleteById(2);
    }

}