package com.managementTool.project.Service;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Exception.FieldEmptyException;
import com.managementTool.project.Exception.ProductNotFoundException;
import com.managementTool.project.Repository.ProductRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void updateProduct(Long id, @NonNull Product product){
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
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

    @Override
    public void createProduct(@NonNull Product product){
        if(product.getPrice() == null) throw new FieldEmptyException("Price field is empty");
        if(product.getProductName() == null) throw new FieldEmptyException("Product name field is empty");
        productRepository.save(product);
    }

}
