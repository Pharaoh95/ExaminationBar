package com.bees.teachers.service;

import java.util.List;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.bees.projectConstant;
import com.bees.manage.bean.College;
import com.bees.others.BaseVo;
import com.bees.others.CustomResult;
import com.bees.teachers.bean.Teacher;
import com.bees.teachers.dao.TeacherDao;
import com.google.gson.JsonElement;

public class TeacherService {
	private TeacherDao teacherDao = new TeacherDao();
	private SimpleEmail email;

	public CustomResult<Teacher> findAll(BaseVo baseVo) {
		String si = baseVo.getSearchInfo();
		if (si != null && !"".equals(si.trim())) {
			return teacherDao.findByName(baseVo);
		}
		return teacherDao.findAll(baseVo);
	}

	public List<College> findcollege() {
		// TODO Auto-generated method stub
		return teacherDao.findcollege();
	}

	public SimpleEmail getEmailObj() throws EmailException {
		if (email == null) {
			email = new SimpleEmail();
			email.setHostName("smtp.163.com");
			email.setCharset("utf-8");
			email.setFrom(projectConstant.serviceEmailAdr, projectConstant.serviceEmailName);
			email.setAuthentication(projectConstant.serviceEmailAdr, projectConstant.serviceEmailAth);
			email.setSubject("工蜂考务吧");
			return email;
		}
		email.getToAddresses().clear();
		return email;
	}

	public List<Teacher> findbytext(String teacher_text) {
		// TODO Auto-generated method stub
		return teacherDao.findbytext(teacher_text);
	}

	public List<Teacher> findbycollege(String collegename) {
		// TODO Auto-generated method stub
		return teacherDao.findbycollege(collegename);
	}

	public Teacher findById(String id) {
		return teacherDao.findById(id);
	}

	public List<Teacher> findRealAll() {
		return teacherDao.findRealAll();
	}

	public CustomResult<Teacher> findByStatus(BaseVo vo) {
		return teacherDao.findByStatus(vo);
	}
}
