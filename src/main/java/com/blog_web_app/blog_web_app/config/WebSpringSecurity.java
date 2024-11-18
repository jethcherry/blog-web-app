package com.blog_web_app.blog_web_app.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> {
            try {
                csrf.disable()
                        .authorizeHttpRequests(authorize ->
                                authorize.requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/register**")).permitAll()
                                        .requestMatchers(new AntPathRequestMatcher("/admin/**"))
                                        .hasAnyRole("ADMIN", "GUEST")
                        )
                        .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/admin/posts")
                                .loginProcessingUrl("/login")
                                .permitAll()
                        )
                        .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                        );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }
}
