function sendEmailDlg(table, index) {
	var row = $(table).datagrid('getRows')[index];
	$('<div></div>').dialog({
		modal : true,
		id : 'emailDialog',
		title : '发送邮件',
		width : 320,
		height : 240,
		href : '/ExaminationBar/fixTabServlet',
		queryParams : {
			method : 'fixEmailPage',
			teacherName : row.name,
			email : row.email
		},
		onClose : function() {
			$(this).dialog('destroy');
		}
	});

}
function sendEmail() {
	var content = $('#emailContent').val();
	$.ajax({
		url : '/ExaminationBar/pushServlet',
		type : 'POST',
		data : {
			method : 'sendEmail',
			content : content
		},
		success : function(result) {
			$.messager.alert('提示', result);
			$('#emailDialog').dialog('close');
		}
	});
}
function showTab(btn) {
	btn = $(btn);
	var etab = $('#etab');
	var text = btn.linkbutton('options').text;
	var tabId = btn.linkbutton('options').id;
	if (etab.tabs('exists', text)) {
		etab.tabs('select', text);
		return;
	}

	etab.tabs('add', {
		id : tabId,
		title : text,
		href : '/ExaminationBar/fixTabServlet',
		closable : true,
		queryParams : {
			method : 'fixTab',
			tab : tabId
		}
	})
}
function logout() {
	var logoutLink = '/ExaminationBar/manageServlet?method=logout'
	$.messager.confirm('提示', '您想要退出该系统吗？', function(rs) {
		if (rs) {
			window.location.href = logoutLink;
		}
	});
}
function resetPwd() {
	$('<div></div>').dialog({
		modal : true,
		id : 'pwdSettingDlg',
		title : '用户密码修改',
		left : 184,
		top : 113,
		width : 255,
		height : 188,
		href : '/ExaminationBar/fixTabServlet',
		queryParams : {
			method : 'fixPage',
			page : 'resetPwd'
		},
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}
function refreshTable(table) {
	table.datagrid('load', {
		method : 'multiSearch'
	});
}
function initTeacherSearcher(searcher, Dg) {
	searcher.searchbox({
		searcher : function(value, name) {
			if (value == null || value == '') {
				return;
			}
			Dg.datagrid('load', {
				method : 'multiSearch',
				searchInfo : value
			});
		},
		prompt : '如：张三',
		width : 130
	});
}