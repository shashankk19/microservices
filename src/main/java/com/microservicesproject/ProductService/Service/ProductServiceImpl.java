package com.microservicesproject.ProductService.Service;

import com.microservicesproject.ProductService.Entity.Product;
import com.microservicesproject.ProductService.Exception.ProductServiceCustomException;
import com.microservicesproject.ProductService.Model.ProductRequest;
import com.microservicesproject.ProductService.Model.ProductResponse;
import com.microservicesproject.ProductService.Repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product.......");
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product Created   .......");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProduct(long productId) {
        log.info("Getting product with id {}",productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ProductServiceCustomException("product not Found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse =new ProductResponse();
        copyProperties(product,productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reducing Quantity {} for Product {}.......",quantity,productId);
        Product product =productRepository.findById(productId)
                .orElseThrow(()-> new ProductServiceCustomException("Product not found","PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductServiceCustomException("Insufficient Items","NOT_ENOUGH_STOCK");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Quantity Reduced.......");
    }


}
