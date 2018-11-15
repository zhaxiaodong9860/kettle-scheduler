<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑作业</title>
    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <%--<link href="static/css/plugins/cron-generator/cronGen.css" rel="stylesheet">--%>
    <link href="static/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>编辑作业</h5>
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
            	<form class="form-horizontal m-t" id="RepositoryJobForm">
            		<input type="hidden" name="jobId" id="jobId" value="${jobId }">
            		<div class="form-group">
            			<label class="col-sm-3 control-label required">资源库：</label>
                    	<div class="col-sm-7">
                    		<select id="jobRepositoryId" name="jobRepositoryId" class="form-control" 
                    				onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;">
	                            <option value="0" selected>请选择资源库</option>
	                        </select>
	                    </div>
            		</div>
           		 	<div class="form-group">
	                    <label class="col-sm-3 control-label required">作业路径：</label>
	                    <div class="col-sm-7">
	                        <input id="jobPath" name="jobPath" type="text" readOnly class="form-control" aria-required="true">
	                    </div>
	                </div>
					<div class="form-group">
						<label class="col-sm-3 control-label required">分类：</label>
						<div class="col-sm-7">
							<select id="categoryId" name="categoryId" class="form-control" aria-required="true">
								<option value="" selected>请选择分类</option>
							</select>
						</div>
					</div>
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">作业名称：</label>
	                    <div class="col-sm-7">
	                        <input id="jobName" name="jobName" type="text" placeholder="请输入作业名称" class="form-control" aria-required="true">
	                    </div>
	                </div>
					<div class="form-group">
						<label class="col-sm-3 control-label required">作业执行策略：</label>
						<div class="col-sm-7">
							<select id="jobQuartz" name="jobQuartz" class="form-control" aria-required="true">
								<option value="" selected>请选择作业执行策略</option>
							</select>
						</div>
					</div>
	                <%--<div class="form-group">
	                	<div id="default" style="display:block">
		                	<label class="col-sm-3 control-label">作业执行策略：</label>
		                    <div class="col-sm-5">
		                        <select id="jobQuartz" name="jobQuartz" class="form-control">
		                            <option value="0" selected>请选择作业执行策略</option>
		                        </select>
		                    </div>
	                	</div>
	                    <div id="custom" style="display:none">
		                	<label class="col-sm-3 control-label">作业执行策略：</label>
		                    <div class="col-sm-5">
		                        <input id="customerQuarz" readonly name="customerQuarz" placeholder="请选择定时任务的cron编码" class="form-control">
		                    </div>
	                	</div>
	                    <div class="col-sm-3">
	                    	<button type="button" class="btn btn-w-m btn-primary" id="changeQuartz"><i class="fa fa-exchange" aria-hidden="true"></i>&nbsp;切换输入方式</button>
	                    </div>
	                </div>--%>
	                <div class="form-group">
            			<label class="col-sm-3 control-label required">记录日志级别：</label>
                    	<div class="col-sm-7">
                    		<select id="jobLogLevel" name="jobLogLevel" class="form-control">
                    			<option value="">请选择日志级别</option>
	                            <option value="basic">基本日志</option>
	                            <option value="detail">详细日志</option>
	                            <option value="error">错误日志</option>
	                            <option value="debug">调试日志</option>
	                            <option value="minimal">最小日志</option>
	                            <option value="rowlevel">行级日志（非常详细）</option>
	                        </select>
	                    </div>
            		</div>    
	                <div class="form-group">
	                    <label class="col-sm-3 control-label">作业描述：</label>
	                    <div class="col-sm-7">
	                        <textarea id="jobDescription" name="jobDescription" placeholder="请输入作业描述，不超过500字" class="form-control" aria-required="true"></textarea>
	                    </div>
	                </div>           
	                <div class="form-group">
	                    <div class="col-sm-9 col-sm-offset-3">
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
    <%--<script src="static/js/plugins/cron-generator/cronGen.js"></script>--%>
    <!-- jQuery Validation plugin javascript-->
    <script src="static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="static/js/plugins/validate/messages_zh.min.js"></script>
    <script src="static/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- layer javascript -->
    <script src="static/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="static/js/content.js?v=1.0.0"></script>    
	<script src="static/js/plugins/jsTree/jstree.min.js"></script>	  
	<script src="static/js/my/job-edit.js"></script>   
</body>
</html>