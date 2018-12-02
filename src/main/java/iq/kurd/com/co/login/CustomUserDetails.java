package iq.kurd.com.co.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -4450269958885980297L;
	private String username;
	private String signCerti;

	public CustomUserDetails(String username, String signCerti) {
		this.username = username;
		this.signCerti = signCerti;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

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