<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
       	 <div class="foot_area">
            <div class="footer_logo1"><span class="blind">KURD</span></div>
            <div class="footer_logo2">
            	<span class="f_logo_01"><span class="blind">NEBID logo</span></span>
            	<span class="f_logo_02"><span class="blind">NEBID logo</span></span>
            </div>
            <div class="copyright">© 2019 KURD PPS System </div>
        </div>
        
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/jquery-ui.js" ></script> --%>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/jquery.inputmask.bundle.js" ></script> --%>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/inputformatter.js" ></script> --%>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/cookie.js" ></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/alert.js" ></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/validation.js" ></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/file.js" ></script> 
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/string.js" ></script>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/pagination.js" ></script> --%>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/jquery-dateFormat.js" ></script> --%>
<%-- <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/common/datepicker.js" ></script> --%>

<script>
	$("#messageAlertBtn, #messageAlertCloseBtn").click(function () {
		util_layerClose();
	})
	
	function selectTr(obj) {
		var tr = $(obj).closest("table tr");
		if ($(tr).hasClass("select")) {
			$(tr).removeClass("select");
		} else {
			$(tr).siblings("tr").removeClass("select");
			$(tr).addClass("select");
		}
	}
</script>

 <!-- confirmModal Modal --> 
<!--   <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"> -->
<!--   <div class="modal-dialog modal-dialog-centered modal-sm" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--          <button type="button" class="close" data-dismiss="modal">&times;</button>  -->
<!--         <h4 class="modal-title">Confirm</h4> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         <p class="confirm_status"></p>  -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" id="btnOk" onclick="fn_modal_confirm();" class="btn btn-primary">Ok</button> -->
<!--          <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>  -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->


 <!-- infoModal Modal -->
<!--   <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"> -->
<!--   <div class="modal-dialog modal-dialog-centered modal-md" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h4 class="modal-title">Information</h4> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         <div class="result_status"></div> -->
         
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" id="btnOk" onclick="fn_modal_ok();" class="btn btn-primary">Ok</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
