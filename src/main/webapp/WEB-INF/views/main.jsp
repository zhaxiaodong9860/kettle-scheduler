<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
	<link rel="shortcut icon" href="static/img/favicon.ico">
    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="row">
    		<div class="col-sm-4">
                <div class="widget style1 navy-bg">
                    <div class="row">
                        <div class="col-sm-4" style="opacity:0.2">
                        	<i class="fa fa-globe fa-5x" aria-hidden="true"></i>
                        </div>
                        <div class="col-sm-8 text-right" style="font-size:20px">
                            <span> 总监控任务数 </span>
                            <h2 class="font-bold" id="allNum"></h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="widget style1 navy-bg">
                    <div class="row">
                        <div class="col-sm-4" style="opacity:0.2">
                        	<i class="fa fa-cogs fa-5x" aria-hidden="true"></i>
                        </div>
                        <div class="col-sm-8 text-right" style="font-size:20px">
                            <span> 监控作业数 </span>
                            <h2 class="font-bold" id="jobNum"></h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="widget style1 navy-bg">
                    <div class="row">
                        <div class="col-sm-4" style="opacity:0.2">
                            <i class="fa fa-exchange fa-5x" aria-hidden="true"></i>
                        </div>
                        <div class="col-sm-8 text-right" style="font-size:20px">
                            <span> 监控转换数 </span>
                            <h2 class="font-bold" id="transNum"></h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
    		<div class="col-sm-6">
	    		<div class="ibox float-e-margins">
		            <div class="ibox-title">
		                <h5>转换监控记录</h5>
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
		            	<div class="right">	
			            	<button onclick="searchTrans()" class="btn btn-w-m btn-info" type="button">
			            		<i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;刷新列表
		            		</button>
		            	</div>
		                <table id="transMonitorList" data-toggle="table"
							data-url="main/getTransList.shtml"
							data-query-params=queryParams data-query-params-type="limit"
							data-pagination="true"
							data-side-pagination="server" data-pagination-loop="false">
							<thead>
								<tr>
									<th data-field="monitorId">记录编号</th>
									<th data-field="monitorTrans" data-formatter="MonitorTransFormatter">转换名称</th>
									<th data-field="monitorSuccess">转换执行成功次数</th>
								</tr>
							</thead>
						</table>
		            </div>
		        </div>
    		</div>
    		<div class="col-sm-6">
	    		<div class="ibox float-e-margins">
		            <div class="ibox-title">
		                <h5>作业监控记录</h5>
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
		            	<div class="right">	
			            	<button onclick="searchJobs()" class="btn btn-w-m btn-info" type="button">
			            		<i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;刷新列表
		            		</button>
		            	</div>
		                <table id="jobMonitorList" data-toggle="table"
							data-url="main/getJobList.shtml"
							data-query-params=queryParams data-query-params-type="limit"
							data-pagination="true"
							data-side-pagination="server" data-pagination-loop="false">
							<thead>
								<tr>
									<th data-field="monitorId">记录编号</th>
									<th data-field="monitorJob" data-formatter="MonitorJobFormatter">作业名称</th>
									<th data-field="monitorSuccess">作业执行成功次数</th>
								</tr>
							</thead>
						</table>
		            </div>
		        </div>
    		</div>
   		</div>
   		<div class="row">
   			<div class="ibox float-e-margins">
	            <div class="ibox-title">
	                <h5>监控状况</h5>
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
	                <div class="echarts" id="kettleLine"></div>
	            </div>
	        </div>
   		</div>
	</div>
	<!-- 全局js -->
    <script src="static/js/jquery.min.js?v=2.1.4"></script>
    <script src="static/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- Bootstrap table -->
    <script src="static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <!-- layer javascript -->
    <script src="static/js/plugins/layer/layer.min.js"></script>
    <!-- ECharts -->
    <script src="static/js/plugins/echarts/echarts.js"></script>
    <!-- 自定义js -->
    <script src="static/js/content.js?v=1.0.0"></script>    
    <!-- customer -->
    <script src="static/js/my/main.js" type="text/javascript"></script>
</body>
</html>