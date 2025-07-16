package com.modhwadiya.sweetshop.config;


import com.modhwadiya.sweetshop.model.Sweet;
import com.modhwadiya.sweetshop.repository.SweetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class initializes the database with some sample sweets
 * when the application starts.
 */
@Component
public class DataInitializer implements CommandLineRunner
{

    private final SweetRepository sweetRepository;

    // Constructor injection
    public DataInitializer(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    @Override
    public void run(String... args) {
        // Adding 5 sample sweets to the database
        sweetRepository.save(new Sweet("Kaju Katli", "Nut-Based", 50.0, 20));
        sweetRepository.save(new Sweet("Gajar Halwa", "Vegetable-Based", 30.0, 15));
        sweetRepository.save(new Sweet("Gulab Jamun", "Milk-Based", 10.0, 50));
        sweetRepository.save(new Sweet("Chocolate Barfi", "Chocolate", 40.0, 25));
        sweetRepository.save(new Sweet("Candy Floss", "Candy", 5.0, 100));
    }
}
