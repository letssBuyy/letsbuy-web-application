package com.application.letsbuy.internal.entities;

import com.application.letsbuy.internal.enums.ActiveInactiveEnum;
import com.application.letsbuy.internal.utils.AgeRange;
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
    @Column
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column
    private String name;

    @Email
    @NotBlank
    @Column
    private String email;

    @CPF
    @NotBlank
    @Column
    private String cpf;

    @NotBlank
    @Column
    private String password;

    @AgeRange(minAge = 18)
    @Column
    private LocalDate birthDate;

    @Column
    @Pattern(
            regexp = "^(?:\\+55\\s?)?(?:\\([1-9][1-9]\\)|[1-9][1-9])\\s?(?:9?[1-9]\\d{3})[-\\s]?\\d{4}$",
            message = "Numero de celular inválido!"
    )
    private String phoneNumber;

    @Column
    private String profileImage;

    @Column
    private String cep;

    @Column
    private String road;

    @Column
    private Long number;

    @Column
    private String neighborhood;

    @Column
    private String complement;

    @Column
    private String state;

    @Column
    private String city;

    @Enumerated(EnumType.STRING)
    @Column
    private ActiveInactiveEnum isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Adversiment> adversiments;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @OneToOne
    private BankAccount bankAccount;

    @Column
    private Double amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Withdraw> withdraws;

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
        return null;
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
