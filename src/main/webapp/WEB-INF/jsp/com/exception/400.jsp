
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
<link rel="stylesheet" type="text/css" href="${contextPath}css/common.css">
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}js/common/event.js" ></script>
<script type="text/javascript" >

	var confirmStr = "confirm";	

	function fn_toMain(){
		window.location.replace("/pt/sample/board/SelectSampleBoardList.do");	
	}

	parent.util_alertCall('예외상황이 발생하였습니다.',"${destURL}");
</script>
</head>

<body id="body_login">
 <!-- Content START // -->
 <div class="vtable_outer">
    	<div class="vtable_middle">
          <div class="boxTy5" >
          	<div class="interval">
                 <p class="txt1">EX New on-line E-Procurement System</p>
                 <p class="txt2">400 Error!</p>
                 <p class="txt3 mt15 mb30">BAD REQUEST</p>
                 <span class="btnTy4"><input type="button" class="btn" onclick="javascript:fn_toMain();" 
   value="Main"></span>
             </div>
          </div>
         <!-- //Content END -->
       </div>
  </div>
</body>
</html>
