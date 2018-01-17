var moduleCode = '04002';
var curRoleId;

var type = {};
type.FIND = 1;
type.DELETE = 2;
type.MODIFY = 3;
type.ADD = 4;

var role = {};
function initFun() {
	if(secure.find){
		dialog = BootstrapDialog.loading();
		findListInfo();
	}
	if(!secure.add)
		$('button.add-btn').remove();
	if(secure.add)
		$('button.add-btn').removeClass('hide');
}
/*
 * 获取列表数据
 * te5l.com [K]
 */
function findListInfo() {
	$.post('mgr/role/findRoleList', {
		page : page,
		searchVal : $('input.searchInput').val()
	}, function(data) {
		var tbody = $('tbody.tbody').empty();
		dialog.close();
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i,v){
			$("<tr></tr>")
			.append($("<td></td>").append(v.id))
			.append($("<td></td>").append(v.name))
			.append($("<td></td>").append(v.time))
			.append($("<td></td>").append(v.creator))
			.append($("<td></td>").append(v.description))
			.append($("<td></td>").append(analyzeBtns(v)))
			.appendTo(tbody);
		});
	}, 'json');
	findRolePage();
}
/*
 * 获取列表分页
 * te5l.com [K]
 */
function findRolePage() {
	$.post('mgr/role/findRolePage', {page : page,searchVal : $('input.searchInput').val()}, function(data) {
		$.analysisPage(data.body); 
	}, 'json');
}
/*
 * 解析数据列表的操作按钮
 * te5l.com [K]
 */
function analyzeBtns(v) {
	var btns = ""; 
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox(" + v.id + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "" ;
	btns += secure.modify ? "<button type='button' class='btn btn-success btn-xs' onclick='listModule(" + v.id + ")'><span class='glyphicon glyphicon glyphicon-star'></span>模块</button>" : "" ;
	btns += secure.del ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete(" + v.id + ")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "" ;
	return btns;
}
/*
 * 显示添加角色窗口
 * te5l.com [K]
 */
function showAddBox(){
	$('.empty').removeClass('empty');
	$('input.addRoleName').val('');
	$('textarea.addRoleDesc').val('');
	BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加角色
 * te5l.com [K]
 */
function addRole(){
	$.isSubmit = true;
	role.name = $.verifyForm($('input.addRoleName'), true);
	role.description = $.verifyForm($('textarea.addRoleDesc'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/role/addRole', {name : role.name,desc : role.description}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.add-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	}, 'json');
}
/*
 * 显示编辑角色信息窗口
 * te5l.com [K]
 */
function showModifyBox(id){
	$('.empty').removeClass('empty');
	if(!id) return;
	role.id = id;
	dialog = BootstrapDialog.loading();
	$.getJSON('mgr/role/findRoleById', {id : id}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		$('input.modifyRoleName').val(data.body.name);
		$('textarea.modifyRoleDesc').val(data.body.description);
		BootstrapDialog.showModel($('div.modify-box'));
	});
}
/*
 * 编辑
 * te5l.com [K]
 */
function modifyRole(){
	if(!role.id) return;
	$.isSubmit = true;
	role.name = $.verifyForm($('input.modifyRoleName'), true);
	role.desc = $.verifyForm($('textarea.modifyRoleDesc'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/role/modifyRole', {
		id : role.id,
		name : role.name,
		desc : role.desc
	}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.modify-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	}, 'json');
}
/*
 * 提示并删除数据
 * te5l.com [K]
 */
function hintDelete(id){
	if(!id) return;
	BootstrapDialog.confirm("请确认是否删除该角色?<br /><span class='placeholder'>PS: 删除后该角色关联的帐号全部将失效, 请谨慎操作!<span>", function(result){
		if(!result) return;
		dialog = BootstrapDialog.isSubmitted();
		$.getJSON('mgr/role/deleteRole', {id:id}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}

/*
 * 遍历模块列表数据
 * te5l.com [K]
 */
function listModule(id){
	if(!id) return;
	curRoleId = id;
	dialog = BootstrapDialog.loading();
	$.getJSON('mgr/role/findAllModule', {roleId : curRoleId}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		var tbody = $('table.module-table').empty();
		$.each(data.body, function(i,v){
			if(v.level == 0){
				$("<tr class='module-tr super-module'></tr>")
				.append($("<td colspan='2'></td>").append(v.name)) 
				.appendTo(tbody); 
				$.each(data.body, function(k,l){
					if(l.superCode == v.code){
						$("<tr class='child-module'></tr>")
						.append($("<td class='child-module-name'></td>").append(l.name+"：")) 
						.append($("<td class='child-module-power'></td>")
							.append($("<label></label>").append(findFind(l)))
							.append($("<label></label>").append(findAdd(l)))
							.append($("<label></label>").append(findModify(l)))
							.append($("<label></label>").append(findDelete(l)))
						)
						.appendTo(tbody);
					}
				});
			}
		});
		BootstrapDialog.showModel($('div.role-module-box'));
	});
}
function findFind(l){
	return "<input type='checkbox' "+$.findChecked(l.find)+" onclick='setRoleSecureValid(this, \""+l.code+"\", "+type.FIND+")' />查询";
}
function findDelete(l){
	return "<input type='checkbox' "+$.findChecked(l.del)+" onclick='setRoleSecureValid(this, \""+l.code+"\", "+type.DELETE+")' />删除";
}
function findModify(l){
	return "<input type='checkbox' "+$.findChecked(l.modify)+" onclick='setRoleSecureValid(this, \""+l.code+"\", "+type.MODIFY+")' />修改";
}
function findAdd(l){
	return "<input type='checkbox' "+$.findChecked(l.add)+"  onclick='setRoleSecureValid(this, \""+l.code+"\", "+type.ADD+")' />添加";
}
/*
 * 为角色设置模块
 * te5l.com [K]
 */
function setRoleSecureValid(obj, code, type){
	if(!code) return;
	$.post('mgr/role/setRoleSecureValid', {
		rold : curRoleId,
		code : code, 
		type : type,
		add : $(obj).is(':checked')
	}, function(data){
		if(!$.isSuccess(data)) return;
	}, 'json');
}
