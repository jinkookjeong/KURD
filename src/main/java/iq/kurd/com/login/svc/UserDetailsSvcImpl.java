package iq.kurd.com.login.svc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import iq.kurd.com.login.dao.UserDAO;
import iq.kurd.com.login.vo.UserVO;

@Service("userDetailsService")
public class UserDetailsSvcImpl implements UserDetailsService {
 
@Resource(name = "userDAO")
UserDAO userDAO;
 
 protected Log log = LogFactory.getLog(this.getClass());
	
  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
	  UserBuilder builder = null;
	  try {   
		  
		  UserVO userVO = new UserVO();
		  userVO.setUserId(userId);
		  log.info("userId: >>>>> "+userId);
		  
		  userVO = userDAO.selectUser(userVO);
		  if(userVO != null) {
			  log.info("DB UserVO: "+userVO.toString());
			  
			  List<UserVO> authList = userDAO.selectUserAuthList(userVO);
			  log.info("authList: "+authList.size());
		      
	          int authCount = authList.size();
	    	  String role[] = new String[authCount]; 
			  for (int i = 0; i < authCount; i++) {			 
				 role[i] = authList.get(i).getAuthNm();
				 log.info("role: "+role[i]);
		  	  }

			  builder = org.springframework.security.core.userdetails.User.withUsername(userId);
			  builder.username(userVO.getUserId());
		      builder.password(userVO.getPassword());
		      builder.roles(role);
			  log.info("builder.toString() >>> "+builder.toString());
			
		  } else {
			  log.info("notFound");
			  throw new UsernameNotFoundException("User not found.");
		  }
	  }catch(Exception ex) {
		  ex.printStackTrace();
		  throw new UsernameNotFoundException(ex.getMessage(), ex.getCause());
	  }
	  return builder.build();
  }
}