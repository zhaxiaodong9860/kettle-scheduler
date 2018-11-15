<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加任务分类</title>
    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>添加任务分类</h5>
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
            	<form class="form-horizontal m-t" id="CategoryForm">
	                <div class="form-group">
	                    <label class="col-sm-3 control-label required">分类名称：</label>
	                    <div class="col-sm-7">
	                        <input id="categoryName" name="categoryName" type="text" placeholder="请输入任务分类名称" class="form-control" aria-required="true">
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
    <!-- jQuery Validation plugin javascript-->
    <script src="static/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="static/js/plugins/validate/messages_zh.min.js"></script>
    <script src="static/js/bootstrap.min.js?v=3.3.6"></script>
    <!-- layer javascript -->
    <script src="static/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="static/js/content.js?v=1.0.0"></script>
    <script>
        $.validator.setDefaults({
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            success: function (element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            errorElement: "span",
            errorPlacement: function (error, element) {
                if (element.is(":radio") || element.is(":checkbox")) {
                    error.appendTo(element.parent().parent().parent());
                }else {
                    error.appendTo(element.parent());
                }
            },
            errorClass: "help-block m-b-none",
            validClass: "help-block m-b-none"
        });
        $().ready(function () {
            var icon = "<i class='fa fa-times-circle'></i> ";
            $("#CategoryForm").validate({
                rules: {
                    categoryName: {
                        required: true,
                        maxlength: 50,
                        remote:{
                            type: 'POST',
                            cache: false,
                            url: 'category/IsCategoryExist.shtml',
                            data: {categoryName: function () { return $("#categoryName").val(); }}
                        }
                    }
                },
                messages: {
                    categoryName: {
                        required: icon + "请输入用户昵称",
                        maxlength: icon + "分类名称长度不能超过50",
                        remote: icon + ("用户名已存在，请重新输入！")
                    }
                },
                submitHandler: function (form) {
                    $.post("category/insert.shtml", decodeURIComponent($(form).serialize(), true), function (data) {
                        var result = JSON.parse(data);
                        if (result.status == "success") {
                            layer.msg('添加成功', {
                                time: 2000,
                                icon: 6
                            });
                            setTimeout(function () {
                                location.href = "view/category/listUI.shtml";
                            }, 2000);
                        } else {
                            layer.msg(result.message, {icon: 2});
                        }
                    });
                }
            });
        });
        var cancel = function(){
            location.href = "view/category/listUI.shtml";
        }
    </script>
</body>
</html>