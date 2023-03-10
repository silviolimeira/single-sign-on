package com.limeira.demo3.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.limeira.demo3.entities.MyUser;

public class MySecurityAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;
	
	private final boolean isAuthenticated;
	private final String name;
	private final String password;
	private final MyUser myUser;
	private final Collection<GrantedAuthority> authorities;
	
	public MySecurityAuthentication(Collection<GrantedAuthority> authorities, String name, MyUser myUser, String password) {
		this.authorities = authorities;
		this.name = name;
		this.password = password;
		this.myUser = myUser;
		this.isAuthenticated = password == null;
	}
	
	public static MySecurityAuthentication unauthenticated(String name, String passord) {
		return new MySecurityAuthentication(Collections.emptyList(), name, null, passord);
	}

	public static MySecurityAuthentication authenticated(MyUser myUser) {
		return new MySecurityAuthentication(myUser.getAuthorities(), myUser.getUsername(), myUser, null);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return myUser;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new IllegalArgumentException("Don't do this");
	}

	public String getPassword() {
		return password;
	}
	
}
