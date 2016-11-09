$(function() {
	var msgInfoDg = $('#msgInfoDg');
	var statusCbbx = $('#statusCbbx');
	initTeacherSearcher($('#teacher'), msgInfoDg);
	statusCbbx.combobox({
		valueField : 'id',
		textField : 'text',
		width : 70,
		panelHeight : 64,
		value : 2,
		data : [ {
			id : 2,
			text : '全部'
		}, {
			id : 1,
			text : '已接收'
		}, {
			id : 0,
			text : '未送达'
		} ],
		onSelect : function(record) {
			if (record.id == 2) {
				msgInfoDg.datagrid('load', {
					method : 'multiSearch',
				});
				return;
			}
			msgInfoDg.datagrid('load', {
				method : 'findByStatus',
				searchInfo : record.id
			});
		}
	})
	msgInfoDg.datagrid({
		url : '/ExaminationBar/TeacherServlet',
		width : 100,
		fit : true,
		columns : [ [
				{
					checkbox : true,
					field : 'sel',
					align : 'center',
					width : 90,
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
					title : '电子邮箱',
					align : 'center',
					field : 'email',
					width : 160,
				},
				{
					title : '联系电话',
					align : 'center',
					field : 'phone_num',
					width : 140,
				},
				{
					title : '消息送达情况',
					align : 'center',
					field : 'status',
					width : 100,
					formatter : function(value, row, index) {
						if (row.status) {
							return '已接收';
						}
						return '未送达';
					},
					styler : function(value, row, index) {
						if (!row.status) {
							return 'color:red;';
						}
					}
				},
				{
					title : '操作1',
					field : 'opt1',
					align : 'center',
					formatter : function(value, row, index) {
						return '<a href="#" onclick="sendEmailDlg(\'#msgInfoDg\','
								+ index + ')">发送邮件</a>';
					},
					width : 80,
				},
				{
					title : '操作2',
					field : 'opt2',
					align : 'center',
					formatter : function(value, row, index) {
						return '<a href="#" onclick="sendMsg(' + index
								+ ')">发送APP消息</a>';
					},
					width : 100,
				}, ] ],
		striped : true,
		rownumbers : true,
		pagination : true,
		pageList : [ 14 ],
		pageSize : 14,
		toolbar : '#msgInfoDgTb',
		queryParams : {
			method : 'multiSearch',
		}
	})
})
function sendBatchMsg() {
	$.ajax({
		url : '/ExaminationBar/pushServlet',
		type : 'POST',
		data : {
			method : 'pushBatch',
			status : $('#statusCbbx').combobox('getValue')
		},
		success : function(result) {
			$.messager.show({
				title : '提示',
				msg : result,
				timeout : 1500,
				showType : 'slide',
			});
		}
	});
}
function sendMsg(index) {
	var row = $('#msgInfoDg').datagrid('getRows')[index];
	$.ajax({
		url : '/ExaminationBar/pushServlet',
		type : 'POST',
		data : {
			method : 'pushForOne',
			teacherId : row.id,
			teacherName : row.name
		},
		success : function(result) {
			$.messager.alert('提示', result);
		}
	});
}
