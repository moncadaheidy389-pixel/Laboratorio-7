package com.example.petmatch_lab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.petmatch_lab.model.Pet;

@Repository
public class InMemoryPetRepository {

    private final List<Pet> pets = List.of(
        new Pet(1L, "Luna", "Perro", 3, "Le en canta cazar en las junglas.","Mediano"),
        new Pet(2L, "Milo", "Gato", 2, "Prefiere lugares tranquilos.","Pequeño"),
        new Pet(3L, "Nube", "Conejo", 1, "Es curioso y le gusta explorar.","Pequeño"),
        new Pet(4L, "Princesa", "Tortuga", 4, "Le gusta nadar.","Mediana"),
        new Pet(5L, "Rocky", "Perro", 5, "Es juguetón y le encanta jugar.","Grande")

    );

    public List<Pet> findAll() {
        return pets;
    }

    public Optional<Pet> findById(long id) {
        for (Pet pet : pets) {
            if (pet.getId() == id) {
                return Optional.of(pet);
            }
        }

        return Optional.empty();
    }
}