var moduleCode = '01001';
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
	$.post('./mgr/employees/internship/findEmployeesInternshipList', {
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
	$.post('./mgr/employees/internship/findEmployeesInternshipPage', {
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
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='modifyInternshipEmpl("+v.id+")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "" ;
	btns += secure.find ? "<button type='button' class='btn btn-success btn-xs' onclick='record("+v.id+")'><span class='glyphicon glyphicon-align-left'></span>记录</button>" : "" ;
	btns += secure.modify ? "<button type='button' class='btn btn-info btn-xs' onclick='enroll("+v.id+")'><span class='glyphicon glyphicon-link'></span>录取</button>" : "" ;
	btns += secure.modify ? "<button type='button' class='btn btn-warning btn-xs' onclick='eliminate("+v.id+")'><span class='glyphicon glyphicon-hand-left'></span>淘汰</button>" : "" ;
	return btns;
}
/*
 * 录取员工
 * te5l.com [K]
 */
function enroll(id){
	if(!id) return;
	BootstrapDialog.confirm('请确证是否录取该员工!', function(result){
		if(!result) return;
		dialog = BootstrapDialog.loading();
		$.getJSON('./mgr/employees/internship/enroll', {emId : id}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}
/*
 * 淘汰员工
 * te5l.com [K]
 */
function eliminate(id){
	if(!id) return;
	empl.id = id;
	$('textarea.eliminate-employees-text').val(""); 
	BootstrapDialog.showModel($('div.eliminate-employees-box'));
}
/*
 * 淘汰员工
 */
function eliminateEmployees(){
	if(!empl.id) return;
	$.isSubmit = true;
	var note = $.verifyForm($('textarea.eliminate-employees-text'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('./mgr/employees/internship//eliminate', {emId:empl.id, note:note}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.eliminate-employees-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	});
}
function modifyInternshipEmpl(id){ 
	if(!id) return;
	window.open('./employees_internship_info.html?moduleCode=01001&emplNo='+id);
}