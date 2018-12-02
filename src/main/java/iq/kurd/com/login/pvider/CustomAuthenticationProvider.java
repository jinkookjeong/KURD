package iq.kurd.com.login.pvider;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import iq.kurd.com.login.dao.UserDAO;
import iq.kurd.com.login.vo.UserDetailsVO;
import iq.kurd.com.login.vo.UserVO;


public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	protected Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private PasswordEncoder passwordEncoder; 
		
    @Resource(name = "userDAO")
    UserDAO userDAO;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    };
    
    
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken result = null;
		String userId = (String)authentication.getPrincipal();
		String signCerti = (String)authentication.getCredentials();
		System.out.println(">>> userId: "+userId);
		System.out.println(">>> signCerti 1: "+signCerti);
		try {
			
		    try {
			    signCerti = URLDecoder.decode(signCerti, "UTF-8");
			} catch(UnsupportedEncodingException uee) {
				signCerti = (String)authentication.getCredentials();
			}
		    UserVO userVO = new UserVO();
		    userVO.setUserId(userId);
		    log.info("userId: >>>>> "+userId);

		    userVO = userDAO.selectUser(userVO);
		    
		    if(userVO != null) {
			      log.info("dbPasword: "+userVO.getPassword());
	
			      boolean bool = passwordEncoder.matches(signCerti, userVO.getPassword());
			      System.out.println("bool : "+bool);
			      if (!bool) {
			    	  throw new BadCredentialsException("The password does not match."); 
			      }
			      
			      List<UserVO> authList = userDAO.selectUserAuthList(userVO);
			      log.info("authList: "+authList.size());
			      
			      List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			      int authCount = authList.size();
			      for (int i = 0; i < authCount; i++) {			 
			    	 roles.add(new SimpleGrantedAuthority(authList.get(i).getAuthNm()));
			    	 System.out.println(authList.get(i).getAuthNm());
			      }
			    	
			      
			      result = new UsernamePasswordAuthenticationToken(userId, signCerti, roles);
			      result.setDetails(new UserDetailsVO(userId, signCerti));
			        
			      log.info("result>>> "+result.toString());
			      
			    } else {
			      log.info("notFound");
			      throw new UsernameNotFoundException("User not found.");
			    }
		   }catch(Exception ex) {
		      ex.printStackTrace();
		      throw new UsernameNotFoundException(ex.getMessage(), ex.getCause());
		   }	  
	
 	    log.info("result: "+result.getName());
		return result;
	}
}