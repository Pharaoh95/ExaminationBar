package com.bees.others;

import javax.servlet.http.HttpServletRequest;

public class generalUtils {

	public static BaseVo getBaseVo(HttpServletRequest request) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String si = request.getParameter("searchInfo");
		BaseVo vo = new BaseVo();
		if (si != null) {
			vo.setSearchInfo(si);
		}
		vo.setPage(page);
		vo.setRows(rows);
		return vo;
	}
}
