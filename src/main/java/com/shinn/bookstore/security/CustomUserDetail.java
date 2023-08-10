package com.shinn.bookstore.security;

import com.shinn.bookstore.model.RoleEntity;
import com.shinn.bookstore.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String address;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }



    // Convert user to userdetail
    public static CustomUserDetail mapUserToUserDetail(UserEntity user) {
        // Get list role of user;
        List<GrantedAuthority> listAuthorities = user.getRoleEntities().stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName().name()))
                .collect(Collectors.toList());
        return new CustomUserDetail(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFullname(),
                user.getPhone(),
                user.getAddress(),
                listAuthorities
        );
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
