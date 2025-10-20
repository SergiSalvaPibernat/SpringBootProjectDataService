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

    @Value("${rsa.public-key}") RSAPublicKey publicKey;

    private JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                //  Enable CORS:
                .cors(Customizer.withDefaults())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                 .csrf(csrf -> csrf.disable())

                 .authorizeHttpRequests( auth -> auth
                         .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/", "/customers/validate", "/customers/validateRegister").permitAll()
                         .anyRequest().authenticated()
                )

                //  PART 8b -
                //  Define this application as an OAuth2 Resource Server.
                //  Configure it to expect receive JWTs on incoming requests.
                //  Decode JWTs using the JwtDecoder defined earlier.
                //  Insert code below:  v v v v v


                // Insert code above: ^ ^ ^ ^ ^
                .oauth2ResourceServer((resourceServer) ->
                        resourceServer.jwt( (customizer) ->
                                customizer.decoder(jwtDecoder())
                        )
                )
                .build();

    }

}

