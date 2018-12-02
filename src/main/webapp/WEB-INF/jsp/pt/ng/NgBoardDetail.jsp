<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>

<%-- <meta name="_csrf" content="${_csrf.token}"/> --%>
<%-- <meta name="_csrf_header" content="${_csrf.headerName}"/> --%>

<script type="text/javaScript" language="javascript">
// var token = $("meta[name='_csrf']").attr("content");
// var header = $("meta[name='_csrf_header']").attr("content");
 
var app = angular.module("ngBoardDetailApp", []);

app.config(['$httpProvider', function ($httpProvider) { //CSRF
    $httpProvider.defaults.headers.common['${_csrf.headerName}'] = '${_csrf.token}';
}]);

app.controller("ngBoardDetailController", function($scope, $http) {
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
	    }).then(success, errorCallBack);
	  }
	 	
	  function success(res) {
		  if(res.data.errMsg === ""){ //errMsg null is success
	 	 	$scope.ngBoardVO = res.data.ngBoardVO; //detail information
	 		$scope.resultInfos = res.data.ngFileVOList;
	 		//$scope.resultInfos = res.data.sampleNgFileList;
	 		
	 		//var tendTypeCd = $scope.ngBoardVO.tendType;
	 		//document.getElementById("TendTypeName").innerHTML = carName;
	 	    	
		  }else{
			  errorCallBack(res);
		  }
	  }
	  
	  $scope.toSend = function(status) {
		  
		  var msg = "Do you want to send this data?";
		  bootbox.confirm({  //Confirm alert			  
			  size: "small", //large, "", small			
			  message: msg,			  
			  callback: function(result){				  
			    if(result){
			       waitingDialog.show("KRG PPS System" ,"Please wait...", {dialogSize: 'sm' });			       
			       $scope.ngBoardVO.status = status;
			       console.log($scope.ngBoardVO);
				      $http({
				         method: 'POST',
				         url: "/pt/ng/rest/updateNgBoardToSend.do",
				         data: angular.toJson($scope.ngBoardVO),
				         headers: {
				             'Content-Type': 'application/json'
				          }
				      }).then(successSendCall, errorCallBack); 
			    }
			  }
	      })		
	  }
	  function successSendCall(res) {
			 waitingDialog.hide();
			 if(res.data.errMsg === ""){ //errMsg null is success
			 		bootbox.alert({ 
					  size: "small",
					  title: "Information",
					  message: "This data was sent successful.", 
					  callback: function(){  getData(); }
					});
			 }else{
				 error(res);
			 }
	  }
	  
	  $scope.toModify = function() {
		  bootbox.confirm({  //Confirm alert
			  size: "small", //large, "", small
			  message: "Do you want to modify this data?", 
			  callback: function(result){				  
			    if(result){
			       waitingDialog.show("KRG PPS System" ,"Going to the page. Please wait...", {dialogSize: 'sm' });
			       var f = document.frm; 			          
				       f.tendNo.value = $scope.ngBoardVO.tendNo;
				       f.tendSeq.value = $scope.ngBoardVO.tendSeq;
				       f.fileRefId.value = $scope.ngBoardVO.fileRefId;
				       f.viewType.value = "modify";
				       f.action = "/pt/ng/sample/boardPage.do"; //Controller 한개url로 처리
				       //f.action = "/pt/ng/sample/boardModify.do";
			     	   f.submit();			     		
			    }
			  }
	      })
	  }
	  
	  $scope.toDelete = function() {
		  bootbox.confirm({ 
			  size: "small", //large, "", small
			  message: "Do you want to delete this data?", 
			  callback: function(result){				  
			    if(result){
			    	  waitingDialog.show("KRG PPS System","This data is deleting...", {dialogSize: 'sm' });
			 		  var tendNo = $scope.ngBoardVO.tendNo;
			 		  var tendSeq = $scope.ngBoardVO.tendSeq;
			 		  var fileRefId = $scope.ngBoardVO.fileRefId; //to delete the file db
			 	      $http({
				         method: 'DELETE',
				         url: '/pt/ng/rest/delBoardDetail.do?tendNo='+tendNo+"&tendSeq="+tendSeq+"&fileRefId="+fileRefId				        		 
				      }).then(successCallBack, errorCallBack);
			    }	  
			  }
	      })
	  };
	  
	  function successCallBack(res) {
			 waitingDialog.hide();
			 if(res.data.errMsg === ""){ //errMsg null is success
			 		bootbox.alert({ 
					  size: "small",
					  title: "Information",
					  message: "This data was deleted successful.", 
					  callback: function(){  $scope.goList(); }
					});
			 }else{
				 error(res);
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
	  
	  
	  //Table Sorting
	  $scope.sort = function(keyname){
	 		$scope.sortKey = keyname;   //set the sortKey to the param passed
	 		$scope.reverse = !$scope.reverse; //if true make it false and vice versa
	  } 
  });
  
