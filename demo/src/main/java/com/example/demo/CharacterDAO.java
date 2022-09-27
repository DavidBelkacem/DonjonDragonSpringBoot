package com.example.demo;

import com.example.demo.web.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharacterDAO extends JpaRepository<Character, Integer> {
    List<Character> findAll();
    Character findById(int id);
    void deleteById(int id);
}
