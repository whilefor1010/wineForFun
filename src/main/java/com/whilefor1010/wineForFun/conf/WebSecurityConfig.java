package com.whilefor1010.wineForFun.conf;

import com.whilefor1010.wineForFun.models.Permission;
import com.whilefor1010.wineForFun.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/catalog").permitAll()
                        //.requestMatchers("/catalog/**").hasAuthority(Permission.WINES_WRITE.getPermission())
                        .requestMatchers("/catalog/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        //.password(passwordEncoder().encode("p1"))
                        .password("p1")
                        .roles(Role.USER.name())
                        //.authorities(Role.USER.getAuthorities())
                        .build();

        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        //.password(passwordEncoder().encode("p1"))
                        .password("p1")
                        .roles(Role.ADMIN.name())
                        //.authorities(Role.ADMIN.getAuthorities())
                        .build();


        return new InMemoryUserDetailsManager(user, admin);
    }

    /*@Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }*/



}
