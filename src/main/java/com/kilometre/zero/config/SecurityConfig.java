package com.kilometre.zero.config;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class SecurityConfig {
	
	@Value("${app.jwt.secret}")
    private String secret;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf(csrf -> csrf.disable())
        	.cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/", "/login", "/oauth2/**", "/exchange_token", "/loginSuccess", "/error").permitAll()
                // USER ENDPOINTS
                .requestMatchers("/api/user/lastSync").permitAll()
                // ACTIVITY ENDPOINTS
                .requestMatchers("/api/activity/syncActivities").permitAll()
                .requestMatchers("/api/activity/getActivitiesForPlanPeriod").permitAll()
                // PLAN ENDPOINTS
                .requestMatchers("/api/plan/createPlan").permitAll()
                .requestMatchers("/api/plan/getAllPlans").permitAll()
                .requestMatchers("/api/plan/getPlanById").permitAll()
                // PLANNED ACTIVITY ENDPOINTS
                .requestMatchers("/api/planned-activity/create").permitAll()
                .requestMatchers("/api/planned-activity/getPlannedActivitiesForPlan").permitAll()
                .requestMatchers("/api/planned-activity/update/**").permitAll()
                .requestMatchers("/api/planned-activity/linkActivity").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                    .defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        request -> request.getServletPath().startsWith("/api/")
                    )
            )
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/loginSuccess", true)
            )
            .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public JwtEncoder jwtEncoder() {
    	SecretKey key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<SecurityContext>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
    	SecretKey originalKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build();
        return jwtDecoder;
    }
}