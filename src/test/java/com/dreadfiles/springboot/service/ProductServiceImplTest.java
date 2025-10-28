package com.dreadfiles.springboot.service;

import com.dreadfiles.springboot.dto.ProductDTO;
import com.dreadfiles.springboot.mapper.ProductMapper;
import com.dreadfiles.springboot.model.Product;
import com.dreadfiles.springboot.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        product = createProduct();
        productDTO = createProductDTO();
    }

    private Product createProduct() {
        Product p = new Product();
        p.setId(1);
        p.setName("Guitar");
        p.setDescription("Electric Guitar");
        p.setValue(1000);
        return p;
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
    void shouldFindAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));
        when(productMapper.toDTOList(anyList())).thenReturn(List.of(productDTO));

        List<ProductDTO> result = productService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productRepository).findAll();
        verify(productMapper).toDTOList(anyList());
    }

    @Test
    void shouldFindProductById() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        Optional<ProductDTO> result = productService.findById(1);

        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productRepository).findById(1);
        verify(productMapper).toDTO(product);
    }

    @Test
    void shouldNotFindProductByIdWhenNotExists() {
        when(productRepository.findById(2)).thenReturn(Optional.empty());

        Optional<ProductDTO> result = productService.findById(2);

        assertThat(result).isNotPresent();

        verify(productRepository).findById(2);
        verify(productMapper, never()).toDTO(any());
    }

    @Test
    void shouldSaveProduct() {
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        ProductDTO result = productService.save(productDTO);

        assertThat(result).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productRepository).save(product);
        verify(productMapper).toEntity(productDTO);
        verify(productMapper).toDTO(product);
    }

    @Test
    void shouldUpdateProductWhenExists() {
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDTO(product)).thenReturn(productDTO);

        Optional<ProductDTO> result = productService.update(1, productDTO);

        assertThat(result).isPresent();
        assertThat(result.get()).usingRecursiveComparison().isEqualTo(productDTO);

        verify(productRepository).findById(1);
        verify(productRepository).save(product);
        verify(productMapper).toDTO(product);
    }

    @Test
    void shouldNotUpdateProductWhenNotExists() {
        when(productRepository.findById(2)).thenReturn(Optional.empty());

        Optional<ProductDTO> result = productService.update(2, productDTO);

        assertThat(result).isNotPresent();

        verify(productRepository).findById(2);
        verify(productRepository, never()).save(any());
        verify(productMapper, never()).toDTO(any());
    }

    @Test
    void shouldDeleteProductWhenExists() {
        when(productRepository.existsById(1)).thenReturn(true);

        boolean result = productService.deleteById(1);

        assertThat(result).isTrue();

        verify(productRepository).existsById(1);
        verify(productRepository).deleteById(1);
    }

    @Test
    void shouldNotDeleteProductWhenNotExists() {
        when(productRepository.existsById(2)).thenReturn(false);

        boolean result = productService.deleteById(2);

        assertThat(result).isFalse();

        verify(productRepository).existsById(2);
        verify(productRepository, never()).deleteById(anyInt());
    }
}