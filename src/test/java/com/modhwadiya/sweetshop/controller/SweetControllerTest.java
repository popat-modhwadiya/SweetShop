
package com.modhwadiya.sweetshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modhwadiya.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Basic integration tests for SweetController.
 * Shows simple TDD checks for all main endpoints.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SweetControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddSweet() throws Exception
    {
        Sweet sweet = new Sweet("Test Sweet", "Test Category", 20.0, 10);

        mockMvc.perform(post("/sweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sweet)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Sweet"));
    }

    @Test
    void testGetAllSweets() throws Exception {
        mockMvc.perform(get("/sweets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testDeleteSweet() throws Exception {
        // First, add a sweet to delete
        Sweet sweet = new Sweet("Delete Sweet", "Temp", 10.0, 5);
        String content = objectMapper.writeValueAsString(sweet);

        String response = mockMvc.perform(post("/sweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Sweet created = objectMapper.readValue(response, Sweet.class);

        mockMvc.perform(delete("/sweets/" + created.getId()))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Kaju", "Halwa", "Jamun"})
    void testSearchByName_WithMultipleValues(String name) throws Exception {
        mockMvc.perform(get("/sweets/search")
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testSearchByCategory() throws Exception {
        mockMvc.perform(get("/sweets/search")
                        .param("category", "Nut-Based"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testSearchByPriceRange() throws Exception {
        mockMvc.perform(get("/sweets/search")
                        .param("minPrice", "5")
                        .param("maxPrice", "60"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


}