<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
<%-- <meta name="_csrf" content="${_csrf.token}"/> --%>
<%-- <meta name="_csrf_header" content="${_csrf.headerName}"/> --%>

<script type="text/javaScript" language="javascript"> 

// var token = $("meta[name='_csrf']").attr("content");
// var header = $("meta[name='_csrf_header']").attr("content");
// console.log(header);
// console.log(token);

var app = angular.module("ngBoardListApp", ['angularUtils.directives.dirPagination','ngMaterial','ngMessages']);

app.config(['$httpProvider', function ($httpProvider) { //CSRF
    $httpProvider.defaults.headers.common['${_csrf.headerName}'] = '${_csrf.token}';
}]);
 
//Controller Part
app.controller("ngBoardListController", function($scope, $http, $timeout, $window) {
 
 $scope.resultInfos = [];
 $scope.ngBoardVO = {};
 
 //List View Count
 $scope.viewCount = {
	recordedPages: [
      {count: '5', name: '5 Views'},
      {count: '10', name: '10 Views'},
      {count: '20', name: '20 Views'},
      {count: '50', name: '50 Views'},
      {count: '100', name: '100 Views'}
    ],
    recordedPage: {count:'10'} //This sets the default value of the select in the ui
 };
  
  //document status(Save/Send)
  $scope.docStatus = [
	 {name: '::: All', code: 'ALL'},
     {name:'Sent', code:'Sent' },
     {name:'Saved', code:'Saved'}
   ];
   $scope.status = $scope.docStatus[0]; //Sent
   
 //Table Sorting
 $scope.sort = function(keyname){
	$scope.sortKey = keyname;   //set the sortKey to the param passed
	$scope.reverse = !$scope.reverse; //if true make it false and vice versa
 } 
  
 //Calendar
 var today = new Date();
 today.setMonth(today.getMonth()-3); //From is 3 months before(default)
 $scope.fromDate = new Date(today.getFullYear(),today.getMonth() , today.getDate());
 $scope.toDate = new Date(); //today
 $scope.minDate = new Date(today.getFullYear()-10,today.getMonth() , today.getDate());
 $scope.maxDate = new Date(today.getFullYear()+10,today.getMonth() , today.getDate());
 $scope.open = function() {	  
   $timeout(function() {
     $scope.fromDate = new Date();
     $scope.toDate = new Date();
     $scope.minDate = new Date(today.getFullYear()-10,today.getMonth() , today.getDate());
     $scope.maxDate = new Date(today.getFullYear()+10,today.getMonth() , today.getDate());
   }, 400);
 }
 //init calendar
 $scope.ngBoardVO.startDate = moment($scope.fromDate).format("YYYYMMDD");
 $scope.ngBoardVO.endDate = moment($scope.toDate).format("YYYYMMDD");
 	
 //Rest call data from server
 function getData() {
   
   var method = "POST";
   var url = "/pt/ng/rest/getBoardList.do";
   
   $http({
	   
        method: method,
        url: url,
        data: angular.toJson($scope.ngBoardVO),
        headers: {
            'Content-Type': 'application/json'
         }
     }).then(success, error);
 }
	
 function success(res) {	 
	 if(res.data.errMsg === ""){ //success
		 $scope.ngBoardVO = res.data;
		 $scope.resultInfos = res.data.ngBoardVOList;
	 }else{
		 error(res);
	 }
 }
 function error(res) {	 
	 bootbox.alert({ 
		  size: "large",
		  title: "ERROR",
		  message: "The data was not searched(" + res.data.errMsg +"). Please contact call center !"
	});
 }

  //get data from server
  getData();  
  
  //click search button
  $scope.searchButton = function() {
 	  
 	if(!$scope.frm.$valid){
 	  alert("Your input data is not wrong. Please check input data.");
	  return;
	}
 	
 	//Data binding 
 	$scope.ngBoardVO.status = $scope.status.code;
    $scope.ngBoardVO.startDate = moment($scope.fromDate).format("YYYY-MM-DD");
 	$scope.ngBoardVO.endDate = moment($scope.toDate).format("YYYY-MM-DD");
 	if($scope.ngBoardVO.startDate > $scope.ngBoardVO.endDate){
 		alert("From Date is wrong.");
 		return;
 	}
 	
 	 getData();
  }
  
  $scope.goModal = function(md) {	  
	  $scope.modal = md;
	  $("#boardModel").modal('show');
  }
  
  $scope.goDetail = function(md) {
	  waitingDialog.show("KRG PPS System" ,"Please wait", {dialogSize: 'sm' });
	  //setTimeout(function () {waitingDialog.hide();}, 3000);
		 
	  var f = document.frm;
	      f.tendNo.value = md.tendNo;
	      f.tendSeq.value = md.tendSeq;
	      f.viewType.value ="detail";
	      f.action = "/pt/ng/sample/boardPage.do";
	 	  //f.action = "/pt/ng/sample/boardDetail.do";		
	 	  f.submit();
  }
  
  $scope.goNew = function() {
	  waitingDialog.show("KRG PPS System","Please wait.", {dialogSize: 'sm' });
	    var f = document.frm;		
	    f.viewType.value ="new";
	    f.action = "/pt/ng/sample/boardPage.do";
		//f.action = "/pt/ng/sample/boardNew.do";
		f.submit();
  }
  
  $scope.searchOrgPopup = function(){
	  
	  $window.$scope = $scope;
	  $window.searchPopValue = angular.toJson($scope.ngBoardVO);      
      var url = "/pt/ng/sample/comPopupOrgSearch.do"; 
	  var title  = "Search";
	  var status = "toolbar=no,directories=no,location=no,scrollbars=no,resizable=no,alwaysRaised=yes,status=no,menubar=no,width=930,height=930,top=0,left=20"; 
	  $window.open(url, title, status);
  };
  
  $scope.logout = function(){
	  var f = document.frm;		 
	    f.action = "/um/logout.do";
		f.submit();
  }
  
}); //end Controller

