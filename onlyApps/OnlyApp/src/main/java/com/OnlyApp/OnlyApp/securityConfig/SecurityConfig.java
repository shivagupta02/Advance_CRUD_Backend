package com.OnlyApp.OnlyApp.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.OnlyApp.OnlyApp.serviceImpl.UserDetailServiceImpl;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
  SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
		
//	return http.csrf(csrf->csrf.disable())
//		.authorizeHttpRequests(request->request
//				.anyRequest().permitAll())
//		.requestMatchers("/api/**").permitAll()
//		.requestMatchers("/api/getAllEmp").hasRole("EMPLOYEE")
//		.anyRequest().authenticated())
//		.httpBasic(withDefaults()).build();
//		
//	}
		return http
			    .csrf(csrf -> csrf.disable()) // Correctly disable CSRF protection
			    .authorizeHttpRequests(authorizeRequests -> authorizeRequests
			        .requestMatchers("**").permitAll() // Allow access to all endpoints under "/api/"
//			        .anyRequest().authenticated() // Any other request must be authenticated
			    )
			    .httpBasic(withDefaults()) // Enable basic HTTP authentication
			    .build();
	}
			    
	
	@Bean
	AuthenticationManager authenticationManager(UserDetailServiceImpl cUserDetailsService,
			PasswordEncoder passwordEncoder) {
 
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(cUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
 
		return new ProviderManager(authenticationProvider);
	}
	  @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
