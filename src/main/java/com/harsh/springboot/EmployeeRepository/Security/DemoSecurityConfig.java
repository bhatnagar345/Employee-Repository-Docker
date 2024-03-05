package com.harsh.springboot.EmployeeRepository.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for JDBC... No Hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id =?");

        //define query to retrieve authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                 configurer
                         .requestMatchers("/").hasRole("EMPLOYEE")
                         .requestMatchers("/leaders/**").hasRole("MANAGER")
                         .requestMatchers("/systems/**").hasRole("ADMIN")
                         .anyRequest().authenticated()
        ).formLogin(form ->
                form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        ).logout(logout ->
                          logout.permitAll()
        ).exceptionHandling(configurer ->
                                     configurer.accessDeniedPage("/access-denied")
        );
        // Use HTTP basic Authentication
        http.httpBasic(Customizer.withDefaults());

        //Disable Cross Site Request Forgery(CSRF)
        //in general, not required for stateless REST APIs that use POST,PUT,DELETE and/or PATCH
        http.csrf(csrf-> csrf.disable());
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager()
//    {
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER","ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,mary,susan);
//    }


}
