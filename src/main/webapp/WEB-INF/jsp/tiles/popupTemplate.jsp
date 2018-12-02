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
    <div class="popup" style="" id="popup_contents">
        <div class="layer_con_w">
			<tiles:insertAttribute name="popupBody" />
        </div>
    </div>
    
	<iframe name="popupResultFrame" height="0" width="0" frameborder="0">
	</iframe>
	<!-- alert 창 영역 --> 
    <div class="layer" id="alertLayer" style="display: none;">
        <div class="layer_con" id="layer_con_body" style="top: 20%;">
        <h2 class="layer_top"><span></span></h2>
        <input type="button" class="btn_close" id="messageAlertCloseBtn" name="close">
            <div class="layer_text_w" id="popContent"></div>
            <input type="button" class="btn_gray" id="messageAlertBtn" onclick="" value="confirmStr" />
        </div>
    </div>

</div>
	<tiles:insertAttribute name="popupFooterDiv" />
</body>
</html>