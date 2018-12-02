package iq.kurd.com.login.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import iq.kurd.com.AbstractCtrl;
import iq.kurd.com.co.login.StrCtnt;

@Controller("loginCtrl")
public class LoginCtrl extends AbstractCtrl{
	
	@RequestMapping(value = "/um/loginForm.do")
	public String loginForm(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		
       log.info("LOGIN form init");
		
		return "loginForm.com";

	}
	
	@RequestMapping(value = "/um/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		
        log.info("logout form init");
		log.info("=======================[Cmd] logout START===============================================");
		
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
		
		log.info("=======================[Cmd] logout END ===============================================");
 		
 		
		return "redirect:/";
	}

}
