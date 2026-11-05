package com.example.petmatch_lab.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.example.petmatch_lab.repository.InMemoryPetRepository;

class PetServiceTests {

    private final PetService petService = new PetService(new InMemoryPetRepository());

    @Test
    void shouldReturnAvailablePets() {
        assertTrue(!petService.findAll().isEmpty());
    }

    @Test
    void shouldFindPetWithKnownId() {
        var pet = petService.findById(1L);

        assertTrue(pet.isPresent());
        assertEquals("Luna", pet.orElseThrow().getName());
    }

    @Test
    void shouldReturnEmptyWhenPetDoesNotExist() {
        assertTrue(petService.findById(999L).isEmpty());
    }
}