package com.diets.excel.domain;

import jakarta.persistence.Entity;

import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name")
    private String foodName;

    private String brand;

    private int weights;

    @Column(name = "calorie", columnDefinition = "TEXT")
    private String calorie;

    @Column(name = "carbohydrate", columnDefinition = "TEXT")
    private String carbohydrate;

    @Column(name = "protein", columnDefinition = "TEXT")
    private String protein;

    @Column(name = "fat", columnDefinition = "TEXT")
    private String fat;

}