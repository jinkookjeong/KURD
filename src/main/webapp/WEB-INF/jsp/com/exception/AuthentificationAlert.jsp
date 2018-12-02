
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="iq.kurd.com.util.format.WebContextUtil" %>
<%@ page import="iq.kurd.com.util.format.LocaleUtil" %>
<%response.setStatus(200);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%
	String portalUrl = (String)WebContextUtil.getUrl("nebid");

	String arCss = ".css";
	request.setAttribute("arCss",arCss);
	
	String contextPath = request.getContextPath();
	request.setAttribute("contextPath",contextPath + "/");

%>
<link rel="stylesheet" type="text/css" href="${contextPath}css/common.css">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}js/common/event.js" ></script>
<script type="text/javascript" >

	var confirmStr = "ok";

	function fn_onload(){
		
		if(parent.document.getElementById("contents") != null || parent.document.getElementById("popup_contents") != null){
<%-- 			util_messageAlertOnParent("<l:mapping programId="COMMON" objId="${resultMsg}" />(<%=(String)request.getAttribute("javax.servlet.forward.servlet_path")%>)");			 --%>
			util_messageAlertOnParent("${resultMsg}<%=(String)request.getAttribute("javax.servlet.forward.servlet_path")%>");			
		}	
	
	}
	
	function fn_toMain(){
		window.location.replace("/pt/main.do");	
	}

	parent.util_alertCall('잘못된 접근입니다.',"${destURL}");
	
</script>
</head>
<body id="body_login" onload="javascript:fn_onload()">
   <!-- Content START // -->
   <div class="vtable_outer">
      	<div class="vtable_middle">
            <div class="boxTy5" >
            	<div class="interval">
                   <p class="txt1">EX on-line E-Procurement System</p>
                   <p class="txt2">Authentification Error!</p>
                   <p class="txt3 mt15 mb30">잘못된 접근입니다.</p>
                   <span class="btnTy4"><input type="button" class="btn" onclick="javascript:fn_toMain();" 
			       value="Main"></span>
               </div>
            </div>
           <!-- //Content END -->
         </div>
    </div>
</html>
