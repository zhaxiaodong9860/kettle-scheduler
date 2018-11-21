<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>作业列表</title>
    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>作业列表</h5>
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
                <div class="col-sm-5">
                    <a href="view/job/rAddUI.shtml" class="btn btn-w-m btn-info" type="button">
                        <i class="fa fa-plus" aria-hidden="true"></i>&nbsp;新增资源库作业
                    </a>
                    <button class="btn btn-w-m btn-info" type="button" id="startAll" name="startAll">
                        <i class="fa fa-play" aria-hidden="true"></i>&nbsp;全部启动
                    </button>
                    <button class="btn btn-w-m btn-danger" type="button" id="stopAll" name="stopAll">
                        <i class="fa fa-stop" aria-hidden="true"></i>&nbsp;全部停止
                    </button>
                </div>
                <div class="col-sm-7">
                    <div class="form-inline text-right">
                        <div class="form-group ">
                            <label class="control-label m-b-xs" for="categoryId">分类：</label>
                            <select id="categoryId" name="categoryId" class="form-control m-b-xs"
                                    aria-required="true">
                                <option value="" selected>请选择分类</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="control-label m-b-xs" for="jobName">作业名称：</label>
                            <input id="jobName" name="jobName" type="text" placeholder="请输入作业名称"
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

            <table id="jobList" data-toggle="table"
                   data-url="job/getList.shtml"
                   data-query-params=queryParams data-query-params-type="limit"
                   data-pagination="true"
                   data-side-pagination="server" data-pagination-loop="false">
                <thead>
                <tr>
                    <th data-field="jobId">作业编号</th>
                    <th data-field="categoryId" data-formatter="categoryNameFormatter">分类</th>
                    <th data-field="jobName" data-formatter="jobNameFormatter">作业名称</th>
                    <%--<th data-field="jobDescription" data-formatter="jobNameFormatter">作业描述</th>--%>
                    <th data-field="jobPath">作业路径</th>
                    <th data-field="jobQuartz" data-formatter="jobQuartzFormatter">执行策略</th>
                    <th data-field="jobStatus" data-formatter="jobStatusFormatter">作业状态</th>
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
        initButtonStatus();
    });

    function jobNameFormatter(value, row, index) {
        if (value.length > 15) {
            var newValue = value.substring(0, 14);
            return newValue + "……";
        } else {
            return value;
        }
    };

    function categoryNameFormatter(value, row, index) {
        var categoryName = "";
        $.ajax({
            type: 'POST',
            async: false,
            url: 'category/getCategory.shtml',
            data: {
                "categoryId": value
            },
            success: function (data) {
                var category = data.data;
                categoryName = category.categoryName;
            },
            error: function () {
                alert("系统出现问题，请联系管理员");
            },
            dataType: 'json'
        });
        return categoryName;
    };

    function jobQuartzFormatter(value, row, index) {
        var quartzDesc = "";
        $.ajax({
            type: 'POST',
            async: false,
            url: 'quartz/getQuartz.shtml',
            data: {
                "quartzId": value
            },
            success: function (data) {
                var quartz = data.data;
                quartzDesc = quartz.quartzDescription;
            },
            error: function () {
                alert("系统出现问题，请联系管理员");
            },
            dataType: 'json'
        });
        return quartzDesc;
    };

    function jobStatusFormatter(value, row, index) {
        var state = "";
        $.ajax({
            type: 'POST',
            async: false,
            url: 'job/getJobRunState.shtml',
            data: {
                "jobId": row.jobId
            },
            success: function (data) {
                state = data;
            },
            error: function () {
                alert("系统出现问题，请联系管理员");
            },
            dataType: 'json'
        });
        if (state == "BLOCKED") {
            return "正在运行";
        } else if (state == "NORMAL") {
            return "空闲";
        } else if(state == "ERROR"){
            return "错误";
        }else if(state == "COMPLETE"){
            return "完成";
        }else if(state == "PAUSED"){
            return "暂停";
        }else if(state == "NONE"){
            return "停止";
        }else{
            return "未定义";
        }
    }

    function actionFormatter(value, row, index) {
        if (row.jobStatus == "1") {
            return ['<a class="btn btn-danger btn-xs" id="stop" type="button"><i class="fa fa-stop" aria-hidden="true"></i>&nbsp;停止</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="edit" type="button"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;编辑</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="delete" type="button"><i class="fa fa-trash" aria-hidden="true"></i>&nbsp;删除</a>'].join('');
        } else if (row.jobStatus == "2") {
            return ['<a class="btn btn-primary btn-xs" id="start" type="button"><i class="fa fa-play" aria-hidden="true"></i>&nbsp;启动</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="edit" type="button"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;编辑</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="delete" type="button"><i class="fa fa-trash" aria-hidden="true"></i>&nbsp;删除</a>'].join('');
        } else {
            return ['<a class="btn btn-primary btn-xs" id="edit" type="button"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;编辑</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="delete" type="button"><i class="fa fa-trash" aria-hidden="true"></i>&nbsp;删除</a>'].join('');
        }
    };
    window.actionEvents = {
        'click #start': function (e, value, row, index) {
            layer.confirm('确定启动该作业？', {
                    btn: ['确定', '取消']
                },
                function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'POST',
                        async: true,
                        url: 'job/start.shtml',
                        data: {
                            "jobId": row.jobId
                        },
                        success: function (data) {
                            location.replace(location.href);
                        },
                        error: function () {
                            alert("系统出现问题，请联系管理员");
                        },
                        dataType: 'json'
                    });
                },
                function () {
                    layer.msg('取消操作');
                }
            );
        },
        'click #stop': function (e, value, row, index) {
            layer.confirm('确定停止该作业？', {
                    btn: ['确定', '取消']
                },
                function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'POST',
                        async: true,
                        url: 'job/stop.shtml',
                        data: {
                            "jobId": row.jobId
                        },
                        success: function (data) {
                            location.replace(location.href);
                        },
                        error: function () {
                            alert("系统出现问题，请联系管理员");
                        },
                        dataType: 'json'
                    });
                },
                function () {
                    layer.msg('取消操作');
                }
            );
        },
        'click #edit': function (e, value, row, index) {
            $.ajax({
                type: 'POST',
                async: true,
                url: 'job/getJob.shtml',
                data: {
                    "jobId": row.jobId
                },
                success: function (data) {
                    var Job = data.data;
                    if (Job.jobStatus == 1) {
                        layer.alert("修改前请先停止任务");
                    } else {
                        var jobId = row.jobId;
                        location.href = "view/job/editUI.shtml?jobId=" + jobId;
                    }
                },
                error: function () {
                    alert("系统出现问题，请联系管理员");
                },
                dataType: 'json'
            });
        },
        'click #delete': function (e, value, row, index) {
            $.ajax({
                type: 'POST',
                async: true,
                url: 'job/getJob.shtml',
                data: {
                    "jobId": row.jobId
                },
                success: function (data) {
                    var Job = data.data;
                    if (Job.jobStatus == 1) {
                        layer.alert("删除前请先停止任务");
                    } else {
                        layer.confirm('确定删除该单位？', {
                                btn: ['确定', '取消']
                            },
                            function (index) {
                                layer.close(index);
                                $.ajax({
                                    type: 'POST',
                                    async: true,
                                    url: 'job/delete.shtml',
                                    data: {
                                        "jobId": row.jobId
                                    },
                                    success: function (data) {
                                        location.replace(location.href);
                                    },
                                    error: function () {
                                        alert("系统出现问题，请联系管理员");
                                    },
                                    dataType: 'json'
                                });
                            },
                            function () {
                                layer.msg('取消操作');
                            }
                        );
                    }
                },
                error: function () {
                    alert("系统出现问题，请联系管理员");
                },
                dataType: 'json'
            });
        },
    };

    function queryParams(params) {
        var temp = {limit: 10, offset: params.offset, categoryId: $("#categoryId").val(), jobName: $("#jobName").val()};
        return temp;
    };

    function search() {
        $('#jobList').bootstrapTable('refresh', "job/getList.shtml");
        initButtonStatus();
    }

    $("#startAll").click(function () {
        layer.confirm('确定启动全部作业？', {
                btn: ['确定', '取消']
            },
            function (index) {
                layer.close(index);
                $.ajax({
                    type: 'POST',
                    async: true,
                    url: 'job/startAll.shtml',
                    data: {
                        categoryId: $("#categoryId").val(),
                        jobName: $("#jobName").val()
                    },
                    success: function (data) {
                        location.replace(location.href);
                    },
                    error: function () {
                        alert("系统出现问题，请联系管理员");
                    },
                    dataType: 'json'
                });
            },
            function () {
                layer.msg('取消操作');
            }
        );
    });
    $("#stopAll").click(function () {
        layer.confirm('确定停止全部作业？', {
                btn: ['确定', '取消']
            },
            function (index) {
                layer.close(index);
                $.ajax({
                    type: 'POST',
                    async: true,
                    url: 'job/stopAll.shtml',
                    data: {
                        categoryId: $("#categoryId").val(),
                        jobName: $("#jobName").val()
                    },
                    success: function (data) {
                        location.replace(location.href);
                    },
                    error: function () {
                        alert("系统出现问题，请联系管理员");
                    },
                    dataType: 'json'
                });
            },
            function () {
                layer.msg('取消操作');
            }
        );
    });

    function initButtonStatus(){
        $.ajax({
            type: 'POST',
            async: false,
            url: 'job/getStartTaskCount.shtml',
            data: {
                categoryId: $("#categoryId").val(),
                jobName: $("#jobName").val()
            },
            success: function (data) {
                if (data == 0) {
                    $("#stopAll").attr("disabled", true);
                } else {
                    $("#stopAll").attr("disabled", false);
                }
            },
            error: function () {
                alert("请求失败！请刷新页面重试");
            },
            dataType: 'json'
        });
        $.ajax({
            type: 'POST',
            async: false,
            url: 'job/getStopTaskCount.shtml',
            data: {
                categoryId: $("#categoryId").val(),
                jobName: $("#jobName").val()
            },
            success: function (data) {
                if (data == 0) {
                    $("#startAll").attr("disabled", true);
                } else {
                    $("#startAll").attr("disabled", false);
                }
            },
            error: function () {
                alert("请求失败！请刷新页面重试");
            },
            dataType: 'json'
        });
    }
</script>
</body>
</html>