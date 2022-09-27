package com.example.demo.web;

import com.example.demo.CharacterDAO;
import com.example.demo.web.Character;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("API pour les opérations CRUD sur les personnages.")
@RestController
public class CharacterController {
    @Autowired
    private CharacterDAO characterDAO;

    @ApiOperation(value = "Récupère la liste de tous les personnages en base de données")
    @GetMapping("/characters")
    public List<Character> getCharacters() {
        List<Character> charactersList = characterDAO.findAll();
        return charactersList;
    }

    @ApiOperation(value = "Récupère un personnage en fonction de son ID")
    @GetMapping("/characters/{id}")
    public Character getCharacter(@PathVariable("id") int id) {
        Character character = characterDAO.findById(id);
        return character;
    }

    @ApiOperation(value = "Ajoute un nouveau personnage dans la liste de tous les personnages")
    @PostMapping("/characters/add")
    public void addCharacter(@RequestBody Character character) {
        characterDAO.save(character);
    }

    @ApiOperation(value = "Modifie un personnage existant")
    @PutMapping("/characters/{id}")
    public void updateCharacter(@RequestBody Character modifiedCharacter, @PathVariable int id) {
        Character actualCharacter = characterDAO.findById(id);
        if (actualCharacter.getName() != modifiedCharacter.getName() && modifiedCharacter.getName() != null) {
            actualCharacter.setName(modifiedCharacter.getName());
        }
        if (actualCharacter.getType() != modifiedCharacter.getType() && modifiedCharacter.getType() != null) {
            actualCharacter.setType(modifiedCharacter.getType());
        }
        if (actualCharacter.getLife() != modifiedCharacter.getLife() && modifiedCharacter.getLife() != null) {
            actualCharacter.setLife(modifiedCharacter.getLife());
        }
        characterDAO.save(actualCharacter);
    }

    @ApiOperation(value = "Supprime un personnage existant")
    @DeleteMapping("/characters/{id}")
    public void deleteCharacter(@PathVariable("id") int id) {
        characterDAO.deleteById(id);
    }


}