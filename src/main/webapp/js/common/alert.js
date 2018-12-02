/**
 * offset 설정
 *
 * @param contentsObj
 * @returns offsetObj
 */
function util_setOffset(contentsObj, documentObj){

	var offsetObj   = new Object();
	offsetObj.top = contentsObj.offsetTop;
	offsetObj.left = contentsObj.offsetLeft;
	offsetObj.width = contentsObj.offsetLeft + contentsObj.offsetWidth;
	offsetObj.height = contentsObj.offsetTop + contentsObj.offsetHeight;
	offsetObj.centerLeft = contentsObj.offsetLeft + (contentsObj.offsetWidth / 2);

	/*
	 * 높이 가운데 정렬 시작
	 */
	var scrollTop = 0;
	var scrollHeight= 0;
	if(navigator.userAgent.indexOf("MSIE") > -1 ){
		//scrollTop = documentObj.documentElement.scrollTop;
		//scrollHeight = documentObj.documentElement.offsetHeight;
		if(document == documentObj){
			//scrollTop = window.scrollY;
			scrollTop =  (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
			scrollHeight = window.screen.height;
		}else{
			//scrollTop = parent.window.scrollY;
			scrollTop =  (parent.window.pageYOffset !== undefined) ? parent.window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
			//scrollHeight = parent.window.screen.height;
			scrollHeight = window.outerHeight;
		}
	}else{

		// 아이프레임에서 하는 경우에는 부모창의 높이를 구해야 한다.
		if(document == documentObj){
			scrollTop = window.scrollY;
			//scrollTop =  (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
			//scrollHeight = window.screen.height;
			//scrollTop = documentObj.documentElement.scrollTop;
			scrollHeight = documentObj.documentElement.offsetHeight;
		}else{
			//scrollTop = parent.window.scrollY;
			scrollTop =  (parent.window.pageYOffset !== undefined) ? parent.window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;
			//scrollHeight = parent.window.screen.height;
			scrollHeight = window.outerHeight;
		}

	}
	offsetObj.centerTop = scrollTop + (scrollHeight / 2);
	/*
	 * 높이 가운데 정렬 끝
	 */

	offsetObj.executionImageLeft = offsetObj.centerLeft - 16;
	offsetObj.executionImageTop = offsetObj.centerTop - 16;
	offsetObj.messageAlertLeft = offsetObj.centerLeft - 150;
	offsetObj.messageAlertTop = offsetObj.centerTop;

	return offsetObj;

}

/**
 * 알림메세지 창 오픈
 *
 * @param reMsg 출력 메세지
 * @param destUrl 이동페이지
 */
function util_alertCall(reMsg,destUrl){
	$("#messageAlertBtn, #messageAlertCloseBtn").bind('click', function() {
		util_layerClose();
	});
	
	if(destUrl == null || destUrl == ""){
		gfn_onMask();
	} else {
		$('#messageAlertBtn, #messageAlertCloseBtn').bind('click', function() {
			util_movePage(destUrl);
		});
	}
	
	if(confirmStr == null || confirmStr == "" ){
		confirmStr = "OK";
	}
	
	$("#messageAlertBtn").val(confirmStr);
	$("#popContent").html(reMsg);
	$("#alertLayer").show();
	
	popContent.innerHTML += "<div style=\"position:absolute; top:4px; left:44px; color:#fff;\">Information</div>";
	
	$("#alertLayer").attr("onkeypress", "if (event.keyCode == 27) { util_layerClose(); }");
	$('#messageAlertBtn').focus();
}

/**
 * 배경 레이어 설정
 *
 * @param offsetObj
 * @param documentObj
 * @returns layerDivObj
 */
function util_setLayerDIv(offsetObj, documentObj){

	if(documentObj.getElementById("layerDiv") != null){
		return;
	}

	var layerDivObj = documentObj.createElement("div");
	layerDivObj.setAttribute("id", "layerDiv");

	var left = offsetObj.left;
	var top = offsetObj.top;

	var width;

	if(documentObj.getElementById("contents") != null){
		width = documentObj.getElementById("contents").offsetWidth;
	}else if(documentObj.getElementById("popup_contents") != null){
		width = documentObj.getElementById("popup_contents").offsetWidth;
	}

	var height = offsetObj.height;

	var styleStr = "position:absolute; filter:alpha(opacity=50); opacity:0.5; background-color:#ffffff; ";
	styleStr    += "left:" + left + "px; top:" + top + "px; width:" + width + "px; height:" + height + "px;";

	layerDivObj.setAttribute("style", styleStr);

	documentObj.getElementsByTagName("body")[0].appendChild(layerDivObj);

	return layerDivObj;

}

/**
 * 처리중 이미지 보여주기
 *
 * @param offsetObj
 * @returns imageObj
 */
function util_getExecutionImage(offsetObj, documentObj){

	var imageObj = documentObj.createElement("img");
	imageObj.setAttribute("id", "executionImageImg");
	imageObj.setAttribute("src", "/images/common/loading.gif");
	var top;

	if(documentObj.getElementById("contents") != null){
		top = offsetObj.executionImageTop;
	}else if(documentObj.getElementById("popup_contents") != null){
		top = documentObj.getElementById("popup_contents").offsetHeight / 2;
	}

    imageObj.setAttribute("style",
			              "position:absolute; width:32px; height:32px; left:" + offsetObj.executionImageLeft + "px; top:" + top + "px;");

	return imageObj;

}

/**
 * offset 만들어서 리턴
 *
 * @param documentObj
 * @returns 컨텐트 offset
 */
function util_getContentsOffSet(documentObj){

	var contentsObj = null;
	
	if(documentObj.getElementById("contents") != null){
		contentsObj =  documentObj.getElementById("contents");
	}else if(documentObj.getElementById("popup_contents") != null){
		contentsObj =  documentObj.getElementById("popup_contents");
	}else{
		contentsObj =  documentObj.getElementById("messageAlertBody");
	}
	
	return util_setOffset(contentsObj, documentObj);

}

/**
 * offset 만들어서 리턴(팝업)
 *
 * @param documentObj
 * @returns 컨텐트 offset
 */
function util_getContentsOffSetOnPopup(documentObj){

	var contensObj =  documentObj.getElementById("popup_contents");
	return util_setOffset(contensObj, documentObj);

}

/**
 * MessageAlertDiv 리턴
 *
 * @param documentObj
 * @returns messageAlertDiv
 */
function util_getMessageAlertDiv(documentObj, htmlStr, left, top, divType){

	if(documentObj.getElementById("messageAlertDiv") != null){
		return;
	}
	
	var messageAlertDivObj = documentObj.createElement("div");
	messageAlertDivObj.setAttribute("id", "messageAlertDiv");
	
	messageAlertDivObj.setAttribute("class", "boxTy6");

//	var styleStr = "position:absolute; width:300px;";
	var styleStr = "z-index:10; position:absolute;";
	//styleStr    += "left:" + left + "px; top:" + top + "px;";
	styleStr    += "left:" + left + "px;";

	messageAlertDivObj.setAttribute("style", styleStr);

	messageAlertDivObj.innerHTML = htmlStr;

	documentObj.getElementsByTagName("body")[0].appendChild(messageAlertDivObj);

//	var messageDivHeight = documentObj.getElementById("messageDiv").offsetHeight;
//	var messageDivWidth = documentObj.getElementById("messageDiv").offsetWidth;

	//var alertMainObjWidth = Number(messageDivWidth) + 100;
//	var alertMainObjWidth = Number(messageDivWidth);
//	var alertMainObjHeight = Number(messageDivHeight) + 45;

	//documentObj.getElementById("alert_main").style.width = alertMainObjWidth + "px";
//	documentObj.getElementById("alert_main").style.height = alertMainObjHeight + "px";
	
	//documentObj.getElementById("messageAlertDiv").style.width = alertMainObjWidth + "px";
	
//	if(documentObj.getElementById("popup_contents") != null){
//		messageAlertDivObj.style.top = ((top  / 2)) + "px";
//	}else{
//		messageAlertDivObj.style.top = top - (alertMainObjHeight / 2) - 100 + "px";
//	}

	// IE에서는 keydown으로 하면 자동으로 클릭이 되므로 처리
	if(navigator.userAgent.indexOf("MSIE") > -1 && event != null){

		if(event.keyCode != 0){
			event.keyCode = 0;
		}

	}

	if(divType == "alert"){
		var tempFocusObj = documentObj.getElementById("messageAlertBtn");
		setTimeout(function(){tempFocusObj.focus();tempFocusObj.selected = true;},10);
	}else if(divType == "confirm"){
		var focusObj = documentObj.getElementById("confirmOkBtn");
		setTimeout(function(){focusObj.focus();focusObj.selected = true;},10);
	}

	return messageAlertDivObj;

}

/**
 * 레이어 이미지 리턴
 *
 * @param documentObj
 * @returns layerDivObj
 */
function util_appendLayerDiv(documentObj){

	var offsetObj = util_getContentsOffSet(documentObj);
	return util_setLayerDIv(offsetObj, documentObj);

}

/**
 * 레이어 이미지 리턴(팝업)
 *
 * @param documentObj
 * @returns layerDivObj
 */
function util_appendLayerDivOnPopup(documentObj){

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);
	return util_setLayerDIv(offsetObj, documentObj);

}

