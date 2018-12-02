<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="iq.kurd.com.constant.Constant"%>
<%@page import="iq.kurd.com.util.format.LocaleUtil"%>
<%@page import="iq.kurd.com.util.format.ObjToConvert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>

<%
//String langCode = LocaleUtil.getLangCode();

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
<%-- 		<%} %> --%>
		</span>
	</h2>
	<!-- menu -->
	<div class="nav_area">
		<c:forEach var="list1" items="${leftMnList.mnList}" varStatus="status">
			<c:choose>
				<c:when  test="${list1.menuLavel eq 2 }" >
					<c:if test="${menuIdVO.menuId eq list1.upperMenuId }">
						<c:set var="level2Id" value="${list1.menuId}" />
						<a href="#none" class="nav_depth1 <c:if test='${list1.menuId eq menuIdVO.upperMenuId }' >on</c:if>" >
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
				 	<c:if test="${level2Id eq list1.upperMenuId }">
						<ul class="nav_lst">
							<li class="<c:if test='${list1.menuId eq menuIdVO.subMenuId }' >on</c:if>">
								<a href="<kurd:getProperty keyName='${fn:toLowerCase(list1.menuCd)}' />${list1.menuUrl}?menuId=${menuIdVO.menuId}&upperMenuId=${list1.upperMenuId}&subMenuId=${list1.menuId}">
								<span>
								${list1.menuMnKo }
<%-- 								<%if(("en").equals(langCode)){ %> --%>
<%-- 								${list1.menuMnEn } --%>
<%-- 								<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 								${list1.menuMnKo } --%>
<%-- 								<%}else{ %> --%>
<%-- 								${list1.menuMnAb } --%>
<%-- 								<%	} %> --%>
								</span>
								</a>
							</li>
						</ul>
				 	</c:if>
			 	</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
			

	
				