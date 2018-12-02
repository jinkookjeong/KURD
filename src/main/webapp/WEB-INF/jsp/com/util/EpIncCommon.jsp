<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/jsp/com/util/TagLibraries.jsp" %>
	

<script type="text/javascript" src="/js/common/alert.js"></script>
<script type="text/javascript" language="javascript" src="/js/common/EpCommon.js"></script>
<script type="text/javascript" language="javascript">

var msg_errors_is_required = "msg_errors_is_required";
var msg_errors_maxlength_is ="msg_errors_maxlength_is";


var msg_count_limit = "msg_count_limit";

var button_select =  "button_select" ;
var button_delete =   "button_delete";

var subtitle_goods_information = "subtitle_goods_information";
var subtitle_services_information = "subtitle_services_information";
    
var label_goods_Information_list =   "label_goods_Information_list" ;
var label_services_Information_list =  "label_services_Information_list" ;

var mandatory_label_list = [
                            'label_lot_no'
                           ,'label_item_no'
                           ,'label_classification_id'
                           ,'label_required_quantity'
                           ,'label_unit'              
                           ,'label_estimated_price'  
                           ,'label_sample_yn'       
                           ];

var maxlength_label_list = [
                            'label_specification'
                           ,'label_place_of_delivery'
                           ,'label_delivery_condition'
 
                           ];
