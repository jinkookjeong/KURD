package iq.kurd.com.co.login.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.service.LoginService;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginCTRL {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Resource
	MessageSource messageSource;

	@Resource(name = "loginService")
	LoginService loginService;

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("=======================[Cmd] logout START===============================================");
		}
		HttpSession session = null;
		session = request.getSession(false);
		try {

			if (session != null) {
				session.removeAttribute(StrCtnt.JONEPS_ACTOR);
				session.invalidate();
			}

			session = request.getSession(true);

			// 전체 쿠키 삭제하기
			Cookie[] cookies = request.getCookies();

			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];

					// 저장된 쿠키 이름을 가져온다
					String cName = c.getName();

					// 쿠키값을 가져온다
					String cValue = c.getValue();

					if (logger.isDebugEnabled()) {
						logger.debug(cName + " : " + cValue);
					}

					// 쿠키의 유효시간을 0으로 설정하여 만료시킨다
					cookies[i].setMaxAge(0);

					// 응답 헤더에 추가한다
					response.addCookie(cookies[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("=======================[Cmd] logout END===============================================");
		}

		return "redirect:/pt/main.do";
	}

	/*
	@RequestMapping(value = "/um/loginForm.do")
	public String loginForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("=======================[Cmd] loginForm START===============================================");
		}
		HttpSession session = null;
		session = request.getSession();
		try {
//			session.setAttribute(StrCtnt.REFER_URL, request.getHeader("REFERER"));
//			if (logger.isDebugEnabled()) {
//				logger.debug(StrCtnt.REFER_URL + " : " + request.getHeader("REFERER"));
//			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("=======================[Cmd] loginForm END===============================================");
		}

		return "/login/loginForm.um";
	}*/
}
