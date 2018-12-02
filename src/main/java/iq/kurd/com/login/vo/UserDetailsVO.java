package iq.kurd.com.login.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserDetailsVO implements UserDetails{

	private String username;
	private String signCerti;

	public UserDetailsVO(String username, String signCerti) {
		this.username = username;
		this.signCerti = signCerti;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		System.out.println("UserDetailsVO getAuthorities>>>");
		return authorities;
	}

	public String getUsername() {
		return username;
	}
	
	public String getSignCerti() {
		return signCerti;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		return null;
	}
}

