package com.dany.order.management.product.controller;

import com.dany.order.management.commons.APIResponse;
import com.dany.order.management.product.dto.ProductDto;
import com.dany.order.management.product.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Post")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/search/{productName}")
    public ResponseEntity<List<ProductDto>> searchProducts(@PathVariable("productName") String productName) {
        List<ProductDto> body = productService.searchProductByName(productName);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addProduct(@RequestBody @Validated ProductDto productDto) {
        productService.addProduct(productDto);
        return new ResponseEntity<>(new APIResponse(true, "Product created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<APIResponse> updateProduct(@PathVariable("productId") Integer productId,
                                                     @RequestBody @Validated ProductDto productDto) {
        productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(new APIResponse(true, "Product updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable("productId") Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(new APIResponse(true, "Product deleted"), HttpStatus.OK);

    }
}