/**
 * 배경 레이어 숨김
 *
 * @param documentObj
 * @returns void
 */
function util_hideExecutionLayer(documentObj){

	var layerDivObj = documentObj.getElementById("layerDiv");
	if(layerDivObj != null){
		documentObj.getElementsByTagName("body")[0].removeChild(layerDivObj);
	}

}

/**
 * 처리중 이미지 보여주기
 *
 * @param void
 * @returns void
 */
function util_showExecutionImage() {
	
	if ($("#throbber").length) return;
	$("<div>", {
		id: "throbber",
		css: {
			"position": "fixed", "z-index": 998,
		    "top": 0, "left": 0, "right": 0, "bottom": 0
		}
	}).append(
		$("<div>", {
			css: {
				"width": "100%", "height": "100%",
			 	"background-color": "white", "opacity": .5
			}
		})
	).append(
		$("<img>", {
			src: "/images/common/loading.gif",
			css: {
				"position": "absolute", "margin": "auto",
				"top": 0, "left": 0, "right": 0, "bottom": 0,
				"pointer-events": "none"
			}
		})
	).appendTo(document.body);
}

/**
 * 처리중 이미지 보여주기
 *
 * @param void
 * @returns void
 */
function util_showExecutionImage2(){
	
	var offsetObj = util_getContentsOffSet(document);
	var imageObj = util_getExecutionImage(offsetObj, document);

	if(document.getElementById("executionImageImg") != null){
		return;
	}

	document.getElementsByTagName("body")[0].appendChild(imageObj);
	util_appendLayerDiv(document);

}

