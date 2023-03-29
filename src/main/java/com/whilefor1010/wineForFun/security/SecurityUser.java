/*package com.whilefor1010.wineForFun.security;

import com.whilefor1010.wineForFun.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    //private final List<SimpleGrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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

   /* public static UserDetails fromUser(User user){

        GrantedAuthority[] = new Collection<GrantedAuthority>()

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),  {
        }
        );


    }*/

/*}*/
