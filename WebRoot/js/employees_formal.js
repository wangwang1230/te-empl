var moduleCode = '01002';
var empl = {};
function initFun() {
	if (secure.find) {
		findDepartment();
		findListInfo();
		dialog = BootstrapDialog.loading();
	}
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
 * 获取员工列表信息
 * te5l.com [K]
 */
function findListInfo() {
	var serType = $('button.search-btn').attr('name');
	var serVal = $('input.search-val').val();
	var department = $('select.select-department').val();
	var position = $('select.select-position').val();
	$.post('./mgr/employees/formal/findEmployeesTrainingList', {
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
	$.post('./mgr/employees/formal/findEmployeesTrainingPage', {
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
 * 解析按钮组
 * te5l.com [K]
 */
function analysisBtns(v){
	var btns = "";
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='modifyInternshipEmpl("+v.id+")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "" ;
	btns += secure.find ? "<button type='button' class='btn btn-success btn-xs' onclick='record("+v.id+")'><span class='glyphicon glyphicon-align-left'></span>记录</button>" : "" ;
	btns += secure.modify ? "<button type='button' class='btn btn-info btn-xs' onclick='enroll("+v.id+")'><span class='glyphicon glyphicon-tag'></span>培训</button>" : "" ;
	btns += secure.modify ? "<button type='button' class='btn btn-warning btn-xs' onclick='departure("+v.id+")'><span class='glyphicon glyphicon-remove'></span>离职</button>" : "" ;
	return btns;
}
/*
 * 跳转到编辑页面
 * te5l.com [K]
 */
function modifyInternshipEmpl(id){ 
	if(!id) return;
	window.open('./employees_internship_info.html?moduleCode=01002&emplNo='+id);
}
/*
 * 员工离职
 * te5l.com [K]
 */
function departure(id){
	if(!id) return;
	empl.id = id;
	$('textarea.departure-employees-text').val('');
	BootstrapDialog.showModel($('div.departure-employees-box'));
}
/*
 * 员工离职
 * te5l.com [K]
 */
function departureEmployees(){
	if(!empl.id) return;
	$.isSubmit = true;
	var note = $.verifyForm($('textarea.departure-employees-text'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.getJSON('./mgr/employees/formal/departure',{id:empl.id, note:note}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		BootstrapDialog.hideModel($('div.departure-employees-box'));
		findListInfo();
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
 * 获取培训项目
 */
function enroll(id){
	if(!id) return;
	dialog = BootstrapDialog.loading();
	$.getJSON('./mgr/employees/formal/findTrainingRecord',{emplId : id}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		var tbody = $('tbody.training-record').empty();
		$.each(data.body, function(i,v){
			$('<tr></tr>')
			.append($('<td></td>').append(v.id))
			.append($('<td></td>').append(v.name))
			.append($('<td></td>').append(v.time))
			.append($('<td></td>').append(v.status))
			.append($('<td></td>').append(analysisRecordBtns(v)))
			.appendTo(tbody);
		});
		BootstrapDialog.showModel($('div.enroll-list-box'));
	});
}
function analysisRecordBtns(v){
	var btns = "";
	if(v.state == 5 && secure.find)
		btns += "<button type='button' class='btn btn-info btn-xs' onclick='showNote(\"" + v.note + "\")'><span class='glyphicon glyphicon-pause'></span>查看评分</button>";
	return btns;
}
/*
 * 显示备注
 * te5l.com [K]
 */
function showNote(message){
	new BootstrapDialog({
		title : '我的评分结果',
		message : message,
		type : BootstrapDialog.TYPE_PRIMARY,
		closeabled : true,
		buttons: [{label: '关闭',action: function(dialog) {dialog.close();}}]
	}).open();
}
