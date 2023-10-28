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
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                .antMatchers(HttpMethod. POST, "/api/adversiments").permitAll()
                .antMatchers(HttpMethod. POST, "/api/adversiments/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/adversiments").permitAll()
                .antMatchers(HttpMethod.GET, "/api/adversiments/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/adversiments/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/adversiments/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/emails/welcome").permitAll()
                .antMatchers(HttpMethod.POST, "/api/emails/trocar-senha").permitAll()
                .antMatchers(HttpMethod.GET, "/api/csv").permitAll()
                .antMatchers(HttpMethod.GET, "/api/csv/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/search-binary-price/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/adversiments/contest/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/adversiments/like/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/adversiments/like/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/adversiments/like/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/images/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/images/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/images/**").permitAll()
                .antMatchers(HttpMethod. POST, "/api/bank-account-users").permitAll()
                .antMatchers(HttpMethod.GET, "/api/bank-account-users/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/bank-account-users/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/bank-account-users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/chats/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/chats/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/chats/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/messages/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/messages/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/messages/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/searches/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/searches/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/trackings/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/payment-controll/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/payment-user-advertisements/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/payment-user-advertisements/**").permitAll()
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
