$(document).ready(function () {
	$.ajax({
        type: 'POST',
        async: false,
        url: 'repository/database/getSimpleList.shtml',
        data: {},
        success: function (data) {
        	for (var i=0; i<data.length; i++){
        		$("#transRepositoryId").append('<option value="' + data[i].repositoryId + '">' + data[i].repositoryName + '</option>');
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
        url: 'category/getSimpleList.shtml',
        data: {},
        success: function (data) {
            for (var i=0; i<data.length; i++){
                $("#categoryId").append('<option value="' + data[i].categoryId + '">' + data[i].categoryName + '</option>');
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
        url: 'quartz/getSimpleList.shtml',
        data: {},
        success: function (data) {
        	for (var i=0; i<data.length; i++){
        		$("#transQuartz").append('<option value="' + data[i].quartzId + '">' + data[i].quartzDescription + '</option>');
        	}
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });	
	// $("#customerQuarz").cronGen({
    	// direction : 'left'
	// });
	reset();
});

var reset = function(){
	var transId = $("#transId").val();
	$.ajax({
        type: 'POST',
        async: false,
        url: 'trans/getTrans.shtml',
        data: {
        	transId : transId
        },
        success: function (data) {
        	var Trans = data.data;
        	$("#transRepositoryId").find("option[value=" + Trans.transRepositoryId + "]").prop("selected",true);
        	$("#transPath").val(Trans.transPath);
            $("#categoryId").find("option[value=" + Trans.categoryId + "]").prop("selected",true);
        	$("#transName").val(Trans.transName);
        	$("#transQuartz").find("option[value=" + Trans.transQuartz + "]").prop("selected",true);
        	$("#transLogLevel").find("option[value=" + Trans.transLogLevel + "]").prop("selected",true);
        	$("#transDescription").val(Trans.transDescription);      	
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });
}

// $("#changeQuartz").click(function(){
// 	$("#default").toggle();
// 	$("#custom").toggle();
// 	$("#transQuartz").val("");
// 	$("#customerQuarz").val("");
// });
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
    $("#RepositoryTransForm").validate({
        rules: {
        	transName: {
        		required: true,
        		maxlength: 50
        	},
            categoryId:{
                required: true
            },
            transQuartz:{
                required: true
            },
        	transLogLevel: {
        		required: true,
        	},
        	transDescription: {
        		maxlength: 500
        	}
        },
        messages: {
        	transName: {
        		required: icon + "请输入转换名称",
        		maxlength: icon + "转换名称不能超过50个字符"
        	},
            categoryId:{
                required: icon + "请选择作业分类"
            },
            transQuartz:{
                required: icon + "请选择转换执行策略"
            },
            transLogLevel: {
        		required: icon + "请选择转换的日志记录级别",
        	},
        	transDescription: {
        		maxlength: icon + "转换描述不能超过500个字符"
        	}
        },
        submitHandler:function(form){
        	$.post("trans/update.shtml", decodeURIComponent($(form).serialize(),true), function(data){
        		var result = JSON.parse(data);
    			if(result.status == "success"){
    				layer.msg('更新成功',{
            			time: 2000,
            			icon: 6
            		});              		
            		setTimeout(function(){
            			location.href = "view/trans/listUI.shtml";
            		},2000);
    			}else {
    				layer.msg(result.message, {icon: 2}); 
    			}
    		});
        } 
    });
});

var cancel = function(){
	location.href = "view/trans/listUI.shtml";
}