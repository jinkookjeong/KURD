package iq.kurd.com.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import iq.kurd.com.co.login.CookieAuthenticationProvider;
import iq.kurd.com.co.login.StrCtnt;
import iq.kurd.com.co.login.service.LoginService;
import iq.kurd.com.co.login.vo.JonepsActor;
import iq.kurd.com.co.login.vo.LoginVo;
import iq.kurd.com.co.login.vo.UsrAuthoVo;
import iq.kurd.com.co.menu.service.MenuService;
import iq.kurd.com.co.menu.vo.MenuIdVO;
import iq.kurd.com.co.menu.vo.MenuListVO;
import iq.kurd.com.co.menu.vo.MenuVO;
import iq.kurd.com.constant.Constant;
import iq.kurd.com.exception.AuthenticationException;
import iq.kurd.com.util.format.ObjToConvert;
import iq.kurd.com.util.format.SystemUtil;
 
@Component
public class KurdInterceptor extends HandlerInterceptorAdapter {

	@Resource(name="loginService")
	LoginService loginService;

	@Resource(name="menuService")
	MenuService menuService;
  
	@Resource
	CookieAuthenticationProvider cookieAuthenticationProvider;

	protected Log log = LogFactory.getLog(super.getClass());

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("=============Interceptor preHandle=============");
		 log.info(">>>>>>>>>>>>> "+request.getRequestURI());
		 
        if ( request.getRequestURI().contains("/rest/") ) { //REST 일경우는 그냥 리턴하여(REST는 데이터만 조회함)
			return true;
		}
        
		log.debug("Local is " + SystemUtil.isOsLocal());
		log.debug("reqURI is " + request.getRequestURI());
       
