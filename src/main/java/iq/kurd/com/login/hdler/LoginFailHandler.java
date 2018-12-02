package iq.kurd.com.login.hdler;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Repository;

import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.vo.JonepsActor;

@Repository
public class LoginFailHandler implements AuthenticationFailureHandler {
	
	private String loginidname; // 로그인 id값이 들어오는 input 태그 name
	private String loginpasswdname; // 로그인 password 값이 들어오는 input 태그 name
	private String loginredirectname; // 로그인 성공시 redirect 할 URL이 지정되어 있는 input 태그 name
	private String exceptionmsgname; // 예외 메시지를 request의 Attribute에 저장할 때 사용될 key 값
	private String defaultFailureUrl; // 화면에 보여줄 URL(로그인 화면)
	
	public LoginFailHandler() {
		this.loginidname = "signCerti";
//		this.loginpasswdname = "signCerti";
		this.loginredirectname = "loginRedirect";
		this.exceptionmsgname = "loginFailMsg";
		this.defaultFailureUrl = "/um/loginForm.do";
	} 
	
	public String getLoginidname() {
		return loginidname;
	}
	
	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}
	
	public String getLoginpasswdname() {
		return loginpasswdname;
	}
	
	public void setLoginpasswdname(String loginpasswdname) {
		this.loginpasswdname = loginpasswdname;
	}
	
	public String getExceptionmsgname() {
		return exceptionmsgname;
	}
	
	public String getLoginredirectname() {
		return loginredirectname;
	}
	
	public void setLoginredirectname(String loginredirectname) {
		this.loginredirectname = loginredirectname;
	}
	
	public void setExceptionmsgname(String exceptionmsgname) {
		this.exceptionmsgname = exceptionmsgname;
	}
	
	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}
	
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {
		// Request 객체의 Attribute에 사용자가 실패시 입력했던 로그인 ID와 비밀번호를 저장해두어 로그인 페이지에서 이를 접근하도록 한다
//		String loginid = request.getParameter(loginidname);
//		String loginpasswd = request.getParameter(loginpasswdname);
//		String loginRedirect = request.getParameter(loginredirectname);
		
//		request.setAttribute(loginidname, loginid);
//		request.setAttribute(loginpasswdname, loginpasswd);
//		request.setAttribute(loginredirectname, loginRedirect);
		
		// Request 객체의 Attribute에 예외 메시지 저장
//		request.setAttribute(exceptionmsgname, auth.getMessage());
		
//		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
		
		System.out.println("onAuthenticationFailure: "+auth.getMessage());
		request.setAttribute("resultMsg", auth.getMessage());
		//request.getRequestDispatcher("/WEB-INF/jsp/com/exception/LoginError.jsp").forward(request, response);		
		request.getRequestDispatcher("/um/loginForm.do").forward(request, response);
	}
	
//	 private ObjectMapper objectMapper = new ObjectMapper();
//	@Override
//    public void onAuthenticationFailure(
//      HttpServletRequest request,
//      HttpServletResponse response,
//      AuthenticationException exception) 
//      throws IOException, ServletException {
//  
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        Map<String, Object> data = new HashMap<>();
//        data.put(
//          "timestamp", 
//          Calendar.getInstance().getTime());
//        data.put(
//          "exception", 
//          exception.getMessage());
// 
//        response.getOutputStream()
//          .println(objectMapper.writeValueAsString(data));
//    }
	
}
