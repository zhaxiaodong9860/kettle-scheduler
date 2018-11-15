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
	$("#customerQuarz").cronGen({
    	direction : 'left'
	});	
	initFileInput("jobFile", "job/uploadJob.shtml");
});
//初始化fileinput控件（第一次初始化）
function initFileInput(ctrlName, uploadUrl) {    
    var control = $('#' + ctrlName); 
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        allowedFileExtensions: ['kjb'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload: false, //是否显示上传按钮
        showCaption: true,//是否显示标题
        dropZoneEnabled: false,//是否显示拖拽区域
        browseClass: "btn btn-primary", //按钮样式             
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
    });
}
$("#jobFile").on("filebatchselected", function(event, files) {
	$("#jobFile").fileinput("upload");
	$(".kv-upload-progress").hide();
});
$("#jobFile").on("fileuploaded", function(event, data, previewId, index) {
	$("#jobPath").val(data.response.data);
});

$("#changeQuartz").click(function(){
	$("#default").toggle();
	$("#custom").toggle();
	$("#jobQuartz").val("");
	$("#customerQuarz").val("");
});

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
        	jobLogLevel: {
        		required: icon + "请选择作业的日志记录级别",
        	},
        	jobDescription: {
        		maxlength: icon + "作业描述不能超过500个字符"
        	}
        },
        submitHandler:function(form){
        	$.post("job/insert.shtml", decodeURIComponent($(form).serialize(),true), function(data){
        		var result = JSON.parse(data);
    			if(result.status == "success"){
    				layer.msg('添加成功',{
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