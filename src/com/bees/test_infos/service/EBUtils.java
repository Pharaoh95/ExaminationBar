package com.bees.test_infos.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;

import com.bees.test_infos.bean.testInfo;
import com.bees.test_infos.dao.testInfoDao;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFReader;

import cn.itcast.jdbc.TxQueryRunner;

public class EBUtils {
	/**
	 * 全部数据已解析 教师Id通过testInfo.getTchid1和getCollege_name1获取 Tchid2同理,查数据库的语句形如：
	 * Select * from view_teachers where college %会%计% and name = 彭赤兵
	 * 
	 * @param in
	 * @return
	 */
	public static ArrayList<testInfo> DBFParser(InputStream in) {
		System.out.println("in" + in);
		ArrayList<testInfo> testInfolist = new ArrayList<>();
		Object[] objs = null;
		try {
			DBFReader dbfreader = new DBFReader(in);
			while ((objs = dbfreader.nextRecord()) != null) {
				testInfo ti = new testInfo();
				// 设置ID
				ti.setId(UUID.randomUUID().toString().replace("-", ""));
				// 班级名称解析
				ti.set_class(objs[0].toString().trim());
				// 人数解析
				double count = (double) objs[1];
				ti.setCount((int) count);
				// 地点解析
				ti.setAddress(objs[2].toString().trim());
				// 学号起止解析字符串
				ti.setStend_num(objs[3].toString());
				// 学号起止解析
				ti.setStart_num(objs[4].toString().trim());
				ti.setEnd_num(objs[5].toString().trim());
				// objs[7]数据库相关操作

				ti.setCollege(objs[7].toString().trim());
				// 校区代码解析
				double scode = (double) objs[6];
				ti.setScode((int) scode);
				ti.setSchooldepart(objs[18].toString());
				// 考务办公室解析
				ti.setOffice(objs[19].toString());

				// 第一个考务信息解析
				String proj = objs[8].toString().trim();
				// 监考老师解析,根据两项信息去查数据库获取教师Id
				String tname1 = objs[9].toString().trim();
				String tcname1 = objs[10].toString().trim();
				String tname2 = objs[11].toString().trim();
				String tcname2 = objs[12].toString().trim();
				ti.setTchid1(tname1);
				ti.setTchid2(tname2);
				ti.setCollege_name1(tcname1);
				ti.setCollege_name2(tcname2);

				String proj2 = objs[13].toString().trim();
				if (!proj.equals("")) {
					if (proj.contains("大学英语")) {
						ti.setRemark(objs[22].toString().trim());
					}
					ti.setProject(proj);
					// 监考员信息1匹配数据库代码写下面
					//
					// 并set起始时间段1
					// 新建第二个考务信息
					String time = objs[20].toString().trim();
					EBUtils.ParseDate(time, ti);
					testInfolist.add(ti);
					if (!proj2.equals("")) {
						testInfo ti2 = (testInfo) ti.clone();
						// 第二场考试的教师Id查数据库
						String tname3 = objs[14].toString().trim();
						String tcname3 = objs[15].toString().trim();
						String tname4 = objs[16].toString().trim();
						String tcname4 = objs[17].toString().trim();
						ti2.setTchid1(tname3);
						ti2.setTchid2(tname4);
						ti2.setCollege_name1(tcname3);
						ti2.setCollege_name2(tcname4);
						// 第二场考试的备注消息设置
						ti2.setRemark(null);
						if (proj2.contains("大学英语")) {
							ti2.setRemark(objs[22].toString().trim());
						}
						ti2.setId(UUID.randomUUID().toString().replace("-", ""));
						ti2.setProject(proj2);
						// 监考员信息2匹配数据库代码写下面
						//
						// 并set起始时间段2
						String time2 = objs[21].toString().trim();
						EBUtils.ParseDate(time2, ti2);
						testInfolist.add(ti2);
					}
				} else {
					if (proj.contains("大学英语")) {
						ti.setRemark(objs[22].toString().trim());
					}
					// 只有一个考务信息但是在第二栏
					ti.setProject(proj2);
					String time = objs[21].toString().trim();
					EBUtils.ParseDate(time, ti);
					testInfolist.add(ti);
				}
			}
		} catch (DBFException e) {
			System.out.println("DBF解析器异常");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println("关闭流异常");
				}
			}
		}
		return testInfolist;
	}

	private static void ParseDate(String time, testInfo ti) {
		String[] state = time.split("-");
		String starttime = state[0];
		String endtime = state[1].trim();
		// 开始时间解析
		String[] spstr1 = starttime.split("月");
		int month = Integer.parseInt(spstr1[0]);
		// 6日上午9：00
		String[] spstr2 = spstr1[1].split("日");
		int date = Integer.parseInt(spstr2[0]);
		// 上午9：00
		int hour = 0;
		int minute = 0;
		// 小时解析
		String spstr3 = spstr2[1].split("午")[1];
		String[] spstr4 = spstr3.split("：");
		hour = Integer.parseInt(spstr4[0]);
		minute = Integer.parseInt(spstr4[1]);

		Calendar c1 = Calendar.getInstance();
		c1.set(2016, month - 1, date, hour, minute, 0);
		// 结束时间解析(11：00)
		int hour2 = Integer.parseInt(endtime.split("：")[0]);
		int minute2 = Integer.parseInt(endtime.split("：")[1]);
		Calendar c2 = Calendar.getInstance();
		c2.set(2016, month - 1, date, hour2, minute2, 0);
		// 时间解析完成设置开始和结束的时间
		ti.setStart_time(c1.getTime());
		ti.setEnd_time(c2.getTime());
	}
}
