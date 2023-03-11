package com.managementTool.project.Controller;

import com.managementTool.project.Entity.Product;
import com.managementTool.project.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="api/v1/products")
public class ProductsController {

  private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id){
      return productService.getProduct(id);
    }

    @GetMapping("{id}/price")
    public Double getPrice(@PathVariable("id") Long id){
        return productService.getProductPrice(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.createProduct(product);
    }

    @PutMapping(path = "{id}")
    public  void updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProductById(id);
    }
}
