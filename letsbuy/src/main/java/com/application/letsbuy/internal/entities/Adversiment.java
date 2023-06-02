package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.AdversimentEnum;
import com.application.letsbuy.internal.enums.CategoryEnum;
import com.application.letsbuy.internal.enums.QualityEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @Enumerated(EnumType.STRING)
    private QualityEnum quality;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "adversiment_id")
    private List<Image> images;
    @Enumerated(EnumType.STRING)
    private AdversimentEnum isActive;
    @Enumerated(EnumType.STRING)
    private AdversimentEnum contest;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "adversiment_id")
    private List<Tracking> trackings;

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
        this.isActive = AdversimentEnum.ACTIVE;
        this.contest = AdversimentEnum.INACTIVE;
    }

    @Override
    public String toString() {
        return "Adversiment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", postDate=" + postDate +
                ", lastUpdate=" + lastUpdate +
                ", saleDate=" + saleDate +
                ", category=" + category +
                ", quality=" + quality +
                ", user=" + user +
                '}';
    }
}

