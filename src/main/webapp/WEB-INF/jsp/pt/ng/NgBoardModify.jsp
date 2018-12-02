<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>

<script type="text/javaScript" language="javascript">

var fileArray = new Array(); //첨부파일 정보 전송을 위한 변수
var app = angular.module("ngBoardModifyApp", []);
app.config(['$httpProvider', function ($httpProvider) { //CSRF
    $httpProvider.defaults.headers.common['${_csrf.headerName}'] = '${_csrf.token}';
}]);
app.controller("ngBoardModifyController", function($scope, $http) {
	   
	  $scope.ngBoardVO = {};	  
	  $scope.ngBoardVO.tendNo = "${ngBoardVO.tendNo}"; //jQuery로 받아온 pk
	  $scope.ngBoardVO.tendSeq = "${ngBoardVO.tendSeq}"; //jQuery로 받아온 pk
	  
	  getData();
	  
	  function getData() {
		  var method = "POST";
		  var url = "/pt/ng/rest/getBoardDetail.do";
		  $http({
		       method: method,
		       url: url,
		       data: angular.toJson($scope.ngBoardVO),
		       headers: {
		           'Content-Type': 'application/json'
		        }
		  }).then(successCallback, errorCallBack);
	  }
	 	
	  function successCallback(res) {
		  if(res.data.errMsg === ""){ //errMsg null is success
	 	 	  $scope.ngBoardVO = res.data.ngBoardVO; //detail information	
	 	 	  
	 	 	  var json = Array();
	 		  json = JSON.parse(res.data.cdMapList.tendTypeCd); //HashMap에 tendTypeCd: [{cd:'',cdNm:''},{}]
	 		  $scope.tendTypes = json; //TendType Code를 Select Option에 대입함	 		  
	 		  //console.log(json);
	 	      var selected = 0;
		 	  for (i = 0; i < json.length; i++) {
		 		  var cd = json[i]['cd']; //TendType Code가 같은것
		 		  if(cd == $scope.ngBoardVO.tendType){
		 			 selected = i;
		 		  }
		 	  }
	 	 	  $scope.tendType = $scope.tendTypes[selected]; //Select opeion의 default selected setting
	 	 	  
		  }else{
			  errorCallBack(res);
		  }
	  }
	  
	  $scope.toSend = function(status) {
		  $scope.toSave(status);
	  }
	  
	  $scope.toSave = function(status) {
		  
		  var msg = "";
		  if(status === "Sent"){
		  	  msg = "Do you want to send this data?";
		  }else{
			  msg = "Do you want to save this data?";
		  }
				  
		  bootbox.confirm({  //Confirm alert			  
			  size: "small", //large, "", small			
			  message: msg,			  
			  callback: function(result){				  
			    if(result){
			       waitingDialog.show("KRG PPS System" ,"Please wait...", {dialogSize: 'sm' });
			       $scope.ngBoardVO.ngFileVOList = fileArray; //첨부파일
					  $scope.ngBoardVO.fromUserId = "C203540000011A";
					  $scope.ngBoardVO.pageId = "ngBoard";
					  $scope.ngBoardVO.toUserId = "G2220122120";
					  $scope.ngBoardVO.status = status;
					  $scope.ngBoardVO.tendType = $scope.tendType.cd;
				      $http({
				         method: 'POST',
				         url: "/pt/ng/rest/updateBoardDetail.do",
				         data: angular.toJson($scope.ngBoardVO),
				         headers: {
				             'Content-Type': 'application/json'
				          }
				      }).then(successSavedCall, errorCallBack); 
			    }
			  }
	      })		
	  }
	  
	  function successSavedCall(res) {	 
			 waitingDialog.hide();
			 $scope.ngBoardVO = res.data.ngBoardVO;
			 if(res.data.errMsg === ""){ //errMsg null is success
			 		bootbox.alert({ 
					  size: "small",
					  title: "Information",
					  message: "This data was saved successful.", 
					  callback: function(){ $scope.goList(); }
					});
			 }else{
				 errorCallBack(res);
			 }
	  } 
	  
	  function errorCallBack(res) {	 
			waitingDialog.hide();
	        bootbox.alert({ 
				  size: "large",
				  title: "ERROR!",
				  message: "The data is error : " + res.data.errMsg 
			});
	  }
	  
	  $scope.goList = function() {
		  var f = document.frm;
			f.method = "post";
			f.action = "/pt/ng/sample/boardList.do";
			f.submit();		
	  }
});
</script>

