package com.example.demo.web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String type;
    private int life;

    public Character() {

    }
    public Character(String name, String type, int life) {
        this.name = name;
        this.type = type;
        this.life = life;
    }
    public Character(int id, String name, String type, int life) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() >= 3) {
            this.name = name;
        } else {
            this.name = "DefaultName";
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("Magicien") || type.equals("Guerrier")) {
            this.type = type;
        } else {
            this.type = "Guerrier";
        }
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(int life) {
        if (life > 0 && life <= 10) {
            this.life = life;
        } else {
            this.life = 5;
        }
    }

    @Override
    public String toString() {
        return this.name + " " + this.type + " " + this.life + " " + this.id;
    }
}