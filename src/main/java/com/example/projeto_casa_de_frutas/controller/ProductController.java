package com.example.projeto_casa_de_frutas.controller;

import com.example.projeto_casa_de_frutas.model.Product;
import com.example.projeto_casa_de_frutas.model.ProductType;
import com.example.projeto_casa_de_frutas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product save(@RequestBody Product product){
        return service.createProduct(product);
    }

    @GetMapping
    public List<Product> listAll(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/nameSearch/{name}")
    public List<Product> getByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/typeSearch/{type}")
    public Product getByName(@PathVariable ProductType type) {
        return service.findByType(type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public Product patch(@PathVariable Long id, @RequestBody Product updates) {
        return service.updateProduct(updates, id);
    }


}
