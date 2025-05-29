package com.example.projeto_casa_de_frutas.repository;

import com.example.projeto_casa_de_frutas.model.Product;
import com.example.projeto_casa_de_frutas.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Query(value = "SELECT * FROM product p " +
            "WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:type IS NULL OR p.type = :type) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)", nativeQuery = true)
    List<Product> searchProductsNative(
            @Param("name") String name,
            @Param("type") String type,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );


    List<Product> findByNameContainingIgnoreCase(String name);
    Optional<Product> findByType(ProductType type);

}
