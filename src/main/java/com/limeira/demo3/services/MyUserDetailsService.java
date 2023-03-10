package com.limeira.demo3.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.limeira.demo3.entities.MyUser;
import com.limeira.demo3.entities.Users;
import com.limeira.demo3.repositories.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Users> userByUsername = usersRepository.findByUsername(username);
		if (!userByUsername.isPresent()) {
			log.error("Could not find user with that username: {}", username);
			throw new UsernameNotFoundException("Invalid credentials!");
		}

		Users user = userByUsername.get();
		if (user == null || !user.getUsername().equals(username)) {
			log.error("Could not find user with that username: {}", username);
			throw new UsernameNotFoundException("Invalid credentials!");
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		user.getAuthorities().stream().forEach(authority -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		});

		return new MyUser(user.getUsername(), user.getPassword(), user.getAccountNonExpired(), user.getAccountNonLocked(), 
				user.getCredentialsNonExpired(), user.getEnabled(), grantedAuthorities,
				user.getFirstName(), user.getLastName(), user.getEmailAddress(), user.getBirthDate());
	}

}
