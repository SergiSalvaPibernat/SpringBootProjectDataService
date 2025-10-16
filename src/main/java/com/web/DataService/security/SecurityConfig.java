package com.web.DataService.security;

import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //@Value("${rsa.public-key}") RSAPublicKey publicKey;
    @Value("${rsa.public-key}") RSAPublicKey publicKey;

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //Enable CORS
                .cors(Customizer.withDefaults())

                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                 .csrf(csrf -> csrf.disable())

                 .authorizeHttpRequests( auth -> auth
                         .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/", "/customers/validate", "/customers/validateRegister").permitAll()
                         .anyRequest().authenticated()
                )

                .oauth2ResourceServer((resourceServer) ->
                        resourceServer.jwt( (customizer) ->
                                customizer.decoder(jwtDecoder())
                        )
                )
                .build();

    }

}

