$(document).ready(function () {
    reset();
});
var reset = function(){
    var uId = $("#uId").val();
    $.ajax({
        type: 'POST',
        async: false,
        url: 'user/getUser.shtml',
        data: {
            uId : uId
        },
        success: function (data) {
            var User = data.data;
            $("#uNickname").val(User.uNickname);
            $("#uEmail").val(User.uEmail);
            $("#uPhone").val(User.uPhone);
            $("#uAccount").val(User.uAccount);
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
    $("#UserForm").validate({
        rules: {
            uNickname: {
                required: true
            },
            uEmail:{
                email:true
            },
            uPhone:{
                digits:true,
                minlength:11
            }
        },
        messages: {
            uNickname: {
                required: icon + "请输入用户昵称"
            },
            uEmail:{
                email: icon + "请输入合法的电子邮件"
            },
            uPhone:{
                digits: icon + "请输入数字",
                minlength: icon + "电话长度必须为11位"
            }
        },
        submitHandler: function (form) {
            $.post("user/update.shtml", decodeURIComponent($(form).serialize(), true), function (data) {
                var result = JSON.parse(data);
                if (result.status == "success") {
                    layer.msg('更新成功', {
                        time: 2000,
                        icon: 6
                    });
                    setTimeout(function () {
                        location.href = "view/user/listUI.shtml";
                    }, 2000);
                } else {
                    layer.msg(result.message, {icon: 2});
                }
            });
        }
    });
});
var cancel = function(){
    location.href = "view/user/listUI.shtml";
}