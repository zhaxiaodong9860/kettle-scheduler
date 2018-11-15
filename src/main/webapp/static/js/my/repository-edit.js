$(document).ready(function () {
	$.ajax({
        type: 'POST',
        async: false,
        url: 'repository/database/getType.shtml',
        data: {},
        success: function (data) {
        	for (var i=0; i<data.length; i++){
        		$("#repositoryType").append('<option value="' + data[i].repositoryTypeCode + '">' + data[i].repositoryTypeDes + '</option>');
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
        url: 'repository/database/getAccess.shtml',
        data: {},
        success: function (data) {
        	for (var i=0; i<data.length; i++){
        		$("#databaseAccess").append('<option value="' + data[i] + '">' + data[i] + '</option>');
        	}
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });	
	reset();
});

var testConnection = function(){
	var kRepository = new FormData(document.getElementById("RepositoryForm"));
	var returnType = false;
	$.ajax({
        type: 'POST',
        async: false,
        url: 'repository/database/ckeck.shtml',
        data: kRepository,
        processData:false,
        contentType:false,
        success: function (data) {
        	if (data.status == "success" && data.data == "success"){
        		returnType = true;
        		layer.msg("连接成功", {icon: 6});
        	}else {
        		layer.msg("连接失败，请检查参数重试", {icon: 5});
        	}
        },
        error: function () {
        	layer.msg("连接失败，请检查参数重试", {icon: 5});
        },
        dataType: 'json'
    });	
	return returnType;
}

var reset = function(){
	var repositoryId = $("#repositoryId").val();
	$.ajax({
        type: 'POST',
        async: true,
        url: 'repository/database/getKRepository.shtml',
        data: {
        	repositoryId : repositoryId
        },
        success: function (data) {
        	var kRepository = data.data;
        	$("#repositoryName").val(kRepository.repositoryName);
        	$("#repositoryUsername").val(kRepository.repositoryUsername);
        	$("#repositoryPassword").val(kRepository.repositoryPassword);
        	$("#repositoryType").find("option[value=" + kRepository.repositoryType + "]").prop("selected",true);
        	$("#databaseAccess").find("option[value=" + kRepository.databaseAccess + "]").prop("selected",true);
        	$("#databaseHost").val(kRepository.databaseHost);
        	$("#databasePort").val(kRepository.databasePort);
        	$("#databaseName").val(kRepository.databaseName);
        	$("#databaseUsername").val(kRepository.databaseUsername);
        	$("#databasePassword").val(kRepository.databasePassword);
        	
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });
}

$.validator.setDefaults({
	highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
    },
    success: function (element) {
        element.closest('.form-group').removeClass('has-error').addClass('has-success');
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if (element.is(":radio") || element.is(":checkbox")) {
            error.appendTo(element.parent().parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"	
});
$().ready(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#RepositoryForm").validate({
        rules: {
        	repositoryName: {
        		required: true,
        		maxlength: 50
        	},
        	repositoryUsername: {
        		required: true,
        		maxlength: 50
        	},
        	repositoryPassword: {
        		required: true,
        		maxlength: 50
        	},
        	repositoryType: {
        		required: true
        	},
        	databaseAccess: {
        		required: true
        	},
        	databaseHost: {
        		required: true,
        		maxlength: 50
        	},
        	databasePort: {
        		required: true,
        		maxlength: 10
        	},
        	databaseName: {
        		required: true,
        		maxlength: 20
        	},
        	databaseUsername: {
        		required: true,
        		maxlength: 50
        	},
        	databasePassword: {
        		required: true,
        		maxlength: 50
        	}
        },
        messages: {
        	repositoryName: {
        		required: icon + "请输入资源库名称",
        		maxlength: icon + "资源库名称不能超过50个字符"
        	},
        	repositoryUsername: {
        		required: icon + "请输入登录资源库用户名",
        		maxlength: icon + "登录资源库用户名不能超过50个字符"
        	},
        	repositoryPassword: {
        		required: icon + "请输入登录资源库密码",
        		maxlength: icon + "登录资源库密码不能超过50个字符"
        	},
        	repositoryType: {
        		required: icon + "请选择资源库类型"
        	},
        	databaseAccess: {
        		required: icon + "请选择资源库数据库访问模式"
        	},
        	databaseHost: {
        		required: icon + "请输入资源库数据库主机名或者IP地址",
        		maxlength: icon + "作业描述不能超过50个字符"
        	},
        	databasePort: {
        		required: icon + "请输入资源库数据库端口号",
        		maxlength: icon + "资源库数据库端口号不能超过10个字符"
        	},
        	databaseName: {
        		required: icon + "请输入资源库数据库名称",
        		maxlength: icon + "资源库数据库名称不能超过20个字符"
        	},
        	databaseUsername: {
        		required: icon + "请输入资源库数据库登录账号",
        		maxlength: icon + "资源库数据库登录账号不能超过50个字符"
        	},
        	databasePassword: {
        		required: icon + "请输入资源库数据库登录密码",
        		maxlength: icon + "资源库数据库登录密码不能超过50个字符"
        	}
        },
        submitHandler:function(form){        	
        	//做判断
        	if (testConnection()){
        		$.post("repository/database/update.shtml", decodeURIComponent($(form).serialize(),true), function(data){
            		var result = JSON.parse(data);
        			if(result.status == "success"){
        				layer.msg('更新成功',{
                			time: 2000,
                			icon: 6
                		});              		
                		setTimeout(function(){
                			location.href = "view/repostory/listUI.shtml";
                		},2000);
        			}else {
        				layer.msg(result.message, {icon: 2}); 
        			}
        		});
        	}
        } 
    });
});

var cancel = function(){
	location.href = "view/repostory/listUI.shtml";
}