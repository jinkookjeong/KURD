<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="con">
	<div class="tableTy2">
		<div class="dataArea">
			<form:form name="frm" modelAttribute="resultInfo" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="searchConditions">
			<input type="hidden" name="currentPageNo" value="${coCommonCodeSVO.currentPageNo}">
			<input type="hidden" name="offSet" value="${coCommonCodeSVO.offSet}">

			<table  class="data">
			    <colgroup>
			        <col width="30%"/>
			        <col width="70%"/>
			    </colgroup>
			    <thead>
			        <tr style="border:1px solid #ccc">
			            <th class="tC" scope="col">CD</th>
			            <th class="tC" scope="col">CD_NM_STR</th>
			            
			        </tr>
			    </thead>
			    <tbody>
			        <c:choose>
			            <c:when test="${fn:length(resultList) > 0}">
							<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
							<tr>
								<td class="tC">${resultInfo.cd }</td>
								<td class="tC">${resultInfo.cdNmStr }</td>
							</tr>
							</c:forEach>
						</c:when>
			            <c:otherwise>
			                <tr>
			                    <td colspan="2" style="border:1px solid #ccc">조회된 결과가 없습니다.</td>
			                </tr>
			            </c:otherwise>
					</c:choose>
				</tbody>
			</table>
			</form:form>
		</div>
		
		
		<div class="pageListNum">
			<tfa:CurrentPage pageInfo="${coCommonCodeSVO}" />
		</div>
		<div class="boardNavigation" style="text-align: center;">
			<ul class="page">
				<tf:pagination formName="frm" listURL="/co/code/selectListPageCoCommonCode.do?cdCls=${coCommonCodeSVO.cdCls }" paginationInfo="${coCommonCodeSVO}" target="_self" />
			</ul>
		</div>
		
	</div>
</div>

</body>
</html>