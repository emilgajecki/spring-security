package com.example.security1.filters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    // metoda przechwytuje zapytanie http i moze wykonywac wiele operacji
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // filtrujemy witaj i nadajemy uprawnienia - wszystko możesz
        http.authorizeRequests()
                .antMatchers("/witaj").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                //dodawanie strony logowania - osobne sekcje oddzziela sie and
                .and()
                //wrzuca nas po zalogowaniu do witaj
                .formLogin().defaultSuccessUrl("/witaj").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();

    }

    // tworzymy bean, aby miec uzytkownikowK
    @Bean
    public UserDetailsService userDetailsService() {
        // metoda withDefaultPasswordEncoder - nie ma kodowanego hasla
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user1")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
