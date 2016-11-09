<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/index.js"></script>
<title>考务信息发布中心</title>
</head>
<body class="easyui-layout">
	<!-- 北侧系统标题栏-->
	<div data-options="region:'north',split:true,collapsible:false"
		style="height: 80px; text-align: center; background: #CFFCFC">
		<h1>九 江 学 院 考 务 信 息 发 布 后 台</h1>
	</div>

	<!-- 西侧分类导航栏-->
	<div data-options="region:'west',title:'导航栏',split:true"
		style="width: 177px;">
		<div style="padding: 5px; margin-top: 2px" align="center">
			<a id='testInfo'
				style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-search'"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid"
				onclick="showTab(this)"> 考务信息 </a>
		</div>
		<div style="padding: 5px;" align="center">
			<a id='msgInfo'
				style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-ok'"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid"
				onclick="showTab(this)"> 消息送达情况</a>
		</div>
		<div style="padding: 5px;" align="center">
			<a id='teacherInfo'
				style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-man'"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid"
				onclick="showTab(this)"> 教师信息 </a>
		</div>
		<!-- <div style="padding: 5px;" align="center">
			<a id='classInfo'
				style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-add'"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid"
				onclick="showTab(this)"> 班级信息 </a>
		</div> -->
		<div style="padding: 5px;" align="center">
			<a id='help'
				style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-tip'"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid"
				onclick="showTab(this)">使用帮助 </a>
		</div>
		<div style="padding: 5px;" align="center">
			<a style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-lock'" onclick="resetPwd()"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid">
				密码修改 </a>
		</div>
		<div style="padding: 5px;" align="center">
			<a style="width: 150px; text-align: left; font-family: 微软雅黑;"
				data-options="plain:true,iconCls:'icon-back'" onclick="logout()"
				class="easyui-linkbutton l-btn l-btn-small l-btn-plain easyui-fluid">
				退出系统 </a>
		</div>
	</div>
	<!-- 中央数据表格及操作区-->
	<div data-options="region:'center',title:'信息管理'"
		style="padding: 5px; background: #eee;">
		<div id="etab" class="easyui-tabs" data-options="fit:true">
			<div title="使用帮助"
				data-options="href:'/ExaminationBar/fixTabServlet?method=fixTab&tab=help',closable:true">
			</div>
		</div>
	</div>
	<!-- 南侧授权说明 -->
	<div data-options="region:'south'"
		style="padding: 5px; text-align: center">
		<span style="font-family: Arial;"> &#169;</span> jju
		www.pharaoh1995.cn
	</div>
	<!-- 放在这里延缓加载带来的延迟 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/easyui/themes/icon.css" />
</body>
</html>