<div id="contents"> 
<div id="ngBoardModifyApp" ng-app="ngBoardModifyApp" ng-controller="ngBoardModifyController">
<div class="table_area">
 <form name="frm" method="post"  accept-charset="utf-8"> 
	 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<table class="ta_style1">
		<colgroup>
			<col width="15%">
			<col width="35%">
			<col width="15%">
			<col width="35%">
		</colgroup>
		<thead></thead>
		<tfoot></tfoot>
		<tbody>
			<tr>
				<th><div><label for="tendNo" maxlength="20">Tend No</label></div></th>
				<td colspan="3">
					<div> {{ngBoardVO.tendNo}} </div>
				</td>
			</tr>
				<tr>
				<th><div><label for="tendNm" maxlength="20">Tend Nm</label></div></th>
				<td colspan="3">
				    <input type="text" class="form-control input-sm" name="tendName" ng-model="ngBoardVO.tendNm" ng-required="true" />
                    <span style="color:Red" ng-show="frm.tendName.$error.required"> * This field is required </span>               				 							
				</td>	
			</tr>
				<tr>
				<th><div><label for="tendType" maxlength="20">Tend Type</label></div></th>
				<td colspan="3">					 
					<select ng-model="tendType" ng-options="tendType.cdNm for tendType in tendTypes"></select>
				</td>
			</tr>
			<tr>
				<th><div><label for="writer" maxlength="20">Writer</label></div></th>
				<td colspan="3">
					 <div>{{ngBoardVO.id}}</div>
				</td>
			</tr>
			<tr>
				<th><div><label for="title" maxlength="20">Title</label></div></th>
				<td colspan="3">
					<input type="text" class="form-control input-sm" name="title" ng-model="ngBoardVO.title" ng-required="true" />	
					<span style="color:Red" ng-show="frm.title.$error.required"> * This field is required </span>			  
				</td>
			</tr>
			<tr>
				<th><div><label for="status" maxlength="20">Status</label></div></th>
				<td colspan="3">
					<div>{{ngBoardVO.status}}</div>						  
				</td>
			</tr>
			<tr>
				<th><div><label for="contents" maxlength="20">Content</label></div></th>
				<td colspan="3">
			  		 <textarea class="well well-lg" rows="10" cols="100" style="width:500px; height:150px;" name="contents" placeholder="contents..." ng-model="ngBoardVO.contents" ng-required="true"></textarea>
					 <span style="color:Red" ng-show="frm.contents.$error.required"> * This field is required </span>						
				</td>
			</tr>
		</tbody>
	</table>
	
	 <!-- Attached documents end -->
	<h3 class="con_sub_tit">
        <span>Attachment Files</span>
    </h3>
	<!-- 첨부파일 처리 -->
	<!-- 수정일때 fileRefId 추가 필요 init 초기화면이냐 아니냐, realDel: 진짜 삭제하냐정보 -->
    <jsp:include page="/WEB-INF/jsp/com/util/NgFileUpload.jsp" flush="true" >    
         <jsp:param name="fileRefId" value="${ngBoardVO.fileRefId}"  /> 
    	 <jsp:param name="userId" value="C203540000011A" />
    	 <jsp:param name="pageId" value="ngBoard" />
    	  <jsp:param name="init" value="N" />
    	  <jsp:param name="realDel" value="N" />
    </jsp:include>
    <br> 
	<div class="btn_box_w5 btn_w_center">
		<input type="submit" ng-click="toSend('Sent')" class="btn btn-primary btn-md" name="sendBtn" value="To Send" />
	    <button type="button" ng-click="toSave('Saved')" class="btn btn-primary btn-md" >To Save</button>
		<button type="button" ng-click="goList()" class="btn btn-default btn-md" >Go to list</button>		
	</div>
	<!-- </form> -->
	</form>
</div>
</div>
</div>
