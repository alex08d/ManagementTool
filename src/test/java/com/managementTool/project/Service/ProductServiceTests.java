package com.managementTool.project.Service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Exception.ProductNotFoundException;
import com.managementTool.project.Repository.ProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTests{

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @Before
    public void setup() {
        product = new Product();
        product.setId(1L);
        product.setProductName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(9.99);
    }

    @Test
    public void getProductById_existingProduct_returnsProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertEquals(product, result);
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductById_nonExistingProduct_throwsProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.getProduct(1L);
    }

    @Test
    public void getProducts_returnsListOfProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getProducts();

        assertEquals(productList, result);
    }

    @Test
    public void updateProduct_existingProduct_updatesProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Test Product");
        updatedProduct.setDescription("This is an updated test product");
        updatedProduct.setPrice(19.99);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doAnswer(invocation -> {
            product.setProductName(updatedProduct.getProductName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return null;
        }).when(productRepository).save(product);

        productService.updateProduct(1L, updatedProduct);

        assertEquals(updatedProduct.getProductName(), product.getProductName());
        assertEquals(updatedProduct.getDescription(), product.getDescription());
        assertEquals(updatedProduct.getPrice(), product.getPrice(), 0.0);
    }

    @Test(expected = ProductNotFoundException.class)
    public void updateProduct_nonExistingProduct_throwsProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.updateProduct(1L, product);
    }

    @Test
    public void deleteProductById_existingProduct_deletesProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProductById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test(expected = ProductNotFoundException.class)
    public void deleteProductById_nonExistingProduct_throwsProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.deleteProductById(1L);
    }

    @Test
    public void getProductPrice_existingProduct_returnsProductPrice() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Double result = productService.getProductPrice(1L);

        assertEquals(product.getPrice(), result, 0.0);
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductPrice_nonExistingProduct_throwsProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.getProductPrice(1L);
    }

}