</script>

<div id="contents">
<div  id="ngBoardDetailApp" ng-app="ngBoardDetailApp" ng-controller="ngBoardDetailController">
<div class="table_area">
	<form id="Frm" name="frm"  accept-charset="utf-8"  method="post" >
	 <input type="hidden" name="idx" value="">
<!-- 	 <button type="button" class="btn btn-primary" onclick="waitingDialog.show('','Searching...',{dialogSize: 'm'});setTimeout(function () {waitingDialog.hide();}, 3000);">Show a dialog</button> -->
<!-- 	 <button type="button" class="btn btn-success" onclick="waitingDialog.show('','Waiting...',{dialogSize: 'sm', progressType: 'success'});setTimeout(function () {waitingDialog.hide();}, 2000);">Show dialog</button> -->         
        <input type="hidden" id="tendNo" name="tendNo" />
		<input type="hidden" id="tendSeq" name="tendSeq" />             
		<input type="hidden" id="fileRefId" name="fileRefId" />
		<input type="hidden" id="viewType" name="viewType" />
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
						<div>{{ngBoardVO.tendNm}}</div>
					</td>
				</tr>
 				<tr>
 				<th><div><label for="tendType" maxlength="20">Tend Type</label></div></th>
					<td colspan="3">
					    <div>{{ngBoardVO.tendTypeNm}}</div>
<%--  					    (<cmcd:cdNm cd="EP1820" />)</div> --%>
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
						<div>{{ngBoardVO.title}}</div>						  
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
					  <div class="well well-lg">{{ngBoardVO.contents}}</div>						
					</td>
				</tr>
			</tbody>
		</table>
		
		<!--  첨부파일 처리 -->
<!-- 	    <input type="hidden" class="form-control input-sm" id="attachFileId" name="attachFileId" ng-model="ngBoardVO.fileRefId" /> -->
	    <h3 class="con_sub_tit">
	        <span>Attachment Files (Total {{resultInfos.length}})</span>
	    </h3>
		<div class="table_area">
		   <table class="ta_style3" id="tb_attachedFile">
		       <colgroup>
		           <col width="10%">
		           <col width="70%">
		       </colgroup>
		       <thead>
		           <tr>
		              <th ng-click="sort('rownum')" class="al_c">NO
				    	<span class="glyphicon sort-icon" ng-show="sortKey=='rownum'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
					  </th>
						
		               <th ng-click="sort('originFileNm')" class="al_c">File Name
				    	<span class="glyphicon sort-icon" ng-show="sortKey=='originFileNm'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
					  </th>            
		           </tr>       
		       </thead>
		       <tbody>
		         <tr ng-repeat="resultInfo in resultInfos|orderBy:sortKey:reverse" >
                   <td class="al_c">{{resultInfo.rownum}}</td>
                   <td class="al_l"><a href="/pt/ng/rest/download?fileRefId={{resultInfo.fileRefId}}&fileUuid={{resultInfo.fileUuid}}" >{{resultInfo.originFileNm}} ({{resultInfo.fileSize /1024 | number}} KB)</a></td>                                      
		         </tr>       
		          <tr ng-show="!resultInfos.length">
			       <td colspan="2" style="text-align :center">
			         <span class="text-warning">There is no file.</span>
			       </td>
		    	</tr>
		        </tbody>
		    </table>
		</div>
			<div ng-if="ngBoardVO.status === 'Saved'" class="btn_box btn_w_center">
				<button type="button" ng-click="toSend('Sent')" class="btn btn-primary btn-md" >To send</button>
				<button type="button" ng-click="toModify()" class="btn btn-primary btn-md" >To modify</button>
				<button type="button" ng-click="toDelete()" class="btn btn-danger btn-md" >To delete</button>
				<button type="button" ng-click="goList()" class="btn btn-default btn-md" >Go to list</button>
		   </div>
			<div ng-if="ngBoardVO.status === 'Sent'" class="btn_box btn_w_center">
				<button type="button" ng-click="goList()" class="btn btn-default btn-md" >Go to list</button>
		   </div>
	</form> 

</div>
</div>
</div>