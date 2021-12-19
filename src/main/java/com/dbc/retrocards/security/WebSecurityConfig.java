package com.dbc.retrocards.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().cors().and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/auth").permitAll()
                .antMatchers(HttpMethod.POST,"/Sprint", "/retrospectiva", "/kudobox").hasRole("FACILITADOR")
                .antMatchers(HttpMethod.POST, "/itemderetrospectiva", "/kudocards").hasRole("MEMBRO")
                .antMatchers(HttpMethod.DELETE,"/itemderetrospectiva", "/kudocards").hasRole("MEMBRO")
                .antMatchers(HttpMethod.GET, "/**").hasAnyRole("FACILITADOR", "MEMBRO")
//                .antMatchers("/**").hasRole("FACILITADOR") //ROLE_ADMIN
//                .antMatchers("/**").hasRole("FACILITADOR") //ROLE_ADMIN
                .anyRequest().authenticated()

                //filtro de autenticação...
                .and().addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**",
                "/v2/api-docs",
                "/swagger-ui.html",
                "/swagger-resources/**"
                );
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
