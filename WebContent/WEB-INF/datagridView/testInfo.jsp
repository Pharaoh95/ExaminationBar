<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table id="testInfoDg"></table>
	<div id="testInfoDgTb" style="padding: 8px">
		<table>
			<tr>
				<td>考试名称：</td>
				<td><input id="projName"></td>
				<td width="6px"></td>
				<td>监考人：</td>
				<td><input id="teacherName"></td>
				<td width="6px"></td>
				<td>考试单位：</td>
				<td><input id="projCollege"></td>
				<td width="6px"></td>
				<td><a id="multiSearch" class="easyui-linkbutton"
					onclick="multiSearch()"
					data-options="iconCls:'icon-search',plain:true">综合查询</a></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>导入考务：</td>
				<td><input id="fb" style="width: 180px"></td>
				<td width="6px"></td>
				<td><a class="easyui-linkbutton"
					onclick="refreshTable($('#testInfoDg'))"
					data-options="iconCls:'icon-reload',plain:true">刷新</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datagridView/testInfo.js"></script>
</body>
</html>