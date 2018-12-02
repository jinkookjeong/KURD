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
			<div class="page_my">
<%-- 				<tiles:insertAttribute name="infoBarDiv" /> --%>
			</div>
			<div class="content" id="contents">
				<div class="nav"> 
					<tiles:insertAttribute name="leftMenuDiv" />
				</div>
				<div class="con">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
<!-- 		<iframe name="ResultFrame" height="0" width="0" style="border: 0px;"></iframe> -->
		<!-- alert 창 영역 --> 
<!-- 	    <div class="layer" id="alertLayer" style="display: none;"> -->
<!-- 	        <div class="layer_con" id="layer_con_body" style="top: 20%;"> -->
<!-- 	        	<h2 class="layer_top"><span></span></h2> -->
<!-- 	        	<input type="button" class="btn_close" id="messageAlertCloseBtn" name="close"> -->
<!-- 	            <div class="layer_text_w" id="popContent"></div> -->
<!-- 	            <input type="button" class="btn_gray" id="messageAlertBtn" value="confirmStr" /> -->
<!-- 	        </div> -->
<!-- 	    </div> -->
		<div id="footer">
			<tiles:insertAttribute name="footerDiv" />
		</div>
	</div>
</body>
</html>