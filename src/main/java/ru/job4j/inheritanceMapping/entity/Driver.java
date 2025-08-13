package ru.job4j.inheritancemapping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Driver extends Employee {
    @Column
    private char category;

    @Column(name = "car_brand")
    private String carBrand;

    public Driver(String name, int salary, double experience, char category, String carBrand) {
        super(name, salary, experience);
        this.category = category;
        this.carBrand = carBrand;
    }
}
