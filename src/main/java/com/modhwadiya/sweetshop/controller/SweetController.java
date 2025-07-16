package com.modhwadiya.sweetshop.controller;

import com.modhwadiya.sweetshop.model.Sweet;
import com.modhwadiya.sweetshop.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling Sweet Shop operations.
 * Provides APIs for add, view, delete, search, purchase and restock sweets.
 */
@RestController
@RequestMapping("/sweets")
public class SweetController
{

    @Autowired
    private SweetService sweetService;

    /**
     * Add a new sweet.
     * Example POST JSON:
     * {
     * "name": "Rasgulla",
     * "category": "Milk-Based",
     * "price": 15.0,
     * "quantityInStock": 30
     * }
     */
    @PostMapping
    public ResponseEntity<Sweet> addSweet(@RequestBody Sweet sweet)
    {
        Sweet createdSweet = sweetService.addSweet(sweet);
        return new ResponseEntity<>(createdSweet, HttpStatus.CREATED);
    }
}