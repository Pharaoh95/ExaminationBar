package com.bees.manage.servlet;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bees.manage.service.manageService;
import com.google.gson.Gson;

import cn.itcast.servlet.BaseServlet;

public class manageServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private manageService manageservice = new manageService();
	private Gson gson = new Gson();

	public String varify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 获取登录表单参数
		String id = request.getParameter("username");
		String password = request.getParameter("password");
		String remenber = request.getParameter("remenber");
		System.out.println(remenber);
		try {
			if (manageservice.varify(id, password)) {
				// 记住账号信息
				if (remenber != null) {
					Cookie cookie = new Cookie("username", id);
					cookie.setMaxAge(60 * 60 * 24 * 7);
					response.addCookie(cookie);
				}
				// 清楚错误信息
				if (session.getAttribute("error") != null) {
					session.removeAttribute("error");
				}
				session.setAttribute("Manager", id);
				return "r:/index.jsp";
			}
			// 登录失败
			return "/View/login.jsp";
		} catch (IOException e) {
			return "/WEB-INF:/jsp/error.jsp";
		}
	}

	// 用户登出
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "r:/View/login.jsp";
	}

	public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String original = request.getParameter("original");
		String current = request.getParameter("current");
		String id = (String) request.getSession().getAttribute("Manager");
		System.out.println(id + " : " + original);
		response.getWriter().print(manageservice.varifyResetPwd(id, original, current));
	}

	public void getColleges(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().print(gson.toJson(manageservice.findAllCollege()));
	}
	
}
