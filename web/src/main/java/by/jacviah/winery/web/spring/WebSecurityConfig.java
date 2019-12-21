package by.jacviah.winery.web.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/unauthorized")
                .permitAll()
                .antMatchers("/home**",
                        "/bottle",
                        "/bottle-list**")
                .hasAnyAuthority("ROLE_USER", "ROLE_SOMMELIER")
                .antMatchers("/subscribe")
                .hasAuthority("ROLE_USER")
                .antMatchers("/subscribers", "/recommendation")
                .hasAuthority("ROLE_SOMMELIER")
                .anyRequest().authenticated()
        .and().
                exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint();
    }
}
