package com.managementTool.project.Config;

import com.managementTool.project.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(UserService userService, CustomAuthenticationEntryPoint authenticationEntryPoint) {
        this.userService = userService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(this.userService);
        return  daoAuthenticationProvider;
    }

    @Override
    protected void configure(@NonNull HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/addUser").permitAll()
                .antMatchers("/addRole").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/products/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/v1/products/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/products/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAnyRole("ADMIN")
                .antMatchers("/admin").hasAnyRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER")
                .anyRequest().authenticated().and().httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);

    }
}
