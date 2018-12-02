package iq.kurd.com.co.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Repository;

import iq.kurd.com.co.login.dao.LoginDAO;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;
import iq.kurd.com.co.menu.service.MenuService;
import iq.kurd.com.co.menu.vo.MenuListVO;
import iq.kurd.com.constant.Constant;
import iq.kurd.com.util.format.ObjToConvert;
import iq.kurd.com.util.format.SystemUtil;
import iq.kurd.com.util.login.LoginUtil;

//@Repository
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Resource LoginDAO loginDAO;
	private static final Log logger = LogFactory.getLog(LoginSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Resource(name="menuService")
	MenuService menuService;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		String referUrl = null;
		
		try {
			
			JonepsActor actor = LoginUtil.getActor(request);
			HttpSession session = request.getSession();
			
			String signCerti = request.getParameter("signCerti");
			String encrCerti = request.getParameter("encrCerti");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String usrId = request.getParameter("usrId");

			System.out.println("encrCerti : "+encrCerti);
			System.out.println("username : "+username);
			System.out.println("password : "+password);
			System.out.println("usrId : "+usrId);
			
			try {
			    signCerti = URLDecoder.decode(signCerti, "UTF-8");
			} catch(UnsupportedEncodingException uee) {
				signCerti = (String)authentication.getCredentials();
			}	
			
			System.out.println("LoginSuccessHandler :"+request.getRequestURL());
			System.out.println("authentication :"+authentication);
			System.out.println("authentication.isAuthenticated() :"+authentication.isAuthenticated());
			System.out.println("authentication.getCredentials() :"+authentication.getCredentials());
			
			//이미 로그인 성공한 정보를 활용하여 Session에 넣는다
			System.out.println("authentication.getName(): "+authentication.getName());
			System.out.println("authentication.getPrincipal(): "+authentication.getPrincipal());
			
			List<GrantedAuthority> loginAuthList = (List<GrantedAuthority>)authentication.getAuthorities();		
			int authCount = loginAuthList == null ? 0 : loginAuthList.size();
			
			List<UsrAuthoVo> authList = new ArrayList<UsrAuthoVo>();
			for (int i = 0; i < authCount; i++) {
				UsrAuthoVo usrAuthoVo = new UsrAuthoVo();
				GrantedAuthority grantedAuthority = loginAuthList.get(i);
				
				usrAuthoVo.setUsrId(usrId);
				usrAuthoVo.setAuthoNm(grantedAuthority.getAuthority());
				authList.add(usrAuthoVo);
			}
			
			
			//LoginVo loginVo = new LoginVo();
			//loginVo.setUsrId("admin");
			//loginVo.setSignCerti(signCerti);
			session = request.getSession(true);
			
			actor.setMastNm(username);
			actor.setAprovYn("Y");
			actor.setUsrId(usrId);
			actor.setSignCerti(signCerti);
			actor.setAuthCdList(authList);
			System.out.println("actor.toString: "+actor.toString());
			
			// 세션유지시간 (1시간)
			session.setMaxInactiveInterval(60 * 60);
			session.setAttribute(StrCtnt.JONEPS_ACTOR, actor);
			
			//response.addCookie(LoginUtil.makeCookie(StrCtnt.USR_ID, actor.getUsrId()));
			//response.addCookie(LoginUtil.makeCookie(StrCtnt.SIGN_CERTI, actor.getSignCerti()));
			
//			if(StringUtils.isEmpty(request.getParameter("usrId"))) {
//				usrId = actor.getUsrId();
//			} else {
//				usrId = request.getParameter("usrId");
//			}
//			loginVo.setUsrId(usrId);
//			loginVo.setSignCerti(signCerti);
//			
//			if(StringUtils.isEmpty(signCerti)) {
//				actor = loginDAO.loginCheckById(loginVo);
//			} else {
//				actor = loginDAO.loginCheck(loginVo);
//			}
//			
//			if(actor != null) {
//				/**
//				 * loginDt update
//				 */
//				loginDAO.updateUsrLoginDt(loginVo);
//				
//				actor.setAuthCdList(loginDAO.selectUsrAuthList(loginVo));
//				session = request.getSession(true);
//				// 세션유지시간 (1시간)
//				session.setMaxInactiveInterval(60 * 60);
//				
//				response.addCookie(LoginUtil.makeCookie(StrCtnt.USR_ID, actor.getUsrId()));
//				response.addCookie(LoginUtil.makeCookie(StrCtnt.SIGN_CERTI, actor.getSignCerti()));
//				
//				//세션 클러스터링 사용 여부 확인
//				if(Constant.CLUSTER_YN){
//					
//					if(logger.isDebugEnabled()){
//						logger.debug("=======================login Chk OK=========");
//					}
//					
//					
////				response.addCookie(LoginUtil.makeCookie(StrCtnt.USR_ID, request.getParameter("usrId")));
//					
//					session.setAttribute(StrCtnt.JONEPS_ACTOR, actor);
//					
//					List<UsrAuthoVo> authCdList =  actor.getAuthCdList();
//					
//					if(ObjToConvert.isNotEmpty(authCdList)){
//						Map<String, Object> paraMap = new HashMap<String, Object>();
//						
//						MenuListVO topMenuListVO = new MenuListVO(); 
//						MenuListVO leftMenuListVO = new MenuListVO(); 
//						
//						List releList = new ArrayList();
//						
//						for(UsrAuthoVo authVo : authCdList){
//							releList.add(authVo.getAuthoCd());
//						}
//						
//						paraMap.put("roleList", releList);
//						
//						paraMap.put("myMenuCd", Constant.MY_MENU_CODE);
//						paraMap.put("myMenuCdPtrn", Constant.MY_MENU_CODE.substring(0, 4));
//						
//						topMenuListVO.setMnList(menuService.selectTopMenuList(paraMap));
//						session.setAttribute(Constant.TOP_MENU_LIST, topMenuListVO);
//						
//						leftMenuListVO.setMnList(menuService.selectLeftMnList(paraMap));
//						session.setAttribute(Constant.LEFT_MENU_LIST, leftMenuListVO);
//					}
//					
//				//세션클러스터링 미사용시 쿠키에 인증	
//				}else{
//					response.addCookie(LoginUtil.makeCookie(StrCtnt.USR_ID, request.getParameter("usrId")));
//					
//					Cookie[] cookies = request.getCookies();
//
//					if (cookies != null) {
//						for(int i=0; i<cookies.length; i++){
//							if(StrCtnt.REFER_URL.equals(cookies[i].getName())){
//								referUrl = cookies[i].getValue();
//							}
//						}
//					}
//				
//				}
//				
//			}
//
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setStatus(HttpServletResponse.SC_OK);
//		
//		if(StringUtils.isEmpty(referUrl)){
//			if (SystemUtil.isOsLocal().booleanValue()) {
//				referUrl = KurdProperties.getProperty("localPTUrl");
//			} else {
//				referUrl = KurdProperties.getProperty("JonepsPTUrl");
//			}
//		}
//		response.sendRedirect(referUrl);
		
		request.setAttribute("redirect", referUrl);
		request.setAttribute("message", "Success");
		request.getRequestDispatcher("/WEB-INF/jsp/com/util/index.jsp").forward(request, response);
	}
}