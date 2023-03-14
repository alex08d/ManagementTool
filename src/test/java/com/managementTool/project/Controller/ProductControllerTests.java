package com.managementTool.project.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductControllerTests{

    @Mock
    private ProductService productService;

    private ProductsController productsController;
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productsController = new ProductsController(productService);
    }

    @Test
    public void testGetProducts() {
        List<Product> products = Arrays.asList(new Product(1L, "product1", "description1", 99.0 ),
                new Product(2L, "product2", "description2", 100.0 ));

        when(productService.getProducts()).thenReturn(products);

        List<Product> response = productsController.getProducts();

        assertEquals(2, response.size());
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1L, "product1", "description1", 99.0 );

        when(productService.getProduct(1L)).thenReturn(product);

        Product response = productsController.getProductById(1L);

        assertEquals(1L, response.getId());
    }

    @Test
    public void testGetPrice() {
        Double price = 10.0;

        when(productService.getProductPrice(1L)).thenReturn(price);

        Double response = productsController.getPrice(1L);

        assertEquals(price, response);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(1L, "product1", "description1", 99.0 );

        ResponseEntity<Product> response = productsController.addProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product(1L, "product1", "description1", 99.0 );

        ResponseEntity<Product> response = productsController.updateProduct(1L, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteProduct() {
        Long id = 1L;

        ResponseEntity<String> response = productsController.deleteProduct(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
