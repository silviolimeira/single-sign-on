//package com.limeira.demo3.config;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationEventPublisher;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.limeira.demo3.services.MyUserDetailsService;
//
//import providers.MySecurityAuthenticationProvider;
//import providers.TestAuthenticationProvider;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//	
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
//		
//		http.authorizeHttpRequests(authConfig -> {
//			authConfig.requestMatchers(HttpMethod.GET, "/").permitAll();
//			authConfig.requestMatchers(HttpMethod.GET, "/user").hasAnyAuthority("USER", "ROLE_USER", "OIDC_USER");
//			authConfig.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
//			authConfig.anyRequest().authenticated();
//		}).csrf().disable()
//		.apply(new MySecurityLoginConfigurer(userDetailsService))
//		.and()
//		.formLogin(Customizer.withDefaults())   // login with browser and Build in form
//		.httpBasic(Customizer.withDefaults());  // login with Insonia or Postman and Basic Auth
//
//		return http.build();
//	}
//
//	@Bean
//	UserDetailsService myUserDetailsService() {
//		return new MyUserDetailsService();
//	}
//	
//	@Bean
//	BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public ApplicationListener<AuthenticationSuccessEvent> successEvent() {
//		return event -> {
//			System.err.println("Sucess Login" + event.getAuthentication().getClass().getName() + " - " + event.getAuthentication().getName());
//		};
//	}
//
//	@Bean
//	public ApplicationListener<AuthenticationSuccessEvent> failureEvent() {
//		return event -> {
//			System.err.println("Bad Credentials Login" + event.getAuthentication().getClass().getName() + " - " + event.getAuthentication().getName());
//		};
//	}
//
//}
