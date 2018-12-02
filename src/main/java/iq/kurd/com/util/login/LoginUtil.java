package iq.kurd.com.util.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import iq.kurd.com.KurdProperties;
import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.vo.JonepsActor;

public class LoginUtil {
	public static Cookie makeCookie(String cookieName, String cookieValue){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");

		String domain = "";
		//System.out.println("1YJS os.name >>>>>>>>>>>>>>>>>>>>>>>>>>>> : " + System.getProperty("os.name"));
		if((System.getProperty("os.name").toUpperCase().startsWith("WINDOWS"))){
			domain = KurdProperties.getProperty("localDomain");
		}else{
			domain = KurdProperties.getProperty("JonepsDomain");
		}
		//System.out.println("1YJS domain >>>>>>>>>>>>>>>>>>>>>>>>>> : " + domain);
		if(domain!=null && !"".equals(domain)){
			//cookie.setDomain(domain);
		}
		cookie.setDomain("localhost");
		return cookie;
	}
	
	public static Cookie makeCookie(String cookieName, String cookieValue, int age){
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(age);
		cookie.setPath("/");
		
		String domain = "";
		System.out.println("YJS os.name >>>>>>>>>>>>>>>>>>>>>>>>>>>> : " + System.getProperty("os.name"));
		if((System.getProperty("os.name").toUpperCase().startsWith("WINDOWS"))){
			domain = KurdProperties.getProperty("localDomain");
		}else{
			domain = KurdProperties.getProperty("JonepsDomain");
		}
		System.out.println("YJS domain >>>>>>>>>>>>>>>>>>>>>>>>>> : " + domain);
		if(domain!=null && !"".equals(domain)){
			cookie.setDomain(domain);
		}
		
		return cookie;
	}
	
	
	/**
	 * <pre>
	 * 
	 * getActor Session에 담겨있는 현재 사용자 정보를 반환한다.
	 * 
	 * </pre>
	 * 
	 * @author : saint
	 * @date   : 2016. 6. 14.
	 * @param request
	 * @return Session에 담겨있는 현재 사용자 정보
	 */
	public static JonepsActor getActor(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		JonepsActor actor = (JonepsActor) session.getAttribute(StrCtnt.JONEPS_ACTOR);
		if (actor == null) {
			actor = new JonepsActor();
			session.setAttribute(StrCtnt.JONEPS_ACTOR, actor);
			System.out.println("session settig!!! : "+session.getAttribute(StrCtnt.JONEPS_ACTOR));
		}
		return actor;
	}
}
