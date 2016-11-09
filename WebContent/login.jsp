<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
<title>工蜂考务吧管理员登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<link href="/ExaminationBar/css/index.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
body {
	width: 1366px;
	height: 350px;
	background: url(/ExaminationBar/image/login.gif);
}
</style>
</head>
<%
	Cookie[] cookies = request.getCookies();
	String uname = "";
	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("username")) {
				uname = c.getValue();
				break;
			}
		}
		System.out.println(uname);
	}
%>
<body>
	<div id="login">
		<form action="<c:url value="/manageServlet"/>" method="post">
			<ul>
				<li id="user"><img src="/ExaminationBar/image/people.gif"><input
					required="required" name="username" value="<%=uname%>" /></li>
				<li id="password"><img
					src="/ExaminationBar/image/key.gif" /><input type="password"
					name="password" required="required" pattern="^[A-Za-z0-9]+$"></input></li>
				<li id="jizhumima">
					<div class="checkboxFive" id="five">
						<input type="checkbox" value="1" id="checkboxFiveInput"
							name="remenber" /> <label for="checkboxFiveInput"></label>
					</div>

					<div class="checkboxFive" id="six">
						<input type="checkbox" value="1" id="checkboxSixInput" name="" />
						<label for="checkboxSixInput"></label>
					</div>记住密码&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; 自动登录
				</li>

				<li id="button"><input type="submit" value="登录"></input><input
					type="reset" value="取消"></input></li>
			</ul>
			<input type="hidden" name="method" value="varify" />
		</form>
	</div>
</body>
</html>