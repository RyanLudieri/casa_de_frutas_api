package com.example.projeto_casa_de_frutas.controller;

import com.example.projeto_casa_de_frutas.model.Product;
import com.example.projeto_casa_de_frutas.model.ProductType;
import com.example.projeto_casa_de_frutas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3001")
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
    public Product getById(@PathVariable Integer id) {
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
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public Product patch(@PathVariable Integer id, @RequestBody Product updates) {
        return service.updateProduct(updates, id);
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,  // receber como String aqui
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        ProductType productType = null;

        // Converter tipo string para enum, tratar string vazia
        if (type != null && !type.trim().isEmpty()) {
            try {
                productType = ProductType.valueOf(type.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Tipo inválido, pode lançar exceção ou tratar como null
                productType = null;
            }
        }

        // Tratar name string vazia como null
        if (name != null && name.trim().isEmpty()) {
            name = null;
        }

        return service.searchProducts(name, productType, minPrice, maxPrice);
    }




}
