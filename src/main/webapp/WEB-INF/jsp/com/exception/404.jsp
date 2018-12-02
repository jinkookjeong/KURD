<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%@ page import="iq.kurd.com.util.format.WebContextUtil" %>
<%@ page import="iq.kurd.com.util.format.LocaleUtil" %>
<%response.setStatus(200);%>
<%
	String portalUrl = (String)WebContextUtil.getUrl("nebid");
	request.setAttribute("portalUrl",portalUrl);
	
	String arCss = ".css";
	request.setAttribute("arCss",arCss);
	
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath",contextPath + "/");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Public Procurement System</title>
<%@ include file ="/WEB-INF/jsp/tiles/headerDiv.jsp" %>
<!-- <script type="text/javascript" charset="utf-8" src="/js/common/event.js" ></script> -->
<script type="text/javascript" >

	var confirmStr = "confirm";	

	function fn_toMain(){
		window.location.replace("/home.jsp");	
	}

	function fn_onload(){
		
// 		if(parent.document.getElementById("executionImageImg") != null && 
// 		   parent.document.getElementById("layerDiv") != null){
			
// 			util_hideExecutionAll(parent.document);
			
<%-- 			var htmlStr = "<%=(String)request.getAttribute("javax.servlet.forward.servlet_path")%>파일이 존재 하지 않습니다.<br>"; --%>

// 			util_makeMessageAlertDivNoConfirm(parent.document, htmlStr, "javascript:parent.main.location.href = '<c:out value='${portalUrl}'/>/pt/main/movePtMainContent.do'");
			
// 		}
 	}
	
	//parent.util_alertCall('페이지를 찾을수가 없습니다.',"${destURL}");
	
</script>
</head>

<body id="body_login" onload="javascript:fn_onload()">
 <!-- Content START // -->
 <div class="vtable_outer">
    	<div class="vtable_middle">
          <div class="boxTy5" >
          	<div class="interval">
                 <p class="txt1">Kurd on-line E-Procurement System</p>
                 <p class="txt2">404 Not Found</p>
                 <p class="txt3 mt15 mb30">페이지를 찾을수가 없습니다.</p>
                 <span class="btnTy4"><input type="button" class="btn" onclick="javascript:fn_toMain();" 
   value="Main"></span>
             </div>
          </div>
         <!-- //Content END -->
       </div>
  </div>
</body>
</html>