        HttpSession session = request.getSession();
        if(ObjToConvert.isNotEmpty(session.getAttribute(StrCtnt.JONEPS_ACTOR) )){
        	JonepsActor sessionActor = (JonepsActor)session.getAttribute(StrCtnt.JONEPS_ACTOR);
        	System.out.println("Interceptor actor getUsrId>> "+sessionActor.getUsrId());
            System.out.println("Interceptor actor getMastNm >> "+sessionActor.getMastNm());
            List<UsrAuthoVo> authList = (List<UsrAuthoVo>)sessionActor.getAuthCdList();
            for (int i = 0; i < authList.size(); i++) {
    			UsrAuthoVo vo = authList.get(i);
    			System.out.println("auth getUsrId: "+vo.getUsrId());
    			System.out.println("auth name: "+vo.getAuthoNm());
    		}
        }
		
//        if(!authorCheck2(request, session)){
//			System.out.println("Wrong Access!");
//			throw new AuthenticationException("wrong Access");		
//		}
        return true;
	        
//		//현재 시간을 가지고옴.
//		long currentTime = System.currentTimeMillis();
//
//		//현재 시간을 모델에 넣음.
//		request.setAttribute("bTime", currentTime);
//
//		String contextPath = request.getContextPath();
//		request.setAttribute("contextPath",contextPath + "/");
//		
//		System.out.println("request.getAttribute contextPath: "+request.getAttribute("contextPath"));
//		
//		//HttpSession session = request.getSession();
//
////		//세션 클러스터링 사용 여부 확인
//		if(Constant.CLUSTER_YN){
//			//인증 사용자용
//			if(ObjToConvert.isNotEmpty(session.getAttribute(StrCtnt.JONEPS_ACTOR) )){
//				System.out.println("로긴 했음=======================");
//			//인증안받은 roll_anonymous 용
//			}else{
//				//top 메뉴 존재 여부 확인
//				if(ObjToConvert.isEmpty(session.getAttribute(Constant.TOP_MENU_LIST) )){
//
//					Map<String, Object> paraMap = new HashMap<String, Object>();
//
//					//mymenu 최초 페이지 정보조회용
//					paraMap.put("myMenuCd", Constant.MY_MENU_CODE);
//					paraMap.put("myMenuCdPtrn", Constant.MY_MENU_CODE.substring(0, 4));
//
//					MenuListVO tomMenuListVO = new MenuListVO();
//					tomMenuListVO.setMnList(menuService.selectTopMenuList(paraMap));
//					session.setAttribute(Constant.TOP_MENU_LIST, tomMenuListVO);
//
//				}
//				//레프트 메뉴 존재 여부 확인
//				if(ObjToConvert.isEmpty(session.getAttribute(Constant.LEFT_MENU_LIST) )){
//					Map<String, Object> paraMap = new HashMap<String, Object>();
//
//					//mymenu 최초 페이지 정보조회용
//					paraMap.put("myMenuCd", Constant.MY_MENU_CODE);
//					paraMap.put("myMenuCdPtrn", Constant.MY_MENU_CODE.substring(0, 4));
//
//					MenuListVO leftMenuListVO = new MenuListVO();
//					leftMenuListVO.setMnList(menuService.selectLeftMnList(paraMap));
//					session.setAttribute(Constant.LEFT_MENU_LIST, leftMenuListVO);
//				}
//
//
//			}
//
//		//클러스터링 사용 안할 경우
//		}else{
//
//			Cookie[] cookies = request.getCookies();
//			String usrId = "";
//
//			if (cookies != null) {
//				for(int i=0; i<cookies.length; i++){
//
//					if(StrCtnt.USR_ID.equals(cookies[i].getName())){
//						usrId = cookies[i].getValue();
//					}
//				}
//			}
//
//			//사용자 ID 쿠키 존재할 경우
//			if(StringUtils.isNotEmpty(usrId)){
//				if(log.isDebugEnabled()){
//					log.debug("쿠키 로그인 O");
//				}
//				if(null == session.getAttribute(StrCtnt.JONEPS_ACTOR) || ! usrId.equals(((JonepsActor) session.getAttribute(StrCtnt.JONEPS_ACTOR)).getUsrId())){
//					if(log.isDebugEnabled()){
//						log.debug("세션 로그인 X");
//					}
//
//					Authentication auth = new UsernamePasswordAuthenticationToken(usrId, null);
//					CookieAuthenticationProvider cap = cookieAuthenticationProvider;
//					cap.setUsrId(usrId);
//					Authentication result = cap.authenticate(auth);
//					SecurityContextHolder.getContext().setAuthentication(result);
//					LoginVo loginVo = new LoginVo();
//					loginVo.setUsrId(usrId);
//					JonepsActor actor = loginService.loginCheck(loginVo);
//					if(actor != null){
//						try {
// 
//						actor.setAuthCdList(loginService.selectUsrAuthList(loginVo));
//
//						session.setAttribute(StrCtnt.JONEPS_ACTOR, actor);
//
//						menuMake(request, session, actor.getAuthCdList());
//						} catch (Exception e) {
//							// TODO: handle exception
//							e.printStackTrace();
//						}
//					}else{
//
//					}
//				}else{
//					session.invalidate();
//				}
//
//			//사용자 ID 쿠키 미존재일 경우
//			} else {
//				if(log.isDebugEnabled()){
//					log.debug("쿠키 로그인 X 세션 클리어");
//				}
//
//				Map<String, Object> paraMap = new HashMap<String, Object>();
//
//				//mymenu 최초 페이지 정보조회용
//				paraMap.put("myMenuCd", Constant.MY_MENU_CODE);
//				paraMap.put("myMenuCdPtrn", Constant.MY_MENU_CODE.substring(0, 4));
//
//				MenuListVO tomMenuListVO = new MenuListVO();
//				tomMenuListVO.setMnList(menuService.selectTopMenuList(paraMap));
//				
//				
//				session.setAttribute(Constant.TOP_MENU_LIST, tomMenuListVO);
//
//				MenuListVO leftMenuListVO = new MenuListVO();
//				leftMenuListVO.setMnList(menuService.selectLeftMnList(paraMap));
//				session.setAttribute(Constant.LEFT_MENU_LIST, leftMenuListVO);
//
//			}
//		}
//
//		//권한 관리 체크
//		if(!authorCheck(request, session)){
//			throw new AuthenticationException("wrong Access");
//			//System.out.println("Wrong Access!");
//		}
//
//		//메뉴 생성
//		makeMenuId(request,session);
//
//		String reqURI = request.getRequestURI();
//		String partUrl = "";
//		if (reqURI.indexOf("/") == 0 && reqURI.indexOf("/", 1) > 1) {
//			partUrl = reqURI.substring(0, reqURI.indexOf("/", 1));
//		}
//		request.setAttribute("partUrl", partUrl);

//		return true;
	}

	public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object handler, org.springframework.web.servlet.ModelAndView modelAndView)
			throws Exception {
		log.debug("=============Interceptor postHandle=============");
	}

	/**
	 * 세션 클러스터링을 사용하지 않을 경우
	 * menu 생성
	 * @param request
	 * @param session
	 * @param authCdList
	 * @throws Exception
	 */
	private void menuMake(HttpServletRequest request,HttpSession session, List<UsrAuthoVo> authCdList) throws Exception{

		Map<String, Object> paraMap = new HashMap<String, Object>();

		//권한 정보 LIST
		List roleList = new ArrayList();
		//session 값 확인용
		boolean sessionChk = false;

		try {

		for(UsrAuthoVo li : authCdList){
			String authCd = li.getAuthoCd();
			roleList.add(authCd);
		/*	if(ObjToConvert.isNotEmpty(sessionAuthCdList)){
				//기존세션과 동일한지의 여부 체크를 위해 첫반복시 false값을 가짐
				sessionChk = false;
				for(UsrAuthoVo li2 : sessionAuthCdList){
					String authCd2 = li2.getAuthoCd();
					if(authCd.equals(authCd2)){
						sessionChk = true;
						break;
					}
				}
				//2번째 반복 종료 후 true 가 없다면 다른 권한 정보로 보고 세션 체크실패로 본다
				if(!sessionChk){
					break;
				}*/
			}


		//top 메뉴 존재 여부 확인 미존재시 left 메뉴도 없다고 보고 권한에 맞는 메뉴 호출 혹은 권한정보가 안맞을 경우
//		if(ObjToConvert.isEmpty(session.getAttribute(Constant.TOP_MENU_LIST)) || !sessionChk ){

			paraMap.put("roleList", roleList);

			//mymenu 디폴트주소를 가져오기 위한 파라미터
			paraMap.put("myMenuCd", Constant.MY_MENU_CODE);
			paraMap.put("myMenuCdPtrn", Constant.MY_MENU_CODE.substring(0, 4));

			//topmenu list
			MenuListVO topMenuListVO = new MenuListVO();
			//left list
			MenuListVO leftMenuListVO = new MenuListVO();

			//TopMenuList GET
			topMenuListVO.setMnList(menuService.selectTopMenuList(paraMap));
			session.setAttribute(Constant.TOP_MENU_LIST, topMenuListVO);

			//LeftMenuList GET
			leftMenuListVO.setMnList(menuService.selectLeftMnList(paraMap));
			session.setAttribute(Constant.LEFT_MENU_LIST, leftMenuListVO);

//		}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// TODO: handle exception
		}

	}

	/**
	 * 메뉴 아이디 생성
	 * @param request
	 * @param session
	 * @throws Exception
	 */
	private void makeMenuId(HttpServletRequest request,HttpSession session)throws Exception{
		String menuId = request.getParameter("menuId");
		String upperMenuId = request.getParameter("upperMenuId");
		String subMenuId = request.getParameter("subMenuId");
		String noneMn = request.getParameter("noneMn");

		MenuIdVO menuIdVO = new MenuIdVO();
		MenuIdVO inItmenuIdVO = (MenuIdVO)session.getAttribute("menuIdVO");
		//A태그가 아닌 상태로 넘어오는 주소에 대해서 메뉴 ID 생성
		if(("Y").equals(noneMn)){
			MenuListVO leftMenuListVO = (MenuListVO)session.getAttribute(Constant.LEFT_MENU_LIST);

			for(MenuVO menuVO :leftMenuListVO.getMnList()){
				if(ObjToConvert.isNotEmpty(menuVO.getMenuUrl())){
					
					
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					System.out.println("request.getRequestURI()======================>"+request.getRequestURI()+"&&menuVO.getMenuUrl()::::::::::::::::::::::::"+menuVO.getMenuUrl());
					
					
					if(request.getRequestURI().contains(menuVO.getMenuUrl())){
						menuVO = getMenuLv3(leftMenuListVO.getMnList(), menuVO);
						upperMenuId = menuVO.getUpperMenuId();
						//2레벨에서 링크가 넘어온 경우 발생에 대비해서 비교함
						if(!("000000").contains(upperMenuId) || ObjToConvert.isEmpty(menuId) ){
							menuId = menuService.selectMenuId(upperMenuId);
							subMenuId = menuVO.getMenuId();
						}
						break;
					}
				}

			}
		}else{

			if(ObjToConvert.isNotEmpty(inItmenuIdVO)){
				if(ObjToConvert.isEmpty(menuId)){
					menuId = inItmenuIdVO.getMenuId();
				}
				if(ObjToConvert.isEmpty(upperMenuId)){
					upperMenuId = inItmenuIdVO.getUpperMenuId();
				}
				if(ObjToConvert.isEmpty(subMenuId)){
					subMenuId = inItmenuIdVO.getSubMenuId();
				}
			}
		}


		menuIdVO.setMenuId(menuId);
		menuIdVO.setSubMenuId(subMenuId);
		menuIdVO.setUpperMenuId(upperMenuId);
		session.setAttribute("menuIdVO", menuIdVO);

	}
	
	private MenuVO getMenuLv3(List<MenuVO> menuList, MenuVO menu) {
		MenuVO menuLv3 = menu;
		while (menuLv3.getMenuLavel() > 3) {
			boolean isUpperMenuFound = false;
			for (MenuVO vo : menuList) {
				if (vo.getMenuId().equals(menuLv3.getUpperMenuId())) {
					menuLv3 = vo;
					isUpperMenuFound = true;
					break;
				}
			}
			if (!isUpperMenuFound) break;
		}
		return menuLv3;
	}
	
	private boolean authorCheck2(HttpServletRequest request,HttpSession session)throws Exception{

		boolean authOk = false;

		String uri = request.getRequestURI();
		System.out.println("authorCheck uri: "+uri);
		
		if( ("/pt/main.do").contains(uri) || uri.endsWith("setLocale.do") || ("/um/loginForm.do").contains(uri)){
			authOk = true;
			return authOk;
		}
		
	
		MenuListVO topMenuListVO =  (MenuListVO)session.getAttribute(Constant.TOP_MENU_LIST);
		
		for( MenuVO vo : topMenuListVO.getMnList()){
			System.out.println("vo.getMenuUrl(): "+vo.getMenuUrl());
			if(uri.equals(vo.getMenuUrl())){
				authOk = true;
				System.out.println("========= authOk = true ==========");
				break;
			}
		}

		System.out.println("authOk >>> "+authOk);
		if(authOk){
			return authOk;
		}


		return authOk;

	}
	/**
	 * 권한 인증 여부 확인
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private boolean authorCheck(HttpServletRequest request,HttpSession session)throws Exception{

		boolean authOk = false;

		String uri = request.getRequestURI();
		System.out.println("authorCheck uri: "+uri);
		
		if( ("/pt/main.do").contains(uri) || uri.endsWith("setLocale.do") || ("/um/loginForm.do").contains(uri)){
			authOk = true;
			return authOk;
		}
		
		if(ObjToConvert.isNotEmpty(session.getAttribute(StrCtnt.JONEPS_ACTOR) )){
			if(uri.startsWith("/ed/")){
				authOk = true;
				return authOk;
			}
		}
		MenuListVO topMenuListVO =  (MenuListVO)session.getAttribute(Constant.TOP_MENU_LIST);
		MenuListVO leftMenuListVO =  (MenuListVO)session.getAttribute(Constant.LEFT_MENU_LIST);
		for( MenuVO vo : topMenuListVO.getMnList()){
			if(uri.equals(vo.getMenuUrl())){
				authOk = true;
				break;
			}
		}

		if(authOk){
			return authOk;
		}

		for( MenuVO vo : leftMenuListVO.getMnList()){
			if(uri.equals(vo.getMenuUrl())){
				authOk = true;
				break;
			}
		}

		if(authOk){
			return authOk;
		}

		return authOk;

	}
	
	  @Override
	    public void afterCompletion(HttpServletRequest request,
	            HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
/*
	        //view를 리턴하기 직전에 실행됨
//	        if (request.getRequestURI().endsWith("do") &&  !request.getRequestURI().endsWith("getTime.do")) {
        	if (request.getRequestURI().endsWith("do")) {
	        	//현재 시간을 구한다.
	        	long currentTime = System.currentTimeMillis();

	        	//요청이 시작된 시간을 가져온다.
	        	long beginTime = (long) request.getAttribute("bTime");

	        	//현재시간에서 요청이 시작된 시간을 뺀다.
	        	// --> 총 처리시간을 구한다.
	        	long processedTime = currentTime-beginTime;

	        	System.out.println("요청된 URL : "+request.getRequestURI());
	        	System.out.println("총 처리 시간은:"+processedTime+"  second:"+ (processedTime/1000) % 60);
//	        System.out.println("총 처리 시간은:"+processedTime+"  second:"+ TimeUnit.MICROSECONDS.toSeconds(processedTime));
	        	PTimeVO ptVO = new PTimeVO();

	        	long ptTime = (processedTime/1000) % 60;
	        	String prUrl = null;

	        	if(ObjToConvert.isEmpty(request.getRequestURI())){
	        		prUrl = "none";
	        	}else{
	        		prUrl = request.getRequestURI();
	        	}

		        System.out.println("prurl=========="+prUrl);
		        ptVO.setPrUrl(prUrl);
		        ptVO.setPrTime("총 처리 시간은:"+processedTime+"  second:"+ (processedTime/1000) % 60);
		        ptVO.setPrMTime((int)processedTime);
		        ptVO.setPrSTime((int)ptTime);
		        
		        if ( !SystemUtil.isOsLocal() ) 
		            prService.insertPrTime(ptVO);
			}

	        super.afterCompletion(request, response, handler, ex);
	        
*/	        
	    }


}
