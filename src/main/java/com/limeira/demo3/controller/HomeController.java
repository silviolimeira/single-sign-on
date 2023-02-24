package com.limeira.demo3.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.limeira.demo3.entities.MyUser;

@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "<h1>Welcome home!</h1>";
	}

	@GetMapping("/user")
	public String user(Authentication authentication) {
		return "<h1>Welcome User!</h1><h2>" + authentication.getName() + "!<h2>";
	}

	@GetMapping("/admin")
	public String admin(Authentication authentication) {
		MyUser myUser = (MyUser) authentication.getPrincipal();
		
		return "<h1>Welcome Admin</h1><h2>" + authentication.getAuthorities() + "!</h2>" +
		"<p>MyUser:<br>User name: " + myUser.getUsername() +
		"<br>User lastname: " + myUser.getLastName() +
		"<br>User fullname: " + myUser.getFullName() +
		"<br>User email address: " + myUser.getEmailAddress() +
		"<br>Authorities> " + myUser.getAuthorities();
	}

}
