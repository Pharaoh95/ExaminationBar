package com.bees.test_infos.bean;

import java.util.Date;

public class testInfo implements Cloneable {
	private String id;
	private String college;
	private String _class;
	private String project;
	private int scode;
	private Date start_time;
	private Date end_time;
	private String remark;
	private String office;
	private int count;
	private String stend_num;
	private String major_name;

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	private String tchid1;
	private String tchid2;
	private String college_name1;
	private String college_name2;

	public String getTchid1() {
		return tchid1;
	}

	public void setTchid1(String tchid1) {
		this.tchid1 = tchid1;
	}

	public String getTchid2() {
		return tchid2;
	}

	public String getCollege_name1() {
		return college_name1;
	}

	public void setCollege_name1(String college_name1) {
		this.college_name1 = college_name1;
	}

	public String getCollege_name2() {
		return college_name2;
	}

	public void setCollege_name2(String college_name2) {
		this.college_name2 = college_name2;
	}

	public void setTchid2(String tchid2) {
		this.tchid2 = tchid2;
	}

	private String address;
	private String start_num;
	private String end_num;
	private String schooldepart;

	public String getSchooldepart() {
		return schooldepart;
	}

	public void setSchooldepart(String schooldepart) {
		this.schooldepart = schooldepart;
	}

	public String getStart_num() {
		return start_num;
	}

	public void setStart_num(String start_num) {
		this.start_num = start_num;
	}

	public String getEnd_num() {
		return end_num;
	}

	public void setEnd_num(String end_num) {
		this.end_num = end_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "testInfo [id=" + id + ", college=" + college + ", _class=" + _class + ", project=" + project
				+ ", scode=" + scode + ", start_time=" + start_time + ", end_time=" + end_time + ", remark=" + remark
				+ ", office=" + office + ", count=" + count + ", stend_num=" + stend_num + ", major_name=" + major_name
				+ ", tchid1=" + tchid1 + ", tchid2=" + tchid2 + ", college_name1=" + college_name1 + ", college_name2="
				+ college_name2 + ", address=" + address + ", start_num=" + start_num + ", end_num=" + end_num
				+ ", schooldepart=" + schooldepart + "]";
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public int getScode() {
		return scode;
	}

	public void setScode(int scode) {
		this.scode = scode;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStend_num() {
		return stend_num;
	}

	public void setStend_num(String stend_num) {
		this.stend_num = stend_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Object clone() {
		testInfo ti = new testInfo();
		try {
			ti = (testInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("无法复制对象异常");
		}
		return ti;
	}
}
