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
<title>课程管理</title>

<link
	href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
	
<script type="text/javascript" src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"t src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<style>
	body,button, input, select, textarea,h1 ,h2, h3, h4, h5, h6 ,lable{ font-family: Microsoft YaHei,'宋体' , Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;}
</style>
<script>
	$(function() {
		$('#table').bootstrapTable('destroy');
		$('#table').bootstrapTable({
			url : '/course/listAllCourse',
			queryParamsType:'',
			pagination : true,
			sidePagination : 'server',
			toolbar : '#toolbar',
			striped : true,
// 			detailView: true,
			search : true,
			cache :　false,
			showColumns : true,
			showRefresh : true,
			showToggle : true,
			showPaginationSwitch : true,
			idField : 'courseId',
			clickToSelect : true,
			singleSelect : false,
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 5, 10, 15, 'ALL' ],
			dataField : 'rows',
			totalField : 'total',
			sortable: true,
// 			sortName : '',
// 			sortOrder :　'asc',
			locale: "zh-CN",//中文支持,
			queryParams : function(params) {
				params.sortName = this.sortName;
				params.sortOrder = this.sortOrder;
				params.pageSize = this.pageSize;
				params.pageNumber = this.pageNumber;
				params.search = $('.search input').val();
				return params
				},
			onCheck : function(row) {
				
			},
			onLoadSuccess : function(data) {
				
				
			},
			columns : [ {
				checkbox : true
			}, {
				field : 'courseId',
				title : '课程编号',
				sortable:true
			}, {
				field : 'courseName',
				title : '课程名称',
				sortable:true
			}, {
				field : 'credits',
				title : '课程学分',
				sortable:true
			}]
		});
		
		
		$('#remove').click(function() {
			var rows = $('#table').bootstrapTable('getAllSelections');
			var major = $('#select').val();
			if (rows.length == 0) {
				alert('请选择一条记录！')
			} else {
				var courseIds =  new Array();
				for(i=0;i<rows.length;i++){
					courseIds.push(rows[i].courseId);
				}
				$.ajax({
					url : '${basePath}appointPlanOfStudy',
					data : {
						courseIds : courseIds, major : major
					},
					dataType : 'json',
					method : 'POST',
					success : function(data) {
						
						if (data.success) {
							alert('添加成功！');
							$('#table').bootstrapTable('refresh');
						} else {
							alert('添加失败！')
						}
					}
				})
			}
		});

	})
</script>
</head>
<body>
	<div id="toolbar" class="btn-group">
		<select name="mojor" id="select"  class="form-control"> 
			<option selected="selected">请选择专业专业</option>
			<option value="电子商务">电子商务</option> 
			<option value="市场营销">市场营销</option> 
			<option value="人力资源">人力资源</option>
			<option value="工商管理">工商管理</option>
		</select>
		<br>
		<button id="remove" class="btn btn-default" >
			<i class="glyphicon glyphicon-add"></i> 添加
		</button>
	</div>
	<div class="container-fluid">
		<table id="table"></table>
	</div>
	<div class="container-fluid">
		<table id="table2"></table>
	</div>
</body>
</html>