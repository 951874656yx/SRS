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
<title>授课管理</title>

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
			columns : [{
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
			}
// 			,{
// 					title : '操作',
// 					align : 'center',
// 					formatter : function (value,row,index){
// 									return '<a id="'+row.courseId+'"  onclick="addCourseId(\''+ row.courseId +'\')" class="add_btn btn btn-default" " data-toggle="modal"'+
// 									'data-target="#myModal1"> 添加班次</a>'
// 					}	
// 				}
			],
			onExpandRow: function (index, row, $detail) {
				
				childTable(index, row, $detail);
				
		    },
		    onCollapseRow : function(index, row){	
		    }
			
		});
		
		// 子表
		function childTable(index, row, $detail){
			 	var parentid = row.courseId;
			    var group_com = $detail.html('<table></table>').find('table');
			    $(group_com).bootstrapTable('destroy');
			    $(group_com).bootstrapTable({
			    	url: '${basePath}findSectionByCourseId',
			    	method: 'get',
			    	queryParams: { courseId: parentid },
			    	ajaxOptions: { courseId: parentid },
//  			    	toolbar : '#toolbar',
			    	sidePagination : 'server',
			    	clickToSelect: true,
			    	cache :　false,
			    	uniqueId: "",
			    	dataField : "rows",
			    	onLoadSuccess : function(data) {
						
					},
			        columns: [ {
						radio : true
					}, {
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
			        },{
			            title : '操作',
			            align : 'center',
			            events : operateEvents,
			            formatter : function (value,row,index){
			            		var teacherId = $('#teacherId').val();
			            		if(row.instructor==null){
			            			return '<div id="toolbar" class="btn-group"  >'+
				            		'<button  type="button"  class="teach btn btn-default" >'+
				            		 	'<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>授课'+
				            		'</button>'+
				            	'</div>';
			            		}
			            		else if(row.instructor.id==teacherId){
			            			return  '<div id="toolbar" class="btn-group"  >'+
// 				            		'<button  type="button"  class="exitTeach btn btn-default" >'+
// 			            		 	'<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>退选'+
// 			            		'</button>'+
			            	'</div>';
			            		}
			           	 	}
			        }
			        ]
			    });
		}
	
});


// 编辑时给 form 赋值
window.operateEvents = {
		'click .teach': function (e, value, row, index) {
			$.ajax({
				url : '/section/appointInstructor',
				data : {sectionId : row.sectionId , courseId : row.representedCourse.courseId},
				dataType : 'json',
				method : 'POST',
				success : function(data) {
					
					if (data.success) {
						alert('授课成功！')
						$('#table').bootstrapTable('refresh');
					} else {
						alert('修改失败！')
					}
				}
			});
		      }
};
	

</script>
</head>
<body>
	<input type="hidden" value="${teacher.id}" id="teacherId"/>
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
					<h4 class="modal-title" id="myModalLabel">添加班次</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="ff">
						<div class="form-group">
							<label for="courseId" class="col-sm-2 control-label">课程编号</label>
							<div class="col-sm-9">
								<input disabled type="text" class="form-control" id="courseId" placeholder="请输入课程编号">	
								<input name="representedCourse.courseId" type="hidden" class="form-control" id="courseId_add" placeholder="请输入课程编号">
							</div>
						</div>
						<div class="form-group">
							<label for="sectionId" class="col-sm-2 control-label">班次</label>
							<div class="col-sm-9">
								<input name="sectionId" type="text" class="form-control" id="sectionId" placeholder="请输入班次">	
							</div>
						</div>
						<div class="form-group">
							<label for="dayOfWeek" class="col-sm-2 control-label">星期</label>
							<div class="col-sm-9">
								<input name="dayOfWeek" type="text" class="form-control" id="dayOfWeek" placeholder="请输入星期">	
							</div>
						</div>
						<div class="form-group">
							<label for="timeOfday" class="col-sm-2 control-label">时间</label>
							<div class="col-sm-9">
								<input name="timeOfDay" type="text" class="form-control" id="timeOfday" placeholder="请输入时间">	
							</div>
						</div>
						<div class="form-group">
							<label for="room" class="col-sm-2 control-label">教室</label>
							<div class="col-sm-9">
								<input name="room" type="text" class="form-control" id="room" placeholder="请输入教室">	
							</div>
						</div>
						<div class="form-group">
							<label for="seatingcapacity" class="col-sm-2 control-label">容量</label>
							<div class="col-sm-9">
								<input name="seatingCapacity" type="text" class="form-control" id="seatingcapacity" placeholder="请输入容量">	
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
					<h4 class="modal-title" id="myModalLabel">修改班次</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="form_edit">
						<div class="form-group">
							<label for="courseId_edit" class="col-sm-2 control-label">编号</label>
							<div class="col-sm-9">
								<input disabled type="text" class="form-control" id="courseId_edit" placeholder="请输入课程编号">	
								<input name="representedCourse.courseId" type="hidden" class="form-control" id="courseId_edit_send" placeholder="请输入课程编号">
							</div>
						</div>
						<div class="form-group">
							<label for="sectionId_edit" class="col-sm-2 control-label">班次</label>
							<div class="col-sm-9">
								<input name="sectionId" type="text" class="form-control" id="sectionId_edit" placeholder="请输入班次">	
							</div>
						</div>
						<div class="form-group">
							<label for="dayOfWeek_edit" class="col-sm-2 control-label">星期</label>
							<div class="col-sm-9">
								<input name="dayOfWeek" type="text" class="form-control" id="dayOfWeek_edit" placeholder="请输入星期">	
							</div>
						</div>
						<div class="form-group">
							<label for="timeOfDay_edit" class="col-sm-2 control-label">时间</label>
							<div class="col-sm-9">
								<input name="timeOfDay" type="text" class="form-control" id="timeOfDay_edit" placeholder="请输入时间">	
							</div>
						</div>
						<div class="form-group">
							<label for="room_edit" class="col-sm-2 control-label">教室</label>
							<div class="col-sm-9">
								<input name="room" type="text" class="form-control" id="room_edit" placeholder="请输入教室">	
							</div>
						</div>
						<div class="form-group">
							<label for="seatingCapacity_edit" class="col-sm-2 control-label">容量</label>
							<div class="col-sm-9">
								<input name="seatingCapacity" type="text" class="form-control" id="seatingCapacity_edit" placeholder="请输入容量">	
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