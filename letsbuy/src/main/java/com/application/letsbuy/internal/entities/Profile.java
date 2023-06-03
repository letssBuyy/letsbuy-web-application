package com.application.letsbuy.internal.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Setter
@Table(name = "profile")
public class Profile implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