var label_required_attribute_value =  "label_required_attribute_value";
    
	var FILE_COUNT_LIMIT = 10;
	

 	function gfnEp_RDLItemPopup(rdlVerId , rdlCategCd, rdlCategNm, rdlItemCd, rdlGenerNm, unitNm, unitCd, clId, clNmEn, clNmAr) {

 	    var retVal;
 	    rdl_paramList = ["rdlVerId" , "rdlCategCd", "rdlCategNm", "rdlItemCd", "rdlGenerNm", "unitNm",  "unitCd", "clId", "clNmEn", "clNmAr"];
 	    
 	    rdl_targetList = [rdlVerId , rdlCategCd, rdlCategNm, rdlItemCd, rdlGenerNm, unitNm, unitCd, clId, clNmEn, clNmAr];
 	    
 	    // document.domain = "<joneps:getPopDomain />";
 	    var url = "<joneps:getProperty keyName='ca' />/ca/cs/selectListRdlItemMapngFind.do";
 	    var title  = "Find RDL Maping";
 	    var popOption = "width=830px,height=600px,toolbar=no,menubar=no,resizable=no,scrollbars=yes,copyhistory=no,location=no";
 	    retVal = window.open(url, title, popOption);
 	    retVal.focus();
 	}
 	
    function gfnEp_commodityPop(screenName){
    	//alert(screenName );
    	var tendTypeCd1 = "";
        if( screenName == "PRCURE_PLAN"){
        	 tendTypeCd1 =  $("#bizTypeCd").val();
        }else if( screenName == "TEND_INVIT"){
        	 tendTypeCd1 =  $("#tendTypeCd1").val(); //procure request 일때 처리 해야함.
        }else if( screenName == "PRCURE_REQ"){
        	tendTypeCd1 = $("#bizTypeCd1GSD").val();
        	
        }
       
        var clType = "";
        if( tendTypeCd1 == 'EP0011' ) {
        	clType = "G";
        } else if( tendTypeCd1 == 'EP0012' ) {
        	clType = "S";
        }else{
        	return;
        }
        
        
        var retVal;
        var url = "<joneps:getProperty keyName='ca' />/ca/cs/selectListPageClComdtFind.do?clType="+clType;
        var popOption = "width=830px,height=710px,toolbar=no,menubar=no,resizable=no,scrollbars=yes,copyhistory=no,location=no";
        //alert(url);
        retVal = window.open(url, "Commodity Find", popOption);
        retVal.focus();
        
   }
  
  //Search Classification Id : Call back function
  function callBackCommodity(ret){
     
      $("#clId").val(ret.clId);
      $("#clNmEn").val(ret.clNmEn);
      $("#clNmAr").val(ret.clNmAr);
    
      var mytable2 = document.getElementById("tb_goods_attr").getElementsByTagName("tbody")[0];
      //Detete Attribute 
      var deleteRowCount = mytable2.rows.length;
      for ( var iRow=1; iRow<= deleteRowCount; iRow++){
            mytable2.deleteRow(0);
      }       
      $("#btn_add_goods_attr").attr("disabled", false);
        
  }	
  

  
  /* ===================================================================
   *  Attached File 
   *  =================================================================== */

   var fileIndex = 0;
   /* --------------------------------------------------------
    * 파일태그 추가
    -------------------------------------------------------- */
   function fn_addFile(addType){

       var tableObj = document.getElementById("fileTable");
       var tableLength = tableObj.rows.length;
       
       // 파일은 5개까지
       if(tableLength >( FILE_COUNT_LIMIT - 1 )){
           alert("msg_count_limit");
           return;
       }

       // 기존에 추가된 파일태그에 값이 없으면 리턴
       for(var i = 1; i <= fileIndex; i++){
           var compareFileObj = document.getElementById("file" + i);
           if(compareFileObj != null &&   compareFileObj.value == ""){
               alert("msg_select_exist_file");
               return;
           }

       }
       fileIndex++;
       fn_addFileRow(addType, fileIndex);

   }


   /* --------------------------------------------------------
    * 파일태그 추가(인텍스)
    -------------------------------------------------------- */
   function fn_addFileRow(addType, paramFileIndex){
	   
       var tableObj = document.getElementById("fileTable");
       var tableLength = tableObj.rows.length;
       //alert( "tableLength="+tableLength);
       var rowObj = tableObj.insertRow(tableLength);
       

       var cellObj = rowObj.insertCell(0);
       cellObj.style.border = "0px";

       var htmlStr  = "<input type=\"file\" id=\"file" + paramFileIndex + "\" name=\"file" + paramFileIndex + "\"  size=\"60\" onchange=\"javascript:fn_checkFileName('"+addType+"', this, " + paramFileIndex + ")\" style=\"width:500px\">&nbsp;";
           htmlStr += "<span class=\"btnTy3\"><input type=\"button\" class=\"btn_gray\" onclick=\"javascript:fn_deleteFileRow('"+addType+"', this.parentNode.parentNode.parentNode)\" value=\"delete\" />";
       cellObj.innerHTML = htmlStr;
       
       if(addType == "UPDATE" ){
           fn_allocateFileSerialno();
       }
   }
   /* --------------------------------------------------------
    * 파일태그 삭제
    -------------------------------------------------------- */
   function fn_deleteFileRow(addType, trObj){

       var tableObj = document.getElementById("fileTable");
       var trIndex = trObj.sectionRowIndex;

       tableObj.deleteRow(trIndex);
       if ( addType == "UPDATE"){
           //추가 by KYH 
           fn_allocateFileSerialno();
       }

   }

   /* --------------------------------------------------------
    * 파일명 체크
    -------------------------------------------------------- */
   function fn_checkFileName(addType, fileObj, paramFileIndex){
	   fn_checkFileNameAllByFileId("false", "file", addType, fileObj, paramFileIndex);
   }
   

   

   /* --------------------------------------------------------
    * File Name & File extension check
    -------------------------------------------------------- */
   function fn_checkRightFileName(isTable, fileIdPrefix, addType, fileObj, fileName, paramFileIndex){
	   // 파일명 유효성 검증 시작
	   var maskStr = "^[0-9|ㄱ-ㅎ|ㅏ-ㅣ|가-힝|+-|_|.| |()]*$";
	   var regPat = new RegExp(maskStr);
	   var result = matchPattern(fileName, regPat);
	   if(result == null){
	       var checkMaskStr = unescape(replaceAll("msg_for_file_name"));
	       checkMaskStr = extractMaskStrs(maskStr, checkMaskStr);
	
	      // alert(checkMaskStr);
	    	fn_clearFileName(isTable, fileIdPrefix, addType, fileObj,  paramFileIndex);
	   }
	   //alert( "fn_checkRightFileName fileName="+fileName);
	   if( fileName != ''){ //추가 by KYH  fileObj.value = "" 으로 바꾸어 주면 다시 fn_checkFileName이 발생 
	       var indexOfDot = fileName.lastIndexOf(".");
	       var fileExt = fileName.substring(indexOfDot + 1, fileName.length).toLowerCase();
	       if ( fileName == "" || fileExt == "exe" || fileExt == "msi" || fileExt == "bat" || fileExt == "cmd" || fileExt == "vbs" || fileExt == "com"
	               || fileExt == "jsp" || fileExt == "cgi" || fileExt == "php" || fileExt == "php3" || fileExt == "asp" || fileExt == "inc" || fileExt == "pl") {
	           alert("msg_unsupported_file_fmt"); 
	           
	           fn_clearFileName(isTable, fileIdPrefix, addType, fileObj,  paramFileIndex);
	       }
	   }
	 //파일명 유효성 검증 끝  
   }
   
   
   function fn_clearFileName(isTable, fileIdPrefix, addType, fileObj,  paramFileIndex){
	   if(navigator.userAgent.indexOf("MSIE") > -1 ){
		   if( isTable == 'true'){
               var mytable = document.getElementById("tb_attached_"+fileIdPrefix).getElementsByTagName("tbody")[0];
               var fileID = document.getElementById("fileTd"+mytable.rows.length);
               var iRow = mytable.rows.length;
		       if( addType == "INSERT"){
                   fileID.innerHTML = "<input name=\"file"+ iRow + "\" id=\"file"+ iRow +"\" style=\"width: 450px;\" onchange=\"javascript:fn_checkFileNameAllByFileId('true', '"+fileIdPrefix+"', '"+addType+"', this,"+ iRow+")\" "+
                    "type=\"file\" size=\"60\" />";    		           
		
		       }else{
		    	    fileID.innerHTML = "<input name=\"file"+ iRow + "\" id=\"file"+ iRow +"\" style=\"width: 450px;\" onchange=\"javascript:fn_checkFileNameAllByFileId('true', '"+fileIdPrefix+"', '"+addType+"', this,"+ iRow+")\" "+
		    	     "type=\"file\" size=\"60\" />";		    	   
		       }        
		   }else{ 
	           fn_deleteFileRow(addType, fileObj.parentNode.parentNode);
	           fn_addFileRow(addType, paramFileIndex);
		   }
	   }else{
		   fileObj.value = "";
	   }	   
   }
   
   
   /* --------------------------------------------------------
    * 선택하지 않은 파일이 있는지 확인 : submit 보내기 전에 확인요  by KYH
    -------------------------------------------------------- */
   function fn_checkSelectedFile(){

       var result = true;

       for(i = 0; i <= fileIndex;i++){

           var fileObj = document.getElementById("file" + i);
           if(fileObj != null &&
              fileObj.value == ""){
               alert("msg_no_value_file", fileObj);
               return false;
           }

       }

       return result;

   }  
   
    
    /* --------------------------------------------------------
     *Allocate file serial number by KYH
     -------------------------------------------------------- */
    function fn_allocateFileSerialno(){
        var idx = 0;
        $("#fileTable").find("tbody tr").each(function(idx){
            idx++;
            idNmStr = "file" + idx;
            $(this).find("td:eq(0)").find("input:eq(0)").val(idx);
        }); 
    }
    

    
    /* --------------------------------------------------------
     * Delete file 기 첨부 파일 삭제 : uniKey로 삭제하게 함. by KYH
     -------------------------------------------------------- */
    function fn_deleteAttachedFile2(deleteUnikey, trObj, filePath){

        var deleteFilesDiv = document.getElementById("deleteFilesDiv");

        deleteFilesDiv.innerHTML += "<td><input type=\"hidden\" name=\"deleteUnikey[]\" value=\"" + deleteUnikey + "\"><\/td>";
        deleteFilesDiv.innerHTML += "<td><input type=\"hidden\" name=\"deleteFilePath\" value=\"" + filePath + "\"><\/td>";

        fn_deleteFileRow("UPDATE", trObj);
        


    }   


    
    /* --------------------------------------------------------
     * Check File Name
     -------------------------------------------------------- */

    
    function fn_checkFileNameAllByFileId(isTable, fileIdPrefix, addType, fileObj, paramFileIndex){
    	
    	//alert(navigator.userAgent.indexOf("MSIE") );
        //var tableLength = tableObj.rows.length;
        //alert("isTable="+isTable+" fileIdPrefix="+fileIdPrefix+" addType="+addType+" fileObj="+fileObj );
        var fileName = "";
        if(navigator.userAgent.indexOf("Firefox") > -1 ){
            fileName = fileObj.value;
        }else{
            var fileName = fileObj.value;
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        }
        
        var tableObj;
        var checkCount = fileIndex;
        if( isTable == 'true'){
            tableObj = document.getElementById("tb_attached_" +fileIdPrefix);
            var mytable = tableObj.getElementsByTagName("tbody")[0];
            existFileDiv = tableObj.getElementsByTagName("div");
            var paramFileIndex =mytable.clickedRowIndex;
           
            checkCount =mytable.rows.length;
        }else{
            tableObj = document.getElementById("fileTable");
            existFileDiv = tableObj.getElementsByTagName("div");
        }   
        var mytable = tableObj.getElementsByTagName("tbody")[0];
       
        //alert("paramFileIndex="+paramFileIndex );
        if( addType =="UPDATE"){
	        // 기추가된 파일에서 확인
	        var existFileDiv ;
	 
	        for(var i = 0; i < fileIndex; i++){	
	            if(existFileDiv[i] != null){
	                //alert( existFileDiv[i].parentNode.id);
	                if(existFileDiv[i].parentNode.id.indexOf("file") == 0 &&
	                   existFileDiv[i].innerHTML == fileName){
	                    alert("msg_duplicate_file_name" );
	                    fn_clearFileName(isTable, fileIdPrefix, addType, fileObj,  paramFileIndex);
	                }
	            }
	        }
        }
        //alert("fn_checkFileNameAllByFileId paramFileIndex="+paramFileIndex +"  mytable.rows.length="+ mytable.rows.length);
	    //새로 추가한 로우  
	    
        for(var i = 1; i <= checkCount; i++){	
            //var compareFileObj = document.getElementById("file" + i);
            var compareFileObj = document.getElementById(fileIdPrefix + i);
            //alert("compareFileObj="+compareFileObj);
            if(compareFileObj != null){
                var compareFileName = "";
                if(navigator.userAgent.indexOf("Firefox") > -1 ){
                    var compareFileName = compareFileObj.value;
                }else{
                    var compareFileName = compareFileObj.value;
                    compareFileName = compareFileName.substring(compareFileName.lastIndexOf("\\") + 1);
                }
                //alert( "paramFileIndex=["+paramFileIndex +"] i=["+ i+ "] fileName=["+fileName+"] compareFileName=["+compareFileName+"]");
                if(paramFileIndex != i &&    fileName == compareFileName  && compareFileName != ""){
                    alert("msg_duplicate_file_name" );
                    fn_clearFileName(isTable, fileIdPrefix, addType, fileObj,  paramFileIndex);
                }
            }
        }
        // -- 중복파일 검색 끝  -- //
        
        // --  파일명 유효성 검증 시작  -- //
        fn_checkRightFileName(isTable, fileIdPrefix, addType, fileObj, fileName, paramFileIndex);

    }

  
     
	
</script>

