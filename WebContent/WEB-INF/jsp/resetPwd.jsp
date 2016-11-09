<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div style="padding: 4px; margin-top: 3px">
		<table>
			<tr>
				<td>原密码：</td>
				<td><input id="original" type="password" style="width: 150px"></td>
			</tr>
		</table>
	</div>
	<div style="padding: 4px;">
		<table>
			<tr>
				<td>新密码：</td>
				<td><input id="current" type="password" style="width: 150px"></td>
			</tr>
		</table>
	</div>
	<div style="padding: 4px;">
		<table>
			<tr>
				<td>再一遍：</td>
				<td><input id="again" type="password" style="width: 150px"></td>
			</tr>
		</table>
	</div>
	<div style="padding: 4px;">
		<table align="center">
			<tr>
				<td><a class="easyui-linkbutton" onclick="submit()">确认修改</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		$(function() {
			initPwdInput($('#original'));
			initPwdInput($('#current'));
			initPwdInput($('#again'));
		})
		function submit() {
			var original = $('#original');
			var current = $('#current');
			var again = $('#again');
			if (!original.validatebox('isValid')) {
				original.focus();
				return;
			}
			if (!current.validatebox('isValid')) {
				current.focus();
				return;
			}
			if (!again.validatebox('isValid')) {
				again.focus();
				return;
			} else {
				if (eval(current.val()) == eval(again.val())) {
					resetPassword();
				} else {
					$.messager.alert('提示', '密码重复输入时错误！', 'info');
					current.val('');
					again.val('');
				}
			}
		}
		function resetPassword() {
			var original = $('#original');
			var again = $('#again');
			var current = $('#current');
			$.ajax({
				type : 'POST',
				url : '/ExaminationBar/manageServlet?method=resetPassword',
				data : {
					original : original.val(),
					current : again.val(),
				},
				success : function(result) {
					console.info(result);
					if (result == 'true') {
						$.messager.alert('提示', '修改成功！', 'info');
						$('#pwdSettingDlg').dialog('close');
					} else {
						$.messager.alert('提示', '原密码错误！', 'info');
						original.val('');
					}
				}
			});
		}
		function initPwdInput(input) {
			input.validatebox({
				validType : 'length[6,12]',
				invalidMessage : '密码在 6-12 位',
				required : true,
			});
		}
	</script>
</body>
</html>