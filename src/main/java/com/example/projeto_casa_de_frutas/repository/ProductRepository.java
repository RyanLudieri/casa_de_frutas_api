package com.example.projeto_casa_de_frutas.repository;

import com.example.projeto_casa_de_frutas.model.Product;
import com.example.projeto_casa_de_frutas.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    Optional<Product> findByType(ProductType type);

}
