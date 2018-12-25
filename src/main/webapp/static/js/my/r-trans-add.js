var treeData;
	
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
});

$("#transRepositoryId").change(function(){
	var repositoryId = $(this).val(); 
	if (repositoryId > 0){
		$.ajax({
			type: 'POST',
		 	async: false,
		 	url: 'repository/database/getTransTree.shtml',
		 	data: {
		 		repositoryId : repositoryId  
		    },
		    success: function (data) {
		  		treeData = data;
	 		},
		 	error: function () {
		  		alert("请求失败！重新操作");
		 	},
		 	dataType: 'json'
		});
	}else{
		treeData = null;	
	}  
});

$("#transPath").click(function(){	
	var $transRepositoryId = $("#transRepositoryId").val();
	if (treeData != null){
		var index = layer.open({
			type: 1,
			title: '请选择转换',
			area: ["300px", '100%'],
			skin: 'layui-layer-rim',
			content: '<div id="repositoryTree"></div>'
		});
		$('#repositoryTree').jstree({
            'core': {
                'data': treeData
            },
            'plugins' : ["search"]
        }).bind('select_node.jstree', function (event, data) {  //绑定的点击事件
        	var transNode = data.node;
        	if (transNode.icon == "none"){
        		var transPath = "";
        		//证明是最子节点
        		for (var i = 0; i < treeData.length; i++){
        			if (treeData[i].id == transNode.parent){
        				transPath = treeData[i].path;
        			}
        		}
        		for (var i = 0; i < treeData.length; i++){
        			if (treeData[i].id == transNode.id){
        				transPath += "/" + treeData[i].text;
        			}	
        		}
        		layer.close(index);
        		$("#transPath").val(transPath);
        	}
        });	
	}else if($transRepositoryId != "" && treeData == null){
		layer.msg("请等待资源库加载");
	}else if($transRepositoryId == ""){
		layer.msg("请先选择资源库");
	}	
});

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
            transRepositoryId:{
                required: true
            },
        	transPath: {
        		required: true
        	},
            categoryId: {
                required: true,
            },
        	transName: {
        		required: true,
        		maxlength: 50
        	},
            transQuartz:{
                required: true
            },
        	transLogLevel: {
        		required: true
        	},
        	transDescription: {
        		maxlength: 500
        	}
        },
        messages: {
            transRepositoryId:{
                required: icon + "请选择资源库"
            },
        	transPath: {
        		required: icon + "请选择转换",
        	},
            categoryId:{
                required: icon + "请选择作业分类"
            },
        	transName: {
        		required: icon + "请输入转换名称",
        		maxlength: icon + "转换名称不能超过50个字符"
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
        	$.post("trans/insert.shtml", decodeURIComponent($(form).serialize(),true), function(data){
        		var result = JSON.parse(data);
    			if(result.status == "success"){
    				layer.msg('添加成功',{
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
