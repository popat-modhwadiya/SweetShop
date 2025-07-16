package com.modhwadiya.sweetshop.controller;

import com.modhwadiya.sweetshop.model.Sweet;
import com.modhwadiya.sweetshop.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Get list of all sweets.
     */
    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        List<Sweet> sweets = sweetService.getAllSweets();
        return new ResponseEntity<>(sweets, HttpStatus.OK);
    }

    /**
     * Delete a sweet by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSweet(@PathVariable Long id) {
        try {
            sweetService.deleteSweet(id);
            return new ResponseEntity<>("Sweet deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Sweet not found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Search sweets by name, category or price range.
     * Example: /sweets/search?name=Kaju
     * Example: /sweets/search?category=Milk-Based
     * Example: /sweets/search?minPrice=10&maxPrice=50
     */
    @GetMapping("/search")
    public ResponseEntity<List<Sweet>> searchSweets(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {

        if (name != null) {
            return new ResponseEntity<>(sweetService.searchByName(name), HttpStatus.OK);
        } else if (category != null) {
            return new ResponseEntity<>(sweetService.searchByCategory(category), HttpStatus.OK);
        } else if (minPrice != null && maxPrice != null) {
            return new ResponseEntity<>(sweetService.searchByPriceRange(minPrice, maxPrice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Purchase sweet: decrease stock quantity.
     * Example POST JSON:
     * {
     *   "quantity": 5
     * }
     */
    @PostMapping("/{id}/purchase")
    public ResponseEntity<?> purchaseSweet(@PathVariable Long id, @RequestBody QuantityRequest request) {
        try {
            Sweet updatedSweet = sweetService.purchaseSweet(id, request.getQuantity());
            return new ResponseEntity<>(updatedSweet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * Simple request body for quantity operations.
     * Example:
     * {
     *   "quantity": 5
     * }
     */
    public static class QuantityRequest {
        private int quantity;

        public QuantityRequest() {
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }


}