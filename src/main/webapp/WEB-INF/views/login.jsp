<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>登录 | Kettle调度监控平台</title>
    <link rel="shortcut icon" href="static/img/favicon.ico">
  	<link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="static/lib/jquery.uniform/themes/css/uniform.default.min.css" rel="stylesheet" type="text/css"/>
    <link href="static/lib/metronic/css/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="static/lib/metronic/css/components.css" rel="stylesheet" type="text/css"/>
</head>
<script>
//解决子框架嵌套的问题
if(window != window.parent){
	window.parent.location.reload(true);
}
</script>
<body class="login">
<div class="logo">
    <a href="javascript:void(0)"><span style="font-size: 24px;color: white;">Kettle调度监控平台</span></a>
</div>
<div class="content">
    <form id="loginForm" name="loginForm" class="login-form" action="index/login.shtml" method="POST" novalidate>
        <h3 class="form-title">登录你的账户</h3>
        <div id="alert" class="alert alert-danger">
            <button class="close" data-close="alert"></button>
			<span id="errorMsg">${errorMsg}</span>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">账号</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="请输入用户账号" name="uAccount" id="uAccount"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="请输入密码" name="uPassword" id="uPassword"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox" style="margin-left: 20px">
                <input type="checkbox" name="remember" id="remember"/>记住用户名
            </label>
            <button type="button" onclick="submitFrom()" class="btn blue pull-right">登录 <i class="m-icon-swapright m-icon-white"></i></button>
        </div>
    </form>
</div>
<div class="copyright"></div>
<script src="static/js/jquery.min.js?v=2.1.4"></script>
<script src="static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="static/lib/jquery-backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<!-- <script src="static/lib/jquery.uniform/dist/jquery.uniform.min.js" type="text/javascript"></script> -->
<script src="static/lib/metronic/scripts/metronic.js" type="text/javascript"></script>
<script src="static/js/my/login.js" type="text/javascript"></script>
</body>
</html>
