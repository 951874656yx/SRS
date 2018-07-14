<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<link href="/easyui/themes/metro-green/easyui.css" rel="stylesheet">
<link href="/easyui/themes/icon.css" rel="stylesheet">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>


<style type="text/css">
	.footer{
		width:100%;
		text-align:center;
		line-hight:35px;
	}
	.top-bg{
		
		height:80px;
	}
</style>

<script>

</script>
<script>

	
	
</script>
</head>
<body class="easyui-layout" style="width: 100%;height: 100%">
	//上侧
	<div region="north" border="true" split="true" style="overflow: hidden;height: 80px;">
	<c:if test="${empty sessionScope.teacher}">
		<c:redirect url="teacherlogin.jsp" />
	</c:if>
	<c:if test="${not empty sessionScope.teacher}">
		<div class="top-bg" style="margin:40px 40px 10px 0;float:right">
			<p>${teacher.name} &nbsp;<
				 ${teacher.id}>[${teacher.title}&nbsp;${teacher.department}]&nbsp;
				 <a onclick="return exitLogin()" href="exitLogin">退出登录</a>
			</p> 
			
			<div id="exit-wind" class="easyui-dialog" title="My Dialog" style=" display: none;overflow: hidden;width:400px;height:200px;"
			    data-options="iconCls:'icon-save',resizable:true,modal:true" closed="true"
			    toolbar="#dlg-toolbar" buttons="#dlg-buttons">
			    Dialog Content.
			    <div id="dlg-buttons">
					<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:alert('Ok')">Ok</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="return dialogClose(this)">Cancel</a>
				</div>
			</div>
		</div>
	</c:if>
	
	</div>

	//下侧
	<div region="south" border="true" split="true" style="overflow: hidden;height: 40px">
		<div class="footer">版权所有：<a href=""></a></div>
    </div>

	//左侧
    <div region="west" split="true" title="导航菜单" style="width: 200px;">
		//导航栏
        <div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">

            <div title="课程管理" iconcls="icon-save" style="overflow: auto; padding: 10px;">
                <ul class="easyui-tree">
                    <li>
                        <span><a onclick="addTab('课程管理','course/course')" href="#">课程管理</a></span>
                    </li>
                </ul>
                <ul class="easyui-tree">
                    <li>
                        <span><a onclick="addTab('班次管理','section/section')" href="#">班次管理</a></span>
                    </li>
                </ul>
            </div>
            <div title="课表管理" iconcls="icon-reload"  style="padding: 10px;">
                <ul class="easyui-tree">
                	<li>
                		<span><a onclick="addTab('授课管理','section/teachOfSection')" href="#">授课管理</a></span>
                	</li>
                	<li>
                		<span><a onclick="addTab('课表查询','schedule/teacherSchedule')" href="#">课表查询</a></span>
                	</li>
                </ul> 
            </div>
            <div title="指派成绩" iconcls="icon-reload"  style="padding: 10px;">
                <ul class="easyui-tree">
                	<li>
                		<span><a onclick="addTab('指派成绩','section/assignGrade')" href="#">指派成绩</a></span>
                	</li>
                </ul> 
            </div>
            <div title="学习计划" iconcls="icon-reload"  style="padding: 10px;">
                <ul class="easyui-tree">
                	<li>
                		<span><a onclick="addTab('学习计划','course/addPlanOfStudy')" href="#">学习计划</a></span>
                	</li>
                </ul> 
            </div>
        </div>
    </div>

	//中部
    <div id="mainPanle" region="center" style="overflow: hidden;width: 100%;height: 100%">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			 
            <div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">
				
			
            </div>

        </div>
    </div>

<script>
	function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);
	} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
}
</script>	
</body>
</html>