package com.limeira.demo3.entities;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyUser extends User {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String fullName;
	private String emailAddress;
	private LocalDate birthDate;

	public MyUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String firstName, String lastName, String emailAddress, LocalDate birthDate

	) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = firstName + " " + lastName;
		this.emailAddress = emailAddress;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "MyUser [firstName=" + firstName + ", lastName=" + lastName + ", fullName=" + fullName
				+ ", emailAddress=" + emailAddress + ", birthDate=" + birthDate + "]";
	}

}
