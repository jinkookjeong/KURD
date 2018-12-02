<%@page import="java.util.List"%>
<%@page import="iq.kurd.com.util.format.LocaleUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String navTlt = "menu Test";
String mnTlt = "menu Test";
//String langCode = LocaleUtil.getLangCode();



%>
					<h2 class="nav_tit">
<%-- 						<span><%=langCode %></span> --%>
						<span>Ko</span>
					</h2>
					<!-- menu -->
						<c:forEach var="list1" items="${leftMnList}" varStatus="status">
					<div class="nav_area">
					<c:choose>
						<c:when  test="${list1.menuLavel eq 2 }" >
							<a href="#none" class="nav_depth1">
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
						</c:when>
					 	<c:otherwise>
							<ul class="nav_lst">
								<li>
									<a href="${list1.menuUrl}">
									<span>
										${list1.menuMnKo }
<%-- 									<%if(("en").equals(langCode)){ %> --%>
<%-- 									${list1.menuMnEn } --%>
<%-- 									<%}else if(("ko").equals(langCode)){ %> --%>
<%-- 									${list1.menuMnKo } --%>
<%-- 									<%}else{ %> --%>
<%-- 									${list1.menuMnAb } --%>
<%-- 									<%	} %> --%>
									</span>
									</a>
								</li>
								
							</ul>
					 	</c:otherwise>
					</c:choose>
					</div>
						</c:forEach>
					
	
	
				