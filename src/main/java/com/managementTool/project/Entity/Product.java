package com.managementTool.project.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @SequenceGenerator(name = "products_sequence", allocationSize = 1)
    @GeneratedValue(generator = "products_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonProperty
    private String productName;

    @JsonProperty
    private String description;

    @JsonProperty
    private Double price;
}
