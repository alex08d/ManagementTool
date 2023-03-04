package com.managementTool.project.Controller;

import com.managementTool.project.Service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="api/v1/products")
public class ProductsController {

  private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
}
