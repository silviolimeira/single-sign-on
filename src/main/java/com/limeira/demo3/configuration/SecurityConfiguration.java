package com.limeira.demo3.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(authConfig -> {
//			authConfig.requestMatchers("/h2-console/*").permitAll();
//			authConfig.requestMatchers(HttpMethod.GET, "/").permitAll();
//			authConfig.requestMatchers(HttpMethod.GET, "/user").hasAnyAuthority("USER", "ROLE_USER", "OIDC_USER");
//			authConfig.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
//			authConfig.anyRequest().authenticated();
//		}).csrf().disable().headers().frameOptions().disable().and().formLogin(Customizer.withDefaults())
//				.httpBasic(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults());
//
//		return http.build();
//	}

	// Example using many filterChains

	@Bean
	@Order(100)
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/user").authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/user/*").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER");
			authConfig.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	@Order(101)
	SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
		http.securityMatcher("/admin").authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/admin/*").hasAnyAuthority("ROLE_ADMIN");
			authConfig.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	@Order(102)
	SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
		http.securityMatcher("/").authorizeHttpRequests(authConfig -> {
			authConfig.anyRequest().permitAll();
		}).formLogin(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	@Order(103)
	SecurityFilterChain securityFilterChain3(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authConfig -> {
			authConfig.requestMatchers("/h2-console/*").permitAll();
			authConfig.anyRequest().denyAll();
		}).csrf().disable().headers().frameOptions().disable().and().formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults()).oauth2Login(Customizer.withDefaults());
		return http.build();
	}


	@Bean
	JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		return jdbcUserDetailsManager;
	}

	// option 1
	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
	}

}
