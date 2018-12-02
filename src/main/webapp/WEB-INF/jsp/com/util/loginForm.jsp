<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN</title>
<%@ include file ="/WEB-INF/jsp/tiles/headerDiv.jsp" %>
<style>
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}
.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>    
<script type="text/javascript">
function fn_login(usrId) {
	var f = document.frm;
	f.method = "post";
	f.valStartDt = "2018.11.20";
	f.valExpirDt = "2019.11.20";
	f.action = "/loginProcess.do";
	f.submit();
}
</script>
<body>
<a href="/"><h3> go Home</h3></a>
	<div class="container">
		  <form class="form-signin" id="frm" name="frm" action="/loginProcess.do" method="post">
		  <input type="hidden" name="encrCerti" id="encrCerti" value="" />
			<input type="hidden" name="valStartDt" id="valStartDt" value="" />
			<input type="hidden" name="valExpirDt" id="valExpirDt" value="" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
	        <h2 class="form-signin-heading">Please sign in </h2>
	        
	        <c:if test="${not empty resultMsg}">
	        	<div class="alert alert-danger" id="errMsgDiv" role="alert" style="display:yes;">${resultMsg}</div>
	        </c:if>
	        
	        <p>
	          <label for="userId" class="sr-only">UserId</label>
	          <input type="text" id="userId" name="userId"  class="form-control" placeholder="userId" required="" autofocus="">
	        </p>
	        <p>
	          <label for="signCerti" class="sr-only">SignCerti(Password)</label>
	          <input type="signCerti" id="signCerti" name="signCerti" value ="admin123" class="form-control" placeholder="signCerti" required="">
	        </p>
	        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="fn_login()">Sign in</button>
	      </form>
	</div>
</body>

