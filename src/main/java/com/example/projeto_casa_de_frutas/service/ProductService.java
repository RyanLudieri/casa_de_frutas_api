package com.example.projeto_casa_de_frutas.service;

import com.example.projeto_casa_de_frutas.model.Product;
import com.example.projeto_casa_de_frutas.model.ProductType;
import com.example.projeto_casa_de_frutas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public List<Product> findByName(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }

    public Product findByType(ProductType type){
        return repository.findByType(type).orElseThrow(() -> new RuntimeException("Produto do tipo " + type + " não encontrado!" ));
    }

    public Product createProduct(Product product){
        return repository.save(product);
    }

    public Product updateProduct(Product newProduct, Long id){
        Product existingProduct = findById(id);

        // Atualiza todos os campos
        if(newProduct.getName() != null) existingProduct.setName(newProduct.getName());
        if(newProduct.getPrice() != null) existingProduct.setPrice(newProduct.getPrice());
        if(newProduct.getType() != null) existingProduct.setType(newProduct.getType());

        return repository.save(existingProduct);
    }

    public void delete(Long id) {
        Product produto = findById(id);
        repository.delete(produto);
    }


}
