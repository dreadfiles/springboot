package com.dreadfiles.springboot.mapper;

import com.dreadfiles.springboot.dto.ProductDTO;
import com.dreadfiles.springboot.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> toDTOList(List<Product> productList);

    List<Product> toEntityList(List<ProductDTO> productDTOList);

}