package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.TrackingStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tracking")
public class Tracking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private TrackingStatus status;

    @NotNull
    @Column
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "adversiment_id")
    private Adversiment adversiment;

    @PrePersist
    private void prePersist() {
        this.createdDate = LocalDateTime.now();
    }
}
