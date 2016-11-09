package com.bees.classes.servlet;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bees.classes.service._ClassService;

import cn.itcast.servlet.BaseServlet;

public class _ClassServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private _ClassService _classService = new _ClassService();

	public void findbycollege(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
			
			String college_text=new String(request.getParameter("college_text").getBytes("iso-8859-1"),"utf-8");
			request.getSession().setAttribute("ClassList",_classService.findbycollege(college_text));
		}
	public void findbyclassnum(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String classnum=new String(request.getParameter("classnum").getBytes("iso-8859-1"),"utf-8");
		request.getSession().setAttribute("ClassList",_classService.findbyclassnum(classnum));
	}
	
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.getSession().setAttribute("ClassList",_classService.findAll());
	}

	
	}

