<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/common/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn_cookie").click(function(){
		setCookie('JONEPS', 'JONEPS123', 1);
	});
	$("#btn_delCookie").click(function(){
		setCookie('JONEPS', "", 1);
		setCookie('USR_ID', "", 1);
		setCookie('AUTHO_CD', "", 1);
		setCookie('usr_id', "", 1);
		setCookie('autho_cd', "", 1);
	});
});


function setCookie(name, value, expiredays) {
	var today = new Date();
    today.setDate(today.getDate() + expiredays);
    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';';
}
function delCookie(name, value, expiredays) {
	var today = new Date();
    today.setDate(today.getDate() + expiredays);
    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';';
}
</script>
</head>
<body>

※ 사용자 정보 
<div>
	user_id : ${JONEPS_ACTOR.usrId}, chrgr_nm : ${JONEPS_ACTOR.chrgrNm}
</div>
<br>
<a href="http://127.0.0.1">홈으로</a>
<a href="http://127.0.0.1:8080">8080홈으로</a>

<c:forEach var="name" items="${pageContext.session.attributeNames}">
	Name: ${name}  ::: Value: ${sessionScope[name]}<br>
</c:forEach>

<input type="button" id="btn_cookie" value="쿠키생성">
<input type="button" id="btn_delCookie" value="쿠키삭제">

</body>
</html>