package com.workintech.zoo.entity;

import com.workintech.zoo.enums.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kangaroo extends Animal {
    private double height;
    private boolean isAggressive;

    public Kangaroo(int id, String name, double weight, Gender gender, double height, boolean isAggressive) {
        super(id, name, weight, gender);
        this.height = height;
        this.isAggressive = isAggressive;
    }
}
