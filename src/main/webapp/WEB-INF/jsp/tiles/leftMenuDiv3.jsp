<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="iq.kurd.com.constant.Constant"%>
<%@page import="iq.kurd.com.util.format.LocaleUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
//String langCode = LocaleUtil.getLangCode();
String mId		= request.getParameter("menuId");
List<HashMap<String,Object>> leftList = new ArrayList();
boolean myMnChk = false;
if(!mId.isEmpty()){
	if(mId.startsWith("PT02")){
		myMnChk = true;
	}
}
	
if(myMnChk){
	leftList = (List<HashMap<String, Object>>) session.getAttribute(Constant.MY_MENU_LIST);
}else{
	leftList = (List<HashMap<String, Object>>) session.getAttribute(Constant.LEFT_MENU_LIST);
}

%>
	<h2 class="nav_tit">
		<span>
		${mnSelList.menuMnKo }
<%-- 		<%if(("en").equals(langCode)){ %> --%>
<%-- 		${mnSelList.menuMnEn } --%>
<%-- 		<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 		${mnSelList.menuMnKo } --%>
<%-- 		<%}else{ %> --%>
<%-- 		${mnSelList.menuMnAb } --%>
<%-- 		<%	} %> --%>
		</span>
	</h2>
	<!-- menu -->
	<div class="nav_area">
	${mId}dffff
		<c:forEach var="list1" items="${leftMnList}" varStatus="status">
			<c:choose>
				<c:when  test="${list1.menuLavel eq 2 }" >
					<c:if test="${param.menuId eq list1.upperMenuId }">
						<a href="#none" class="nav_depth1 <c:if test='${list1.menuId eq param.upperMenuId }' >on</c:if>" >
							<span>
							${list1.menuMnKo }
<%-- 							<%if(("en").equals(langCode)){ %> --%>
<%-- 							${list1.menuMnEn } --%>
<%-- 							<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 							${list1.menuMnKo } --%>
<%-- 							<%}else{ %> --%>
<%-- 							${list1.menuMnAb } --%>
<%-- 							<%	} %> --%>
							</span>
						</a>
					</c:if>
				</c:when>
			 	<c:otherwise>
					<ul class="nav_lst">
						<li class="<c:if test='${list1.menuId eq param.subMenuId }' >on</c:if>">
							<a href="${list1.menuUrl}?upperMenuId=${list1.upperMenuId}&subMenuId=${list1.menuId}">
							<span>
							${list1.menuMnKo }
<%-- 							<%if(("en").equals(langCode)){ %> --%>
<%-- 							${list1.menuMnEn } --%>
<%-- 							<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 							${list1.menuMnKo } --%>
<%-- 							<%}else{ %> --%>
<%-- 							${list1.menuMnAb } --%>
<%-- 							<%	} %> --%>
							</span>
							</a>
						</li>
					</ul>
			 	</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
			

	
				