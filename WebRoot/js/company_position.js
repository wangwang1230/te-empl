var moduleCode = '03002';
var position = {};

function initFun() {
	if(secure.find){
		dialog = BootstrapDialog.loading();
		findDepartmentSelect(0, $('select.deptList'));
		findListInfo();
	}
	if(!secure.add)
		$('button.add-btn').remove();
	if(secure.add)
		$('button.add-btn').removeClass('hide');
}
/*
 * 获取职位列表数据
 * te5l.com [K]
 */
function findListInfo() {
	$.post('mgr/position/findPositionListInfo', {
		page : page,
		deptId : $('select.deptList').val(),
		searchValue : $('input.searchInput').val()
	}, function(data){
		dialog.close();
		var tbody = $('tbody.tbody').empty();
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i, v){
			$("<tr></tr>")
			.append($("<td></td>").append(v.id))
			.append($("<td></td>").append(v.positionName))
			.append($("<td></td>").append(v.time))
			.append($("<td></td>").append(v.creator))
			.append($("<td></td>").append(v.departmentName))
			.append($("<td></td>").append(v.description))
			.append($("<td></td>").append(analyzeBtns(v)))
			.appendTo(tbody);
		});
	}, 'json');
	$.post('mgr/position/findPositionPage', {
		page : page,
		deptId : $('select.deptList').val(),
		searchValue : $('input.searchInput').val()
	}, function(data){
		$.analysisPage(data.body);
	}, 'json');
}
/*
 * 解析操作按钮
 * te5l.com [K]
 */
function analyzeBtns(v){
	var btns = "";
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+v.id+")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "" ;
	btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete("+v.id+")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "" ;
	return btns;
}
/*
 * 显示编辑窗口
 * te5l.com [K]
 */
function showModifyBox(id){
	$('.empty').removeClass('empty');
	if(!id) return;
	dialog = BootstrapDialog.loading();
	$.getJSON('mgr/position/findPositionById', {id:id}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		position.id = data.body.poId;
		findDepartmentSelect(data.body.poDepartment, $('select.modifyDeptList'));
		$('input.modifyName').val(data.body.poName);
		$('textarea.modifyDesc').val(data.body.poDescription);
		BootstrapDialog.showModel($('div.modify-box'));
	});
}
/*
 * 修改职位信息
 * te5l.com [K]
 */
function modifyPosition(){
	$.isSubmit = true;
	if(!position.id) return;
	position.deptId = $.verifyForm($('select.modifyDeptList'), true);
	position.name = $.verifyForm($('input.modifyName'), true);
	position.desc = $.verifyForm($('textarea.modifyDesc'), false);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/position/modifyPosition', {
		id : position.id,
		deptId : position.deptId,
		name : position.name,
		desc : position.desc
	}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.modify-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo(); 
	}, 'json');
}
/**
 * 获取部门下拉菜单
 * @param curDepartient 当前要被选中的部门
 * @param eml 要被赋值的元素
 * te5l.com [K]
 */
function findDepartmentSelect(curDepartient, eml) {
	eml.empty().append("<option value=0>请选择部门</option>");
	$.getJSON('mgr/position/findAllDepartment', function(data) {
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i,v){
			$("<option "+analyzeSelect(v.id,curDepartient) +" value="+v.id+"></option>")
			.append(v.name)
			.appendTo(eml);
		});
	}); 
}
/*
 * te5l.com [K]
 */
function analyzeSelect(id, curDepartient){
	return curDepartient > 0 && id == curDepartient ? " selected=true " : "" ;
}
/*
 * 显示添加窗口
 * te5l.com [K]
 */
function showAddBox(){
	$('.empty').removeClass('empty');
	$('input.addName').val("");
	$('textarea.addDesc').val("");
	findDepartmentSelect(0, $('select.addDeptList'));
	BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加职位信息
 */
function addPosition(){
	$.isSubmit = true;
	position.deptId = $.verifyForm($('select.addDeptList'), true);
	position.name = $.verifyForm($('input.addName'), true);
	position.desc = $.verifyForm($('textarea.addDesc'), false);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/position/addPosition', {
		deptId : position.deptId,
		name : position.name,
		desc : position.desc
	}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.add-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	}, 'json');
}
/*
 * 提示并确定删除职位信息
 * te5l.com [K]
 */
function hintDelete(id){
	if(!id) return;
	BootstrapDialog.confirm("请确认是否删除该职位!", function(result){
		if(!result) return;
		dialog = BootstrapDialog.isSubmitted();
		$.getJSON('mgr/position/deletePosition',{id : id}, function(data){
			if(!$.isSuccess(data)) return;
			dialog.close();
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}