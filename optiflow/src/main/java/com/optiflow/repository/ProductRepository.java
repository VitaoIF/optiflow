package com.optiflow.repository;

import com.optiflow.entities.Product;
import com.optiflow.entities.enums.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT p FROM Product p
            WHERE (:name IS NULL OR LOWER(p.name) LIKE %:name%)
            AND (:brand IS NULL OR LOWER(p.brand) LIKE %:brand%)
            AND (:type IS NULL OR p.type = :type)
            AND (:minPrice IS NULL OR p.price >= :minPrice)
            AND (:maxPrice IS NULL OR p.price <= :maxPrice)
            AND (:active IS NULL OR p.active = :active)
            """)
    Page<Product> findAllWithFilters(
            String name,
            String brand,
            ProductType type,
            Double minPrice,
            Double maxPrice,
            Boolean active,
            Pageable pageable
    );
}
