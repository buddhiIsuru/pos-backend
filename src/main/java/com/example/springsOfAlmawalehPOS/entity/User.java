package com.example.springsOfAlmawalehPOS.entity;

import com.example.springsOfAlmawalehPOS.permission.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "outlet_id",referencedColumnName = "id")
    private Outlet outlet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id",referencedColumnName = "id")
//    private Role role;
}
