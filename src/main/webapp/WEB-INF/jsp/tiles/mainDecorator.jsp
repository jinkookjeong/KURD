<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<tiles:insertAttribute name="headerDiv" />
</head>
<body>
	<div id="main">
		<div id="sitewrap">
	 		<div id="wrap">
				<div id="header">
					<tiles:insertAttribute name="topMenuDiv" />
				</div>
				<div id="con_wrap">
					<div class="left_w">
						<tiles:insertAttribute name="leftMenuDiv" />
					</div>
					<div class="content" id="contents">
						<tiles:insertAttribute name="body" /><%-- 변경금지 --%>
					</div>
				</div>
			</div>
			<div>
				<tiles:insertAttribute name="footerDiv" />
			</div>
		</div>
	</div>
</body>
</html>