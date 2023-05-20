package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.utils.AgeRange;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Table(name = "user")
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;
    @Email
    @NotBlank
    private String email;
    @CPF
    @NotBlank
    private String cpf;
    @NotBlank
    private String password;
    @AgeRange(minAge = 18)
    private LocalDate birthDate;
    @Column
    @Pattern(
            regexp = "^(?:\\+55\\s?)?(?:\\([1-9][1-9]\\)|[1-9][1-9])\\s?(?:9?[1-9]\\d{3})[-\\s]?\\d{4}$",
            message = "Numero de celular inválido!"
    )
    private String phoneNumber;
    private String profileImage;
    private String cep;
    private String road;
    private Long number;
    private String complement;
    @Enumerated(EnumType.STRING)
    private ActiveInactiveEnum isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private List<Adversiment> adversiments;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @OneToMany
    private List<BankAccountUser> bankAccounts = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String cpf, String password, LocalDate birthDate, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.isActive = ActiveInactiveEnum.ACTIVE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
