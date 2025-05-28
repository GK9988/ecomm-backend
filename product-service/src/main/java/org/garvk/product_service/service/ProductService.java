package org.garvk.product_service.service;

import org.garvk.product_service.exception.ProductNotFound;
import org.garvk.product_service.model.Product;
import org.garvk.product_service.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo aInProductRepo){
        this.productRepo = aInProductRepo;
    }

    public Product addNewProduct(Product aInProduct){
        return productRepo.save(aInProduct);
    }

    public Product getProduct(Long aInId){
        Product aOutProduct = productRepo.findById(aInId).orElse(null);
        if(null == aOutProduct) throw new ProductNotFound();
        return aOutProduct;
    }

    public List<Product> getProductByIds(List<Long> aInIds){
        return productRepo.findAllById(aInIds);
    }

}
