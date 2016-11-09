<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table id="msgInfoDg"></table>
	<div id="msgInfoDgTb" style="padding: 8px">
		<table>
			<tr>
				<td><a class="easyui-linkbutton"
					onclick="refreshTable($('#msgInfoDg'))"
					data-options="iconCls:'icon-reload',plain:true">刷新</a></td>
				<td width="6px"></td>
				<td>教师姓名：</td>
				<td><input id="teacher"></td>
				<td width="6px"></td>
				<td>状态：</td>
				<td><input id="statusCbbx" name="searchInfo" /></td>
				<td width="4px"></td>
				<td><a href="#" onclick="sendBatchMsg()"
					class="easyui-linkbutton" iconCls="icon-ok" plain="true">向该状态用户发送消息</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/datagridView/msgInfo.js"></script>
</body>
</html>