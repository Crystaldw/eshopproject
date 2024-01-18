package de.telran.eshopproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {

    @SequenceGenerator(name = "name_seq", sequenceName = "name_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name_seq")
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;
    @Enumerated(EnumType.STRING)
    private EnumUserRole enumUserRole;

    @Enumerated(EnumType.STRING)
    private EnumGender enumGender;

    public User(String firstName,
                String lastName,
                String userName,
                String password,
                String email,
                EnumUserRole enumUserRole,
                EnumGender enumGender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.enumUserRole = enumUserRole;
        this.enumGender = enumGender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(enumUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
