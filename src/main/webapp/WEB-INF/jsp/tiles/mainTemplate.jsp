<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<tiles:insertAttribute name="headerDiv" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<tiles:insertAttribute name="topMenuDiv" />
		</div>
		<div id="container">
			<tiles:insertAttribute name="body" />
		</div>
<!-- 		<iframe name="ResultFrame" height="0" width="0"   style="border: 0px;" ></iframe> -->
		<div id="footer">
			<tiles:insertAttribute name="footerDiv" />
		</div>
	</div>
</body>
</html>