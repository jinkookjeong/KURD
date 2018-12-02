<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%

out.println(request.getHeader("REFERER"));
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KURD:: KOICA</title>
<script type="text/javascript">
parent.location.href = "${empty redirect ? '/' : redirect}";
</script>
</head>
</html>