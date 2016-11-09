package com.bees.manage.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.bees.manage.bean.College;
import com.bees.manage.dao.OtherDao;

public class manageService {
	private OtherDao otherdao = new OtherDao();

	public boolean varify(String id, String password) throws IOException {
		return otherdao.findUser(id, password);
	}

	public boolean varifyResetPwd(String id, String original, String current) throws IOException {
		if (otherdao.findUser(id, original)) {
			otherdao.updateUser(id, current);
			return true;
		}
		return false;
	}

	public List<College> findAllCollege() {
		return otherdao.findAllCollege();
	}

	public List<College> findChildCollege() {
		return otherdao.findChildCollege();
	}
}
