
package com.modhwadiya.sweetshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modhwadiya.sweetshop.model.Sweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}