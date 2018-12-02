<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>

<html>
   <head>
      <title>AngularJS</title>
 <style type="text/css">
div{
  display:inline-block;
  vertical-align:top;
  color: red;
  margin-top: 5px;
  }
.red{
         color:red
     }
       .green{
         color:green
     }
</style>

	<script src="/js/angular/angular.min.js"></script>	
    <script src="/js/angular/angular-messages.min.js"></script>
    <script src="/js/app/app.js"></script>
        
   </head>
  <body>
   <body ng-app="EmployeeManagement" ng-controller="EmployeeController">
      <h3>
         CRUD: Spring Boot + Rest + AngularJS
      </h3>
      <form name="Frm" ng-submit="submitEmployee()" >
         <table border="0" class="ta_style1">
            <tr>
               <td>Emp Id</td>
               <td>{{employeeForm.empId}}</td>
            </tr>
            <tr>   
               <td>Emp No</td>
               <td>
                 	<input type="text" name="empNo" placeholder="number" ng-model="employeeForm.empNo" ng-required="true" />
               		<div ng-messages="Frm.empNo.$error"><div ng-message="required">* This field is required</div></div>		
               </td>               
            </tr>
            <tr>
               <td>Emp Name</td>
               <td><input type="text" name="employeeName" placeholder="character" ng-model="employeeForm.empName"  ng-required="true" />
                <div ng-messages="Frm.employeeName.$error"><div ng-message="required">* This field is required</div></div>
               </td>               
            </tr>
            
            <tr>
               <td>Email</td>
               <td><input type="text" name="employeeEmail" placeholder="Email"  ng-model="employeeForm.email"
               			  ng-pattern="/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/" ng-required="true" />
                	<span style="color:Red" ng-show="Frm.employeeEmail.$error.required"> Required! </span>
					<span style="color:Red" ng-show="Frm.employeeEmail.$dirty&&Frm.employeeEmail.$error.pattern">* Please Enter Valid Email</span>
               </td>               
            </tr>    
            <tr>
               <td>Mobile</td>
		       <td><input type="text" name="employeePhoneNumber"  placeholder="+81-010-111-4444" ng-model="employeeForm.phoneNumber"		       	           		       			   
		                ng-pattern="/^[0-9]{1,10}$/" ng-required="true"/>
<!-- 		                ng-pattern="/^\+\d{2}-\d{2,3}-\d{3,4}-\d{3,4}$/" ng-required="true"/> -->
		           <span style="color:Red" ng-show="Frm.employeePhoneNumber.$error.required"> Required! </span>
				   <span style="color:Red" ng-show="Frm.employeePhoneNumber.$dirty&&Frm.employeePhoneNumber.$error.pattern">* Mobile number should be in valid formate.</span>
               </td>
            </tr>
            <tr>

               <td>Age</td>
             <td><input type="text" name="employeeAge" placeholder="Enter an age" step="0" ng-model="employeeForm.age" 
		                  ng-pattern="/^[0-9]{1,5}$/" ng-minlength="1" ng-maxlength="3" required />
		                  <span style="color:Red" ng-show="Frm.employeeAge.$error.required"> Required! </span>
						  <span style="color:Red" ng-show="Frm.employeeAge.$dirty&&Frm.employeeAge.$error.pattern">Only Numbers Allowed, Maximum 5 Characters</span>
               </td>
             </tr>  
            </tr>
            <tr>
               <td>Price(00.00)</td>
		       <td><input type="text" name="employeeSalary" placeholder="Decimal." step="0.00" ng-model="employeeForm.salary"		            
		            ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"  ng-minlength="2" ng-maxlength="8" ng-required="true"/>		              		   
		           <div ng-messages="Frm.employeeSalary.$error">
				      <div ng-message="required">* This field is required</div>
				      <span style="color:Red" ng-show="Frm.employeeSalary.$dirty&&Frm.employeeSalary.$error.pattern">소수점 이하 2자리까지 </span>
				      <div ng-message="minlength">* 소수점 포함 2자리 이상요구됨</div>
				      <div ng-message="maxlength">* 소수점포함 8자리 이하요구됨</div>
				   </div>
               </td>               
            </tr>
            <tr>
               <td>Emp password</td>
                 <td>			                 
		          <input type="password" placeholder="Password" ng-model="employeeForm.password" name="password" required 
		                 ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$/" >
		           <div ng-show="Frm.password.$error.pattern" style="color:red" role="alert">* 최소 4글자, 최대 8글자이고 적어도 1나의 소문자, 대문자, 숫자를 포함해야합니다.</div>
		         </td>
		    </tr>
		    <tr>
	          <td>Emp password Confirm:</td>
	          <td> 
	            <input type="password" placeholder="Password" ng-model="employeeForm.repassword" ng-keyup="compare(employeeForm.repassword)" name="repassword" 
	                   ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$/" />
	            <div ng-show="isconfirm || Frm.repassword.$dirty " 
	                 ng-class="{true:'green',false:'red'}[isconfirm]">Password {{isconfirm==true?'':'not'}} match</div>
              </td>
            </tr>
            <tr>
               <td colspan="2">
                  <input type="submit" value="Submit" class="blue-button" />
               </td>
            </tr>
            
         </table>
       
      </form>
      <br/>
      <a class="create-button" ng-click="createEmployee()">Create Employee</a>
      <table border="1">
         <tr>
            <th>Emp Id</th>
            <th>Emp No</th>
            <th>Emp Name</th>
            <th>salary</th>
            <th>age</th>
            <th>phoneNumber</th>
            <th>email</th>
            <th>password</th>
            <th>repassword</th>            
            <th>Edit</th>
            <th>Delete</th>
         </tr> 
         <!-- $scope.employees -->
         <tr ng-repeat="employee in employees">
            <td> {{ employee.empId }}</td>
            <td> {{ employee.empNo }}</td>
            <td >{{ employee.empName }}</td>
            <td >{{ employee.salary }}</td>
            <td >{{ employee.age }}</td>
            <td >{{ employee.phoneNumber }}</td>
            <td >{{ employee.email }}</td>
            <td >{{ employee.password }}</td>
            <td >{{ employee.repassword }}</td>
            <td>
            <a ng-click="editEmployee(employee)" class="edit-button">Edit</a>
            </td>
            <td>
            <a ng-click="deleteEmployee(employee)" class="delete-button">Delete</a>
            </td>
         </tr>
         
      

      <br>공통코드 Tag 라이브러리 JSP 처리 cmcd : EP0000(<cmcd:cdNm cd="EL0000" />) 

				  
      </table>
      
      			
      
      
      </body>
   </body>
</html>