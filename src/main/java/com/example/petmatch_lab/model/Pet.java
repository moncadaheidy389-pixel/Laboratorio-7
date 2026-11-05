package com.example.petmatch_lab.model;

public class Pet {

    private final long id;
    private final String name;
    private final String species;
    private final int age;
    private final String description;
    private final String size;

    public Pet(long id, String name, String species, int age, String description, String size) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.description = description;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }
}