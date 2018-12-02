<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="X-Content-Type-Options" content="nosniff" >
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,target-densitydpi=medium-dpi">
<title>Kurd PPS System</title>
<!-- <link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'> -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jordan.css">
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

<script type="text/javascript">
	var confirmStr = "확인"; //공통제공 팝업의 확인버튼 변수
	var cancelStr = "취소";	//공통제공 팝업의 취소버튼 변수
	var requiredMessage = "다음 항목은 필수입력값입니다."; //공통제공 validator의 필수항목 변수
	var caliberPoint = ","; // 소수점 구분 기호
	var caliberSeparater = "."; // 숫자 세자리 구분 기호
	var alertTransTitle = "정보"; // 메시지 레이어 팝업 타이틀
</script>


