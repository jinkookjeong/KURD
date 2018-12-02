<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,target-densitydpi=medium-dpi">
<title>JORDAN:: KOICA</title>
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<style>
body{background:#e9f1f3;font-family: 'Open Sans',Arial,Verdana,Helvetica,sans-serif;font-size:12px}
#wrap_log{margin-top:120px}
.logo{overflow:hidden;margin:10px auto;width:790px}
.logo_01{float:left;background:url(/images/common/sp_login.png) no-repeat 0 0;width:107px;height:69px}
.logo_02{float:right;background:url(/images/common/sp_login.png) no-repeat 0 -86px;width:141px;height:69px}
.wrap_box{border-bottom:1px solid #008fda;padding:60px 0 80px;border-top:4px solid #008fda;background:#fff;width:790px;margin:0 auto;-webkit-box-shadow: 0 0 2px 1px rgba(0,0,0,.1);text-align:center}
.wrap_box:after{clear:both;display:block;content:''}
.login_img{float:left;vertical-align:top;background:url(/images/common/sp_login.png) no-repeat 0 -175px;width:134px;height:73px;margin:60px 60px 0}
.login_info{float:left;text-align:left;padding-left:60px;width:385px;vertical-align:top;position:relative}
.login_info:before{position:absolute;left:0;top:0;width:1px;height:200px;background:#dbdbdb;content:''}
.login_title{color:#008fda;font-size:32px;margin:0;font-family: 'Open Sans',Arial,Verdana,Helvetica,sans-serif;}
.login_desc{color:#696969;font-size:14px;margin:10px 0 0}
.btn_login{margin-top:20px;background:#404d5e;display:inline-block;width:150px;height:40px;line-height:40px;text-align:center;color:#fff;text-decoration:none;font-family: 'Open Sans',Arial,Verdana,Helvetica,sans-serif;font-size:16px;font-weight:600}
</style>
</head>

<body>
	<tiles:insertAttribute name="body" />
</body>
</html>