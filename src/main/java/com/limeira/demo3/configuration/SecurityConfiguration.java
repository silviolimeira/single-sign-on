package com.limeira.demo3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	UserDetailsService userDetailsService() {
		var admin = User.builder().username("cesar").password("123456").roles("USER", "ADMIN").build();
		var user = User.builder().username("silvio").password("123456").roles("USER").build();

		return new InMemoryUserDetailsManager(admin, user);

	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers(HttpMethod.GET, "/").permitAll();
			authConfig.requestMatchers(HttpMethod.GET, "/user").hasRole("USER");
			authConfig.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return http.build();
	}


}
