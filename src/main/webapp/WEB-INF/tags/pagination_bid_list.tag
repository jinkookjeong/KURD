<%@ tag pageEncoding="UTF-8" language="java" body-content="tagdependent"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="listURL" required="true"%>
<%@ attribute name="formName" required="true"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="paginationInfo" required="true" rtexprvalue="true" type="egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo" %>

<script type="text/javaScript" language="javascript">
function fn_pageview(pageNo){


	parent.document.${formName}.currentPageNo.value = pageNo;
	parent.document.${formName}.action = "<c:url value='${listURL}'/>";
	parent.document.${formName}.target = "${target}";
	parent.document.${formName}.submit();
}
</script>
<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_pageview"/>

