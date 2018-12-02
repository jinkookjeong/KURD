<%@page import="iq.kurd.com.util.format.LocaleUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%
//String langCode = LocaleUtil.getLangCode();

%>
	<div class="page_my_area">
		<span class="my_area"><em class="my_ico"></em> Welcome <strong>${JONEPS_ACTOR.chrgrNm}</strong></span>
		<div class="page_loca">
			<a href="#" class="page_home">home</a>
			${mnSelList.menuMnKo }
<%-- 					<%if(("en").equals(langCode)){ %> --%>
<%-- 					${mnSelList.menuMnEn } --%>
<%-- 					<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 					${mnSelList.menuMnKo } --%>
<%-- 					<%}else{ %> --%>
<%-- 					${mnSelList.menuMnAb } --%>
<%-- 					<%	} %> --%>
			<c:forEach var="list1" items="${leftMnList.mnList}" varStatus="status">
				<c:choose>
					<c:when  test="${list1.menuLavel eq 2 &&  menuIdVO.upperMenuId eq list1.menuId }" >
							<span class="page_curr">
							${list1.menuMnKo }
<%-- 								<%if(("en").equals(langCode)){ %> --%>
<%-- 								${list1.menuMnEn } --%>
<%-- 								<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 								${list1.menuMnKo } --%>
<%-- 								<%}else{ %> --%>
<%-- 								${list1.menuMnAb } --%>
<%-- 								<%	} %> --%>
							</span>
					</c:when>
	 				<c:otherwise>
	 					<c:if test="${menuIdVO.subMenuId eq list1.menuId }">
	 						<span class="page_curr">
	 						<c:set var="screenTitle" value="${list1.menuMnKo }" scope="request" />
<%-- 									<%if(("en").equals(langCode)){ %> --%>
<%-- 									<c:set var="screenTitle" value="${list1.menuMnEn }" scope="request" /> --%>
<%-- 									<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 									<c:set var="screenTitle" value="${list1.menuMnKo }" scope="request" /> --%>
<%-- 									<%}else{ %> --%>
<%-- 									<c:set var="screenTitle" value="${list1.menuMnAb }" scope="request" /> --%>
<%-- 									<%	} %> --%>
									${screenTitle}
							</span>
		 				</c:if>
	 				</c:otherwise>
 				</c:choose>
		</c:forEach>
		</div>
	</div>
