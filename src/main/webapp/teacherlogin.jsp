<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录</title>

<link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet">

<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">

<link href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">

<script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<!--jquery.validate-->

<script src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="${path}/js/main.js"></script>
<style type="text/css">
	body{background: url(${path}/img/4.jpg) no-repeat;background-size: cover;font-size: 16px;}
		.form{background: rgba(255,255,255,0.2);width:550px;margin:100px auto;}
		#login_form{display: block;}
		#register_form{display: none;}
/* 		.fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;} */
/* 		input[type="text"],input[type="password"]{padding-left:26px;} */
	
</style>
</head>
	<body>
		<!--
			基础知识：
			网格系统:通过行和列布局
			行必须放在container内
			手机用col-xs-*
			平板用col-sm-*
			笔记本或普通台式电脑用col-md-*
			大型设备台式电脑用col-lg-*
			为了兼容多个设备，可以用多个col-*-*来控制；
		-->
		<!--
			从案例学知识，来做一个登录，注册页面
			用到font-awesome 4.3.0；bootstrap 3.3.0；jQuery Validate
		-->
	<div class="container">
		<div class="form row">
			<form  class="form-horizontal col-sm-offset-2 col-md-offset-2" id="teacher_login" action="teacher_sendLogin" method="post">
				<div class="col-sm-6 col-md-6" style="margin:30px 75px;padding:auto;"><h3 class="form-title">TEACHER LOGIN</h3></div>
				<div class="col-sm-9 col-md-9" >
					
					<div class="form-group">
						<!-- <i class="fa fa-user fa-lg"></i> -->
					
								<span class="col-sm-2 control-label glyphicon glyphicon-user"></span>
						
						<div class="col-sm-10">
						<input id="id"  class="form-control " type="text" placeholder="学号" name="id" autofocus="autofocus" maxlength="20"/>
						</div>
					</div>
					<div class="form-group">
							<label class="col-sm-2 control-label "><i class="fa fa-lock fa-lg"></i></label>
							<div class="col-sm-10">
							<input id="password" class="form-control " type="password" placeholder="密码" name="password" maxlength="18"/>
							</div>
					</div>
					
					<div class="form-group" style="margin:20px 50px ">
						<label class=" checkbox" style="font-size:14px">
							<input type="checkbox" name="remember" value="1"/> 记住密码
						</label>
						<hr />
						<a href="javascript:;" id="register_btn" class="">注册账户</a>
					</div>
					
					<div class="form-group">
						<input type="submit"   id="loginbtn"  class="btn .popover-hide btn-success pull-right" value="Login "/> 
						<span data-toggle="popover" data-placement="left" title="账号或密码错误" data-content="账号或密码错误，请重新输入" id="returnMessage" class="pull-right " style="display:none">
							<small class="help-block" data-bv-validator="stringLength" data-bv-for="students_id" data-bv-result="INVALID" style="color:red;font-size:16px"></small>
						 </span>
						  
					</div>
				</div>
			</form>
		</div>

		<div class="form row">
			<form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="register_form">
				
				<div class="col-sm-6 col-md-6" style="margin:auto 50px;padding:auto;"><h3 class="form-title">注册</h3></div>
				<div class="col-sm-9 col-md-9">
					<div class="form-group">
						<i class="fa fa-user fa-lg"></i>
						<input class="form-control required" type="text" placeholder="Username" name="username" autofocus="autofocus"/>
					</div>
					<div class="form-group">
							<i class="fa fa-lock fa-lg"></i>
							<input class="form-control required" type="password" placeholder="Password" id="register_password" name="password"/>
					</div>
					<div class="form-group">
							<i class="fa fa-check fa-lg"></i>
							<input class="form-control required" type="password" placeholder="Re-type Your Password" name="rpassword"/>
					</div>
					<div class="form-group">
							<i class="fa fa-envelope fa-lg"></i>
							<input class="form-control eamil" type="text" placeholder="Email" name="email"/>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-success pull-right" value="Sign Up "/>
						<a href=""  class="btn btn-info pull-left" id="back_btn" >Back</a>
					</div>
				</div>
			</form>
		</div>
		</div>
	
	</body>
</html>
