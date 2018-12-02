<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
${leftMenuStr}
<a href="${pageContext.request.contextPath}/pt/sample/PtSampleList.do">샘플링크</a><br>
<a href="${pageContext.request.contextPath}/pt/sample/TotalSample.do">샘플예제링크</a>
 --%>
					<h2 class="nav_tit">
						<span>Service Center</span>
					</h2>
					<!-- menu01 -->
					<div class="nav_area">
						<!-- [D]선택시 class="on"추가 -->
						<a href="/pt/sample/TotalSample.do" class="nav_depth1 on"><span>Samples</span></a>
						<ul class="nav_lst">
						<!-- [D]선택시 class="on"추가 -->
						<li><a href="/pt/sample/commoncode/ComnOrganizationList.do"><span>기관 조회 페이지</span></a></li>
						<li><a href="/pt/sample/commoncode/ComnTestList.do"><span>공통코드 조회 및 캘린더</span></a></li>
						<li><a href="/pt/sample/board/SelectSampleBoardList.do"><span>샘플게시판</span></a></li>
						<li><a href="/pt/sample/ExceptionTest.do" target="ResultFrame"><span>팝업 예외 테스트</span></a></li>
						<li><a href="/pt/sample/MessageTest.do" target="ResultFrame"><span>등록 메세지 테스트 </span></a></li>
						<li><a href="/document/Java_Util.xlsx"><span>자바 유틸 함수 메뉴얼</span></a></li>
						<li><a href="/document/JavaScript_Util.xlsx"><span>자바스크립트 유틸 함수 메뉴얼</span></a></li>
						</ul>
					</div>
					
					<!-- menu02 -->
					<div class="nav_area">
						<a href="#none" class="nav_depth1"><span>Law Info</span></a>
						<ul class="nav_lst">
						<li><a href="#none"><span>Regulations</span></a></li>
						<li><a href="#none"><span>Public Procuring Decree</span></a></li>
						</ul>
					</div>
					
					<!-- menu03 -->
					<div class="nav_area">
						<a href="#none" class="nav_depth1"><span>User Guide</span></a>
						<ul class="nav_lst">
						<li><a href="#none"><span>Manuals</span></a></li>
						<li><a href="#none"><span>Guide of user registration</span></a></li>
						</ul>
					</div>
	
