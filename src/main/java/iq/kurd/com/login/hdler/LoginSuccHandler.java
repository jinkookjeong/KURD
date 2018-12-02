package iq.kurd.com.login.hdler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Repository;

import iq.kurd.com.KurdProperties;
import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.dao.LoginDAO;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.UsrAuthoVo;
import iq.kurd.com.co.menu.service.MenuService;
import iq.kurd.com.util.format.SystemUtil;
import iq.kurd.com.util.login.LoginUtil;

@Repository
public class LoginSuccHandler implements AuthenticationSuccessHandler {

	@Resource LoginDAO loginDAO;
	protected Log log = LogFactory.getLog(super.getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Resource(name="menuService")
	MenuService menuService;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		String referUrl = null;
		
		try {
			log.info("========== login successHandler ==========");
			
			String signCerti = request.getParameter("signCerti");
			String encrCerti = request.getParameter("encrCerti");
			String userId = request.getParameter("userId");
			String valStartDt = request.getParameter("valStartDt");
			String valExpirDt = request.getParameter("valExpirDt");
			
			log.info("#### signCerti : "+signCerti);
			log.info("#### username : "+userId);
			log.info("#### usrId : "+userId);
			log.info("#### valStartDt : "+valStartDt);
			log.info("#### valExpirDt : "+valExpirDt);
			
			//try {
			 //   signCerti = URLDecoder.decode(signCerti, "UTF-8");
			//} catch(UnsupportedEncodingException uee) {
			//	signCerti = (String)authentication.getCredentials();
			//}	
			
			log.info("LoginSuccessHandler :"+request.getRequestURL());
			log.info("authentication :"+authentication);
			log.info("authentication.isAuthenticated() :"+authentication.isAuthenticated());
			log.info("authentication.getCredentials() :"+authentication.getCredentials());
			log.info("authentication.getName(): "+authentication.getName());
			log.info("authentication.getPrincipal(): "+authentication.getPrincipal());
			
			//이미 로그인 성공한 정보를 활용하여 Session에 넣는다
			List<GrantedAuthority> loginAuthList = (List<GrantedAuthority>)authentication.getAuthorities();		
			int authCount = loginAuthList == null ? 0 : loginAuthList.size();
			List<UsrAuthoVo> authList = new ArrayList<UsrAuthoVo>();
			for (int i = 0; i < authCount; i++) {
				UsrAuthoVo usrAuthoVo = new UsrAuthoVo();
				GrantedAuthority grantedAuthority = loginAuthList.get(i);
				
				usrAuthoVo.setUsrId(userId);
				usrAuthoVo.setAuthoNm(grantedAuthority.getAuthority());
				authList.add(usrAuthoVo);
			}
			
			HttpSession session = request.getSession(true);
			JonepsActor actor = LoginUtil.getActor(request);
			actor.setMastNm(userId);
			actor.setAprovYn("Y");
			actor.setUsrId(userId);
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
		
		if(StringUtils.isEmpty(referUrl)){
			if (SystemUtil.isOsLocal().booleanValue()) {
				referUrl = KurdProperties.getProperty("localUrl");
			} else {
				referUrl = KurdProperties.getProperty("kurdUrl");
			}
		}

		//로그인 화면을 보여주긴 전 화면 URL정보 가져오기
  	    //requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response); 
		if (savedRequest != null) { 
		 	 referUrl = savedRequest.getRedirectUrl();	 
		  	 requestCache.removeRequest(request, response);		  	
		}
	     
	     System.out.println("referUrl: "+referUrl);
	     redirectStrategy.sendRedirect(request, response, referUrl);
	      
		//response.sendRedirect(referUrl);
		
		//request.setAttribute("redirect", referUrl);
		//request.setAttribute("message", "Success");
		//request.getRequestDispatcher("/WEB-INF/jsp/com/util/loginSuccess.jsp").forward(request, response);
	}
}