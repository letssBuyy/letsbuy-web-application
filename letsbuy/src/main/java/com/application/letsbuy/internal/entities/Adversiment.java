package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "adversiment")
public class Adversiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 50)
    private String title;
    @NotBlank
    @Size(max = 255)
    private String description;
    @DecimalMin("1.0")
    @DecimalMax("99999999.0")
    private Double price;
    private LocalDate postDate;
    private LocalDate lastUpdate;
    private LocalDate saleDate;
    private CategoryEnum category;
    private QualityEnum quality;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Adversiment(User user, String title, String description, Double price, LocalDate postDate, LocalDate lastUpdate, LocalDate saleDate, CategoryEnum category, QualityEnum quality) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.price = price;
        this.postDate = postDate;
        this.lastUpdate = lastUpdate;
        this.saleDate = saleDate;
        this.category = category;
        this.quality = quality;
    }
}

