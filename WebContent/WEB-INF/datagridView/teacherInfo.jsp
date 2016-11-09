<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table id="teacherDg"></table>
	<div id="teacherDgTb" style="padding: 6px">
		<table>
			<tr>
				<td><a class="easyui-linkbutton"
					onclick="refreshTable($('#teacherDg'))"
					data-options="iconCls:'icon-reload',plain:true">刷新</a></td>
				<td width="6px"></td>
				<td>教师姓名：</td>
				<td><input id="teacher2"></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datagridView/teacherInfo.js"></script>
</body>
</html>