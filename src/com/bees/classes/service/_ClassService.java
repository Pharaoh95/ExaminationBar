package com.bees.classes.service;

import java.util.List;

import com.bees.classes.bean._Class;
import com.bees.classes.dao._ClassDao;

public class _ClassService {
	private _ClassDao _classDao = new _ClassDao();
	public List<_Class> findAll(){
		return _classDao.findAll();
	}
	public List<_Class> findRealAll(){
		return _classDao.findRealAll();
	}
	public List<_Class> findbycollege(String college_text) {
		// TODO Auto-generated method stub
		return _classDao.findbycollege(college_text);
	}
	public List<_Class> findbyclassnum(String classnum) {
		// TODO Auto-generated method stub
		return _classDao.findbyclassnum(classnum);
	}
}
