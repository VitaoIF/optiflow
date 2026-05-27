package com.optiflow.service;

import com.optiflow.dto.request.SaleItemRequest;
import com.optiflow.dto.request.SaleRequest;
import com.optiflow.dto.response.SaleResponse;
import com.optiflow.entities.Client;
import com.optiflow.entities.Product;
import com.optiflow.entities.Sale;
import com.optiflow.entities.SaleItem;
import com.optiflow.entities.enums.SaleStatus;
import com.optiflow.exceptions.custom.*;
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

        Client client = findClientById(request.clientId());

        List<SaleItem> items = createSaleItems(request.items());

        Double totalPrice = calculateTotalPrice(items);
        Sale sale = SaleMapper.toEntity(request, items, client, totalPrice);

        associateItemsWithSale(items, sale);

        Sale savedSale = saleRepository.save(sale);

        return SaleMapper.toSaleResponse(savedSale);
    }

    public SaleResponse cancelSale(Long saleId){
        Sale sale = findSaleById(saleId);

        validateSaleCancell(sale);

        restoreStock(sale);

        sale.setSaleStatus(SaleStatus.CANCELED);

        Sale savedSale = saleRepository.save(sale);

        return SaleMapper.toSaleResponse(savedSale);
    }

    private void restoreStock(Sale sale) {

        for(SaleItem item : sale.getSaleItems()) {

            Product product = item.getProduct();

            product.setStockQuantity(
                    product.getStockQuantity() + item.getQuantity()
            );
        }
    }

    private Sale findSaleById(Long saleId){
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("Venda não encontrada"));
    }

    private void validateSaleCancell(Sale sale){
        if(sale.getSaleStatus() == SaleStatus.CANCELED){
            throw new InvalidSaleException("Venda já cancelada");
        }
    }

    private Client findClientById(Long clientId){
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado"));
    }

    private List<SaleItem> createSaleItems(List<SaleItemRequest> itemRequest){
        List<SaleItem> saleItems = new ArrayList<>();

        for(SaleItemRequest request: itemRequest){
            Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado "));

            validateStock(product, request.quantity());
            decreaseStock(product, request.quantity());

            SaleItem item = SaleItemMapper.toEntity(request, product);
            saleItems.add(item);

        }
        return saleItems;
    }

    private void validateStock(Product product, Integer quantity){

        if(product.getStockQuantity() < quantity){

            throw new InsufficientStockException(
                    "Estoque insuficiente para o produto: "
                            + product.getName()
            );
        }
    }

    private void decreaseStock(Product product, Integer quantity){
        product.setStockQuantity(product.getStockQuantity() - quantity);
    }

    private Double calculateTotalPrice(List<SaleItem> saleItems){
        return saleItems.stream()
                .mapToDouble(item ->
                        item.getQuantity()
                                * item.getUnitPrice()
                )
                .sum();
    }

    private void associateItemsWithSale(List<SaleItem> items, Sale sale){
        for(SaleItem item : items){
            item.setSale(sale);
        }
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
