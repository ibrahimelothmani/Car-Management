package ibrahim.car.management.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.management.HttpSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configure HttpSecurity using SecurityFilterChain bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admin/**").hasRole("admin")  // Restrict access to admin role
                                .requestMatchers("/user/**").hasRole("user")   // Restrict access to user role
                                .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .clientRegistrationRepository(clientRegistrationRepository()));
        return http.build();
    }

    // Register Keycloak authentication provider with Spring Security
    @Bean
    public KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        return new KeycloakAuthenticationProvider();
    }

    // Keycloak-specific session manager
    @Bean
    public HttpSessionManager httpSessionManager() {
        return new HttpSessionManager();
    }

    // Optional: Keycloak Spring Boot config resolver (if you need more control over Keycloak config)
    @Bean
    public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    // Configure OAuth2 client registration repository if needed (for integration with OAuth2)
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        // Configure the repository for OAuth2 client registration, this could be autoconfigured by Spring Boot
        return null;
    }

    // Optional: Configure the OAuth2 authorized client repository
    @Bean
    public OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository() {
        // Configure authorized client repository
        return null;
    }
}



