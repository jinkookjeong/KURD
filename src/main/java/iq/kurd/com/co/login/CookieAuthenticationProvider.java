package iq.kurd.com.co.login;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import iq.kurd.com.co.login.service.LoginService;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CookieAuthenticationProvider implements AuthenticationProvider { 
	
	protected Logger logger = Logger.getLogger(getClass());
     
	@Resource MessageSource messageSource;
	
	@Resource(name="loginService")
	LoginService loginService;
	
	private String usrId;
	private String signCerti;
    
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    public void setUsrId(String usr_id){
    	this.usrId = usr_id;
    }
    
    public void setSignCerti(String sign_certi){
    	this.signCerti = sign_certi;
    }
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println("user_id : " + usr_id);
//        String passwd = (String)authentication.getCredentials();
        
        LoginVo loginVo = new LoginVo();
        loginVo.setUsrId(usrId);
        loginVo.setSignCerti(signCerti);
		
		JonepsActor actor = null;
		String message = null;
        
		try {
			actor = loginService.loginCheckCookie(loginVo);
			actor.setAuthCdList(loginService.selectUsrAuthList(loginVo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(actor != null){
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			for(UsrAuthoVo usrAuthoVo : actor.getAuthCdList()) {
				roles.add(new SimpleGrantedAuthority(usrAuthoVo.getAuthoCd()));
			}

			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(usrId, signCerti, roles);
			result.setDetails(new CustomUserDetails(usrId, signCerti));

			return result;
		} else {
			message = "none Id";
			if(logger.isDebugEnabled()){
				logger.debug(message);
			}
			message = messageSource.getMessage("error.wrongUserId", null, null, java.util.Locale.getDefault());
			throw new UsernameNotFoundException(message);
		}
	}
}