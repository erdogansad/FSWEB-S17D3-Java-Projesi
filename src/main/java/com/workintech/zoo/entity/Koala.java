package com.workintech.zoo.entity;

import com.workintech.zoo.enums.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Koala extends Animal {
    private double sleepHour;

    public Koala(int id, String name, double weight, Gender gender, double height, double sleepHour) {
        super(id, name, weight, gender);
        this.sleepHour = sleepHour;
    }
}
