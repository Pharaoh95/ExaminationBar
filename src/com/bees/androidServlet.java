package com.bees;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bees.classes.bean._Class;
import com.bees.classes.service._ClassService;
import com.bees.manage.bean.College;
import com.bees.manage.service.manageService;
import com.bees.teachers.bean.Teacher;
import com.bees.teachers.service.TeacherService;
import com.bees.test_infos.service.testInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.itcast.servlet.BaseServlet;

public class androidServlet extends BaseServlet {
	private testInfoService testinfoService = new testInfoService();
	private TeacherService teacherService = new TeacherService();
	private _ClassService _classService = new _ClassService();
	private manageService manageService = new manageService();

	public void findByTeacherID(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String id = new String(request.getParameter("id").getBytes("ISO-8859-1"), "UTF-8");
		String role = request.getParameter("role");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		String jsonData = gson.toJson(testinfoService.findByPersonalInfo(role, id));
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("获取考务IO流异常");
		}
	}

	public void findTeacherIds(HttpServletRequest request, HttpServletResponse response) {
		List<Teacher> list = teacherService.findRealAll();
		List<String> ids = new ArrayList<>();
		for (Teacher teacher : list) {
			ids.add(teacher.getId());
		}
		String jsonData = new Gson().toJson(ids);
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("获取IdIO流异常");
		}
	}

	public void findAllTeacher(HttpServletRequest request, HttpServletResponse response) {
		List<Teacher> list = teacherService.findRealAll();
		List<String> names = new ArrayList<>();
		for (Teacher teacher : list) {
			names.add(teacher.getName());
		}
		String jsonData = new Gson().toJson(names);
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("获取教师信息异常");
		}
	}

	public void LoadIndexInfo(HttpServletRequest request, HttpServletResponse response) {
		List<Teacher> Teachers = teacherService.findRealAll();
		List<_Class> classes = _classService.findRealAll();
		List<String> info = new ArrayList<>();
		for (Teacher teacher : Teachers) {
			info.add(teacher.getName());
		}
		for (_Class _class : classes) {
			info.add(_class.getClassnum());
		}
		String jsonData = new Gson().toJson(info);
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public void findTeacherById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String jsonData = new Gson().toJson(teacherService.findById(id));
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("根据Id获取教师信息异常");
		}
	}

	public void findByIndexInfo(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		// 处理编码问题
		String info = new String(request.getParameter("info").getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(info);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String jsonData = gson.toJson(testinfoService.findByIndexInfo(info));
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("根据索引获取考务信息异常");
		}
	}

	public void findAllCollege(HttpServletRequest request, HttpServletResponse response) {
		List<College> colleges = manageService.findChildCollege();
		List<String> collegenames = new ArrayList<>();
		for (College college : colleges) {
			collegenames.add(college.getName());
		}
		String jsonData = new Gson().toJson(collegenames);
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("获取学院信息失败");
		}
	}

	public void findClassesByCollege(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String college = new String(request.getParameter("college").getBytes("ISO-8859-1"), "UTF-8");
		List<_Class> classes = _classService.findbycollege(college);
		List<String> classnames = new ArrayList<>();
		for (_Class _class : classes) {
			classnames.add(_class.getClassnum());
		}
		String jsonData = new Gson().toJson(classnames);
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public void findByTeacherName(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		List<Teacher> list = teacherService.findbytext(name);
		String jsonData = new Gson().toJson(list.get(0));
		try {
			response.getWriter().print(jsonData);
		} catch (IOException e) {
			throw new RuntimeException("根据姓名查找教师信息出现异常");
		}
	}
}
