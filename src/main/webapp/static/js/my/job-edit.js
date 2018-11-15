$(document).ready(function () {
	$.ajax({
        type: 'POST',
        async: false,
        url: 'repository/database/getSimpleList.shtml',
        data: {},
        success: function (data) {
        	for (var i=0; i<data.length; i++){
        		$("#jobRepositoryId").append('<option value="' + data[i].repositoryId + '">' + data[i].repositoryName + '</option>');
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
        		$("#jobQuartz").append('<option value="' + data[i].quartzId + '">' + data[i].quartzDescription + '</option>');
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
	var jobId = $("#jobId").val();
	$.ajax({
        type: 'POST',
        async: false,
        url: 'job/getJob.shtml',
        data: {
        	jobId : jobId
        },
        success: function (data) {
        	var job = data.data;
        	$("#jobRepositoryId").find("option[value=" + job.jobRepositoryId + "]").prop("selected",true);
        	$("#jobPath").val(job.jobPath);
            $("#categoryId").find("option[value=" + job.categoryId + "]").prop("selected",true);
        	$("#jobName").val(job.jobName);
        	$("#jobQuartz").find("option[value=" + job.jobQuartz + "]").prop("selected",true);
        	$("#jobLogLevel").find("option[value=" + job.jobLogLevel + "]").prop("selected",true);
        	$("#jobDescription").val(job.jobDescription);      	
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
// 	$("#jobQuartz").val("");
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
    $("#RepositoryJobForm").validate({
        rules: {
        	jobName: {
        		required: true,
        		maxlength: 50
        	},
            categoryId:{
                required: true
            },
            jobQuartz:{
                required: true
            },
        	jobLogLevel: {
        		required: true,
        	},
        	jobDescription: {
        		maxlength: 500
        	}
        },
        messages: {
        	jobName: {
        		required: icon + "请输入作业名称",
        		maxlength: icon + "作业名称不能超过50个字符"
        	},
            categoryId:{
                required: icon + "请选择作业分类"
            },
            jobQuartz:{
                required: icon + "请选择作业执行策略"
            },
        	jobLogLevel: {
        		required: icon + "请选择作业的日志记录级别",
        	},
        	jobDescription: {
        		maxlength: icon + "作业描述不能超过500个字符"
        	}
        },
        submitHandler:function(form){
        	$.post("job/update.shtml", decodeURIComponent($(form).serialize(),true), function(data){
        		var result = JSON.parse(data);
    			if(result.status == "success"){
    				layer.msg('更新成功',{
            			time: 2000,
            			icon: 6
            		});              		
            		setTimeout(function(){
            			location.href = "view/job/listUI.shtml";
            		},2000);
    			}else {
    				layer.msg(result.message, {icon: 2}); 
    			}
    		});
        } 
    });
});

var cancel = function(){
	location.href = "view/job/listUI.shtml";
}