<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%@ page import="org.springframework.context.i18n.LocaleContextHolder" %>
<%@ page import="java.lang.String" %>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="X-Content-Type-Options" content="nosniff" >
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,target-densitydpi=medium-dpi">
<title>Kurd PPS System</title>
<!-- <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'> -->
<%-- <c:set var="localeCode" value="${pageContext.response.locale}" /> --%>

<c:choose>
 <c:when test="${pageContext.response.locale == 'ar'}">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jordan_ar.css">
</c:when>
  <c:otherwise>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jordan.css">
  </c:otherwise>
</c:choose>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" media="all"> --%>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.theme.css" media="all"> --%>
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.structure.css" media="all"> --%>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/jquery-1.11.0.min.js" ></script> --%>

<link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css" href="/css/util/ngCalendar.css">

<script src="/js/jq/jquery.min.js"></script>
<script src="/js/angular/angular.min.js"></script>        
<script src="/js/angular/angular.js"></script>
<script src="/js/angular/angular-animate.min.js"></script>
<script src="/js/angular/angular-aria.min.js"></script>
<script src="/js/angular/angular-material.js"></script>
<script src="/js/angular/angular-messages.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/js/angular/dirPagination.js"></script>
<script src="/js/util/moment.min.js"></script>
<script src="/js/util/waiting.js"></script>
<script src="/js/bootstrap/bootbox.min.js"></script>
