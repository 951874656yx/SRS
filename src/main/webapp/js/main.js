/*登录页*/
	


$(document).ready(function () {
	/*判断账号密码是否正确*/
$("#students_id").change(function(){
    $("#returnMessage").css('display','none');
});

$(function () { $("[data-toggle='tooltip']").tooltip(); });

 $(function () { $('.popover-hide').popover('hide');});

$("#password").change(function(){
    $("#returnMessage").css('display','none');
});
	/*设置登录from的表单验证*/
	$("#login_form").bootstrapValidator({
//			message: 'This value is not valid',
			err: {
		            container: 'tooltip'
		        },
	        　		feedbackIcons: {
	            　　　　　　　　valid: 'glyphicon glyphicon-ok',
	            　　　　　　　　invalid: 'glyphicon glyphicon-remove',
	            　　　　　　　　validating: 'glyphicon glyphicon-refresh'
	        　　　　　　　　   },
	        fields: {
	            id: {
	                message: '用户名验证失败',
	                validators: {
	                    notEmpty: {
	                        message: '用户名不能为空'
	                    },
	                    stringLength: {
                            min: 6,
                            max: 18,
                            message: '用户名长度必须在6到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
	                    
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    },
	                    stringLength: {
                            min: 6,
                            max: 18,
                            message: '密码长度必须在6到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '密码只能包含大写、小写、数字和下划线'
                        }
	                }
	            }
	      }
	}).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        // Get the form instance
        var $form = $(e.target);
        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');
        // Use Ajax to submit form data
        $.getJSON("student/checkLogin", $form.serialize(), function (data) { 
        
            if (data.success==true) {
            	window.location.href = 'student/sendLogin'
            }
            else if (data.success==false) {
                $('#returnMessage').css('display','block');
                 $('#returnMessage').tooltip('show');
                setTimeout(
                    function () {
                  $('#returnMessage').css('display','none');
                   $('#returnMessage').tooltip('hide');
                    }, 3000
                );
            	//$('#loginbtn').html('操作成功').addClass('alert-success').show().delay(1500).fadeOut();
            }
            else {
                alert("未知错误");
            }
        	
        });
	});
	
	/*设置登录from的表单验证*/
	$("#teacher_login").bootstrapValidator({
//			message: 'This value is not valid',
			err: {
		            container: 'tooltip'
		        },
	        　		feedbackIcons: {
	            　　　　　　　　valid: 'glyphicon glyphicon-ok',
	            　　　　　　　　invalid: 'glyphicon glyphicon-remove',
	            　　　　　　　　validating: 'glyphicon glyphicon-refresh'
	        　　　　　　　　   },
	        fields: {
	            id: {
	                message: '用户名验证失败',
	                validators: {
	                    notEmpty: {
	                        message: '用户名不能为空'
	                    },
	                    stringLength: {
                            min: 6,
                            max: 18,
                            message: '用户名长度必须在6到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '用户名只能包含大写、小写、数字和下划线'
                        }
	                    
	                }
	            },
	            password: {
	                validators: {
	                    notEmpty: {
	                        message: '密码不能为空'
	                    },
	                    stringLength: {
                            min: 6,
                            max: 18,
                            message: '密码长度必须在6到18位之间'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '密码只能包含大写、小写、数字和下划线'
                        }
	                }
	            }
	      }
	}).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        // Get the form instance
        var $form = $(e.target);
        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');
        // Use Ajax to submit form data
        $.getJSON("teacher/teacher_checkLogin", $form.serialize(), function (data) { 
        
            if (data.success==true) {
            	window.location.href = 'teacher/teacher_sendLogin'
            }
            else if (data.success==false) {
                $('#returnMessage').css('display','block');
                 $('#returnMessage').tooltip('show');
                setTimeout(
                    function () {
                  $('#returnMessage').css('display','none');
                   $('#returnMessage').tooltip('hide');
                    }, 3000
                );
            	//$('#loginbtn').html('操作成功').addClass('alert-success').show().delay(1500).fadeOut();
            }
            else {
                alert("未知错误");
            }
        	
        });
	});
	
	/*注册form的表单验证*/
	$("#register_form").bootstrapValidator({
		err: {
            container: 'tooltip'
        },
    　		feedbackIcons: {
        　　　　　　　　valid: 'glyphicon glyphicon-ok',
        　　　　　　　　invalid: 'glyphicon glyphicon-remove',
        　　　　　　　　validating: 'glyphicon glyphicon-refresh'
    　　　　　　　　   },
        fields: {
           students_id: {
               message: '用户名验证失败',
               validators: {
                   notEmpty: {
                       message: '用户名不能为空'
                   },
                   stringLength: {
                       min: 6,
                       max: 18,
                       message: '用户名长度必须在6到18位之间'
                   },
                   regexp: {
                       regexp: /^[a-zA-Z0-9_]+$/,
                       message: '用户名只能包含大写、小写、数字和下划线'
                   }
                   
               }
           },
           password: {
               validators: {
                   notEmpty: {
                       message: '密码不能为空'
                   },
                   stringLength: {
                       min: 6,
                       max: 18,
                       message: '密码长度必须在6到18位之间'
                   },
                   regexp: {
                       regexp: /^[a-zA-Z0-9_]+$/,
                       message: '密码只能包含大写、小写、数字和下划线'
                   }
               }
           },
           repassword:{
        	   validators: {
        		   notEmpty:{
        			   message: '确认密码不能为空'
        		   },
        		   stringLength: {
        			   min: 6,
        			   max: 18,
        			   message: '确认密码不正确'
        		   },
                    identical: {
                        field: 'password',
                        message: '两次密码不同请重新输入'
                    }
        		
        	   }
           },
		   email: {
			   validators: {
				   notEmpty: {
					   message: '邮箱不能为空'
				   },
				   regexp: {
					   regexp:  /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/,
					   message: '邮箱格式有误'
				   }

			   }
		   }
		}
       
		
	});
});
$(function() {
	$("#register_btn").click(function() {
		$("#register_form").css("display", "block");
		$("#login_form").css("display", "none");
	});
	$("#back_btn").click(function() {
		$("#register_form").css("display", "none");
		$("#login_form").css("display", "block");
	});
});