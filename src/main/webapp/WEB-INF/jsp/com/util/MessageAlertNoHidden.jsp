
<%
 /**
  * @Description : 메시지 Alert 창을 호출해주는 화면
  * @Modification Information
  * @
  * @  수정일      수정자              수정내용
  * @ -------    --------    ---------------------------
  * @ 2011.06.22   김기훈              최초작성
  *
  */
%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<c:out value="${commonScript}" escapeXml="false"/>
</head>
<body>
	<!--  메시지 Alert 창 처리 -->
	<c:if test="${validateMessage != null}">
		<script type="text/javascript">
			
			if(parent.document.getElementById("contents") != null){
				util_messageAlertOnParent("${validateMessage}", "${focusObjectName}");
			}else{
				util_messageAlertOnPopup("${validateMessage}", "${focusObjectName}");
			}


		</script>
	</c:if>
	<c:if test="${resultMsg != null}">
		<script type="text/javascript">

			var isNonForward = "${isNonForward}";
			var isReturn = "${isReturn}";

			
			var url = "${contextPath}${destURL}";
			url = url.replace("//", "/");

			util_showResult("${resultMsg}", url);
			
		</script>
	</c:if>
</body>
</html>
