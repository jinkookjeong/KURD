package iq.kurd.com.co.login;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import iq.kurd.com.co.login.service.LoginService;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;
import iq.kurd.com.util.format.LocaleUtil;
 
public class CustomAuthenticationProvider implements AuthenticationProvider {

	protected Logger logger = Logger.getLogger(getClass());

	@Resource MessageSource messageSource;

	@Resource(name="loginService")
	LoginService loginService;

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("authentication: "+authentication);
		System.out.println("authentication: "+authentication.getName());
		System.out.println("authentication: "+authentication.getPrincipal());
		
		
		//String usrId = (String)authentication.getPrincipal();
		//String signCerti = (String)authentication.getCredentials();
		
		return authentication;
	}
	
	public Authentication authenticate2(Authentication authentication) throws AuthenticationException {
		String usrId = (String)authentication.getPrincipal();
		String signCerti = (String)authentication.getCredentials();
		
		try {
		    signCerti = URLDecoder.decode(signCerti, "UTF-8");
		} catch(UnsupportedEncodingException uee) {
			signCerti = (String)authentication.getCredentials();
		}

		LoginVo loginVo = new LoginVo();
		loginVo.setUsrId(usrId);
		loginVo.setSignCerti(signCerti);

		JonepsActor actor = null;
		String message = null;

		try {
			if(StringUtils.isNotEmpty(signCerti)) {
				actor = loginService.loginCheck(loginVo);
			} else {
				actor = loginService.loginCheckById(loginVo);
			}

			if(actor != null){
				loginVo.setUsrId(actor.getUsrId());
				if(StringUtils.isNotEmpty(signCerti)) {
					actor.setAuthCdList(loginService.selectUsrAuthList(loginVo));
				} else {
					actor.setAuthCdList(loginService.selectUsrAuthListById(loginVo));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(actor != null){
			if (!"Y".equals(actor.getAprovYn())) {
				message = "unapproved user";
				throw new UsernameNotFoundException(message);
			}

			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			for(UsrAuthoVo usrAuthoVo : actor.getAuthCdList()) {
				roles.add(new SimpleGrantedAuthority(usrAuthoVo.getAuthoCd()));
			}

			UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(actor.getUsrId(), signCerti, roles);
			result.setDetails(new CustomUserDetails(actor.getUsrId(), signCerti));

			return result;
		} else {
			if (StringUtils.isNotEmpty(signCerti)) {
				message = "certificate is not registered";
			} else {
				message = "USER_ID is not registered";
			}
			if(logger.isDebugEnabled()){
				logger.debug("langCode : " + LocaleUtil.getLangCode());
				logger.debug(message);
			}
//			message = messageSource.getMessage("error.wrongUserId", null, null, java.util.Locale.getDefault());
			throw new UsernameNotFoundException(message);
		}
	}
}