
<%
 /**
  * @Description : Exception Index
  * @Modification Information
  * @
  * @  수정일      수정자              수정내용
  * @ -------    --------    ---------------------------
  * @ 2012.06.14    Park, Cheolu            최초작성
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%@ page import="iq.kurd.com.util.format.WebContextUtil" %>
<%@ page import="iq.kurd.com.util.format.LocaleUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PPS - Public Procurement System</title>
<script type="text/javascript" language="javascript">

	(function fn_resizeWindow(){
		window.dialogWidth = "600px";
		window.dialogHeight = "400px";
	})();

	function fn_onload(){
	}

	function fn_close(){
		window.close();
	}


</script>
</head>
<body onload="javascript:fn_onload();">
메시지 출력  <br/>
<strong>${msg }</strong> <br/>
<input type="button" value="확인" onclick="javascript:fn_close()"/>
</body>
</html>
