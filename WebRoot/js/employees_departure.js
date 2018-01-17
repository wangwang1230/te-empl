var moduleCode = '01004';
var empl = {};
function initFun() {
	if (secure.find) {
		findDepartment();
		findListInfo();
		dialog = BootstrapDialog.loading();
	}
	if(!secure.add) $('button.add-empl-btn').remove();
	if(secure.add ) $('button.add-empl-btn').removeClass('hide');
}
$(function() {
	$('.search-select, .dropdown-menu').on('click', function(e) {
		$target = $(e.target);
		var searchBtn = $('button.search-btn');
		searchBtn.text($target.text());
		searchBtn.append("<span class='caret'></span>");
		searchBtn.attr('name', $target.attr('name'));
	});
	$('select.select-department').change(function(e){
		findPosition($(this).val());
	});
});
/*
 * 新窗口打开新建员工信息页面
 * te5l.com [K]
 */
function newInternshipEmpl() {
	window.open('./employees_internship_info.html?moduleCode=01001');
}
/*
 * 获取员工列表信息
 * te5l.com [K]
 */
function findListInfo() {
	var serType = $('button.search-btn').attr('name');
	var serVal = $('input.search-val').val();
	var department = $('select.select-department').val();
	var position = $('select.select-position').val();
	$.post('./mgr/employees/departure/findEmployeesInternshipList', {
		serType : serType,
		serVal : serVal,
		department : department,
		position : position,
		page : page
	}, function(data) {
		var tbody = $('tbody.tbody-info').empty();
		dialog.close();
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i,v){
			$("<tr></tr>")
			.append($("<td></td>").append(v.id))
			.append($("<td></td>").append(v.name))
			.append($("<td></td>").append(v.sex ? '女' : '男'))
			.append($("<td></td>").append(v.identity))
			.append($("<td></td>").append(v.date))
			.append($("<td></td>").append(v.department))
			.append($("<td></td>").append(v.position))
			.append($("<td></td>").append(analysisBtns(v)))
			.appendTo(tbody);
		});
	}, 'json');
	// 获取分页信息
	$.post('./mgr/employees/departure/findEmployeesInternshipPage', {
		serType : serType,
		serVal : serVal,
		department : department,
		position : position,
		page : page
	}, function(data) {$.analysisPage(data.body);}, 'json');
}
/*
 * 获取部门列表
 * te5l.com [K]
 */
function findDepartment(){
	$.getJSON('./mgr/findDepartment', function(data){
		var departmentList = $('select.select-department').empty().append("<option value=0>请选择部门</option>");
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i,v){
			$('<option value='+v.id+'></option>').append(v.name).appendTo(departmentList);
		});
	});
}
/*
 * 根据部门ID, 获取职位列表
 * te5l.com [K]
 */
function findPosition(deptId){
	var positionList = $('select.select-position').empty().append("<option value=0>请选择职位</option>");
	if(deptId < 1) return;
	$.getJSON('./mgr/findPositionByDeptId', {deptId : deptId}, function(data){
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i, v){
			$('<option value='+v.id+'></option>').append(v.name).appendTo(positionList);
		});
	});
}
/*
 * 解析按钮组
 * te5l.com [K]
 */
function analysisBtns(v){
	var btns = "";
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='enroll("+v.id+")'><span class='glyphicon glyphicon-pencil'></span>重新录取</button>" : "" ;
	btns += secure.find ? "<button type='button' class='btn btn-success btn-xs' onclick='record("+v.id+")'><span class='glyphicon glyphicon-align-left'></span>记录</button>" : "" ;
	btns += secure.find ? "<button type='button' class='btn btn-info btn-xs' onclick='showNote("+v.id+")'><span class='glyphicon glyphicon-link'></span>离职原因</button>" : "" ;
	btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='destroy("+v.id+")'><span class='glyphicon glyphicon-minus'></span>销毁数据</button>" : "" ;
	return btns;
}
/*
 * 销毁数据
 * te5l.com [K]
 */
function destroy(id){
	if(!id) return;
	BootstrapDialog.confirm("请确认是否销毁该员工所有所有档案!<br /><span class='placeholder'>PS: 请谨慎操作!!!</span>", function(result){
		if(!result) return;
		dialog = BootstrapDialog.loading();
		$.getJSON('./mgr/employees/departure/destroy', {emplId:id}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}
/*
 * 显示备注信息
 * te5l.com [K]
 */
function showNote(id){
	if(!id) return;
	dialog = BootstrapDialog.loading();
	$.getJSON('./mgr/employees/departure/findDepartureNote', {emplId:id}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		new BootstrapDialog({
			title : '离职备注',
			message : data.body,
			type : BootstrapDialog.TYPE_PRIMARY,
			closeabled : true,
			buttons: [{label: '关闭',action: function(dialog) {dialog.close();}}]
		}).open();
	});
}
function enroll(id){
	if(!id) return;
	empl.id = id;
	$('textarea.enroll-employees-text').val('');
	BootstrapDialog.showModel($('div.enroll-employees-box'));
}
function enrollEmployees(){
	if(!empl.id) return;
	$.isSubmit = true;
	var note = $.verifyForm($('textarea.enroll-employees-text'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('./mgr/employees/departure/enrollEmployees', {emplId : empl.id, note : note}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.enroll-employees-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	},'json');
}
