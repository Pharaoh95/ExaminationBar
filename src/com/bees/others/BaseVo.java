package com.bees.others;

/**
 * Vo����,Ŀǰ�ͷ�ҳͨ��
 * 
 * @author Pharaoh
 *
 */
public class BaseVo {
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String searchInfo;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "BaseVo [page=" + page + ", rows=" + rows + ", order=" + order + ", sort=" + sort + ", searchInfo="
				+ searchInfo + "]";
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getIndex() {
		int page = this.page;
		return (page - 1) * rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSearchInfo() {
		return searchInfo;
	}

	public void setSearchInfo(String searchInfo) {
		this.searchInfo = searchInfo;
	}
}
