package com.managementTool.project.Service;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Exception.FieldEmptyException;
import com.managementTool.project.Exception.ProductNotFoundException;
import com.managementTool.project.Repository.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(Long id, Product product){
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if (product.getProductName() != null) productToUpdate.setProductName(product.getProductName());
        if (product.getDescription() != null) productToUpdate.setDescription(product.getDescription());
        if (product.getPrice() != null) productToUpdate.setPrice(product.getPrice());
        productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProductById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Double getProductPrice(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return product.getPrice();
    }

    public List<Product> getProductsByPriceRange(Double price, Double minPrice, Double maxPrice) {
        if (price != null) {
            return productRepository.findByPrice(price);
        } else {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        }
    }

    @Override
    public void createProduct(@NonNull Product product){
        if(product.getPrice() == null) throw new FieldEmptyException("Price field is empty");
        if(product.getProductName() == null) throw new FieldEmptyException("Product name field is empty");
        productRepository.save(product);
    }

}
