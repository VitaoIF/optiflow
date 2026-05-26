package com.optiflow.service;

import com.optiflow.dto.request.ProductRequest;
import com.optiflow.dto.response.ProductResponse;
import com.optiflow.entities.Product;
import com.optiflow.exceptions.custom.PrescriptionNotFoundException;
import com.optiflow.mapper.ProductMapper;
import com.optiflow.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductResponse insert(ProductRequest request){
        Product product = ProductMapper.toEntity(request);

        Product saved = repository.save(product);

        return ProductMapper.toProductResponse(saved);
    }

    public Page<ProductResponse> findAll(Pageable pageable){
        Page<Product> products = repository.findAll(pageable);
        return products.map(ProductMapper::toProductResponse);
    }

    public ProductResponse findById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new PrescriptionNotFoundException("Produto não encontrado"));
        return ProductMapper.toProductResponse(product);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public ProductResponse update(Long id, ProductRequest request){
        Product entity = repository.getReferenceById(id);
        updateProduct(entity, request);
        Product updated = repository.save(entity);
        return ProductMapper.toProductResponse(updated);
    }

    private void updateProduct(Product product, ProductRequest request){
        product.setName(request.name());
        product.setBrand(request.brand());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setType(request.type());
        product.setActive(request.active());
    }
}
