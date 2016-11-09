package com.bees.teachers.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bees.manage.bean.College;
import com.bees.others.BaseVo;
import com.bees.others.CustomResult;
import com.bees.teachers.bean.Teacher;

import cn.itcast.jdbc.TxQueryRunner;

public class TeacherDao {
	static QueryRunner qr = new TxQueryRunner();
	private static CustomResult<Teacher> result = new CustomResult<>();
	private static ScalarHandler scalarHandler = new ScalarHandler();
	private static BeanListHandler<Teacher> beanListHandler = new BeanListHandler<>(Teacher.class);

	public CustomResult<Teacher> findAll(BaseVo vo) {
		String countSql = "SELECT COUNT(*) FROM view_teachers";
		String sql = "SELECT * FROM view_teachers LIMIT ?,?";
		try {
			int total = (int) (long) qr.query(countSql, new ScalarHandler());
			List<Teacher> teachers = qr.query(sql, new BeanListHandler<Teacher>(Teacher.class), vo.getIndex(),
					vo.getRows());
			result.setRows(teachers);
			result.setTotal(total);
			return result;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Teacher> findRealAll() {
		String sql = "SELECT * FROM view_teachers ";
		try {
			return qr.query(sql, new BeanListHandler<Teacher>(Teacher.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<College> findcollege() {
		String sql = "SELECT * FROM view_colleges";
		try {
			return qr.query(sql, new BeanListHandler<College>(College.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Teacher> findbytext(String teacher_text) {
		// ID号查询还未完成：需要对传过来的数据进行判断
		String sql = "SELECT * FROM view_teachers WHERE name=? or id=?";
		try {
			return qr.query(sql, new BeanListHandler<Teacher>(Teacher.class), teacher_text, teacher_text);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Teacher> findbycollege(String collegename) {
		String sql = "SELECT * FROM view_teachers WHERE college=? ";
		try {
			return qr.query(sql, new BeanListHandler<Teacher>(Teacher.class), collegename);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public Teacher findById(String id) {

		String sql = "SELECT * FROM view_teachers WHERE id=?";
		try {
			return qr.query(sql, new BeanHandler<Teacher>(Teacher.class), id);
		} catch (SQLException e) {
			throw new RuntimeException("SQL执行异常");
		}
	}

	public CustomResult<Teacher> findByName(BaseVo vo) {
		String si = vo.getSearchInfo();
		String countSql = "SELECT COUNT(*) FROM view_teachers WHERE name LIKE '%" + si + "%' OR id=?";
		String sql = "SELECT * FROM view_teachers WHERE name LIKE '%" + si + "%' OR id=? LIMIT ?,?";
		try {
			int total = (int) (long) qr.query(countSql, scalarHandler, si);
			List<Teacher> teachers = qr.query(sql, beanListHandler, si, vo.getIndex(), vo.getRows());
			result.setRows(teachers);
			result.setTotal(total);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public CustomResult<Teacher> findByStatus(BaseVo vo) {
		String si = vo.getSearchInfo();
		String countSql = "SELECT COUNT(*) FROM view_teachers WHERE status=?";
		String sql = "SELECT * FROM view_teachers WHERE status=? LIMIT ?,?";
		try {
			int total = (int) (long) qr.query(countSql, scalarHandler, si);
			List<Teacher> teachers = qr.query(sql, beanListHandler, si, vo.getIndex(), vo.getRows());
			result.setRows(teachers);
			result.setTotal(total);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}