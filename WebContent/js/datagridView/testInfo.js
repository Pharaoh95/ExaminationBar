$(function() {
	var testInfoDg = $('#testInfoDg');
	var projName = $('#projName');
	var teacherName = $('#teacherName');
	var projCollege = $('#projCollege');
	var fb = $('#fb');
	fb.filebox({
		prompt : 'dbf格式',
		buttonText : '选择文件',
	})
	projCollege.combobox({
		width : 140,
		url : '/ExaminationBar/manageServlet?method=getColleges',
		valueField : 'id',
		textField : 'name',
		onSelect : function(record) {
			projCollege.combobox('setValue', record.id);
			// testInfoDg.datagrid('load', {
			// method : 'findByCollege',
			// searchInfo : record.name
			// })
		}
	});
	teacherName.searchbox({
		searcher : function(value, name) {
			if (value == null || value == '') {
				return;
			}
			testInfoDg.datagrid('load', {
				method : 'multiSearch',
				teacherName : value
			});
		},
		prompt : '如：张三',
		width : 130
	});
	projName.searchbox({
		searcher : function(value, name) {
			if (value == null || value == '') {
				return;
			}
			testInfoDg.datagrid('load', {
				method : 'multiSearch',
				projName : value
			})
		},
		prompt : '如：数据结构',
		width : 155
	});

	testInfoDg.datagrid({
		url : '/ExaminationBar/testInfoServlet',
		width : 100,
		fit : true,
		columns : [ [ {
			field : 'id',
			checkbox : true,
			align : 'center',
			width : 10,
		}, {
			title : '考试项目',
			field : 'project',
			align : 'center',
			width : 140,
		}, {
			title : '考试地点',
			field : 'address',
			align : 'center',
			width : 80,
		}, {
			title : '考试二级学院',
			align : 'center',
			field : 'college',
			width : 150,
		}, {
			title : '考试班级',
			align : 'center',
			field : '_class',
			width : 80,
		}, {
			title : '考试人数',
			align : 'center',
			field : 'count',
			width : 70,
		}, {
			title : '学号起止',
			align : 'center',
			field : 'stend_num',
			width : 60,
		}, {
			title : '开始时间',
			align : 'center',
			field : 'start_time',
			width : 140,
		}, {
			title : '结束时间',
			align : 'center',
			field : 'end_time',
			width : 140,
		}, {
			title : '监考老师1',
			align : 'center',
			field : 'tchid1',
			width : 80,
		}, {
			title : '所属单位',
			align : 'center',
			field : 'college_name1',
			width : 120,
		}, {
			title : '监考老师2',
			align : 'center',
			field : 'tchid2',
			width : 80,
		}, {
			title : '所属单位',
			align : 'center',
			field : 'college_name2',
			width : 120,
		} ] ],
		striped : true,
		rownumbers : true,
		pagination : true,
		pageList : [ 12 ],
		pageSize : 12,
		toolbar : '#testInfoDgTb',
		queryParams : {
			method : 'multiSearch',
		}
	});
})
function multiSearch() {
	$('#testInfoDg').datagrid('load', {
		method : 'multiSearch',
		teacherName : $('#teacherName').val(),
		college : $('#projCollege').combobox('getText'),
		projName : $('#projName').val(),
	})
}