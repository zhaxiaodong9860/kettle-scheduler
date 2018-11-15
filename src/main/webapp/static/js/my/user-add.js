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
//扩展validator的校验方法
$.validator.addMethod("onlyLetterAndDigit",function(value, element, params){
    var regex=new RegExp('^[0-9a-zA-Z]+$');
    return regex.test(value);
},"只能输入字母或数字");
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
            },
            uAccount: {
                required: true,
                onlyLetterAndDigit:true,//使用自定义方法限制只能输入字母或数字
                rangelength:[4,20],
                remote:{
                    type: 'POST',
                    cache: false,
                    url: 'user/IsAccountExist.shtml',
                    data: {uAccount: function () { return $("#uAccount").val(); }}
                }
            },
            uPassword: {
                required:true,
                rangelength:[4,20]
            },
            uChkPassword: {
                required:true,
                equalTo:"#uPassword"
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
            },
            uAccount: {
                required: icon + "请输入用户账号",
                rangelength: icon + "用户名长度必须在4～20位之间",
                remote: icon + "用户名已存在，请重新输入！"
            },
            uPassword: {
                required: icon + "请输入密码",
                rangelength: icon + "密码长度必须在4～20位之间"
            },
            uChkPassword:{
                required: icon + "请再次输入密码",
                equalTo: icon + "密码输入不一致，请重新输入"
            }
        },
        submitHandler: function (form) {
            $.post("user/insert.shtml", decodeURIComponent($(form).serialize(), true), function (data) {
                var result = JSON.parse(data);
                if (result.status == "success") {
                    layer.msg('添加成功', {
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