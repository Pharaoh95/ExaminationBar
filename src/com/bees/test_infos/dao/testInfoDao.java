package com.bees.test_infos.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bees.others.BaseVo;
import com.bees.others.CustomResult;
import com.bees.test_infos.bean.testInfo;

import cn.itcast.jdbc.TxQueryRunner;

public class testInfoDao {
	private TxQueryRunner qr = new TxQueryRunner();
	private static BeanListHandler<testInfo> beanList = new BeanListHandler<testInfo>(testInfo.class);
	private static ScalarHandler scalarHandler = new ScalarHandler();
	private static CustomResult<testInfo> result = new CustomResult<>();
	String sql = null;

	public CustomResult<testInfo> findbyName(BaseVo vo) {
		try {
			sql = "select * from view_test_info where tchid1 =? OR tchid2=? LIMIT ?,?";
			String countSql = "select COUNT(*) from view_test_info where tchid1 =? OR tchid2=?";
			String teacherName = vo.getSearchInfo();
			List<testInfo> testInfos = qr.query(sql, beanList, teacherName, teacherName, vo.getIndex(), vo.getRows());
			int count = (int) (long) qr.query(countSql, scalarHandler, teacherName, teacherName);
			result.setRows(testInfos);
			result.setTotal(count);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	public CustomResult<testInfo> findAll(BaseVo vo) {
		String countSql = "select count(*) from view_test_info";
		sql = "select * from view_test_info limit ?,?";
		// 拼接开始
		// 拼接结束
		try {
			List<testInfo> testInfos = qr.query(sql, beanList, vo.getIndex(), vo.getRows());
			int count = (int) (long) qr.query(countSql, scalarHandler);
			result.setRows(testInfos);
			result.setTotal(count);
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	public List<testInfo> findbytime(String date) {
		try {
			sql = "SELECT * FROM view_test_info WHERE start_time like ?";
			return qr.query(sql, new BeanListHandler<testInfo>(testInfo.class), date + "%");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<testInfo> findByTeacherId(String id) {
		sql = "SELECT * FROM view_test_info WHERE id IN (SELECT test_info_id FROM table_test_info WHERE test_info_tid1=? OR test_info_tid2=?)";
		try {
			return qr.query(sql, new BeanListHandler<testInfo>(testInfo.class), id, id);
		} catch (SQLException e) {
			throw new RuntimeException("SQL语句执行异常");
		}
	}

	public List<testInfo> findByClassName(String _class) {
		sql = "SELECT * FROM view_test_info WHERE _class=? ORDER BY start_time";
		try {
			return qr.query(sql, new BeanListHandler<testInfo>(testInfo.class), _class);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void saveinfoFromDBF(ArrayList<testInfo> list) {
		insert_test_info(list);
		return;
	}

	private void insert_test_info(ArrayList<testInfo> list) {
		// TODO Auto-generated method stub
		for (testInfo info : list) {
			try {
				int collegeid1 = getcollegeid(info.getCollege_name1());
				int collegeid2 = getcollegeid(info.getCollege_name2());
				String Tchild1 = getTchid(info.getTchid1(), getcollegeid(info.getCollege_name1()));
				String Tchild2 = getTchid(info.getTchid2(), getcollegeid(info.getCollege_name2()));
				if (Tchild1 == null || Tchild2 == null || collegeid1 == -1 || collegeid2 == -1) {
					continue;
				}
				// 插入时设置qualified_status=1（未签名）
				System.out.println("tchild1+tchild2" + Tchild1 + Tchild2);
				String sql1 = "update table_teachers set qualified_status=0  WHERE teacher_id =? ";
				String sql = "insert into table_test_info(test_info_listentest,test_info_college,test_info_id,test_info_scode,test_info_tid1,test_info_tid2,"
						+ "test_info_class_name," + "test_info_start,test_info_end,"
						+ "test_info_office,test_info_count,"
						+ "test_info_st_endnum,test_info_address,test_info_project_name) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				qr.update(sql, info.getRemark(), info.getCollege(), info.getId(), info.getScode(), Tchild1, Tchild2,
						info.get_class(), info.getStart_time(), info.getEnd_time(), info.getOffice(), info.getCount(),
						info.getStend_num(), info.getAddress(), info.getProject());
				qr.update(sql1, Tchild1);
				System.out.println("更改成功1");
				qr.update(sql1, Tchild2);
				System.out.println("更改成功2");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private int getcollegeid(String college_name) {
		// TODO Auto-generated method stub
		int collegeid = 0;
		String sql = "select college_id from table_colleges where college_name like ?";
		try {
			Object[] arr = qr.query(sql, new ArrayHandler(),
					"%" + college_name.charAt(0) + "%" + college_name.charAt(1) + "%");
			System.out.println("arr" + arr);
			collegeid = Integer.parseInt(arr[0].toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			return -1;
		} catch (NullPointerException e) {
			return -1;
		}
		return collegeid;
	}

	private String getTchid(String teachername, int collegeid) {
		// TODO Auto-generated method stub
		System.out.println("name+id=" + teachername + collegeid);
		String teacherid = "";
		String sql = "select * from table_teachers where teacher_name =? and parent_id =?";
		try {
			Object[] arr = qr.query(sql, new ArrayHandler(), teachername, collegeid);
			teacherid = arr[0].toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
			// e.printStackTrace();
		} catch (NullPointerException e) {
			return null;
		}
		return teacherid;
	}

	public List<testInfo> findBtIndexInfo(String info) {
		sql = "SELECT * FROM view_test_info WHERE tchid1=? OR tchid2=? OR _class=?";
		try {
			return qr.query(sql, new BeanListHandler<testInfo>(testInfo.class), info, info, info);
		} catch (SQLException e) {
			throw new RuntimeException("数据库异常");
		}
	}

	public CustomResult<testInfo> findbyProj(BaseVo vo) {
		try {
			List<testInfo> testInfos = qr.query(
					"SELECT * FROM view_test_info WHERE project LIKE '%" + vo.getSearchInfo() + "%' LIMIT ?,?",
					beanList, vo.getIndex(), vo.getRows());
			int count = (int) (long) qr.query(
					"SELECT COUNT(*) FROM view_test_info WHERE project LIKE " + "'%" + vo.getSearchInfo() + "%'",
					scalarHandler);
			result.setRows(testInfos);
			result.setTotal(count);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public CustomResult<testInfo> findByCollegeId(BaseVo vo) {
		try {
			List<testInfo> testInfos = qr.query("SELECT * FROM view_test_info WHERE college=? LIMIT ?,?", beanList,
					vo.getSearchInfo(), vo.getIndex(), vo.getRows());
			int count = (int) (long) qr.query("SELECT COUNT(*) FROM view_test_info WHERE college=?", scalarHandler,
					vo.getSearchInfo());
			result.setRows(testInfos);
			result.setTotal(count);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public CustomResult<testInfo> multiSearch(String whereClause, List<Object> params, BaseVo vo) {
		String mainSql = "SELECT * FROM view_test_info WHERE 1=1";
		String countSql = "SELECT COUNT(*) FROM view_test_info WHERE 1=1";
		mainSql += whereClause + " LIMIT ?,?";
		countSql += whereClause;
		try {
			int count = (int) (long) qr.query(countSql, scalarHandler, params.toArray());
			params.add(vo.getIndex());
			params.add(vo.getRows());
			List<testInfo> testInfos = qr.query(mainSql, beanList, params.toArray());
			result.setRows(testInfos);
			result.setTotal(count);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
