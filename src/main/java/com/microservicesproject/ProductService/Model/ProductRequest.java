package com.microservicesproject.ProductService.Model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductRequest {

    private String productName;

    private long quantity;

    private long price;
}
