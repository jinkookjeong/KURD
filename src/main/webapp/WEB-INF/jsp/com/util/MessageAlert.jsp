
<%
 /**
  * @Description : 메시지 Alert 창을 호출해주는 화면
  * @Modification Information
  * @
  * @  수정일      수정자              수정내용
  * @ -------    --------    ---------------------------
  * @ 2014.11.10                 최초작성
  *
  */
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body id="messageAlertBody">
	<!--  메시지 Alert 창 처리 -->
	
	<script type="text/javascript">
<%--	
		var confirmStr = parent.confirmStr;
		var isNonForward = "${isNonForward}";
		var isReturn = "${isReturn}";
		var isSuccess = "${isSuccess}";
		var url = "${contextPath}${destURL}";
		url = url.replace("//", "/");
--%>

	<c:if test="${empty dialogBox}">
		parent.util_alertCall("${resultMsg}","${destURL}");
	</c:if>
	<c:if test="${!empty dialogBox}">
		parent.DialogBox.show(${dialogBox});
	</c:if>
	</script>
    
</body>
</html>
