package com.managementTool.project.Service;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Exception.ProductNotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();
    void updateProduct(Long id, Product product) throws ProductNotFoundException;
    void deleteProductById(Long id) throws ProductNotFoundException;
    Double getProductPrice(Long id) throws ProductNotFoundException;
    void createProduct(Product product);
}