// Calendar Date Format
app.config(function($mdDateLocaleProvider) {
  $mdDateLocaleProvider.formatDate = function(date) {
	  return moment(date).format('DD/MM/YYYY');
  };
  $mdDateLocaleProvider.parseDate = function(dateString) {
      var m = moment(dateString, 'DD/MM/YYYY', true);
      return m.isValid() ? m.toDate() : new Date(NaN);
   }
});


</script> 

<div ng-app="ngBoardListApp" ng-controller="ngBoardListController">
<h4 class="con_tit"><span>Sample(Spring MVC 5 + Spring Security 5 + REST + AngularJS)</h4> 
<!--  <span ng-click="logout()" class="al_r">Logout</span></span> -->
	<div class="table_area1"> 
		<form:form name="frm" method="post" accept-charset="utf-8">
		
			<input type="hidden" id="tendNo" name="tendNo" />
		    <input type="hidden" id="tendSeq" name="tendSeq" />
		    <input type="hidden" id="viewType" name="viewType" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
		      <table class="ta_style1">
					<colgroup>
						<col width="10%">
						<col width="40%">
						<col width="10%">
						<col width="40%">
					</colgroup>				
					<tbody>	
						<tr>
		                    <th scope="row"><div><div>Tend Name</div></div></th>
		                    <td>
		                    	<input type="text" class="form-control input-sm" id="tendName"  name="tendName" ng-model="ngBoardVO.tendNm" /> 
							</td>
		                    
		                    <th scope="row"><div><div>Tend Num</div></div></th>
		                    <td>
								<input type="text" class="form-control input-sm" id="tendNumber" name="tendNumber" ng-model="ngBoardVO.tendNo" /> 
								<input type="hidden" class="form-control input-sm" id="tendSequence" name="tendSequence" ng-model="ngBoardVO.tendSeq" />
		                    </td>
		                </tr>
		                <tr>
		                    <th scope="row"><div><div>Title</div></div></th>
		                    <td>
								 <input type="text" id="title" name="title" class="form-control input-sm" ng-model="ngBoardVO.title" />
		                    </td>
		                    
		                    <th scope="row"><div><div>Status</div></div></th>
		                    <td >
		                    	<select ng-model="status" style="width:100%" ng-options="status.name for status in docStatus">		                    	 
		                    	</select>
							</td>
		                </tr>
		                <tr>
		                 <th scope="row"><div><div>To User</div></div></th>
		                    <td>
								<input type="text" class="form-control input-sm" id="toUserId" name="toUserId"  ng-model="ngBoardVO.toUserId" /> 
		                    </td>
		                    <th scope ="row"><div><div>Org Name</div></div></th>
		                     <td>
		                       <div class="input-group">
			                     <div class="input-group input-group-sm">  
			                     
