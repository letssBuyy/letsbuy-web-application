package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "adversiment")
public class Adversiment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private String postDate;

    @Column
    private String lastUpdate;

    @Column
    private String saleDate;

    @Column
    private CategoryEnum category;

    @Column
    private QualityEnum quality;

    @Column
    private Integer priority;


    public Adversiment(String title, String description, Double price, String postDate, String lastUpdate, String saleDate, CategoryEnum category, QualityEnum quality, Integer priority) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.postDate = postDate;
        this.lastUpdate = lastUpdate;
        this.saleDate = saleDate;
        this.category = category;
        this.quality = quality;
        this.priority = priority;

    }
}

