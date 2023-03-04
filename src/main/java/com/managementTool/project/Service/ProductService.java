package com.managementTool.project.Service;

import com.managementTool.project.Entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public void updateProduct(Long id, Product product) {

    }

    @Override
    public void deleteProductById(Long id) {

    }
}
