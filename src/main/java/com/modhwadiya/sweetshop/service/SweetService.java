package com.modhwadiya.sweetshop.service;

import com.modhwadiya.sweetshop.model.Sweet;
import com.modhwadiya.sweetshop.repository.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Sweet operations.
 * Handles business logic for adding, deleting, searching, purchasing and restocking sweets.
 */
@Service
public class SweetService {

    @Autowired
    private SweetRepository sweetRepository;

    /**
     * Add a new sweet to the shop.
     */
    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    /**
     * Get all sweets available in the shop.
     */
    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    /**
     * Delete a sweet by ID.
     */
    public void deleteSweet(Long id) {
        sweetRepository.deleteById(id);
    }

    /**
     * Search sweets by name (case-insensitive, contains).
     */
    public List<Sweet> searchByName(String name) {
        return sweetRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Search sweets by category (case-insensitive, contains).
     */
    public List<Sweet> searchByCategory(String category) {
        return sweetRepository.findByCategoryContainingIgnoreCase(category);
    }

    /**
     * Search sweets within a given price range.
     */
    public List<Sweet> searchByPriceRange(double minPrice, double maxPrice) {
        return sweetRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Purchase sweets: decrease stock if enough is available.
     */
    public Sweet purchaseSweet(Long id, int quantity) throws Exception {
        Optional<Sweet> optionalSweet = sweetRepository.findById(id);

        if (optionalSweet.isPresent()) {
            Sweet sweet = optionalSweet.get();
            if (sweet.getQuantityInStock() >= quantity) {
                sweet.setQuantityInStock(sweet.getQuantityInStock() - quantity);
                return sweetRepository.save(sweet);
            } else {
                throw new Exception("Not enough stock available!");
            }
        } else {
            throw new Exception("Sweet not found!");
        }
    }

    /**
     * Restock sweets: increase stock quantity.
     */
    public Sweet restockSweet(Long id, int quantity) throws Exception {
        Optional<Sweet> optionalSweet = sweetRepository.findById(id);

        if (optionalSweet.isPresent()) {
            Sweet sweet = optionalSweet.get();
            sweet.setQuantityInStock(sweet.getQuantityInStock() + quantity);
            return sweetRepository.save(sweet);
        } else {
            throw new Exception("Sweet not found!");
        }
    }

}
