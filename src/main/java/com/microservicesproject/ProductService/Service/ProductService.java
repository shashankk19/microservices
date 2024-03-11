package com.microservicesproject.ProductService.Service;

import com.microservicesproject.ProductService.Model.ProductRequest;
import com.microservicesproject.ProductService.Model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProduct(long productId);

    void reduceQuantity(long productId, long quantity);
}
