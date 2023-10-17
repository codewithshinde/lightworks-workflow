package com.lw.spring.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.lw.spring.feign.StoreApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class StoreController {

    @Autowired
    private StoreApi storeapi;

    @GetMapping("/products")
    public JsonNode getProducts() {
        return storeapi.getProducts();
    }
}
