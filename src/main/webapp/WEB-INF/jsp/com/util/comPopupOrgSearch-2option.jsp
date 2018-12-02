<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<html>
<script type="text/javaScript" language="javascript">
var app = angular.module('popupOrgApp',['angularUtils.directives.dirPagination']);

app.controller('popupOrgController', function ($scope, $http, $timeout, $window) {
   
   $scope.resultInfos = []; //Table List
   $scope.cmOrgInfoVO = {}; //Request, Response VO
	
   $scope.searchParamValue = $window.opener.searchPopValue; //Parent 화면에서 입력한 값
   
   var json = JSON.parse($scope.searchParamValue); //Change to JSON
   var searchNm = json.id; //부모창으로 부터 전달받은 값
   var searchUpCode; //상위 콤보
   var searchMidCode; //하위 콤보
   
   $scope.cmOrgInfoVO.orgNm = searchNm; //부모청에서 전달한 값 팝업화면에 뿌려주기
   
   
   //List View Count
   $scope.viewCount = {
   recordedPages: [
        {count: '5', name: '5 Views'},
        {count: '10', name: '10 Views'},
        {count: '20', name: '20 Views'},
        {count: '50', name: '50 Views'},
        {count: '100', name: '100 Views'}
     ],
     recordedPage: {count:'5'}
   };
   
   //Table Sorting
   $scope.sort = function(keyname){
  	$scope.sortKey = keyname;   //set the sortKey to the param passed
  	$scope.reverse = !$scope.reverse; //if true make it false and vice versa
   }
   
   successMidCd(null);
   getData(); //REST Request
   
   function getData() {
	   
	   var method = "POST";
	   var url = "/pt/ng/rest/getOrgInfoList.do";
	   $http({
	        method: method,
	        url: url,
	        data: angular.toJson($scope.cmOrgInfoVO),
	        headers: {
	            'Content-Type': 'application/json'
	         }
	     }).then(success, error);
	 }
	 function success(res) {	 
		if(res.data.errMsg === ""){ //success
			$scope.cmOrgInfoVO = res.data;
			$scope.resultInfos = res.data.cmOrgInfoVOList;
			
			//searchNm = $scope.cmOrgInfoVO.orgNm;
 			var json = Array();
 			json = JSON.parse(res.data.cdMapList.upCodeJson); //HashMap에 tendTypeCd: [{cd:'',cdNm:''},{}]
 			json.unshift({"orgNo":"","orgNm":"::: ALL"}); //처음에 추가
 			
 			$scope.orgUpCodes = json; //상위콤보에 값 대입
 			alert("searchNm: "+searchNm);
 			
 			var selected = 0; 			
 			for (i = 0; i < json.length; i++) {
 			 var no = json[i]['orgNo']; //기관 코드
 			 var nm = json[i]['orgNm']; //기관 명
 			 if(nm == searchNm){ //Parent 화면에서 입력한 값
 			   selected = i; 			
 			 }
 			 if(no == $scope.cmOrgInfoVO.orgNo){ //Org No으로 검색할때
  			   selected = i; 			
  			 }
 			 if(no == searchUpCode){ //Up option에서 변경됬을때 
   			   selected = i; 			
   			 }
 			}
 			alert(selected);
 			
 			$scope.orgUpCode = $scope.orgUpCodes[selected]; //상위 콤보 Selected 실행함
 			
		}else{
			error(res);
		}
	}
		
	 //검색 버튼 클릭시
	$scope.searchButton = function() {
	   getData();
    } 
	
    //상위 콤보 바꿀때 이벤트 처리(하위 콤보 데이터 가져오기위함)
	$scope.hasUpChanged = function() {
    	
		 searchUpCode = $scope.orgUpCode.orgNo; //upCode 콤보가 검색조건 생태로 유지
		 
		 if($scope.orgUpCode.orgNo === "" ){ //ALL		
			 successMidCd(null);		 
		 }else{
		   $scope.cmOrgInfoVO.orgNo = $scope.orgUpCode.orgNo;
		   $scope.cmOrgInfoVO.orgNm = $scope.orgUpCode.orgNm;
			 
		   var method = "POST";
		   var url = "/pt/ng/rest/getOrgMidCodeList.do"; 
		   $http({
		        method: method,
		        url: url,
		        data: angular.toJson($scope.cmOrgInfoVO),
		        headers: {
		            'Content-Type': 'application/json'
		         }
		     }).then(successMidCd, error);
		 }
	}
	
	function successMidCd(res) {	
		if(res == null){
			 var json = Array();
			 json.unshift({"orgNo":"","orgNm":"::: ALL"}); //처음에 추가		
			 $scope.orgMidCodes = json;
			 $scope.orgMidCode = $scope.orgMidCodes[0];
			 $scope.cmOrgInfoVO.orgNo = "";
			 $scope.cmOrgInfoVO.orgNm = "";
		}else{
			if(res.data.errMsg === ""){ //success			
				var json = Array();
			    json = JSON.parse(res.data.cdMapList.midCodeJson); //HashMap에 tendTypeCd: [{cd:'',cdNm:''},{}]		    
	 			json.unshift({"orgNo":"","orgNm":"::: ALL"}); //처음에 추가 			
	 			$scope.orgMidCodes = json; //TendType Code를 Select Option에 대입함
	 			$scope.orgMidCode = $scope.orgMidCodes[0]; //Select opeion의 default selected setting
	
			}else{
				error(res);
			}
		}
	 }
	
    function error(res) {	 
		bootbox.alert({ 
		size: "large",
		title: "ERROR",
		message: "The data was not searched(" + res.data.errMsg +"). Please contact call center !"
	});
   }
   
   $scope.hasMidChanged = function() {
	   $scope.cmOrgInfoVO.orgNo = $scope.orgMidCode.orgNo;
	   if($scope.orgMidCode.orgNo === ""){
	   	 $scope.cmOrgInfoVO.orgNm = "";
	   }else{
		 $scope.cmOrgInfoVO.orgNm = $scope.orgMidCode.orgNm;
	   }
   }
   $scope.applyPopup = function(resultInfo){
       
       $window.opener.$scope.ngBoardVO.id = resultInfo.orgNm;
       $window.opener.$scope.$apply();       
       $window.close();
   };

 });

