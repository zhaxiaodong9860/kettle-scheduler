<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加数据库资源库</title>
    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>添加数据库资源库</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
            	<form class="form-horizontal m-t" id="RepositoryForm">            		             
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库名称：</label>
	                    <div class="col-sm-7">
	                        <input id="repositoryName" name="repositoryName" type="text" placeholder="请输入资源库名称" class="form-control" aria-required="true">
	                    </div>
	                </div>	
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">登录资源库用户名：</label>
	                    <div class="col-sm-7">
	                        <input id="repositoryUsername" name="repositoryUsername" type="text" placeholder="请输入登录资源库用户名" class="form-control" aria-required="true">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">登录资源库密码：</label>
	                    <div class="col-sm-7">
	                        <input id="repositoryPassword" name="repositoryPassword" type="password" placeholder="请输入登录资源库密码" class="form-control" aria-required="true">
	                    </div>
	                </div>	
	                <div class="form-group">
            			<label class="col-sm-3 control-label required">资源库类型：</label>
                    	<div class="col-sm-7">
                    		<select id="repositoryType" name="repositoryType" class="form-control">
	                            <option value="">请选择资源库类型</option>
	                        </select>
	                    </div>
            		</div>  
                	<div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库数据库访问模式 ：</label>
	                    <div class="col-sm-7">
	                    	<select id="databaseAccess" name="databaseAccess" class="form-control">
	                            <option value="">请选择资源库数据库访问模式</option>
	                        </select>
	                    </div>
	                </div>	 
            		<div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库数据库主机名或者IP地址：</label>
	                    <div class="col-sm-7">
	                        <input id="databaseHost" name="databaseHost" type="text" placeholder="请输入资源库数据库主机名或者IP地址" class="form-control" aria-required="true">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库数据库端口号：</label>
	                    <div class="col-sm-7">
	                        <input id="databasePort" name="databasePort" type="text" placeholder="请输入资源库数据库端口号" class="form-control" aria-required="true">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库数据库名称：</label>
	                    <div class="col-sm-7">
	                        <input id="databaseName" name="databaseName" type="text" placeholder="请输入资源库数据库名称" class="form-control" aria-required="true">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库数据库登录账号：</label>
	                    <div class="col-sm-7">
	                        <input id="databaseUsername" name="databaseUsername" type="text" placeholder="请输入资源库数据库登录账号" class="form-control" aria-required="true">
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">资源库数据库登录密码：</label>
	                    <div class="col-sm-7">
	                        <input id="databasePassword" name="databasePassword" type="password" placeholder="请输入资源库数据库登录密码" class="form-control" aria-required="true">
	                    </div>
	                </div>            
	                <div class="form-group">
	                    <div class="col-sm-9 col-sm-offset-3">
	                    	<button class="btn btn-w-m btn-primary" type="button" onclick="testConnection()"><i class="fa fa-check" aria-hidden="true"></i>&nbsp;测试连接</button>
	                    	<button class="btn btn-w-m btn-primary" type="submit"><i class="fa fa-check" aria-hidden="true"></i>&nbsp;保存</button>
	                    	<button class="btn btn-w-m btn-primary" type="button" onclick="cancel()"><i class="fa fa-reply" aria-hidden="true"></i>&nbsp;取消</button>
	                    </div>
	                </div>
	            </form>  
            </div>
        </div>
	</div>
	<!-- 全局js -->
    <script src="static/js/jquery.min.js?v=2.1.4"></script>
    <!-- jQuery Validation plugin javascript-->
    <script src="static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="static/js/plugins/validate/messages_zh.min.js"></script>
    <script src="static/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- layer javascript -->
    <script src="static/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="static/js/content.js?v=1.0.0"></script> 
	<script src="static/js/my/repository-add.js"></script>   
</body>
</html>