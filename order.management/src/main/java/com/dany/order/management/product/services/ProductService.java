package com.dany.order.management.product.services;


import com.dany.order.management.commons.exceptions.EntityNotExistException;
import com.dany.order.management.product.dto.ProductDto;
import com.dany.order.management.product.repository.ProductRepository;
import com.dany.order.management.product.repository.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()){
            throw  new EntityNotExistException("Product id is invalid" + productId);
        }
        return optionalProduct.get();
    }

    public List<ProductDto> searchProductByName(String nameProduct) {
        if (nameProduct != null) {
             return productRepository.searchProductByName(nameProduct)
                     .stream()
                     .map(ProductService::getDtoFromProduct)
                     .toList();
        }

        return listProducts();
    }

    public List<ProductDto> listProducts() {
        return productRepository.findAll().stream().map(ProductService::getDtoFromProduct).toList();
    }

    public void addProduct(ProductDto productDto) {
        Product product = getProductFromDto(productDto);
        productRepository.save(product);
    }

    public void updateProduct(Integer productID, ProductDto productDto) {
        Product product = getProductFromDto(productDto);
        product.setId(productID);
        productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    private static ProductDto getDtoFromProduct(Product product) {
        return new ProductDto(product);
    }

    private static Product getProductFromDto(ProductDto productDto ) {
        return new Product(productDto);
    }


}
