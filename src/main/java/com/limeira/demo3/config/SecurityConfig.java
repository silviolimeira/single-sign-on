package com.limeira.demo3.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.limeira.demo3.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.GET, "/").permitAll()
						.requestMatchers("/tokenbybody").permitAll()
						.requestMatchers("/user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
						.requestMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	UserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ApplicationListener<AuthenticationSuccessEvent> successEvent() {
		return event -> {
			System.err.println("Sucess Login" + event.getAuthentication().getClass().getName() + " - " + event.getAuthentication().getName());
		};
	}

	@Bean
	public ApplicationListener<AuthenticationSuccessEvent> failureEvent() {
		return event -> {
			System.err.println("Bad Credentials Login" + event.getAuthentication().getClass().getName() + " - " + event.getAuthentication().getName());
		};
	}
	
	
}
