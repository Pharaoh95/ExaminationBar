$(function() {
	var teacherDg = $('#teacherDg');
	initTeacherSearcher($('#teacher2'), teacherDg);
	teacherDg.datagrid({
		url : '/ExaminationBar/TeacherServlet',
		width : 100,
		fit : true,
		columns : [ [
				{
					field : 'sel',
					align : 'center',
					width : 90,
					checkbox : true
				},
				{
					title : '教师职工号',
					field : 'id',
					align : 'center',
					width : 90,
				},
				{
					title : '教程姓名',
					field : 'name',
					align : 'center',
					width : 90,
				},
				{
					title : '所属单位',
					field : 'college',
					align : 'center',
					width : 140,
				},
				{
					title : '联系电话',
					align : 'center',
					field : 'phone_num',
					width : 140,
				},
				{
					title : '电子邮箱',
					align : 'center',
					field : 'email',
					width : 150,
				},
				{
					title : '操作',
					field : 'opt2',
					align : 'center',
					formatter : function(value, row, index) {
						return '<a href="#" onclick="sendEmailDlg(\'#teacherDg\','
						+ index + ')">发送邮件</a>';
					},
					width : 100,
				}, ] ],
		striped : true,
		rownumbers : true,
		pagination : true,
		pageList : [ 16 ],
		pageSize : 16,
		toolbar : '#teacherDgTb',
		queryParams : {
			method : 'multiSearch',
		}
	});
})