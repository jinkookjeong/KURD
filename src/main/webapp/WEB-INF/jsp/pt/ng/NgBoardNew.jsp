<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <meta name="_csrf" content="${_csrf.token}"/> --%>
<%-- <meta name="_csrf_header" content="${_csrf.headerName}"/> --%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>

<script type="text/javaScript" language="javascript">

// var token = $("meta[name='_csrf']").attr("content");
// var header = $("meta[name='_csrf_header']").attr("content");

var fileArray = new Array(); //첨부파일 정보 전송을 위한 변수
var app = angular.module("ngBoardNewApp", []);

app.config(['$httpProvider', function ($httpProvider) { //CSRF
    $httpProvider.defaults.headers.common['${_csrf.headerName}'] = '${_csrf.token}';
}]);

app.controller("ngBoardNewController", function($scope, $http) {
	  
	  $scope.ngBoardVO = {};
	  $scope.ngBoardVO.tendNo = "${ngBoardVO.tendNo}"; //Inital data
	  
	  
	  
	  var json = JSON.parse('${cdTendType}');	  
	  json.unshift({"cd":"NON","cdNm":"::: ALL"}); //처음에 추가
	  //json.push({"cd":"NON","cdNm":"::: ALL"}); //마지막에 추가
	  
	  console.log(json);
	  
 	  $scope.tendTypes = json;
 	  $scope.tendType = $scope.tendTypes[0]; //Sent
	  
	  $scope.toSend = function(status) {
		  $scope.toSave(status);
	  }
	   
	  $scope.toSave = function(status) {
		  
		  if(!$scope.frm.$valid){
			 alert("This is not validation. Please check your input data.");				 
			 return;
		 } 
		  
		  alert("fileArray.length save: "+fileArray.length);
 		  if(fileArray.length == 0){
			  bootbox.confirm({  //Confirm alert
				  size: "small", //large, "", small
				  message: "첨부파일을 첨부하지 않았습니다. 그래도 전송하시겠습니까??", 
				  callback: function(result){				  
				    if(result){ 
				    	$scope.toSaveCall(status);
				    }
				  }
		      })
			return;
		  }
		   
		  bootbox.confirm({  //Confirm alert
			  size: "small", //large, "", small
			  message: "Do you want to save this data?", 
			  callback: function(result){				  
			    if(result){ 
			    	$scope.toSaveCall(status);
			    }
			  }
	      }) 
	  } 
	  
	  $scope.toSaveCall = function(status) {
		   
		   bootbox.hideAll(); //Confirm alert hide!
		   
		   waitingDialog.show("KRG PPS System","Please wait" , {dialogSize: 'sm' });
		   
		   //data binding
		   //var jsonInfo = JSON.stringify(fileArray);
		   $scope.ngBoardVO.ngFileVOList = fileArray;
		   $scope.ngBoardVO.fromUserId = "C203540000011A";
		   $scope.ngBoardVO.pageId = "ngBoard";
		   $scope.ngBoardVO.toUserId = "G2220122120";
		   $scope.ngBoardVO.status = status;
		   
		   $scope.ngBoardVO.tendType = $scope.tendType.cd;
		   
	       $http({
	         method: 'POST',
	         url: "/pt/ng/rest/ngNewBoardSave.do",
	         data: angular.toJson($scope.ngBoardVO),
	         timeout: 1000 * 60 * 10,
	         headers: {
	             'Content-Type': 'application/json'
	          }
	       
	        }).then(success, error);
	  }
	
	function success(res) {	 
		 waitingDialog.hide();
		 if(res.data.errMsg === ""){ //errMsg null is success
		 		bootbox.alert({ 
				  size: "small",
				  title: "Information",
				  message: "This data was saved successful.", 
				  callback: function(){  $scope.goList(); }
				});
		 }else{
			 error(res);
		 }
	}
	
	function error(res) {	 
		waitingDialog.hide();
        bootbox.alert({ 
			  size: "large",
			  title: "ERROR!",
			  message: "The data was not saved." + res.data.errMsg 
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
<div id="ngBoardNewApp" ng-app="ngBoardNewApp" ng-controller="ngBoardNewController">

<div class="table_area">
<!-- 	 <form name="frm" id="frm" method="post" ng-submit="toSave()" accept-charset="utf-8" > -->
	 
	 <form name="frm" id="frm" method="post" accept-charset="utf-8" >
<!-- 	  <button type="button" class="btn btn-primary" onclick="waitingDialog.show('Searching...','Please wait a moment.',{dialogSize: 'm'});setTimeout(function () {waitingDialog.hide();}, 30000);">Show a dialog</button> -->
<!-- 	  <button type="button" class="btn btn-success" onclick="waitingDialog.show('Loading...','Please wait',{dialogSize: 'sm', progressType: 'success'});setTimeout(function () {waitingDialog.hide();}, 20000);">Show dialog</button> -->
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
					<th><div><label for="number" maxlength="20">Tend No</label></div></th>
					<td colspan="3">
						<div> {{ngBoardVO.tendNo}} </div>
					</td>
				</tr>
 				<tr>
 				<th><div><label for="Name" maxlength="20">Tend Nm</label></div></th>
					<td colspan="3">
						<div>
						  <input type="text" class="form-control input-sm" name="tendNm" placeholder="tend name..." 
						  	 	 ng-model="ngBoardVO.tendNm" ng-minlength="2" ng-maxlength="10" ng-required="true" />
                            <span style="color:Red" ng-show="frm.tendNm.$error.required">* This field is required.</span>
                            <span style="color:Red" ng-show="frm.tendNm.$error.minlength">* At least 2 characters are required.</span>
                            <span style="color:Red" ng-show="frm.tendNm.$error.maxlength">* 10 characters is required.</span>                           
						</div>
					</td>
				</tr>
 				<tr>
 				<th><div><label for="Type" maxlength="20">Tend Type</label></div></th>
					<td colspan="3">
						<div>
						    
<!-- 						    <input type="text" class="form-control input-sm" name="tendType" ng-model="ngBoardVO.tendType" ng-required="true"  /> -->
                            
							<select ng-model="tendType" ng-options="tendType.cdNm for tendType in tendTypes">		                    	 
		                    </select>
		                    	
<%-- 		                    <c:forEach var="listCommonCode" items="${listCommonCode}" varStatus="status"> --%>
<%-- 								<option value="${listCommonCode.cd}" >${listCommonCode.cdNm}</option>			 --%>
<%-- 							</c:forEach> --%>
		                    	
		                    	
						</div>
					</td>
				</tr>
				<tr>
					<th><div><label for="Writer" maxlength="20">Writer</label></div></th>
					<td colspan="3">
						<div>
						  <input type="text" class="form-control input-sm" name="id" placeholder="writer..." ng-model="ngBoardVO.id" 
						         ng-minlength="2" ng-maxlength="10" ng-required="true"  />
                            <span style="color:Red" ng-show="frm.id.$error.required">* This field is required.</span>
                            <span style="color:Red" ng-show="frm.id.$error.minlength">* At least 2 characters are required.</span>
                            <span style="color:Red" ng-show="frm.id.$error.maxlength">* 10 characters is required.</span>                           
						</div>
					</td>
				</tr>
				<tr>
					<th><div><label for="title" maxlength="20">Title</label></div></th>
					<td colspan="3">
						<div>
						<input type="text" class="form-control input-sm" name="title" placeholder="title..." 
						       ng-model="ngBoardVO.title" ng-minlength="2" ng-maxlength="10" ng-required="true" />
							<span style="color:Red" ng-show="frm.title.$error.required">* This field is required.</span>
                            <span style="color:Red" ng-show="frm.title.$error.minlength">* At least 2 characters are required.</span>
                            <span style="color:Red" ng-show="frm.title.$error.maxlength">* 10 characters is required.</span>
						</div>  
					</td>
				</tr>
				<tr>
					<th><div><label for="Content" maxlength="20">Content</label></div></th>
					<td colspan="3">
						 <textarea class="well well-lg" rows="10" cols="100" style="width:500px; height:150px;" name="contents" placeholder="contents..." 
						           ng-model="ngBoardVO.contents" ng-maxlength="500" ng-required="true"></textarea>
						 <span style="color:Red" ng-show="frm.contents.$error.required"> * This field is required </span>
						 <span style="color:Red" ng-show="frm.contents.$error.maxlength">* 500 characters is required.</span>
					</td>
				</tr>
					
					
			</tbody>
		</table>
		
    <!-- Attached documents end -->
    <h3 class="con_sub_tit">
        <span>Attachment Files</span>
    </h3>
	<!-- 첨부파일 처리 -->
	<!-- 초기에는 fileRefId 필요 없음, init 초기화면이냐 아니냐, realDel: 진짜 삭제하냐정보 -->
    <jsp:include page="/WEB-INF/jsp/com/util/NgFileUpload.jsp" flush="true" >
    	 <jsp:param name="userId" value="C203540000011A" />
    	 <jsp:param name="pageId" value="ngBoard" />
    	 <jsp:param name="init" value="Y" />
    	 <jsp:param name="realDel" value="Y" />
    </jsp:include>
        <br>
		<div class="btn_box btn_w_center">
		    <input type="submit" ng-click="toSend('Sent')" class="btn btn-primary btn-md"  name="sendBtn" value="To send" />			
		    <input type="submit" ng-click="toSave('Saved')" class="btn btn-primary btn-md" name="saveBtn" value="To save" />		   
			<a href="#" id="list" class="btn btn-default btn-md" ng-click="goList()"><span>Go to List</span></a>
		</div>
	</form>
</div>
</div>
</div>