/**
 * 처리중 이미지 보여주기(iframe)
 *
 * @param void
 * @returns void
 */
function util_showExecutionImageOnParent(){

	var offsetObj = util_getContentsOffSet(parent.document);
	var imageObj = util_getExecutionImage(offsetObj, parent.document);

	if(parent.document.getElementById("executionImageImg") != null){
		return;
	}

	parent.document.getElementsByTagName("body")[0].appendChild(imageObj);
	util_appendLayerDiv(parent.document);

}

/**
 * 처리중 이미지 보여주기(팝업)
 *
 * @param void
 * @returns void
 */
function util_showExecutionImageOnPopup(){

	util_showExecutionImage();
/*
	var offsetObj = util_getContentsOffSetOnPopup(document);
	var imageObj = util_getExecutionImage(offsetObj, document);

	if(document.getElementById("executionImageImg") != null){
		return;
	}

	document.getElementsByTagName("body")[0].appendChild(imageObj);
	util_appendLayerDivOnPopup(document);
*/
}

/**
 * 처리중 이미지 숨김
 *
 * @returns void
 */
function util_hideExecutionImage() {
	if ($("#throbber").length) $("#throbber").remove();
}

/**
 * 처리중 이미지 숨김
 *
 * @param documentObj
 * @returns void
 */
function util_hideExecutionImage2(documentObj){
	
	var imgObj = documentObj.getElementById("executionImageImg");
	documentObj.getElementsByTagName("body")[0].removeChild(imgObj);

}

/**
 * 처리중 이미지&레이어 숨김
 *
 * @param documentObj
 * @returns void
 */
function util_hideExecutionAll(documentObj){

	var imgObj = documentObj.getElementById("executionImageImg");
	var layerDivObj = documentObj.getElementById("layerDiv");

	if(layerDivObj != null){
		util_hideExecutionLayer(documentObj);
	}
	if(imgObj != null){
		util_hideExecutionImage(documentObj);
	}

}

/**
 * 메세지 div 숨김
 *
 * @param documentObj
 * @returns void
 */
function util_hideMessageAlert(documentObj){

	var layerDivObj = documentObj.getElementById("messageAlertDiv");
	documentObj.getElementsByTagName("body")[0].removeChild(layerDivObj);

}

/**
 * 메세지 div 만들기
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param paramFocusObjName
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDiv(documentObj, paramHtmlStr, paramFocusObjName, isSuccess){

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	if(paramFocusObjName == null){
		htmlStr += "				<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event)\" value=\"" + confirmStr + "\">";
	}else{
		htmlStr += "				<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event, '" + paramFocusObjName + "')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	return util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * 메세지 div 만들기 자식 프레임에 포커스
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param paramFocusObjName
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDivFocusChild(documentObj, paramHtmlStr, paramFocusObjName, frameName, useId){

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	if(useId == null){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlertFocusChild(event, '" + paramFocusObjName + "', '" + frameName + "')\" value=\"" + confirmStr + "\">";
	}else if(useId != null && useId == "Y"){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlertFocusChild(event, '" + paramFocusObjName + "', '" + frameName + "', 'Y')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	return util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * 메세지 div 만들기(팝업)
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param paramFocusObjName
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDivOnPopup(documentObj, paramHtmlStr, paramFocusObjName){

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	if(paramFocusObjName == null){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event)\" value=\"" + confirmStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event, '" + paramFocusObjName + "')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	return util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * 메세지 div 만들기(" + confirmStr + "만)
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param confirmScript
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDivNoConfirm(documentObj, paramHtmlStr, confirmScript){

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"" + confirmScript + "\" value=\"" + confirmStr + "\">";
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	return util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * 메세지 div 만들기(함수 실행)
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param execStr
 * @param paramFocusObjName
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDivWithExec(documentObj, paramHtmlStr, execStr, paramFocusObjName){

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	if(paramFocusObjName == null){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlertWithExec(event, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlertWithExec(event, '" + execStr + "', '" + paramFocusObjName + "')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	return util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * 메세지 div 만들기(함수 실행, 팝업)
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param execStr
 * @param paramFocusObjName
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDivWithExecOnPopup(documentObj, paramHtmlStr, execStr, paramFocusObjName, isSuccess){

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	if(paramFocusObjName == null){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlertWithExec(event, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlertWithExec(event, '" + execStr + "', '" + paramFocusObjName + "')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	return util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * 메세지 창
 *
 * @param paramMessage
 * @param paramFocusObj
 * @returns void
 */
