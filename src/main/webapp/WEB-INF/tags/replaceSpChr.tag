<%@ tag pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="replaceStr" required="true" type="java.lang.String"%>
<%
	replaceStr = replaceStr.replaceAll("\"","[[dq]]");
	replaceStr = replaceStr.replaceAll("\'","[[sq]]");
 
%>
<%= replaceStr %>