package com.managementTool.project.Service;

import com.managementTool.project.Entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();
    void updateProduct(Long id, Product product);
    void deleteProductById(Long id);
}
