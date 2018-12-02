
<%
 /**
  * @Description : error 화면
  * @Modification Information
  * @
  * @  수정일      수정자              수정내용
  * @ -------    --------    ---------------------------
  * @ 2014.10.17   이민주            초기작성
  *
  */
%>
<%
	String portalUrl = (String)WebContextUtil.getUrl("nebid");
	request.setAttribute("portalUrl",portalUrl);

	String arCss = ".css";
	request.setAttribute("arCss",arCss);
	
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath",contextPath + "/");

%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%@ page import="iq.kurd.com.util.format.WebContextUtil" %>
<%@ page import="iq.kurd.com.util.format.LocaleUtil" %>
<%response.setStatus(200);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PPS - Public Procurement System</title>
<link rel="stylesheet" type="text/css" href="${contextPath}css/common.css">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}js/common/event.js" ></script>
<script type="text/javascript" >

	var confirmStr = "confirm";

	function fn_toMain(){
		window.location.replace("/pt/main.do");	
	}


	function fn_onload(){

		if(parent.document.getElementById("executionImageImg") != null &&
		   parent.document.getElementById("layerDiv") != null){

			util_hideExecutionAll(parent.document);

			var htmlStr = "Unexpected Error<br>";
			htmlStr    += "담당자에게 문의하여 주세요.<br>";

			util_makeMessageAlertDivNoConfirm(parent.document, htmlStr, "javascript:parent.main.location.href = '<c:out value='${portalUrl}'/>/pt/main/movePtMainContent.do'");
		}
	}
	parent.util_alertCall('해당 파일이 없습니다.',"${destURL}");

</script>
</head>
<body id="body_login" onload="javascript:fn_onload();">
<!-- Content START // -->
<div class="vtable_outer">
   	<div class="vtable_middle">
         <div class="boxTy5" >
         	<div class="interval">
                <p class="txt1">Jordan on-line E-Procurement System</p>
                <p class="txt2">해당 파일이 없습니다.</p>
                <p class="txt3 mt15 mb30">담당자에게 문의하여 주세요.</p>
                <span class="btnTy4"><input type="button" class="btn" onclick="javascript:fn_toMain();"
  value="Main"></span>
            </div>
         </div>
        <!-- //Content END -->
      </div>
 </div>
</body>
</html>