var moduleCode = '03001';
var departmentId = 0;

function initFun() {
	if (secure.find) {
		dialog = BootstrapDialog.loading();
		findListInfo();
	}
	if (!secure.add) {
		$('button.add-btn').remove();
	}
	if (secure.add) {
		$('button.add-btn').removeClass('hide');
	}
}
/*
 * 获取部门列表 
 * te5l.com [K]
 */
function findListInfo() {
	$.post('mgr/department/findDepartmentList', {
		page : page,
		searchValue : $('.searchInput').val()
	}, function(data) {
		dialog.close();
		var tbody = $('tbody.tbody').empty();
		if (!$.isSuccess(data)) return;
		$.each(data.body, function(i, v) {
			$('<tr></tr>')
			.append($('<td></td>').append(v.deptId))
			.append($('<td></td>').append(v.deptName))
			.append($('<td></td>').append(v.time))
			.append($('<td></td>').append(v.creator))
			.append($('<td></td>').append(v.fullName))
			.append($('<td></td>').append(v.deptDescription))
			.append($('<td></td>').append(analyzeBtns(v)))
			.appendTo(tbody);
		});
	}, 'json');
	$.post('mgr/department/findDepartmentCount', {
		page : page,
		searchValue : $('.searchInput').val()
	}, function(data) {
		$.analysisPage(data.body);
	}, 'json');
}
/*
 * 分析操作按钮 
 * te5l.com [K]
 */
function analyzeBtns(v) {
	var btns = "";
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+ v.deptId + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "";
	btns += secure.modify ? "<button type='button' class='btn btn-success btn-xs' onclick='showManagerList(" + v.principal + "," + v.deptId + ")'><span class='glyphicon glyphicon-pencil'></span>设置经理</button>" : "";
	btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete(" + v.deptId + ")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "";
	return btns;
}
/*
 * 显示部门员工列表, 选择经理 
 * te5l.com [K]
 */
function showManagerList(principal, deptId) {
	if (!deptId) return;
	dialog = BootstrapDialog.loading();
	departmentId = deptId;
	$.getJSON('mgr/department/findDeptEmplList', {deptId : deptId}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		var list = $('select.principal-list').empty().append('<option value=0>请选择...</option>');
		$.each(data.body, function(i, v) {
			$('<option value=' + v.id + '></option>').append(v.name).appendTo(list);
		});
		BootstrapDialog.showModel($('div.set-principal-box'));
	});
}
/*
 * 设置部门经理 
 * te5l.com [K]
 */
function setPrincipal() {
	$.getJSON('mgr/department/setPrincipal', {
		deptId : departmentId,
		emplId : $('select.principal-list').val()
	}, function(data) {
		if (!$.isSuccess(data)) return;
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		BootstrapDialog.hideModel($('div.set-principal-box'));
		findListInfo();
	});
}
/*
 * 提示并确定删除部门信息 
 * te5l.com [K]
 */
function hintDelete(id) {
	if (!id) return;
	BootstrapDialog.confirm("请确认是否要删除该部门?<br /><span class='placeholder'>PS: 同时会删除该部门下的所有职位!</span>", function(result) {
		if (!result) return;
		dialog = BootstrapDialog.isSubmitted();
		$.getJSON('mgr/department/deleteDepartment', {deptId : id}, function(data) {
			dialog.close();
			if (!$.isSuccess(data)) return;
			findListInfo();
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		});
	});
}
/*
 * 显示部门编辑窗口 
 * te5l.com [K]
 */
function showModifyBox(deptId) {
	$('.empty').removeClass('empty');
	if (!deptId) return;
	dialog = BootstrapDialog.loading();
	$('input.modifyName').val("");
	$('textarea.modifyDesc').val("");
	$.getJSON('mgr/department/findDepartmentById', {deptId : deptId}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		$('input.modifyName').val(data.body.deptName);
		$('textarea.modifyDesc').val(data.body.deptDescription);
		$('button.modifyBtn').attr('onclick', 'modifyDept(' + data.body.deptId + ')');
		$('div.modify-box').modal({
			closable : false,
			show : true
		});
	});
}
/*
 * 修改部门信息
 * te5l.com [K]
 */
var dept = {};
function modifyDept(deptId) {
	if (!deptId) return;
	$.isSubmit = true;
	dept.deptId = deptId;
	dept.deptName = $.verifyForm($('input.modifyName'), true);
	dept.deptDesc = $.verifyForm($('textarea.modifyDesc'), false);
	if (!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/department/modifyDepartment', {
		deptId : dept.deptId,
		name : dept.deptName,
		desc : dept.deptDesc
	}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.modify-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	}, 'json');
}
/*
 * 显示部门添加窗口 
 * te5l.com [K]
 */
function showAddBox() {
	$('.empty').removeClass('empty');
	$('input.addName').val('');
	$('textarea.addDesc').val('');
	BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加部门信息 
 * te5l.com [K]
 */
function addDepartment() {
	$.isSubmit = true;
	dept.deptName = $.verifyForm($('input.addName'), true);
	dept.deptDesc = $.verifyForm($('textarea.addDesc'), false);
	if (!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/department/addDepartment', {name : dept.deptName,desc : dept.deptDesc}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.add-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	}, 'json');
}