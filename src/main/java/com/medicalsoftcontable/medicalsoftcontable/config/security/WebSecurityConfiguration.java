package com.medicalsoftcontable.medicalsoftcontable.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

  @Value("${role_user}")
  private String USER_ROLE;
  @Value("${role_admin}")
  private String ADMIN_ROLE;

  @Bean
  SecurityFilterChain defaultSecurityFilterChainProtected(HttpSecurity http) throws Exception {
    http.cors(
        (cors) -> {
          cors.disable();
        })
        .csrf(
            (csrf) -> {
              csrf.disable();
            })
        .authorizeHttpRequests(
            (auth) -> {
              auth.requestMatchers(new AntPathRequestMatcher("/"))
                  .permitAll()
                  .requestMatchers(new AntPathRequestMatcher("/api/**"))
                  .hasAnyRole(USER_ROLE, ADMIN_ROLE)
                  .requestMatchers(new AntPathRequestMatcher("/admin/**"))
                  .hasAnyRole(ADMIN_ROLE)
                  .anyRequest()
                  .permitAll();
            })
        .formLogin(
            (login -> {
              login.usernameParameter("email");
            }))
        .logout(
            logout -> {
              logout.invalidateHttpSession(true).clearAuthentication(true);
            });

    return http.build();
  }

}
