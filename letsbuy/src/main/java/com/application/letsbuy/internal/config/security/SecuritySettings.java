package com.application.letsbuy.internal.config.security;

import com.application.letsbuy.internal.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@AllArgsConstructor
@EnableWebSecurity
@Configuration
public class SecuritySettings extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/users/**").permitAll()
                .antMatchers(HttpMethod. POST, "/adversiments").permitAll()
                .antMatchers(HttpMethod.GET, "/adversiments").permitAll()
                .antMatchers(HttpMethod.GET, "/adversiments/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/adversiments/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/adversiments/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/emails/welcome").permitAll()
                .antMatchers(HttpMethod.POST, "/emails/trocar-senha").permitAll()
                .antMatchers(HttpMethod.GET, "/csv").permitAll()
                .antMatchers(HttpMethod.GET, "/csv/**").permitAll()
                .antMatchers(HttpMethod.GET, "/search-binary-price/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/adversiments/contest/**").permitAll()
                .antMatchers(HttpMethod.POST, "/adversiments/like/**").permitAll()
                .antMatchers(HttpMethod.GET, "/adversiments/like/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/adversiments/like/**").permitAll()
                .antMatchers(HttpMethod.POST, "/images/**").permitAll()
                .antMatchers(HttpMethod. POST, "/bank-account-users").permitAll()

                .anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
