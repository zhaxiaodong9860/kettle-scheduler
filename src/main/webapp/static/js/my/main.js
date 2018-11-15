$(document).ready(function () {
	/*判断是否为管理员*/
	$.ajax({
        type: 'POST',
        async: false,
        url: 'index/isAdmin.shtml',
        data: {},
        success: function (data) {
        	if (!data){
        		$("#isAdmin").hide();
        	}
        },
        error: function () {
        	window.location.href="view/loginUI.shtml";
        },
        dataType: 'json'
    });
	/*获取全部在监控的任务*/
	$.ajax({
        type: 'POST',
        async: false,
        url: 'main/allRuning.shtml',
        data: {},
        success: function (data) {
        	$("#allNum").text(data);
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });
	/*获取在监控的转换数*/
	$.ajax({
        type: 'POST',
        async: false,
        url: 'trans/monitor/getAllMonitorTrans.shtml',
        data: {},
        success: function (data) {
        	$("#transNum").text(data);
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });	
	/*获取在监控的作业数*/
	$.ajax({
        type: 'POST',
        async: false,
        url: 'job/monitor/getAllMonitorJob.shtml',
        data: {},
        success: function (data) {
        	$("#jobNum").text(data);
        },
        error: function () {
            alert("请求失败！请刷新页面重试");
        },
        dataType: 'json'
    });
	$.ajax({
        type: 'POST',
        async: false,
        url: 'main/getKettleLine.shtml',
        data: {},
        success: function (data) {
        	 console.log(data.data.trans);
            var lineChart = echarts.init(document.getElementById("kettleLine"));
    	    var lineoption = {
    	        title : {
    	            text: '7天内作业和转换的监控状况'
    	        },
    	        tooltip : {
    	            trigger: 'axis'
    	        },
    	        legend: {
    	            data:['作业','转换']
    	        },
    	        xAxis : [
    	            {
    	                type : 'category',
    	                boundaryGap : false,
    	                data : data.data.legend
    	            }
    	        ],
    	        yAxis : [
    	            {
    	                type : 'value',
    	                axisLabel : {
    	                    formatter: '{value}'
    	                }
    	            }
    	        ],
    	        series : [
    	            {
    	                name:data.data.trans.name,
    	                type:'line',
    	                data:data.data.trans.data,
    	                markPoint : {
    	                    data : [
    	                        {type : 'max', name: '最大值'},
    	                        {type : 'min', name: '最小值'}
    	                    ]
    	                },
    	            },
    	            {
    	                name:data.data.job.name,
    	                type:'line',
    	                data:data.data.job.data,
    	                markPoint : {
    	                    data : [
    	                    	{type : 'max', name: '最大值'},
    	                        {type : 'min', name: '最小值'}
    	                    ]
    	                },
    	            }
    	        ]
    	    };
    	    lineChart.setOption(lineoption);
    	    $(window).resize(lineChart.resize);
        },
        error: function () {
        	window.location.href="view/loginUI.shtml";
        },
        dataType: 'json'
    });
});

function MonitorTransFormatter(value, row, index){
	var MonitorTrans = "";
	$.ajax({
        type: 'POST',
        async: false,
        url: 'trans/getTrans.shtml',
        data: {
            "transId": value          
        },
        success: function (data) {
        	var Trans = data.data;
        	MonitorTrans = Trans.transName;		        	 				        	 
        },
        error: function () {
            alert("系统出现问题，请联系管理员");
        },
        dataType: 'json'
    });
	return MonitorTrans;
}; 
function MonitorJobFormatter(value, row, index){
	var MonitorJob = "";
	$.ajax({
        type: 'POST',
        async: false,
        url: 'job/getJob.shtml',
        data: {
            "jobId": value          
        },
        success: function (data) {
        	var job = data.data;
        	MonitorJob = job.jobName;		        	 				        	 
        },
        error: function () {
            alert("系统出现问题，请联系管理员");
        },
        dataType: 'json'
    });
	return MonitorJob;
}; 

function queryParams(params) {
	var temp = { limit: 10, offset: params.offset };
    return temp;
};

function searchTrans(){
	$('#transMonitorList').bootstrapTable('refresh', "main/getTransList.shtml");
}

function searchJobs(){
	$('#jobMonitorList').bootstrapTable('refresh', "main/getJobList.shtml");
}