</script>

<div ng-app="popupOrgApp" ng-controller="popupOrgController">
<form name="frm" id="frm" method="post">

<!--Search Popup -->
<div class="popup" style="">
	<h2 class="layer_tit"> 
		<span>Search Organization</span>
	</h2>
<!-- 	<a href="#" class="btn_close" ng-click="fn_close();"></span></a> -->
	<div class="layer_cont">
		<div class="table_area">
			<table class="ta_style1 noscroll">				
				<colgroup>
					<col width="270" />
					<col width="*" />
				</colgroup>				
					<tr>
						<th rowspan="2">Organization Only Selection(입력할때만 필요하며 검색할땐 불필요)</th>
						<td>    
							<select ng-model="orgUpCode" ng-options="orgUpCode.orgNm for orgUpCode in orgUpCodes" ng-change="hasUpChanged()" style="width:80%"></select>
						</td>
					</tr>
					<tr>
						<td>
							<select ng-model="orgMidCode" ng-options="orgMidCode.orgNm for orgMidCode in orgMidCodes" ng-change="hasMidChanged()" style="width:80%"></select>
						</td>
					</tr>
				</table>
				<table class="ta_style1 noscroll">
					<colgroup>
						<col width="270" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>Organization Code</th>
						<td class="w_420">
							<input type="text" name="orgNo" id="orgNo" ng-model="cmOrgInfoVO.orgNo" style="width: 99%;" value=""  />
						</td>
					</tr>
					<tr>
						<th>Organization Name</th>
						<td class="w_420">
							<input type="text" name="orgNm" id="orgNm" ng-model="cmOrgInfoVO.orgNm" style="width: 99%;" value="" />
						</td>
					</tr>
					<tr>
					   <th scope ="row"><div><div>Filter</div></div></th>
			             <td>  
						  <div>		    
						     <input type="textFilter" id="searchBox" name="searchBox"  placeholder="You can filter in the sarched data..."  class="form-control input-sm" ng-model="searchBox" />				   	
						  </div>
					   </td>
					</tr>				
			</table>
		</div>
		<!-- // 검색영역 -->
		<div class="table_area">
				<div class="float_r">
			     	 <label for="total" style="color:Red">Total({{cmOrgInfoVO.totalRecordCount}})</label>   
					 <select name="recordedPage" id="recordedPage"
					      ng-options="option.name for option in viewCount.recordedPages track by option.count"
					      ng-model="viewCount.recordedPage"></select>
		  	 	      <button type="button" ng-click="searchButton()" class="btn btn-primary btn-sm" >Search</button>
				</div>
		</div>
		<!-- 검색결과 -->
		<div class="srch_cont">
			<!-- 테이블 -->
			<div class="table_area">
				<table class="ta_style3">
					<caption>
						<span class="blind">Information table</span>
					</caption>
					<colgroup>
						<col width="10%">
						<col width="30%">
						<col width="50%">
						<col width="10%">
					</colgroup>
					<tr> 
						<th ng-click="sort('rownum')" class="al_c">NO
							<span class="glyphicon sort-icon" ng-show="sortKey=='rownum'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
						</th>
						<th ng-click="sort('orgNo')" class="al_c">Organization Code
							<span class="glyphicon sort-icon" ng-show="sortKey=='orgNo'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
						</th>
						<th ng-click="sort('orgNm')" class="al_c">Organization Name
							<span class="glyphicon sort-icon" ng-show="sortKey=='orgNm'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
						</th>
						<th class="al_c">Select</th>						
					</tr>
					<tr dir-paginate="resultInfo in resultInfos|filter:searchBox|orderBy:sortKey:reverse|itemsPerPage:viewCount.recordedPage.count" >
                        <td class="al_c">{{resultInfo.rownum}}</td>
		                <td class="al_c">{{resultInfo.orgNo}}</td>
		                <td class="al_c">{{resultInfo.orgNm}}</td>                
		                <td class="al_c"><button type="button" ng-click="applyPopup(resultInfo)" class="btn btn-info btn-sm" >Select</button></td>
		            </tr>
		            <tr ng-show="!resultInfos.length">
				       <td colspan="6" style="text-align :center">
				         <span class="text-warning">There is no data.</span>
				       </td>
				    </tr>  
				</table>
			</div>
			
			<!-- Pagination -->
			<div class="btn_w_center">
				<dir-pagination-controls
							max-size="viewCount.recordedPage.count"
							direction-links="true"
							boundary-links="true" >
				</dir-pagination-controls>
			</div>
		    <br>
		</div>
	</div>
</div>
</form>
</div>

<!--   <body ng-app="childWindowApp" ng-controller="childWindowController"> -->
<!--          <div class="container"> -->
<!--              <table> -->
<!--                  <tr ng-repeat='shareData in shareDatas'> -->
<!--                      <td > -->
<!--                          <input ng-model='shareData.title' class="form-control" size="10" maxlength="10" placeholder="Item Name" required>  -->
<!--                          <a id="popupSymImg" tabindex="-1" ng-click="closeChildWindow(shareData.title);"> Close Child Window </a> -->
<!--                      </td>     -->
<!--                  </tr> -->
<!--              </table> -->
<!--          </div> -->
<!--    </body>  -->
</html>