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
			url : '/course/listCourseForStudent',
			queryParamsType:'',
			pagination : true,
			sidePagination : 'server',
			toolbar : '#toolbar',
			striped : true,
			detailView: true,
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
				params.search = $('#select').val();
				return params
				},
			onCheck : function(row) {
				$('#remove').attr('disabled', false)
			},
			onLoadSuccess : function(data) {
				$('#remove').attr('disabled', 'disabled')
				
			},
			columns : [ {
				radio : true
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
			}],
			onExpandRow: function (index, row, $detail) {
				childTable(index, row, $detail);
		    }
			
		});
		
		// 子表
		function childTable(index, row, $detail){
			 	var parentid = row.courseId;
			    var group_com = $detail.html('<table></table>').find('table');
			    $(group_com).bootstrapTable('destroy');
			    $(group_com).bootstrapTable({
			    	url: '/section/findSectionforStudent',
			    	method: 'get',
			    	queryParams : function(params) {
						params.courseId = parentid;
						params.sortOrder = this.sortOrder;
						params.pageSize = this.pageSize;
						params.pageNumber = this.pageNumber;
						params.search = $('#select').val();
						return params
						},
			    	ajaxOptions: { courseId: parentid },
			    	sidePagination : 'server',
			    	clickToSelect: true,
			    	cache :　false,
			    	uniqueId: "",
			    	dataField : "rows",
			    	onLoadSuccess : function(data) {
						
					},
			        columns: [{
			            field: 'fullSectionId',
			            title : '班次编号'
			        },
			        {
			            field: 'instructor.name',
			            title : '教师姓名'
			        },
			        {
			            field: 'dayOfWeek',
			            title : '星期'
			        },
			        {
			            field: 'timeOfDay',
			            title : '时间'
			        },
			        {
			            field: 'room',
			            title : '教室'
			        },
			        {
			            field: 'seatingCapacity',
			            title : '容量'
			        },
			        {
			            title : '操作',
			            events : operateEvents,
			            formatter : function (value,row,index){
		            		console.log(row);
		            		if(row.studentId==null){
		            			return '<div id="toolbar" class="btn-group"  >'+
			            		'<button  type="button"  class="select btn btn-default" >'+
			            		 	'<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>选课'+
			            		'</button>'+
			            	'</div>';
		            		}
		            		else {
		            			return  '<div id="toolbar" class="btn-group"  >'+
				            		'<button  type="button"  class="exitSelect btn btn-default" >'+
			            		 	'<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>退选'+
			            		'</button>'+
		            	'</div>';
		            		}
		           	 	}
			        }]
			    });
		}
		$("#select").change(function () {  
		    var ss = $(this).children('option:selected').val();  
		    $('#table').bootstrapTable('refresh');
		});	
		
	})

window.operateEvents = {
		'click .select': function (e, value, row, index) {
			var semester = $('#select').val();
			$.ajax({
				url : '/section/appointSection',
				data : {sectionId : row.sectionId , courseId : row.representedCourse.courseId , semester : semester},
				dataType : 'json',
				method : 'POST',
				success : function(data) {
					
					if (data.success) {
						alert(data.msg)
						$('#table').bootstrapTable('refresh');
					} else {
						alert('选课失败！')
					}
				}
			});
		      },
	'click .exitSelect': function (e, value, row, index) {
		var semester = $('#select').val();
		$.ajax({
			url : '/section/cancelSection',
			data : {sectionId : row.sectionId , courseId : row.representedCourse.courseId, semester : semester},
			dataType : 'json',
			method : 'POST',
			success : function(data) {
				
				if (data.success) {
					alert('退选成功！')
					$('#table').bootstrapTable('refresh');
				} else {
					alert('选课失败！')
				}
			}
		});
	      }
};
</script>
</head>
<body>
	<div id="toolbar" class="btn-group">
	<select name="semester" id="select"  class="form-control"> 
		<option selected="selected">学期</option>
		<option value="1">1</option> 
		<option value="2">2</option> 
	</select>	
	</div>
	<div class="container-fluid">
		<table id="table"></table>
	</div>
</body>
</html>