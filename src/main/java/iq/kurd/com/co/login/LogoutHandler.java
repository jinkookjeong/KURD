package iq.kurd.com.co.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import iq.kurd.com.KurdProperties;
import iq.kurd.com.util.login.LoginUtil;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null) {
			authentication = null;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("=======================[Cmd] logout START===============================================");
		}
		HttpSession session = null;
		session = request.getSession(false);
		try {

			response.addCookie(LoginUtil.makeCookie(StrCtnt.USR_ID, null));
			response.addCookie(LoginUtil.makeCookie(StrCtnt.AUTHO_CD, null));
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

			// 특정 쿠키만 삭제하기
			Cookie kc = new Cookie("usrId", null);
			kc.setMaxAge(0);
			response.addCookie(kc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("=======================[Cmd] logout END===============================================");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("PpsProperties.getProperty('JonepsPTUrl') : "
					+ KurdProperties.getProperty("JonepsPTUrl"));
		}
		if((System.getProperty("os.name").toUpperCase().startsWith("WINDOWS"))){
			setDefaultTargetUrl(KurdProperties.getProperty("localPTUrl"));
		} else{
			setDefaultTargetUrl(KurdProperties.getProperty("JonepsPTUrl"));
		}
		super.onLogoutSuccess(request, response, authentication);
	}
}