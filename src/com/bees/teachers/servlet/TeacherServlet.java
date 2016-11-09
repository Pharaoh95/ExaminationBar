package com.bees.teachers.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

import com.bees.manage.bean.College;
import com.bees.others.BaseVo;
import com.bees.others.generalUtils;
import com.bees.teachers.bean.Teacher;
import com.bees.teachers.service.TeacherService;
import com.google.gson.Gson;

import net.sf.json.JSONArray;

public class TeacherServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService = new TeacherService();
	private static final Gson gson = new Gson();

	public String multiSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BaseVo vo;
		try {
			vo = generalUtils.getBaseVo(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "f:/WEB-INF/jsp/error.jsp";
		}
		response.getWriter().print(gson.toJson(teacherService.findAll(vo)));
		return null;
	}

	public void findcollege(HttpServletRequest request, HttpServletResponse response) {
		List<College> collegelist = teacherService.findcollege();
		request.getSession().setAttribute("collegeList", collegelist);
	}

	public void findbycollege(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String collegename = new String(request.getParameter("checkText1").getBytes("iso-8859-1"), "utf-8");
		List<Teacher> teacherList = teacherService.findbycollege(collegename);
		request.getSession().setAttribute("teacherList", teacherList);
	}

	public String findByStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BaseVo vo;
		try {
			vo = generalUtils.getBaseVo(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "f:/WEB-INF/jsp/error.jsp";
		}
		response.getWriter().print(gson.toJson(teacherService.findByStatus(vo)));
		return null;
	}
}
