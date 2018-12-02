<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="iq.kurd.com.util.format.WebContextUtil"%>
<meta charset="utf-8" X-Content-Type-Options="nosniff" >

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Change Language..</title>
<script type="text/javascript" language="javascript">
function bodyOnload(){
  	var emptyForm = document.getElementById("emptyForm");
<%--   	emptyForm.action = "<%= request.getHeader("referer") == null ? "/" : request.getHeader("referer")  %>"; --%>
	emptyForm.action = "/";
  	emptyForm.submit();
}
</script>
</head>
<body onload="bodyOnload();">
<form id="emptyForm" method="post">
</form>
</body>
</html>