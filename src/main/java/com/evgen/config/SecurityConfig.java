package com.evgen.config;

import com.evgen.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.evgen.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/login", "/sign-up").permitAll()
                .antMatchers("/users").hasAnyRole("USER", "ADMIN")
                .antMatchers("/accounts").hasRole("ADMIN")
               // .antMatchers("/sign-up", "/login").anonymous()    // AUTH USER CANNOT GO THERE, ONLY ANON
               // .antMatchers( "/tickets/buy", "/trains",
                //        "/arcs", "/passengers", "/routes/new/arcs",
                 //        "/account").authenticated()               // ANY AUTH USER
               // .antMatchers("/accounts", "/users").hasRole("ROLE_ADMIN")
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("email")
                .failureUrl("/login?error=true")    // HERE WILL REDIRECT IF BAD CREDENTIALS OR USER NOT FOUND
                .and()
                .exceptionHandling()
                .accessDeniedPage("/")  // WHEN AUTH USER WANT TO LOGIN OR SIGN UP ( WANT ANON-PAGES )
                .and().logout()
                .invalidateHttpSession(true);


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
