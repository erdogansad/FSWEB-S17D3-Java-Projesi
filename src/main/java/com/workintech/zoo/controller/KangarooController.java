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

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.enums.Gender;
import com.workintech.zoo.exceptions.AnimalValidator;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping("/")
    public List<Kangaroo> getkangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangaroo(@PathVariable int id) {
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        return kangaroos.get(id);
    }

    @PostMapping("/")
    public Kangaroo addKangaroo(@RequestBody Map<String, Object> payload) {
        int id = kangaroos.size() + 1;
        String name = (String) payload.get("name");
        double height = (double) payload.get("height");
        double weight = (double) payload.get("weight");
        Gender gender = Gender.valueOf((String) payload.get("gender"));
        boolean isAggressive = (boolean) payload.get("isAggressive");
        Kangaroo kangaroo = new Kangaroo(id, name, height, gender, weight, isAggressive);
        AnimalValidator.isIdExist(kangaroos, kangaroo.getId());
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);

        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        double height = (double) payload.get("height");
        double weight = (double) payload.get("weight");
        Gender gender = Gender.valueOf((String) payload.get("gender"));
        boolean isAggressive = (boolean) payload.get("isAggressive");
        Kangaroo kangaroo = new Kangaroo(id, name, height, gender, weight, isAggressive);
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        AnimalValidator.isAnimalValid(kangaroo);
        AnimalValidator.isKangarooValid(kangaroo);

        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id) {
        AnimalValidator.isIdValid(id);
        AnimalValidator.isIdNotExist(kangaroos, id);
        Kangaroo kangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return kangaroo;
    }
}
