package com.vimensa.ship.admin.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                // filter the /login request
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // and filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }
    /**
     * create an account to test
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // create a default account
        /*
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("hellohello")
                .roles("ADMIN");
        */
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select `phone`,`code`,`enabled` from `admin` where `phone`=?")
                .authoritiesByUsernameQuery("select `phone`,`role` from `user_role` where `phone` = ?");
    }
}
