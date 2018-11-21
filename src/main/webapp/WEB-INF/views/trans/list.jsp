<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>转换列表</title>
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
            <h5>转换列表</h5>
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
                    <a href="view/trans/rAddUI.shtml" class="btn btn-w-m btn-info" type="button">
                        <i class="fa fa-plus" aria-hidden="true"></i>&nbsp;新增资源库转换
                    </a>
                    <button class="btn btn-w-m btn-info" type="button" id="startAll" name="startAll">
                        <i class="fa fa-play" aria-hidden="true"></i>&nbsp;全部启动
                    </button>
                    <button class="btn btn-w-m btn-danger" type="button" id="stopAll" name="stopAll">
                        <i class="fa fa-stop" aria-hidden="true"></i>&nbsp;全部停止
                    </button>
                </div>
                <div class="col-sm-7">
                    <div class="form-inline  text-right">
                        <div class="form-group ">
                            <label class="control-label m-b-xs" for="categoryId">分类：</label>
                            <select id="categoryId" name="categoryId" class="form-control m-b-xs"
                                    aria-required="true">
                                <option value="" selected>请选择分类</option>
                            </select>
                        </div>
                        <div class="form-group ">
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
            <table id="transList" data-toggle="table"
                   data-url="trans/getList.shtml"
                   data-query-params=queryParams data-query-params-type="limit"
                   data-pagination="true"
                   data-side-pagination="server" data-pagination-loop="false">
                <thead>
                <tr>
                    <th data-field="transId">转换编号</th>
                    <th data-field="categoryId" data-formatter="categoryNameFormatter">分类</th>
                    <th data-field="transName" data-formatter="transNameFormatter">转换名称</th>
                    <%--<th data-field="transDescription" data-formatter="transNameFormatter">转换描述</th>--%>
                    <th data-field="transPath">转换路径</th>
                    <th data-field="transQuartz" data-formatter="transQuartzFormatter">执行策略</th>
                    <th data-field="transStatus" data-formatter="transStatusFormatter">转换状态</th>
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

    function transNameFormatter(value, row, index) {
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

    function transQuartzFormatter(value, row, index) {
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

    function transStatusFormatter(value, row, index) {
        var state = "";
        $.ajax({
            type: 'POST',
            async: false,
            url: 'trans/getTransRunState.shtml',
            data: {
                "transId": row.transId
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
        if (row.transStatus == "1") {
            return ['<a class="btn btn-danger btn-xs" id="stop" type="button"><i class="fa fa-stop" aria-hidden="true"></i>&nbsp;停止</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="edit" type="button"><i class="fa fa-edit" aria-hidden="true"></i>&nbsp;编辑</a>',
                '&nbsp;&nbsp;',
                '<a class="btn btn-primary btn-xs" id="delete" type="button"><i class="fa fa-trash" aria-hidden="true"></i>&nbsp;删除</a>'].join('');
        } else if (row.transStatus == "2") {
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
            layer.confirm('确定启动该转换？', {
                    btn: ['确定', '取消']
                },
                function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'POST',
                        async: true,
                        url: 'trans/start.shtml',
                        data: {
                            "transId": row.transId
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
            layer.confirm('确定停止该转换？', {
                    btn: ['确定', '取消']
                },
                function (index) {
                    layer.close(index);
                    $.ajax({
                        type: 'POST',
                        async: true,
                        url: 'trans/stop.shtml',
                        data: {
                            "transId": row.transId
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
                url: 'trans/getTrans.shtml',
                data: {
                    "transId": row.transId
                },
                success: function (data) {
                    var Trans = data.data;
                    if (Trans.transStatus == 1) {
                        layer.alert("修改前请先停止任务");
                    } else {
                        var transId = row.transId;
                        location.href = "view/trans/editUI.shtml?transId=" + transId;
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
                url: 'trans/getTrans.shtml',
                data: {
                    "transId": row.transId
                },
                success: function (data) {
                    var Trans = data.data;
                    if (Trans.transStatus == 1) {
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
                                    url: 'trans/delete.shtml',
                                    data: {
                                        "transId": row.transId
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
        var temp = {
            limit: 10,
            offset: params.offset,
            categoryId: $("#categoryId").val(),
            transName: $("#transName").val()
        };
        return temp;
    };

    function search() {
        $('#transList').bootstrapTable('refresh', "trans/getList.shtml");
        initButtonStatus();
    }

    $("#startAll").click(function () {
        layer.confirm('确定启动全部转换？', {
                btn: ['确定', '取消']
            },
            function (index) {
                layer.close(index);
                $.ajax({
                    type: 'POST',
                    async: true,
                    url: 'trans/startAll.shtml',
                    data: {
                        categoryId: $("#categoryId").val(),
                        transName: $("#transName").val()
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
        layer.confirm('确定停止全部转换？', {
                btn: ['确定', '取消']
            },
            function (index) {
                layer.close(index);
                $.ajax({
                    type: 'POST',
                    async: true,
                    url: 'trans/stopAll.shtml',
                    data: {
                        categoryId: $("#categoryId").val(),
                        transName: $("#transName").val()
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
            url: 'trans/getStartTaskCount.shtml',
            data: {
                categoryId: $("#categoryId").val(),
                transName: $("#transName").val()
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
            url: 'trans/getStopTaskCount.shtml',
            data: {
                categoryId: $("#categoryId").val(),
                transName: $("#transName").val()
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