<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>转换监控记录</title>
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
                        <i class="fa fa-exchange fa-5x" aria-hidden="true"></i>
                    </div>
                    <div class="col-sm-8 text-right" style="font-size:20px">
                        <span> 总转换任务数 </span>
                        <h2 class="font-bold" id="allTrans"></h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="widget style1 navy-bg">
                <div class="row">
                    <div class="col-sm-4" style="opacity:0.2">
                        <i class="fa fa-check-circle-o fa-5x" aria-hidden="true"></i>
                    </div>
                    <div class="col-sm-8 text-right" style="font-size:20px">
                        <span> 总成功次数 </span>
                        <h2 class="font-bold" id="allSuccess"></h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="widget style1 yellow-bg">
                <div class="row">
                    <div class="col-sm-4" style="opacity:0.2">
                        <i class="fa fa-times-circle-o fa-5x" aria-hidden="true"></i>
                    </div>
                    <div class="col-sm-8 text-right" style="font-size:20px">
                        <span> 总失败次数 </span>
                        <h2 class="font-bold" id="allFail"></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
            <div class="row">
                <div class="col-sm-12">
                    <div class="form-inline text-right">
                        <div class="form-group">
                            <label class="control-label m-b-xs" for="monitorStatus">监控状态：</label>
                            <select id="monitorStatus" name="monitorStatus" class="form-control m-b-xs"
                                    aria-required="true">
                                <option value=1 selected>启动</option>
                                <option value=2 >停止</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label m-b-xs" for="categoryId">分类：</label>
                            <select id="categoryId" name="categoryId" class="form-control m-b-xs"
                                    aria-required="true">
                                <option value="" selected>请选择分类</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label m-b-xs" for="transName">转换名称：</label>
                            <input id="transName" name="transName" type="text" placeholder="请输入转换名称"
                                   class="form-control m-b-xs" aria-required="true">
                        </div>
                        <div class="form-group">
                            <button onclick="search()" class="btn btn-w-m btn-info" type="button">
                                <i class="fa fa-search" aria-hidden="true"></i>&nbsp;搜索
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <table id="transMonitorList" data-toggle="table"
                   data-url="trans/monitor/getList.shtml"
                   data-query-params=queryParams data-query-params-type="limit"
                   data-pagination="true"
                   data-side-pagination="server" data-pagination-loop="false">
                <thead>
                <tr>
                    <th data-field="monitorId">记录编号</th>
                    <th data-field="categoryName">分类</th>
                    <th data-field="transName">转换名称</th>
                    <th data-field="lastExecuteTime">上次执行时间</th>
                    <th data-field="nextExecuteTime">下次执行时间</th>
                    <th data-field="monitorSuccess">转换执行成功次数</th>
                    <th data-field="monitorFail">转换执行失败次数</th>
                    <th data-field="action" data-formatter="actionFormatter"
                        data-events="actionEvents">操作
                    </th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="static/js/jquery.min.js?v=2.1.4"></script>
<script src="static/js/bootstrap.min.js?v=3.3.6"></script>
<!-- layer javascript -->
<script src="static/js/plugins/layer/layer.min.js"></script>
<!-- 自定义js -->
<script src="static/js/content.js?v=1.0.0"></script>
<!-- Bootstrap table -->
<script src="static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(document).ready(function () {
        $.ajax({
            type: 'POST',
            async: false,
            url: 'trans/monitor/getAllMonitorTrans.shtml',
            data: {},
            success: function (data) {
                console.log(data);
                $("#allTrans").text(data);
            },
            error: function () {
                alert("请求失败！请刷新页面重试");
            },
            dataType: 'json'
        });
        $.ajax({
            type: 'POST',
            async: false,
            url: 'trans/monitor/getAllSuccess.shtml',
            data: {},
            success: function (data) {
                console.log(data);
                $("#allSuccess").text(data);
            },
            error: function () {
                alert("请求失败！请刷新页面重试");
            },
            dataType: 'json'
        });
        $.ajax({
            type: 'POST',
            async: false,
            url: 'trans/monitor/getAllFail.shtml',
            data: {},
            success: function (data) {
                $("#allFail").text(data);
            },
            error: function () {
                alert("请求失败！请刷新页面重试");
            },
            dataType: 'json'
        });
        $.ajax({
            type: 'POST',
            async: false,
            url: 'category/getSimpleList.shtml',
            data: {},
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#categoryId").append('<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>');
                }
            },
            error: function () {
                alert("请求失败！请刷新页面重试");
            },
            dataType: 'json'
        });
    });

    function actionFormatter(value, row, index) {
        return ['<a class="btn btn-primary btn-xs" id="viewDetail" type="button"><i class="fa fa-eye" aria-hidden="true"></i>&nbsp;查看详细</a>'].join('');
    };
    window.actionEvents = {
        'click #viewDetail': function (e, value, row, index) {
            location.href = "view/trans/record/listUI.shtml?transId=" + row.monitorTrans;
        },
    };

    function queryParams(params) {
        var temp = {
            limit: 10,
            offset: params.offset,
            categoryId: $("#categoryId").val(),
            transName: $("#transName").val(),
            monitorStatus:$("#monitorStatus").val()
        };
        return temp;
    };

    function search() {
        $('#transMonitorList').bootstrapTable('refresh', "trans/monitor/getList.shtml");
    };
</script>
</body>
</html>