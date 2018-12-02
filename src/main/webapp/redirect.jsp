<%
	String redirectUrl = (String)request.getAttribute("redirectUrl");
	System.out.println(redirectUrl);
		
	String strName;
	/* Enumeration enu = request.getParameterNames();

	while (enu.hasMoreElements()) {
	 strName= (String) enu .nextElement();
	 System.out.println("<input type='hidden' name='"+strName+"' value='" + request.getParameter(strName)+"'>");
	}  */
	
	response.sendRedirect(redirectUrl);
	
%>
	
	