var focusObj = null;
function util_messageAlert(paramMessage, paramFocusObj){
	$("#messageAlertCloseBtn").bind('click', function() {
		util_layerClose();
	});

	$("#messageAlertBtn").bind('click', function() {
		util_layerClose();
		if(paramFocusObj != null) {
			var tempFocusObj = paramFocusObj;
			setTimeout(function() {
				tempFocusObj.focus();
				tempFocusObj.selected = true;
			}, 10);
		}
	});
	
	$("#messageAlertBtn").val("Cancel");

	$("#popContent").html(paramMessage);
	$("#alertLayer").show();

	var oriHeight = screen.height / 2;
	var comHeight = $("#popContent").height();
	
	if(oriHeight < comHeight) {
		$("#popContent").height(oriHeight);
		$("#popContent").css("overflow", "auto");
		$("#popContent").css("text-align", "left");
	}
	
	popContent.innerHTML += "<div style=\"position:absolute; top:4px; left:44px; color:#fff;\">Error</div>";
	
	$("#alertLayer").attr("onkeypress", "if (event.keyCode == 27) { util_layerClose(); }");
	$('#messageAlertBtn').focus();
}

/**
 * 메세지 창(old)
 *
 * @param paramMessage
 * @param paramFocusObj
 * @returns void
 */
var focusObj = null;
function util_messageAlert2(paramMessage, paramFocusObj){

	focusObj = null;

	util_appendLayerDiv(document);

	var message = paramMessage;

	if(paramFocusObj != null){
		focusObj = paramFocusObj;
	}

	if(document.getElementById("contents") != null){
		util_makeMessageAlertDiv(document, message);
	}else{
		util_makeMessageAlertDivOnPopup(document, message);
	}
}

/**
 * 메세지 창(iframe)
 *
 * @param paramMessage
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertOnParent(paramMessage, paramFocusObjName, isSuccess){

	parent.focusObj = null;

	util_appendLayerDiv(parent.document);

	var message = paramMessage;
	util_makeMessageAlertDiv(parent.document, paramMessage, paramFocusObjName, isSuccess);

}

/**
 * 메세지 창(함수 실행)
 *
 * @param paramMessage
 * @param execStr
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertWithExec(paramMessage, execStr, paramFocusObj){

	focusObj = null;

	util_appendLayerDiv(document);

	var message = paramMessage;

	if(paramFocusObj != null){
		focusObj = paramFocusObj;
	}

	util_makeMessageAlertDivWithExec(document, message, execStr);

}

/**
 * 메세지 창(함수 실행, 차일드 프레임)
 *
 * @param paramMessage
 * @param execStr
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertWithExecOnParent(paramMessage, execStr, paramFocusObj){

	parent.focusObj = null;

	util_appendLayerDiv(parent.document);

	var message = paramMessage;

	if(paramFocusObj != null){
		focusObj = paramFocusObj;
	}

	util_makeMessageAlertDivWithExec(parent.document, message, execStr);

}

/**
 * 메세지 창(함수 실행, 팝업)
 *
 * @param paramMessage
 * @param execStr
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertWithExecOnPopup(paramMessage, execStr, paramFocusObj, isSuccess){

	focusObj = null;

	util_appendLayerDivOnPopup(document);

	var message = paramMessage;

	if(paramFocusObj != null){
		focusObj = paramFocusObj;
	}

	util_makeMessageAlertDivWithExecOnPopup(document, message, execStr, focusObj, isSuccess);

}

/**
 * 메세지 창(팝업)
 *
 * @param paramMessage
 * @param execStr
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertOnPopup(paramMessage, paramFocusObj){

	focusObj = null;
	
	var documentObj = parent.document;

	util_appendLayerDivOnPopup(documentObj);

	var message = paramMessage;

	if(paramFocusObj != null){
		focusObj = paramFocusObj;
	}

	util_makeMessageAlertDivOnPopup(documentObj, message);

}

/**
 * 메세지 창 닫기
 *
 * @param event
 * @param focusObjName
 * @returns void
 */
function util_closeAlert(event, focusObjName){

	util_hideMessageAlert(document);
	util_hideExecutionLayer(document);
	
	if(focusObj != null){

		if (focusObj.type == "select-one") {
			setTimeout(function(){focusObj.focus();focusObj[0].selected = true;},10);
		}else if (focusObj.type == "button" || focusObj.type == "textarea"){
			setTimeout(function(){focusObj.focus();},10);
		}else{
			setTimeout(function(){focusObj.focus();focusObj.select();},10);
		}


	}else if(focusObjName != null){

		focusObj = document.getElementsByName(focusObjName)[0];

		if (focusObj.type == "select-one") {
			setTimeout(function(){focusObj.focus();focusObj[0].selected = true;},10);
		}else if (focusObj.type == "button" || focusObj.type == "textarea"){
			setTimeout(function(){focusObj.focus();},10);
		}else{
			setTimeout(function(){focusObj.focus();focusObj.select();},10);
		}


	}

}

/**
 * 메세지 창 닫기, 자식 프레임 포커스
 *
 * @param event
 * @param focusObjName
 * @returns void
 */
