$(document).ready(function () {
	Metronic.init();  
	var $errorMsg = $("#errorMsg");
	var $alert = $("#alert");
	if ($errorMsg.text() === "" || $errorMsg.text() === "null"){
		$alert.css("display","none");
	}    	
    $.backstretch([
            "static/img/bg/1.jpg",
            "static/img/bg/2.jpg",
            "static/img/bg/3.jpg",
            "static/img/bg/4.jpg"
        ],{
        	fade: 1000,
            duration: 8000
        }
    );
});
$(function () {
	var localStorage = window.localStorage;
    if (localStorage.getItem("uAccount") != null){
    	$("#uAccount").val(localStorage.getItem("uAccount"));
    	$("#uPassword").val(localStorage.getItem("uPassword"));
    	$("#remember").attr("checked", true);
    }
});
//回车事件
document.onkeydown = keydown;
function keydown(e) {
    var currKey = 0, e = e || event;
    currKey = e.keyCode || e.which || e.charCode;//支持IE、FF
    if (currKey == 13) {
    	submitFrom();
    }
}

var submitFrom = function() {
	var localStorage = window.localStorage;
	var $uAccount = $("#uAccount").val();
	var $uPassword = $("#uPassword").val();
	if ($('#remember').is(':checked')) {
		localStorage.setItem("uAccount", $uAccount);
		localStorage.setItem("uPassword", $uPassword);
	}else{
		if(localStorage.getItem("uAccount") != null){
			localStorage.removeItem("uAccount");
    		localStorage.removeItem("uPassword");
		}
	}
	$("#loginForm").submit();
}