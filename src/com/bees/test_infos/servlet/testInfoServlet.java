package com.bees.test_infos.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bees.others.BaseVo;
import com.bees.others.generalUtils;
import com.bees.test_infos.bean.testInfo;
import com.bees.test_infos.service.EBUtils;
import com.bees.test_infos.service.testInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.itcast.servlet.BaseServlet;

public class testInfoServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private testInfoService testinfoService = new testInfoService();
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy年MM月dd日 HH:mm").create();

	public String multiSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String teacherName = request.getParameter("teacherName");
		String projName = request.getParameter("projName");
		String college = request.getParameter("college");
		BaseVo vo;
		try {
			vo = generalUtils.getBaseVo(request);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "f:/WEB-INF/jsp/error.jsp";
		}
		response.getWriter().print(gson.toJson(testinfoService.multiSearch(teacherName, projName, college, vo)));
		return null;
	}

	public void readExcelDateall(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, FileNotFoundException {
		String filepath = new String(request.getParameter("filepath").getBytes("iso-8859-1"), "utf-8");
		System.out.println("filepath" + filepath);
		File file = new File(filepath);
		FileInputStream fs = new FileInputStream(file);
		ArrayList<testInfo> list = EBUtils.DBFParser(fs);
		testinfoService.saveinfoFromDBF(list);
	}

}