function util_closeAlertFocusChild(event, focusObjName, frameName, useId){

	util_hideMessageAlert(document);
	util_hideExecutionLayer(document);

	var childFrame = document.getElementsByName(frameName)[0];
	var focusObj;
	
	if(useId == null){
		focusObj = childFrame.contentDocument.getElementsByName(focusObjName)[0];
	}else if(useId != null && useId == "Y"){
		focusObj = childFrame.contentDocument.getElementById(focusObjName);
	}

	if (focusObj.type == "select-one") {
		setTimeout(function(){focusObj.focus();focusObj[0].selected = true;},10);
	}else if (focusObj.type == "button"){
		setTimeout(function(){focusObj.focus();},10);
	}else{
		setTimeout(function(){focusObj.focus();focusObj.select();},10);
	}

}

/**
 * 메세지 창 닫기(함수 실행)
 *
 * @param event
 * @param execStr
 * @param focusObjName
 * @returns void
 */
function util_closeAlertWithExec(event, execStr, focusObjName){

	util_hideMessageAlert(document);
	util_hideExecutionLayer(document);

	if(focusObj != null){

		if (focusObj.type == "select-one") {
			setTimeout(function(){focusObj.focus();focusObj[0].selected = true;},10);
		}else{
			setTimeout(function(){focusObj.focus();focusObj.select();},10);
		}


	}else if(focusObjName != null){

		focusObj = document.getElementsByName(focusObjName)[0];

		if (focusObj.type == "select-one") {
			setTimeout(function(){focusObj.focus();focusObj[0].selected = true;},10);
		}else{
			setTimeout(function(){focusObj.focus();focusObj.select();},10);
		}

	}

	window.eval(execStr);

}

/**
 * 메세지 창(iframe), 포커스는 iframe으로
 *
 * @param paramMessage
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertOnParentFocusChild(paramMessage, paramFocusObjName, frameName, useId){

	parent.focusObj = null;

	util_appendLayerDiv(parent.document);

	var message = paramMessage;
	util_makeMessageAlertDivFocusChild(parent.document, paramMessage, paramFocusObjName, frameName, useId);

}

/**
 * DML 처리 결과
 *
 * @param resultMsg
 * @param destUrl
 * @returns void
 */
function util_showResult(resultMsg, destUrl,isSuccess){

	var documentObj = parent.document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);
	
	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + resultMsg + "	</div>";
	
	htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_movePage('" + destUrl + "')\" value=\"" + confirmStr + "\">";
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";

	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * DML 처리 결과
 *
 * @param resultMsg
 * @param destUrl
 * @returns void
 */
