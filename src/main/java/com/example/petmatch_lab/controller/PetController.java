package com.example.petmatch_lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import com.example.petmatch_lab.service.PetService;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pets/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
     var pet = petService.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Pet not found"
        ));

    model.addAttribute("pet", pet);
    return "pets/detail";
}

}