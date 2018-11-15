$(document).ready(function () {
    $('#mySwitch input').bootstrapSwitch({
        onText: "启用",
        offText: "停用",
        onColor: "primary",
        offColor: "danger",
        onInit: function () {
            $("#dr").val("0");
        },
        onSwitchChange: function (event, state) {
            if (state == true) {
                $("#dr").val("0");
            } else {
                $("#dr").val("1");
            }
        }
    });
});

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
        } else if (element.is("#cQuarz")) {
            error.appendTo(element.parent().parent());
        } else {
            error.appendTo(element.parent());
        }
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none"
});
$().ready(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#DimEtlConfigForm").validate({
        rules: {
            sourceTable: {
                required: true,
                maxlength: 1500
            },
            targetTable: {
                required: true,
                maxlength: 64
            }, dataSrc: {
                required: true,
                maxlength: 32
            }, etlLevel: {
                required: true,
                maxlength: 32
            }, strSql: {
                required: true,
                maxlength: 4000
            }, strSql2: {
                maxlength: 4000
            }, dbConn: {
                required: true,
                maxlength: 64
            }, dbIp: {
                maxlength: 64
            }, dbType: {
                required: true,
                maxlength: 64
            }, project: {
                required: true,
                maxlength: 500
            }, sourceSchema: {
                required: true,
                maxlength: 64
            }, targetSchema: {
                required: true,
                maxlength: 64
            }, dataTgt: {
                required: true,
                maxlength: 32
            }, fields: {
                required: true,
                maxlength: 4000
            }, groupid: {
                required: true,
                digits: true
            }, daysKeep: {
                required: true,
                digits: true
            },
            fields2: {
                maxlength: 4000
            }
        },
        messages: {

        },
        submitHandler: function (form) {
            $.post("dimEtlConfig/insert.shtml", decodeURIComponent($(form).serialize(), true), function (data) {
                var result = JSON.parse(data);
                if (result.status == "success") {
                    layer.msg('添加成功', {
                        time: 2000,
                        icon: 6
                    });
                    setTimeout(function () {
                        location.href = "view/dimEtlConfig/listUI.shtml";
                    }, 2000);
                } else {
                    layer.msg(result.message, {icon: 2});
                }
            });
        }
    });
});
var cancel = function () {
    location.href = "view/dimEtlConfig/listUI.shtml";
}