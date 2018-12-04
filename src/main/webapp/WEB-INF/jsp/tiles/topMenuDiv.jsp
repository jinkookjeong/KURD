<%@page import="java.util.List"%>
<%@page import="iq.kurd.com.util.format.WebContextUtil" %>
<%@page import="iq.kurd.com.util.format.LocaleUtil"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%
	//String langCode = LocaleUtil.getLangCode();
	String portalUrl = (String)WebContextUtil.getUrl("nebid");
	request.setAttribute("portalUrl",portalUrl);
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath",contextPath + "/");
	 
%>
	<div class="gnb">
		<div class="gnb_area">
			<h1 class="logo"><span class="blind">Kurd e-Procurement System</span><a href="<kurd:getProperty keyName='pt' />" class="logo_link"></a></h1>
			<div class="gnb_menu" style="color: white;">
				<span id="clock"></span>
				<sec:authorize access="isAnonymous()">
					<a href="<kurd:getProperty keyName='um' />/um/loginForm.do" class="g_menu"><span>LOGIN</span></a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
<%-- 					usrName : <sec:authentication property="principal.username" />,					 --%>
					<a href="/um/logout.do" class="g_menu"><span>LOGOUT</span></a>
				</sec:authorize>Top menu
		<c:forEach var="list" items="${topMnList.mnList}" varStatus="status"  >		
			<c:if test="${ 'S' eq list.menuType && 'PT02000000' ne list.menuId }">
				<a href="<kurd:getProperty keyName='${fn:toLowerCase(list.menuCd)}' />${list.menuUrl }" class="g_menu"><span>
						한글
				</span>
				</a>
			</c:if>
		</c:forEach>
				<span class="gnb_lang">
					<a href="/setLocale.do?lang=ku" class="kor"><span class="blind">Ku</span></a>
					<a href="/setLocale.do?lang=en" class="eng"><span class="blind">En</span></a>
					<a href="/setLocale.do?lang=ko" class="kor"><span class="blind">Ko</span></a>
					<a href="/setLocale.do?lang=ar" class="eng"><span class="blind">Ar</span></a>
				</span>
			</div>
		</div>
	</div>
	<div class="snb">
		<div class="snb_area_l">
			<c:forEach var="list" items="${topMnList.mnList}" varStatus="status"  >
				<!-- 레프트메뉴의 메뉴 타이틀을 보여주기 위해 사용 -->
				<c:if test="${list.menuId eq param.menuId }">
					<c:set var="mnSelList" value="${list}" scope="session" />
				</c:if>
				<c:choose>
					<c:when test="${list.menuType eq 'T' }">
						<a href="<kurd:getProperty keyName='${fn:toLowerCase(list.menuCd)}' />${list.menuUrl}" >
							${list.menuMnKo }
							
						</a>
					</c:when>
					<c:otherwise>
						<c:if test="${list.menuId eq 'PT02000000' }">
							<c:set var="myPageMn" value="${list}" />
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div class="snb_area_r">
			<c:if test="${not empty myPageMn }">
				<a href="<kurd:getProperty keyName='${fn:toLowerCase(myPageMn.menuCd)}'/>${myPageMn.menuUrl}">
				${myPageMn.menuMnKo}
				
				</a>
			</c:if>
		</div>
	</div>