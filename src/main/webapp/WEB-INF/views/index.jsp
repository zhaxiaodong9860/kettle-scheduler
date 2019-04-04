<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>Kettle调度监控平台</title>
    <link rel="shortcut icon" href="static/img/favicon.ico">
    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img alt="image" src="static/img/logo.jpg"></span>
                    </div>
                    <div class="logo-element">Data</div>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="fa fa-database" aria-hidden="true"></i>
                        <span class="nav-label">资源库管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="view/repostory/listUI.shtml">&nbsp;数据库资源库</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="fa fa-tasks" aria-hidden="true"></i>
                        <span class="nav-label">任务管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="view/job/listUI.shtml">&nbsp;作业管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="view/trans/listUI.shtml">&nbsp;转换管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="view/quartz/listUI.shtml">&nbsp;执行策略</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="view/category/listUI.shtml">&nbsp;任务分类</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="fa fa-desktop" aria-hidden="true"></i>
                        <span class="nav-label">监控管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="view/job/monitor/listUI.shtml">&nbsp;作业监控</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="view/trans/monitor/listUI.shtml">&nbsp;转换监控</a>
                        </li>
                    </ul>
                </li>
                <li id="isAdmin">
                    <a href="javascript:void(0)">
                        <i class="fa fa-users" aria-hidden="true"></i>
                        <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="view/user/listUI.shtml">&nbsp;用户列表</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:void(0)"><i
                            class="fa fa-bars"></i> </a>
                    <div class="navbar-title">Kettle调度监控平台</div>
                </div>
                <div class="navbar-user">你好，${SESSION_ID.uNickname}</div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="view/mainUI.shtml" class="active J_menuTab" data-id="view/mainUI.shtml">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive">
                        <a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll">
                        <a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther">
                        <a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="index/logout.shtml" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="view/mainUI.shtml" frameborder="0"
                    seamless></iframe>
        </div>
        <div class="footer">
            <div class="copy-right"></div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<!-- 全局js -->
<script src="static/js/jquery.min.js?v=2.1.4"></script>
<script src="static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="static/js/plugins/layer/layer.min.js"></script>
<!-- ECharts -->
<script src="static/js/plugins/echarts/echarts.js"></script>
<!-- 自定义js -->
<script type="text/javascript" src="static/js/hplus.js?v=4.1.0"></script>
<script type="text/javascript" src="static/js/contabs.js"></script>
<!-- 第三方插件 -->
<script type="text/javascript" src="static/js/plugins/pace/pace.min.js"></script>
<!-- 自定义js -->
<script type="text/javascript" src="static/js/my/main.js"></script>
</body>
</html>