function util_showResultOnBody(resultMsg, destUrl){

	var documentObj = document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + resultMsg + "	</div>";
	
	htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_movePage('" + destUrl + "')\" value=\"" + confirmStr + "\">";
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

/**
 * DML 처리 결과(팝업)
 *
 * @param resultMsg
 * @param destUrl
 * @returns void
 */
function util_showResultOnPopup(resultMsg, destUrl, isReturn,isSuccess){

	var documentObj = parent.document;

	util_appendLayerDivOnPopup(documentObj);

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);
	var htmlStr="";

	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + resultMsg + "	</div>";
	
	if(isReturn == "Y"){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:fn_returnValues()\" value=\"" + confirmStr + "\">";		
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_movePageOnPopup('" + destUrl + "')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");
}

/**
 * 화면이동
 *
 * @param destUrl
 * @returns void
 */
function util_movePage(destUrl){
	//parent.main.location.href = destUrl; //포탈연동되면 이걸로 변경
	//parent.main.location.replace(destUrl);
	//history.back();
	//parent.location.href = destUrl; //테스트시에는 이걸로 테스트
	parent.location.replace(destUrl);

}

/**
 * 화면이동
 *
 * @param destUrl
 * @returns void
 */
function util_movePageOnPopup(destUrl){

	//var form = document.getElementsByTagName("form")[0];
	//form.submit();
	parent.location.href = destUrl;

}

/**
 * 창 닫기
 */
function util_layerClose() {
	util_hideExecutionImage();
	$("#alertLayer").hide();
	if ($("#comfirmCancelBtn").length) {
		$("#comfirmCancelBtn").remove();
	}
	$("#messageAlertBtn, #messageAlertCloseBtn").unbind('click');
}

/**
 * 선택 창
 */
function util_confirmAlert(msg, func) {
	if ($("#comfirmCancelBtn").length == 0) {
		$("#layer_con_body").append('<input type="button" class="btn_gray" id="comfirmCancelBtn" onclick="javascript:util_layerClose();" >');
	}
	$("#messageAlertBtn, #messageAlertCloseBtn").bind('click', function() {
		util_layerClose();
	});
	if (func != null && func != "") {
		$('#messageAlertBtn').bind('click', function() {
			eval(func);
		});
	}
	if (confirmStr == null || confirmStr == "" ) confirmStr = "OK";
	$("#messageAlertBtn").val(confirmStr);
	if (cancelStr == null || cancelStr == "" ) cancelStr = "Cancel";
	$("#comfirmCancelBtn").val(cancelStr);
	$("#popContent").html(msg);
	
	$("#alertLayer").show();
	
	popContent.innerHTML += "<div style=\"position:absolute; top:4px; left:44px; color:#fff;\">Information</div>";
	
	$("#alertLayer").attr("onkeypress", "if (event.keyCode == 27) { util_layerClose(); }");
	$('#messageAlertBtn').focus();
}

/**
 * 취소 function 기능 추가 선택 창
 * 
 */
function util_confirmAlertCancelFunc(msg, func, func2) {
	if ($("#comfirmCancelBtn").length == 0) {
		$("#layer_con_body").append('<input type="button" class="btn_gray" id="comfirmCancelBtn" >');
	}
	$("#messageAlertBtn, #messageAlertCloseBtn").bind('click', function() {
		util_layerClose();
	});
	if (func != null && func != "") {
		$('#messageAlertBtn').bind('click', function() {
			eval(func);
		});
	}

	if (func2 != null && func2 != "") {
		$('#comfirmCancelBtn').bind('click', function() {
			util_layerClose();
			eval(func2);
		});
	}
	
	if (confirmStr == null || confirmStr == "" ) confirmStr = "OK";
	$("#messageAlertBtn").val(confirmStr);
	if (cancelStr == null || cancelStr == "" ) cancelStr = "Cancel";
	$("#comfirmCancelBtn").val(cancelStr);
	
	$("#popContent").html(msg);
	
	$("#alertLayer").show();
	
	popContent.innerHTML += "<div style=\"position:absolute; top:4px; left:44px; color:#fff;\">Information</div>";
	
	$("#alertLayer").attr("onkeypress", "if (event.keyCode == 27) { util_layerClose(); }");
	$('#messageAlertBtn').focus();
}

/**
 * 선택 창
 *
 * @param paramHtmlStr
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_confirmAlert2(paramHtmlStr, execStr, cancelExecStr){

	var documentObj = document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "	<input class=\"btn_gray\" id=\"confirmOkBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(true, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	
	if(cancelExecStr != null){
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(false, '" + execStr + "', '" + cancelExecStr + "' )\" value=\"" + cancelStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(false, '" + execStr + "')\" value=\"" + cancelStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "confirm");

}

/**
 * 선택 창(iframe)
 *
 * @param paramHtmlStr
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_confirmAlertOnParent(paramHtmlStr, execStr, cancelExecStr){

	var documentObj = parent.document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "	<input class=\"btn\" id=\"confirmOkBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(true, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	
	if(cancelExecStr != null){
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(false, '" + execStr + "', '" + cancelExecStr + "' )\" value=\"" + cancelStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(false, '" + execStr + "')\" value=\"" + cancelStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";

	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "confirm");

}

/**
 * 선택 창(no submit)
 *
 * @param paramHtmlStr
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_confirmAlertNoSubmit(paramHtmlStr, execStr, cancelExecStr){

	var documentObj = document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "	<input class=\"btn_gray\" id=\"confirmOkBtn\" type=\"button\" onclick=\"javascript:util_selectConfirmNoSubmit(true, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	
	if(cancelExecStr != null){
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirmNoSubmit(false, '" + execStr + "', '" + cancelExecStr + "' )\" value=\"" + cancelStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirmNoSubmit(false, '" + execStr + "')\" value=\"" + cancelStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";

	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "confirm");

}

/**
 * 선택 창(no submit, iframe)
 *
 * @param paramHtmlStr
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_confirmAlertNoSubmitOnParent(paramHtmlStr, execStr, cancelExecStr){

	var documentObj = parent.document;

	util_appendLayerDiv(parent.document);

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "	<input class=\"function_button\" id=\"confirmOkBtn\" type=\"button\" onclick=\"javascript:util_selectConfirmNoSubmit(true, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	
	if(cancelExecStr != null){
		htmlStr += "			<input class=\"function_button\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirmNoSubmit(false, '" + execStr + "', '" + cancelExecStr + "' )\" value=\"" + cancelStr + "\">";
	}else{
		htmlStr += "			<input class=\"function_button\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirmNoSubmit(false, '" + execStr + "')\" value=\"" + cancelStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "confirm");

}

/**
 * 선택 창(팝업)
 *
 * @param paramHtmlStr
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_confirmAlertOnPopup(paramHtmlStr, execStr, cancelExecStr){

	var documentObj = document;

	util_appendLayerDivOnPopup(documentObj);

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "	<input class=\"btn_gray\" id=\"confirmOkBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(true, '" + execStr + "')\" value=\"" + confirmStr + "\">";
	
	if(cancelExecStr != null){
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(false, '" + execStr + "', '" + cancelExecStr + "' )\" value=\"" + cancelStr + "\">";
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"comfirmCancelBtn\" type=\"button\" onclick=\"javascript:util_selectConfirm(false, '" + execStr + "')\" value=\"" + cancelStr + "\">";
	}
	
	htmlStr += "		</div>";
	htmlStr += "	</div>";
	
	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "confirm");

}


/**
 * confirm 선택
 *
 * @param confirmFlag
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_selectConfirm(confirmFlag, execStr, cancelExecStr){

	util_hideMessageAlert(document);
	util_hideExecutionLayer(document);

	if(confirmFlag){

		if(document.getElementById("contents") != null){
			util_showExecutionImage();
		}else{
			util_showExecutionImageOnPopup();
		}
		window.eval(execStr);

	}else{

		//util_hideExecutionLayer(document);

		if(cancelExecStr != null){
			window.eval(cancelExecStr);
		}

	}

}

/**
 * confirm 선택(no submit)
 *
 * @param confirmFlag
 * @param execStr
 * @param cancelExecStr
 * @returns void;
 */
function util_selectConfirmNoSubmit(confirmFlag, execStr, cancelExecStr){

	util_hideExecutionAll(document);

	if(confirmFlag){

		window.eval(execStr);

	}else{

		if(cancelExecStr != null){
			window.eval(cancelExecStr);
		}

	}

}

/**
 * frame 폼의 값을 parent 폼에 입력
 *
 * @param parentForm
 * @param childform
 * @param checkObjectName
 * @param targetObjects
 * @returns void
 */
function util_moveToParentFormWithChecked(parentForm, childform, checkObjectName, targetObjects){

	var checkObjects = document.getElementsByName(checkObjectName);

	var checkedIndex = 0;
	for(var i = 0; i < checkObjects.length; i++){

		if(checkObjects[i].checked){

			for(var j = 0; j < targetObjects.length; j++){

				var tempObj = document.getElementsByName(targetObjects[j])[i];

				if(tempObj != null){

					// 부모한테 같은 object가 있으면 삭제 시작
					parentObjs = parent.document.getElementsByName(tempObj.name);

					if(parentObjs != null &&
					   parentObjs[checkedIndex] != null){

						parentObjs[checkedIndex].value = tempObj.value;

					}else{

						var parentTempObj = parent.document.createElement("input");
						parentTempObj.type = "hidden";
						parentTempObj.name = tempObj.name;
						parentTempObj.value = tempObj.value;

						parentForm.appendChild(parentTempObj);

					}

				}

			}

			var tempCheckObj = parent.document.createElement("input");
			tempCheckObj.type = "hidden";
			tempCheckObj.name = checkObjects[i].name;
			tempCheckObj.value = checkedIndex;

			parentForm.appendChild(tempCheckObj);

			checkedIndex++;

		}

	}

}


/**
 * DML 처리 결과
 *
 * @param resultMsg
 * @param destUrl
 * @returns void
 */
function util_showResultOnBodyException(resultMsg, destUrl){

	var documentObj = document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + resultMsg + "	</div>";
	
	htmlStr += "	<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_moveBack('" + destUrl + "')\" value=\"" + confirmStr + "\">";
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";
	
	util_getMessageAlertDiv(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert");

}

function util_moveBack(destUrl){
	//parent.main.location.href = destUrl; //포탈연동되면 이걸로 변경
	history.back();
	//parent.location.href = destUrl; //테스트시에는 이걸로 테스트
} 

/* confirm ok and no */
function util_messageAlertOk(paramMessage, isSuccess){

	focusObj = null;

	util_appendLayerDiv(document);

	var message = paramMessage;

	if(document.getElementById("contents") != null){
		util_makeMessageAlertDiv(document, message, null, isSuccess);
	}else{
		util_makeMessageAlertDivOnPopup(document, message);
	}
}


/* width alert contents */
function util_messageAlertWidth(paramMessage, width, isSuccess){

	util_appendLayerDiv(document);

	var message = paramMessage;

	if(document.getElementById("contents") != null){
		util_makeMessageAlertWidth(document, message, width, isSuccess);
	}else{
		util_makeMessageAlertDivOnPopupWidth(document, message, width);
	}
}

/* width alert contents */
function util_makeMessageAlertWidth(documentObj, paramHtmlStr, width, isSuccess){

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "	<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event)\" value=\"" + confirmStr + "\">";
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";

	return util_getMessageAlertWidth(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert", width);
}

/* width alert popup_contents */
function util_makeMessageAlertDivOnPopupWidth(documentObj, paramHtmlStr, width){

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event)\" value=\"" + confirmStr + "\">";

	htmlStr += "	</div>";
	htmlStr += "	</div>";

	
	return util_getMessageAlertWidth(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert", width);

}

/* width alert */
function util_getMessageAlertWidth(documentObj, htmlStr, left, top, divType, width){

	if(documentObj.getElementById("messageAlertDiv") != null){
		return;
	}
	
	var messageAlertDivObj = documentObj.createElement("div");
	messageAlertDivObj.setAttribute("id", "messageAlertDiv");
	
	messageAlertDivObj.setAttribute("class", "boxTy7");
	
	if(width == null){
		width = "width:500px;";
	}else if(width < 200 || width > 650){
		width = width = "width:600px;";
	}else{
		width = "width:" + width + "px;";
	}

//	var styleStr = "position:absolute; width:300px;";
	var styleStr = "z-index:3; position:absolute;";
	//styleStr    += "left:" + left + "px; top:" + top + "px;";
	styleStr    += "left:" + left + "px;" + width;

	messageAlertDivObj.setAttribute("style", styleStr);

	messageAlertDivObj.innerHTML = htmlStr;

	documentObj.getElementsByTagName("body")[0].appendChild(messageAlertDivObj);

	var messageDivHeight = documentObj.getElementById("messageDiv").offsetHeight;
	var messageDivWidth = documentObj.getElementById("messageDiv").offsetWidth;

	//var alertMainObjWidth = Number(messageDivWidth) + 100;
	var alertMainObjWidth = Number(messageDivWidth);
	var alertMainObjHeight = Number(messageDivHeight) + 45;

	//documentObj.getElementById("alert_main").style.width = alertMainObjWidth + "px";
	documentObj.getElementById("alert_main").style.height = alertMainObjHeight + "px";
	
	//documentObj.getElementById("messageAlertDiv").style.width = alertMainObjWidth + "px";
	
	if(documentObj.getElementById("popup_contents") != null){
		messageAlertDivObj.style.top = ((top  / 2) - 100 - alertMainObjHeight) + "px";
	}else{
		messageAlertDivObj.style.top = top - (alertMainObjHeight / 2) - 100 + "px";
	}

	// IE에서는 keydown으로 하면 자동으로 클릭이 되므로 처리
	if(navigator.userAgent.indexOf("MSIE") > -1 && event != null){

		if(event.keyCode != 0){
			event.keyCode = 0;
		}

	}

	if(divType == "alert"){
		var tempFocusObj = documentObj.getElementById("messageAlertBtn");
		setTimeout(function(){tempFocusObj.focus();tempFocusObj.selected = true;},10);
	}else if(divType == "confirm"){
		var focusObj = documentObj.getElementById("confirmOkBtn");
		setTimeout(function(){focusObj.focus();focusObj.selected = true;},10);
	}

	return messageAlertDivObj;
}


/**
 * DML result size Up
 *
 * @param resultMsg
 * @param destUrl
 * @returns void
 */
function util_showResultSize(resultMsg, destUrl,isSuccess, width){

	var documentObj = parent.document;

	util_appendLayerDiv(documentObj);

	var offsetObj = util_getContentsOffSet(documentObj);
	
	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + resultMsg + "	</div>";
	
	htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_movePage('" + destUrl + "')\" value=\"" + confirmStr + "\">";
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";

	util_getMessageAlertWidth(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert", width);

}


/**
 * popup result size up
 *
 * @param resultMsg
 * @param destUrl
 * @returns void
 */
function util_showResultOnPopupSize(resultMsg, destUrl, isReturn,isSuccess,width){

	var documentObj = parent.document;

	util_appendLayerDivOnPopup(documentObj);

	var offsetObj = util_getContentsOffSetOnPopup(documentObj);
	var htmlStr="";

	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + resultMsg + "	</div>";
	
	if(isReturn == "Y"){
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:fn_returnValues()\" value=\"" + confirmStr + "\">";		
	}else{
		htmlStr += "			<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_movePageOnPopup('" + destUrl + "')\" value=\"" + confirmStr + "\">";
	}
	
	htmlStr += "	</div>";
	htmlStr += "	</div>";

	util_getMessageAlertWidth(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert",width);
}

/**
 * message (iframe) size up
 *
 * @param paramMessage
 * @param paramFocusObj
 * @returns void
 */
function util_messageAlertOnParentSize(paramMessage, width, paramFocusObjName, isSuccess){

	parent.focusObj = null;

	util_appendLayerDiv(parent.document);

	var message = paramMessage;
	util_makeMessageAlertDivSize(parent.document, paramMessage, paramFocusObjName, isSuccess, width);

}


/**
 * 메세지 div 만들기
 *
 * @param documentObj
 * @param paramHtmlStr
 * @param paramFocusObjName
 * @returns messageAlertDivObj
 */
function util_makeMessageAlertDivSize(documentObj, paramHtmlStr, paramFocusObjName, isSuccess, width){

	var offsetObj = util_getContentsOffSet(documentObj);

	var htmlStr = "";
	
	htmlStr += "	<div class=\"layer\">";
	htmlStr += "	<div class=\"layer_con\">";
	htmlStr += "	<span id=\"alert_main\" class=\"ico_att_txt\">" +  "</span>";
	
	htmlStr += "	<div class=\"layer_text_w\">" + paramHtmlStr + "	</div>";
	
	if(paramFocusObjName == null){
		htmlStr += "				<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event)\" value=\"" + confirmStr + "\">";
	}else{
		htmlStr += "				<input class=\"btn_gray\" id=\"messageAlertBtn\" type=\"button\" onclick=\"javascript:util_closeAlert(event, '" + paramFocusObjName + "')\" value=\"" + confirmStr + "\">";
	}

	htmlStr += "	</div>";
	htmlStr += "	</div>";

	return util_getMessageAlertWidth(documentObj, htmlStr, offsetObj.messageAlertLeft, offsetObj.messageAlertTop, "alert", width);

}

/*
function util_showLayerPopUp(msg) {
	if (msg != null && msg != '') {
		$("#layerPopUp .layer_text_w").html(msg);
	}
	$("#layerPopUp :button").click(function () {
		$("#layerPopUp").hide();
	});
	$("#layerPopUp").show();
}
*/
