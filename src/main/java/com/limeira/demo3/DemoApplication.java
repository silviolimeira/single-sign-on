package com.limeira.demo3;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//	@Bean
//	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, DataSource dataSource) {
//		return args -> {
//			UserDetails user = User.builder().username("user")
//					.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW").roles("USER")
//					.build();
//			UserDetails admin = User.builder().username("admin")
//					.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//					.roles("USER", "ADMIN").build();
//			JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//			users.createUser(user);
//			
//			users.createUser(admin);
//		};
//	}

}
