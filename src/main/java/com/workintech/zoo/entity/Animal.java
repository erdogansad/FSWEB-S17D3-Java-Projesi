package com.workintech.zoo.entity;

import com.workintech.zoo.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Animal {
    private int id;
    private String name;
    private double weight;
    private Gender gender;
}
