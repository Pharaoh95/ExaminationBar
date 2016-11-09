package com.bees.test_infos.service;

import java.util.ArrayList;
import java.util.List;

import com.bees.others.BaseVo;
import com.bees.others.CustomResult;
import com.bees.test_infos.bean.testInfo;
import com.bees.test_infos.dao.testInfoDao;
import com.google.gson.JsonElement;

public class testInfoService {
	private testInfoDao testinfoDao = new testInfoDao();
	private List<Object> sqlParams = new ArrayList<>(7);
	// private List<String> countParams = new ArrayList<>(4);

	public List<testInfo> findbytime(String date) {
		return testinfoDao.findbytime(date);
	}

	public List<testInfo> findByPersonalInfo(String role, String id) {
		if ("student".equals(role)) {
			return testinfoDao.findByClassName(id);
		} else {
			return testinfoDao.findByTeacherId(id);
		}
	}

	public void saveinfoFromDBF(ArrayList<testInfo> list) {
		// 调用Dao方法，想数据库插入数据
		testinfoDao.saveinfoFromDBF(list);
		return;
	}

	public List<testInfo> findByIndexInfo(String info) {
		return testinfoDao.findBtIndexInfo(info);
	}

	public CustomResult<testInfo> multiSearch(String teacherName, String projName, String college, BaseVo vo) {
		// sql参数初始化
		sqlParams.clear();
		String whClause = "";
		// 参数拼接
		if (teacherName != null && !"".equals(teacherName.trim())) {
			whClause += " AND (tchid1 =? OR tchid2=?)";
			sqlParams.add(teacherName);
			sqlParams.add(teacherName);
		}
		if (projName != null && !"".equals(projName.trim())) {
			whClause += " AND project LIKE '%" + projName + "%'";
		}
		if (college != null && !"".equals(college.trim())) {
			whClause += " AND college=?";
			sqlParams.add(college);
		}
		// 拼接完成
		return testinfoDao.multiSearch(whClause, sqlParams, vo);
	}
}
