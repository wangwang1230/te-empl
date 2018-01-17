var moduleCode = '04001';
var curAcctName = "";

function initFun() {
	if(secure.find){
		dialog = BootstrapDialog.loading();
		findListInfo();
	}
	if(secure.add)
		$('button.add-btn').removeClass('hide');
	if(!secure.add)
		$('button.add-btn').remove();
}
/*
 * 获取数据列表
 * te5l.com [K]
 */
function findListInfo() {
	$.post('mgr/account/findAccountList', {
		page : page,
		searchValue : $('input.searchInput').val()
	}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		var tbody = $('tbody.tbody').empty();
		$.each(data.body, function(i, v) {
			$("<tr></tr>")
			.append($("<td></td>").append(v.id))
			.append($("<td></td>").append(v.name))
			.append($("<td></td>").append(v.nickname))
			.append($("<td></td>").append(v.time))
			.append($("<td></td>").append(v.creator))
			.append($("<td></td>").append(analyzeBtns(v)))
			.appendTo(tbody);
		});
	}, 'json');
	findAccountPage();
}
/*
 * 获取数据分页
 * te5l.com [K]
 */
function findAccountPage() {
	$.post('mgr/account/findAccountPage', {
		page : page,
		searchValue : $('input.searchInput').val()
	}, function(data) {
		$.analysisPage(data.body);
	}, 'json');
}
/*
 * 解析操作按钮
 * te5l.com [K]
 */
function analyzeBtns(v){
	var btns = "";
	btns += secure.modify ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModifyBox("+v.id+")'><span class='glyphicon glyphicon-pencil'></span>修改</button>" : "" ;
	btns += secure.modify ? "<button type='button' class='btn btn-info btn-xs' onclick='initPassword("+v.id+")'><span class='glyphicon glyphicon-cog'></span>初始密码</button>" : "" ;
	btns += secure.modify && !v.initAccount ? "<button class='btn btn-success btn-xs' type='button' onclick='roleMgr(\""+v.name+"\")'><span class='glyphicon glyphicon glyphicon-star'></span>管理</button>" : "";
	btns += secure.del && !v.initAccount ? "<button type='button' class='btn btn-danger btn-xs' onclick='hintDelete("+v.id+")'><span class='glyphicon glyphicon-remove'></span>删除</button>" : "" ;
	return btns;
}
/*
 * 显示添加窗口
 * te5l.com [K]
 */
function showAddBox(){
	$('.empty').removeClass('empty');
	$('input.acctName').val("");
	$('input.acctNick').val("");
	$('input.acctPass').val("");
	BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加账户信息
 * te5l.com [K]
 */
var account = {};
function addAccount(){
	$.isSubmit = true;
	account.username = $.verifyForm($('input.acctName'), true);
	account.nickname = $.verifyForm($('input.acctNick'), true);
	account.password = $.verifyForm($('input.acctPass'), true);
	if(!$.isSubmit) reutrn;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/account/addAccount', {
		user : account.username,
		nick : account.nickname,
		pass : account.password,
	}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.add-box'));
		findListInfo();
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
	}, 'json');
}
/*
 * 提示并删除帐号列表
 * te5l.com [K]
 */
function hintDelete(id){
	if(!id) return;
	BootstrapDialog.confirm("请确定是否删除该帐号?", function(result){
		if(!result) return;
		dialog = BootstrapDialog.isSubmitted();
		$.getJSON('mgr/account/delAccount', {id:id}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}
/*
 * 提示并确认初始密码为123456
 * te5l.com [K]
 */
function initPassword(id){
	if(!id) return;
	BootstrapDialog.confirm("请确定是否将该账户密码重置为 <b style='color:red;'>www.te5l.com</b>", function(result){
		if(!result) return;
		dialog = BootstrapDialog.isSubmitted();
		$.getJSON('mgr/account/initPassword', {id:id}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return; 
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		});
	});
}
/*
 * 显示编辑窗口
 * te5l.com [K]
 */
function showModifyBox(id){
	$('.empty').removeClass('empty');
	if(!id) return;
	account.id = id;
	dialog = BootstrapDialog.loading();
	$.getJSON('mgr/account/findAccountById', {id:id}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		$('input.modifyAcctName').val(data.body.name);
		$('input.mdoifyAcctNick').val(data.body.nickname);
		BootstrapDialog.showModel($('div.modify-box'));
	});
}
/*
 * 编辑帐户信息
 * te5l.com [K]
 */
function mdoifyAccount(){
	if(!account.id) return;
	$.isSubmit = true;
	account.nickname = $.verifyForm($('input.mdoifyAcctNick'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('mgr/account/modifyNickname', {
		id : account.id,
		nickname : account.nickname
	}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.modify-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	}, 'json');
}
/*
 * 遍历并显示角色列表
 * te5l.com [K]
 */
function roleMgr(acctName){
	if(!acctName) return;
	var tbody = $('table.module-table').empty();
	curAcctName = acctName;
	dialog = BootstrapDialog.loading();
	$.getJSON('mgr/account/findRole', {acctName : acctName}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i,v){
			$("<tr class='acctRole'></tr>")
			.append($("<td></td>").append($("<label></label>").append(findRoleCheckBox(v) + v.roleName))) 
			.appendTo(tbody);
		});
		BootstrapDialog.showModel($('div.acct-role-module-box'));
	});
}
function findRoleCheckBox(v){
	return "<input type='checkbox' "+$.findChecked(v.opt)+" onclick='setAccountRole(this,"+v.id+")' code='"+v.id+"' />";
}
/*
 * 为当前帐户设置角色
 */
function setAccountRole(obj, id){
	if(!id) return;
	$.post('mgr/account/addAccountRole', {
		id : id, 
		account : curAcctName, 
		add : $(obj).is(':checked')
	}, function(data){
		if(!$.isSuccess(data)) return;
	}, 'json');
}