<!-- 			                        <input type="text" ng-model="iptext.text" /> -->
<!--             						<a id="popupSymImg" tabindex="-1" ng-click="openChildWindow();"> Call Child Window </a> -->
<!--             						<input type="text" id="searchPeNo" style="width:30%" name="searchPeNo" value="" /> -->
            							
            							<input type="text"class="input-lg" size="36px" name="id" ng-model="ngBoardVO.id" placeholder="Org search...">
					                   	<div class="input-group-btn">
					                    	<button class="btn btn-default" type="button" ng-click="searchOrgPopup();">
										         <i class="glyphicon glyphicon-search"></i>
										    </button>
									    </div>
									    
<!-- 				                    	<input type="text"class="input-lg" size="36px" name="id" ng-model="ngBoardVO.id" placeholder="writer search..."> -->
<!-- 					                   	<div class="input-group-btn"> -->
<!-- 					                    	<button class="btn btn-default" type="button" data-toggle="modal" data-target="#boardmodel"> -->
<!-- 										         <i class="glyphicon glyphicon-search"></i> -->
<!-- 										    </button> -->
<!-- 									    </div> -->
								</div>
							   </div>
							</td>
		              </tr>
					  <tr>
					   <th scope ="row"><div><div>Filter</div></div></th>
			             <td>  
						  <div>		    
						     <input type="textFilter" id="searchBox" name="searchBox"  placeholder="You can filter in the sarched data..."  class="form-control input-sm" ng-model="searchBox" />				   	
						  </div>
					   </td>
						 
					  <th scope="row"><div><div>Date</div></div></th>
		     	      <td>
				      <div>
					   <md-content>
				        <md-datepicker ng-model="fromDate" name="fromDate" placeholder="From date" name="startdate" md-min-date="minDate" md-max-date="maxDate" required></md-datepicker>
				        <md-datepicker ng-model="toDate" name="toDate" placeholder="To date" name="enddate" md-min-date="minDate" md-max-date="maxDate" required></md-datepicker>
<!-- 				      <div ng-messages="Frm.fromDate.$error" style="color:Red"> -->
<!-- 				        <div ng-message="valid">From date is not valid!</div> -->
<!-- 				        <div ng-message="required">This field is required</div> -->
<!-- 				        <div ng-message="maxdate">Date is too late!</div> -->
<!-- 				        <div ng-message="mindate">Date is too early!</div> -->
<!-- 				      </div>		 -->
<!-- 				      <div ng-messages="Frm.toDate.$error" style="color:Red"> -->
<!-- 				        <div ng-message="valid">To date is not valid!</div> -->
<!-- 				        <div ng-message="required">This field is required</div> -->
<!-- 				        <div ng-message="maxdate">Date is too late!</div> -->
<!-- 				        <div ng-message="mindate">Date is too early!</div> -->
<!-- 				      </div>				       -->
				     </md-content>
				    </div>
				   </td>				 
	             </tr>
			</tbody>	
			</table>
			
				<div class="float_r">
			     	 <label for="total" style="color:Red">Total({{ngBoardVO.totalRecordCount}})</label>   
					 <select name="recordedPage" id="recordedPage"
					      ng-options="option.name for option in viewCount.recordedPages track by option.count"
					      ng-model="viewCount.recordedPage"></select>
		  	 	      <button type="button" ng-click="searchButton()" class="btn btn-primary btn-sm" >Search</button>
				</div>
		</form:form>	
		<br>&nbsp;
