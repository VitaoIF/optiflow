package com.optiflow.service;

import com.optiflow.dto.request.SaleItemRequest;
import com.optiflow.dto.request.SaleRequest;
import com.optiflow.dto.response.SaleResponse;
import com.optiflow.entities.Client;
import com.optiflow.entities.Product;
import com.optiflow.entities.Sale;
import com.optiflow.entities.SaleItem;
import com.optiflow.exceptions.custom.ClientNotFoundException;
import com.optiflow.exceptions.custom.ProductNotFoundException;
import com.optiflow.exceptions.custom.SaleNotFoundException;
import com.optiflow.mapper.SaleItemMapper;
import com.optiflow.mapper.SaleMapper;
import com.optiflow.repository.ClientRepository;
import com.optiflow.repository.ProductRepository;
import com.optiflow.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public SaleResponse create(SaleRequest request){

        Client client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado"));

        List<SaleItem> items = new ArrayList<>();

        for (SaleItemRequest saleItemRequest: request.items()){
            Product product = productRepository.findById(saleItemRequest.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

            SaleItem saleItem = SaleItemMapper.toEntity(saleItemRequest, product);

            items.add(saleItem);
        }

        Double totalPrice = items.stream()
                .mapToDouble(
                        item ->
                                item.getQuantity() * item.getUnitPrice())
                .sum();
        Sale sale = SaleMapper.toEntity(request, items, client, totalPrice);

        for(SaleItem item : items){
            item.setSale(sale);
        }

        Sale savedSale = saleRepository.save(sale);

        return SaleMapper.toSaleResponse(savedSale);
    }

    @Transactional
    public Page<SaleResponse> findAll(Pageable pageable){
        Page<Sale> sales = saleRepository.findAll(pageable);
        return sales.map(SaleMapper::toSaleResponse);
    }

    @Transactional
    public SaleResponse findById(Long id){
        Sale sale = saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException("Venda não encontrada "));
        return SaleMapper.toSaleResponse(sale);
    }

    @Transactional
    public void delete(Long id){
        saleRepository.deleteById(id);
    }
}
