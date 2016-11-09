package com.bees.classes.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bees.classes.bean._Class;

import cn.itcast.jdbc.TxQueryRunner;

public class _ClassDao {
	QueryRunner qr = new TxQueryRunner();
	/**
	 * 测试完记得加 Limit 10
	 * @return
	 */
	public List<_Class> findAll() {
		try {
			String sql = "SELECT * FROM view_classes ";
			return qr.query(sql, new BeanListHandler<_Class>(_Class.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public List<_Class> findRealAll(){
		try {
			String sql = "SELECT * FROM view_classes ";
			return qr.query(sql, new BeanListHandler<_Class>(_Class.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	public List<_Class> findbycollege(String college_text) {
		try {
			String sql = "SELECT * FROM view_classes WHERE college=? ";
			return qr.query(sql, new BeanListHandler<_Class>(_Class.class),
					college_text);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<_Class> findbyclassnum(String classnum) {
		try {
			String sql = "SELECT * FROM view_classes WHERE classnum=? ";
			return qr.query(sql, new BeanListHandler<_Class>(_Class.class),
					classnum);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
