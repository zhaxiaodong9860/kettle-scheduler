$(document).ready(function () {
    $('#mySwitch input').bootstrapSwitch({
        onText: "启用",
        offText: "停用",
        onColor: "primary",
        offColor: "danger",
        onSwitchChange: function (event, state) {
            if (state == true) {
                $("#dr").val("0");
            } else {
                $("#dr").val("1");
            }
        }
    });
    reset();
});
var reset = function () {
    var configId = $("#id").val();
    $.ajax({
        type: 'POST',
        async: false,
        url: 'dimEtlConfig/getQuartz.shtml',
        data: {
            id: configId
        },
        success: function (data) {
            var dimEtlConfig = data.data;
            $("#sourceTable").val(dimEtlConfig.sourceTable);
            $("#targetTable").val(dimEtlConfig.targetTable);
            $("#dataSrc").val(dimEtlConfig.dataSrc);
            $("#etlLevel").val(dimEtlConfig.etlLevel);
            $("#strSql").val(dimEtlConfig.strSql);
            $("#strSql2").val(dimEtlConfig.strSql2);
            $("#dbConn").val(dimEtlConfig.dbConn);
            $("#dbIp").val(dimEtlConfig.dbIp);
            $("#dbType").val(dimEtlConfig.dbType);
            $("#project").val(dimEtlConfig.project);
            $("#sourceSchema").val(dimEtlConfig.sourceSchema);
            $("#targetSchema").val(dimEtlConfig.targetSchema);
            $("#dataTgt").val(dimEtlConfig.dataTgt);
            $("#fields").val(dimEtlConfig.fields);
            $("#groupid").val(dimEtlConfig.groupid);
            $("#daysKeep").val(dimEtlConfig.daysKeep);
            $("#sourceTable1").val(dimEtlConfig.sourceTable1);
            $("#fields2").val(dimEtlConfig.fields2);
            $("#dr").val(dimEtlConfig.dr);
            $("input[name='cFlag']").find("option[value=" + dimEtlConfig.cFlag + "]").prop("checked", true);
            $("input:radio[name='cFlag']").eq(dimEtlConfig.cFlag).attr("checked", 'checked');
            if (dimEtlConfig.dr == 0) {
                // $('#mySwitch input').bootstrapSwitch('toggleState');
                $('#mySwitch input').bootstrapSwitch('state', true);
            } else {
                // $('#mySwitch input').bootstrapSwitch('toggleState');
                $('#mySwitch input').bootstrapSwitch('state', false);
            }
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
            $.post("dimEtlConfig/update.shtml", decodeURIComponent($(form).serialize(), true), function (data) {
                var result = JSON.parse(data);
                if (result.status == "success") {
                    layer.msg('更新成功', {
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