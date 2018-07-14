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
			url : '${basePath}listAllCourse',
			queryParamsType:'',
			pagination : true,
			sidePagination : 'server',
			toolbar : '#toolbar',
			striped : true,
			detailView: true,
			search : true,
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
				params.search = $('.search input').val();
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
			    	url: '/section/findSectionByCourseId',
			    	method: 'get',
			    	queryParams: { courseId: parentid },
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
			            field: 'instructor.id',
			            title : '教师编号'
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
			        }]
			    });
		}
		
		$('#remove').click(function() {
			var rows = $('#table').bootstrapTable('getSelections');
			if (rows.length == 0) {
				alert('请选择一条记录！')
			} else {
				$.ajax({
					url : '${basePath}delete',
					data : {
						courseId : rows[0].id
					},
					dataType : 'json',
					method : 'POST',
					success : function(data) {
						
						if (data.success) {
							$('#table').bootstrapTable('refresh');
						} else {
							alert('删除失败！')
						}
					}
				})
			}
		});
		
		$('#btn_edit').click(function() {
			var rows = $('#table').bootstrapTable('getSelections');
			if (rows.length == 0) {
				alert('请选择一条记录！');
				return false;
			} else {
				$('#courseId_edit').attr('value',rows[0].courseId);
				$('#courseName_edit').attr('value',rows[0].courseName);
				$('#courseId_edit_send').attr('value',rows[0].courseId);
				$('#credits_edit').attr('value',rows[0].credits);
				return true;
			}
			
		});
		
		$('#submit').click(function(){
  			var url="${basePath}addCourse?"+$('#ff').serialize();
  			
  			$.ajax({
  				url:url,
  				data:{},
  				dataType:'json',
  				method:'POST',
  				success:function(data){
  					$("#close").click();
  					if(data.success){
  						$('#table').bootstrapTable('refresh');
  					}else{
  						alert('添加失败！')
  					}
  				},
  				error:function(){
  					alert('添加失败！请确保所有字段不为空')
  				}
  			})
  		});
		
		$('#submit_edit').click(function(){
			var url="${basePath}updateCourse?"+$('#form_edit').serialize();
			$.ajax({
				url : url,
				data : {},
				dataType : 'json',
				method : 'POST',
				success : function(data) {
					
					if (data.success) {
						$('#myModal_edit').modal('hide');
						alert('修改成功！')
						$('#table').bootstrapTable('refresh');
					} else {
						alert('修改失败！')
					}
				}
			})
  		})

	})
</script>
</head>
<body>
	<div id="toolbar" class="btn-group">
		<button id="add" class="btn btn-default" data-toggle="modal"
			data-target="#myModal1">
			<i class="glyphicon glyphicon-plus"></i> 添加
		</button>
		<button id="btn_edit" type="button" class="btn btn-default"
			data-toggle="modal" data-target="#myModal_edit">
		 	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button id="remove" class="btn btn-danger" disabled>
			<i class="glyphicon glyphicon-remove"></i> 删除
		</button>
	</div>
	<div class="container-fluid">
		<table id="table"></table>
	</div>
	<!--添加课程  -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">

		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加课程</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="ff">
						<div class="form-group">
							<label for="courseId" class="col-sm-2 control-label">课程编号</label>
							<div class="col-sm-9">
								<input name="courseId" type="text" class="form-control" id="courseId" placeholder="请输入课程编号">	
							</div>
						</div>
						<div class="form-group">
							<label for="courseName" class="col-sm-2 control-label">课程名称</label>
							<div class="col-sm-9">
								<input name="courseName" type="text" class="form-control" id="courseName" placeholder="请输入课程名称">	
							</div>
						</div>
						<div class="form-group">
							<label for="credits" class="col-sm-2 control-label">课程学分</label>
							<div class="col-sm-9">
								<input name="credits" type="text" class="form-control" id="credits" placeholder="请输入课程学分">	
							</div>
						</div>
						<div class="form-group">
							<label for="precourseId1" class="col-sm-2 control-label">前置课程1</label>
							<div class="col-sm-9">
								<input name="precourseId" type="text" class="form-control" id="precourseId1" placeholder="请输入前置课程编号">	
							</div>
						</div>
						<div class="form-group">
							<label for="precourseId2" class="col-sm-2 control-label">前置课程2</label>
							<div class="col-sm-9">
								<input name="precourseId" type="text" class="form-control" id="precourseId2" placeholder="请输入前置课程编号">	
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer" >
					<button id="close" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="submit" type="button" class="btn btn-primary">添加</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改课程 -->
	<div class="modal fade" id="myModal_edit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">

		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改课程</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="form_edit">
						<div class="form-group">
							<label for="courseId_edit" class="col-sm-2 control-label">课程编号</label>
							<div class="col-sm-9">
								<input  name="" type="text" class="form-control" id="courseId_edit" disabled placeholder="请输入课程编号">	
								<input  name="courseId" type="hidden" class="form-control" id="courseId_edit_send"  >
							</div>
						</div>
						<div class="form-group">
							<label for="courseName_edit" class="col-sm-2 control-label">课程名称</label>
							<div class="col-sm-9">
								<input name="courseName" type="text" class="form-control" id="courseName_edit" placeholder="请输入课程名称">	
							</div>
						</div>
						<div class="form-group">
							<label for="credits_edit" class="col-sm-2 control-label">课程学分</label>
							<div class="col-sm-9">
								<input name="credits" type="text" class="form-control" id="credits_edit" placeholder="请输入课程学分">	
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer" >
					<button id="close" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="submit_edit" type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>