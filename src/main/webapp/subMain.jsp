<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<!DOCTYPE html>
<html>
    <head>
     <%@ include file ="/WEB-INF/jsp/tiles/headerDiv.jsp" %>
<style>
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}
.form-signin {
  max-width: 850px;
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
    </head>
    <body>
    <div class="form-signin" >
        <h1>Welcome! Kurd PPS Main System</h1>
		
		<sec:authorize access="isAnonymous()">
			<a href="/um/loginForm.do" class="g_menu"><span>▷Go Login</span></a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			▷ user ID : <sec:authentication property="principal" />, 
			<a href="/um/logout.do" class="g_menu"><span>Logout</span></a>
		</sec:authorize>
		<h2>SpringMVC 5 + Srping Security 5 + REST + AngularJS</h2>
        <a href="/pt/ng/sample/board.do">▷ Go Sample AngularJS Board page(WAS1)</a><br>
		<a href="http://localhost:8888/pt/ng/sample/board.do">▷   Go Sample AngularJS Board page(WAS2)</a>
      </div>
    </body>
</html>