<!-- 		테이블 리스트 Header(Sorting) -->
		<table class="ta_style3">
		    <colgroup> 
		        <col width="5%"/>
		        <col width="20%"/>
		        <col width="20%"/>
		        <col width="10%"/>
		        <col width="25%"/>
		        <col width="20%"/>		        
		        <col width="10%"/>
		    </colgroup>
			<tr> 
				<th ng-click="sort('rownum')" class="al_c">NO
					<span class="glyphicon sort-icon" ng-show="sortKey=='rownum'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('tendNo')" class="al_c">Tend Number
					<span class="glyphicon sort-icon" ng-show="sortKey=='tendNo'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('tendNm')" class="al_c">Tend Name
					<span class="glyphicon sort-icon" ng-show="sortKey=='tendNm'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				
				<th ng-click="sort('title')" class="al_c">Title
					<span class="glyphicon sort-icon" ng-show="sortKey=='title'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('contents')" class="al_c">Content
					<span class="glyphicon sort-icon" ng-show="sortKey=='contents'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('wdate')" class="al_c">Wrate Date
					<span class="glyphicon sort-icon" ng-show="sortKey=='wdate'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
				<th ng-click="sort('status')" class="al_c">Status
					<span class="glyphicon sort-icon" ng-show="sortKey=='status'" ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"></span>
				</th>
			</tr>
<!-- 			list -->
			<tr dir-paginate="resultInfo in resultInfos|filter:searchBox|orderBy:sortKey:reverse|itemsPerPage:viewCount.recordedPage.count" >
                
                <td class="al_c">{{resultInfo.rownum}}</td>
                <td class="al_c"><a href="#" ng-click="goDetail(resultInfo)">{{resultInfo.tendNo}}</a></td>
                <td class="al_c"><a href="#" ng-click="goModal(resultInfo)">{{resultInfo.tendNm}}</a></td>                
                <td class="al_l">{{resultInfo.title}}</td>
			    <td class="al_l" >
				<nobr class="tooltipK" title="{{resultInfo.contents}}"> {{resultInfo.contents}} </nobr>
			    </td>  
				<td class="al_c">{{resultInfo.wdate | date:"dd/MM/yyyy HH:mm:ss"}}</td>
				<td class="al_c">{{resultInfo.status}}</td>
            </tr>
            <tr ng-show="!resultInfos.length">
		       <td colspan="6" style="text-align :center">
		         <span class="text-warning">There is no data.</span>
		       </td>
		    </tr>     
		</table>
<!-- 		Pagination -->
		<div class="btn_w_center">
			<dir-pagination-controls
						max-size="viewCount.recordedPage.count"
						direction-links="true"
						boundary-links="true" >
			</dir-pagination-controls>
		</div>		
        <br>  	 
   </div>							   
		
<!-- btn-lg, tn-md, btn-sm, btn-xs -->
		<div class="btn_w_right"><input type="button"  class="btn btn-danger btn-md"  ng-click="goNew();" value="New"></div>	
<!-- Modal -->
<div id="boardModel" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Bid Information</h4>
      </div>
      <div class="modal-body">
        <p> TendNo : {{modal.tendNo}}</p>
        <p> TendNm : {{modal.tendNm}}</p>
        <p> TendSeq : {{modal.tendSeq}}</p>
        <p> Title : {{modal.title}}</p>
        <p> Content : {{modal.contents}}</p>
        <p> Writer : {{modal.id}}</p>
        <p> Hit : {{modal.hit}}</p>
        <p> Status : {{modal.status}}</p>
        <p> Write Date : {{modal.wdate | date:"dd/MM/yyyy HH:mm:ss"}}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>		
</div>		
 	 </div>
	</div>
  </div>
 </div>
</div>
