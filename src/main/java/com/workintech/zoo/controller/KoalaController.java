package com.workintech.zoo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.enums.Gender;
import com.workintech.zoo.exceptions.AnimalValidator;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping("/")
    public List<Koala> getkoalas() {
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getKoala(@PathVariable int id) {
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(koalas, id);
        return koalas.get(id);
    }

    @PostMapping("/")
    public Koala addKoala(@RequestBody Map<String, Object> payload) {
        int id = koalas.size() + 1;
        String name = (String) payload.get("name");
        double weight = (double) payload.get("weight");
        double sleepHour = (double) payload.get("sleepHour");
        Gender gender = Gender.valueOf((String) payload.get("gender"));
        Koala koala = new Koala(id, name, weight, gender, weight, sleepHour);

        AnimalValidator.isIdExist(koalas, koala.getId());
        AnimalValidator.isAnimalValid(koala);
        AnimalValidator.isKoalaValid(koala);

        koalas.put(id, koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        double weight = (double) payload.get("weight");
        double sleepHour = (double) payload.get("sleepHour");
        Gender gender = Gender.valueOf((String) payload.get("gender"));
        Koala koala = new Koala(id, name, weight, gender, weight, sleepHour);

        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(koalas, id);
        AnimalValidator.isAnimalValid(koala);
        AnimalValidator.isKoalaValid(koala);

        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala deleteKoala(@PathVariable int id) {
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(koalas, id);
        Koala koala = koalas.get(id);
        koalas.remove(id);
        return koala;
    }
}
