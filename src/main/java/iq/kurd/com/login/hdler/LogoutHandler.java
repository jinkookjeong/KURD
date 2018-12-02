package iq.kurd.com.login.hdler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Repository;

import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.vo.JonepsActor;

@Repository
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {
 
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null) {
			authentication = null;
		}
		log.info("=======================[Cmd] LogoutHandler START===============================================");
		
		HttpSession session = null;
		session = request.getSession(false);
		try {
			if (session != null) {
				session.removeAttribute(StrCtnt.JONEPS_ACTOR);
				session.invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("=======================[Cmd] LogoutHandler END ===============================================");
		
		//if((System.getProperty("os.name").toUpperCase().startsWith("WINDOWS"))){
		//	setDefaultTargetUrl(KurdProperties.getProperty("localPTUrl"));
		//} else{
		//	setDefaultTargetUrl(KurdProperties.getProperty("JonepsPTUrl"));
		//}
		super.onLogoutSuccess(request, response, authentication);
	}
}