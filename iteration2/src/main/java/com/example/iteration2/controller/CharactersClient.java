package com.example.iteration2.controller;

import com.example.iteration2.model.Character;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class CharactersClient {
    private static final String URL = "http://localhost:8080/characters/";
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/characters")
    private String getCharactersList(Model model) {
        List charactersList= restTemplate.getForObject(URL, List.class);
        model.addAttribute("players", charactersList);
        return "characters";
    }

    @GetMapping("/characters/{id}")
    public String displayCharacter(@PathVariable("id") int id, Model model) {
        String URL_GET_CHARACTER = URL + id;
        Character character= restTemplate.getForObject(URL_GET_CHARACTER, Character.class);
        model.addAttribute("character", character);
        return "character";
    }

    @GetMapping("characters/add")
    public String getAddCharacterForm(Model model) {
        model.addAttribute("character", new Character());
        model.addAttribute("method", "post");
        return "form-character";
    }

    @PostMapping("/characters/add")
        public String addCharacter(@ModelAttribute Character newCharacter, Model model) {
        String URL_ADD_CHARACTER = URL + "add";
        restTemplate.postForLocation(URL_ADD_CHARACTER, newCharacter);
        return "redirect:/characters";
    }

    @DeleteMapping("/characters/{id}")
        public String deleteCharacter(@PathVariable("id") int id) {
            String URL_DELETE_CHARACTER = URL + id;
            restTemplate.delete(URL_DELETE_CHARACTER);
            return "redirect:/characters";
        }

    @GetMapping("characters/update/{id}")
        public String getUpdateCharacterForm(@PathVariable("id") int id, Model model) {
            String URL_GET_CHARACTER = URL + id;
            Character character= restTemplate.getForObject(URL_GET_CHARACTER, Character.class);
            model.addAttribute("character", character);
            model.addAttribute("method", "put");
            return "form-character";
        }

    @PutMapping("/characters/{id}")
        public String updateCharacter(@PathVariable("id") int id, @ModelAttribute Character character) {
            String URL_UPDATE_CHARACTER = URL + id;
            restTemplate.put(URL_UPDATE_CHARACTER, character);
            return "redirect:/characters";
        }


}
