package com.dreadfiles.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor      // Empty constructor needed for JSON deserialization
@AllArgsConstructor     // Constructor with all fields
public class Person {
    private String name;
    private int age;
}
