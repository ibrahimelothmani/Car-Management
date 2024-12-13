package ibrahim.car.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection (be cautious with this)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").permitAll()  // Allow access to the login page
                        .requestMatchers("/api/**").permitAll()  // Allow all API endpoints without authentication
                        .anyRequest().authenticated()  // Require authentication for other requests
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Ensure this is the correct login page URL
                        .permitAll()  // Allow everyone to access the login page
                        .defaultSuccessUrl("/dashboard", true)  // Redirect to /dashboard after successful login
                        .failureUrl("/login?error=true")  // Redirect to /login with an error param if login fails
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Enable a logout URL
                        .logoutSuccessUrl("/login?logout=true")  // Redirect to login page after successful logout
                        .permitAll()
                );

        return http.build();
    }
}
