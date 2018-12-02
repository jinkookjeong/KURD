<%@ tag pageEncoding="UTF-8" body-content="empty"%>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="replaceStr" required="true" type="java.lang.String"%>
<%

	//String[] spCharArr = new String[]{"!","@","#","$","%","^","&","*","(",")","+","-","_","=","|","-","[","]","{","}",";",":","\'","\"",",",".","<",">","/","?","~","`"};
	//String[] spCodeArr = new String[]{"&#33","&#64","&#35","&#36","&#37","&#94","&#38","&#42","&#40","&#41","&#43","&#45","&#95","&#61","&#124","&#91","&#91","&#93","&#123","&#125","&#59","&#58","[[sq]]","&#34","&#44","&#46","&#60","&#62","&#47","&#63","&#126","&#96"};

	String[] spCharArr = new String[]{"'", "\""};
	String[] spCodeArr = new String[]{"[[sq]]", "[[dq]]"};

	for(int i = 0; i < spCharArr.length; i++){
		replaceStr = replaceStr.replace(spCharArr[i], spCodeArr[i]);
	}

%>
<%= replaceStr %>