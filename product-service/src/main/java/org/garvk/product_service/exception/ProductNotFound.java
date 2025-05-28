package org.garvk.product_service.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound() {
        super("Product Not Found");
    }
}
