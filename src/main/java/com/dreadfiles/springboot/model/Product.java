package com.dreadfiles.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    private Integer id;

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private Integer value;

}
