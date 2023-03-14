package com.managementTool.project.Repository;

import com.managementTool.project.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPrice(double price);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

}
