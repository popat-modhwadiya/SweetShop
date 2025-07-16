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

}
