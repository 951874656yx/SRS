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
<title>课表管理</title>

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
			url : '/section/findTranscriptForStudent',
			queryParamsType:'',
// 			pagination : true,
 			sidePagination : 'server',
			toolbar : '#toolbar',
			striped : true,
// 			detailView: true,
// 			search : true,
			cache :　false,
			showColumns : true,
			showRefresh : true,
			showToggle : true,
			showPaginationSwitch : true,
			idField : 'courseId',
			clickToSelect : true,
			singleSelect : true,
			pageNumber : 1,
			pageSize : 3,
// 			pageList : [ 5, 10, 15, 'ALL' ],
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
				params.search = $('#select').val();
				return params
				},
			onCheck : function(row) {
			},
			onLoadSuccess : function(data) {
				
			},
			columns : [ {
		            field: 'section.representedCourse.courseId',
		            title : '课程编号'
		        },{
		            field: 'section.representedCourse.courseName',
		            title : '课程名称'
		        },{
		            field: 'section.fullSectionId',
		            title : '班次编号'
		        },
		        {
		            field: 'section.instructor.name',
		            title : '教师姓名'
		        },
		        {
		            field: 'section.dayOfWeek',
		            title : '星期'
		        },
		        {
		            field: 'section.timeOfDay',
		            title : '时间'
		        },
		        {
		            field: 'section.room',
		            title : '教室'
		        },
		        {
		            field: 'grade',
		            title : '成绩'
		        }],
			onExpandRow: function (index, row, $detail) {
				
				
				
		    },
		    onCollapseRow : function(index, row){
		    	
		    }
			
		});
		
		$("#select").change(function () {  
		    var ss = $(this).children('option:selected').val();  
		    $('#table').bootstrapTable('refresh');
		});	
});


// 编辑时给 form 赋值
window.operateEvents = {
		'change #select': function (e, value, row, index) {

			
		      }
};



</script>
</head>
<body>
	<div id="toolbar" class="btn-group">	
	</div>
	<div class="container-fluid">
		<table id="table"></table>
	</div>
</body>
</html>