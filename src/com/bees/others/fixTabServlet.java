package com.bees.others;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.servlet.BaseServlet;

public class fixTabServlet extends BaseServlet {
	public String fixTab(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tab = request.getParameter("tab");
		return "f:/WEB-INF/datagridView/" + tab + ".jsp";
	}

	public String fixPage(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		return "f:/WEB-INF/jsp/" + page + ".jsp";
	}

	public String fixEmailPage(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String name = new String(request.getParameter("teacherName").getBytes("ISO-8859-1"), "UTF-8");
		String email = request.getParameter("email");
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("teacherName", name);
		return "f:/WEB-INF/jsp/sendMail.jsp";
	}
}
