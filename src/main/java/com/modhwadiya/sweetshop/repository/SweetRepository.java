package com.modhwadiya.sweetshop.repository;

import com.modhwadiya.sweetshop.model.Sweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Sweet entity.
 * Provides basic CRUD and custom finder methods.
 */
public interface SweetRepository extends JpaRepository<Sweet, Long>
{

    /**
     * Find sweets by name containing given string (case-insensitive).
     */
    List<Sweet> findByNameContainingIgnoreCase(String name);

    /**
     * Find sweets by category containing given string (case-insensitive).
     */
    List<Sweet> findByCategoryContainingIgnoreCase(String category);

    /**
     * Find sweets within a given price range.
     */
    List<Sweet> findByPriceBetween(double minPrice, double maxPrice);
}

