package com.dreadfiles.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "Id is required")
    private Integer id;

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Name cannot exceed 40 characters")
    private String name;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

    @NotNull(message = "Value is required")
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    private Integer value;

}
