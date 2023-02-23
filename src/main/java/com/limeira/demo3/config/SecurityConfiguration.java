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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/h2-console/*").permitAll();
			authConfig.requestMatchers(HttpMethod.GET, "/").permitAll();
			authConfig.requestMatchers(HttpMethod.GET, "/user").hasAnyAuthority("USER", "ROLE_USER", "OIDC_USER");
			authConfig.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
			authConfig.anyRequest().authenticated();
		}).csrf().disable().formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
	
	@Bean
	UserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public ApplicationListener<AuthenticationSuccessEvent> successEvent() {
		return event -> {
			System.err.println("Success Login " + event.getAuthentication().getClass().getName() + " - " + event.getAuthentication().getName());
		};
	}
	
	@Bean
	public ApplicationListener<AuthenticationSuccessEvent> failureEvent() {
		return event -> {
			System.err.println("Bad Credentitals Login " + event.getAuthentication().getClass().getName() + " - " + event.getAuthentication().getName());
		};
	}


}
