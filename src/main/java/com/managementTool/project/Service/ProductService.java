package com.managementTool.project.Service;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Exception.ProductNotFoundException;
import com.managementTool.project.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Long id, Product product) throws ProductNotFoundException {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found")); // To Do: implement exception handler
        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product not found"); // Exception Handler
        }
        productRepository.deleteById(id);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

}
