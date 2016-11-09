package com.bees.manage.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bees.manage.bean.College;

import cn.itcast.jdbc.TxQueryRunner;

public class OtherDao {
	private QueryRunner qr = new TxQueryRunner();


	public boolean findUser(String id, String password) {
		try {
			int result = (int) (long) qr.query("SELECT COUNT(*) FROM table_user WHERE id=? AND password=?",
					new ScalarHandler(), id, password);
			return result >= 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	public List<College> findChildCollege() {
		try {
			return qr.query("SELECT * FROM view_colleges WHERE depart = ?", new BeanListHandler<College>(College.class),
					"二级学院");
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<College> findAllCollege() {
		try {
			return qr.query("SELECT * FROM view_colleges", new BeanListHandler<College>(College.class));
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public int updateUser(String id, String password) {
		try {
			return qr.update("UPDATE table_user SET password=? WHERE id=?", password, id);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}
}
