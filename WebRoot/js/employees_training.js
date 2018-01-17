var moduleCode = '01003';
function initFun() {
	if (secure.add) $('button.btn-create').removeClass('hide');
	if (!secure.add) $('button.btn-create').remove();
	if (secure.find) {
		dialog = BootstrapDialog.loading();
		findListInfo();
	}
}
var training = {};
/*
 * 显示添加窗口 
 * te5l.com [K]
 */
function showAddBox() {
	$('.empty').removeClass('empty');
	$('input.addName').val("");
	$('input.addStartDate').val("");
	$('input.addEndDate').val("");
	$('textarea.addDesc').val("");
	BootstrapDialog.showModel($('div.add-box'));
}
/*
 * 添加培训项目 
 * te5l.com [K]
 */
function addTraining() {
	$.isSubmit = true;
	training.name = $.verifyForm($('input.addName'), true);
	training.startdate = $.verifyForm($('input.addStartDate'), true);
	training.enddate = $.verifyForm($('input.addEndDate'), true);
	training.insertion = $.verifyForm($('select.addInsertion'), true);
	training.description = $.verifyForm($('textarea.addDesc'), false);
	if (!$.isSubmit) return;
	// 添加培训项目前, 需要再次确认
	BootstrapDialog.confirm("请再次确认是否添加 <b>" + training.name + "</b> 这个培训项目, 创建后该数据不能删除, 请谨慎操作!", function(result) {
		if (!result) return;
		dialog = BootstrapDialog.loading();
		$.post('./mgr/training/addTraining', {
			name : training.name,
			description : training.description,
			startTime : training.startdate,
			endTime : training.enddate,
			isInsertAttend : training.insertion
		}, function(data) {
			dialog.close();
			if (!$.isSuccess(data)) return;
			BootstrapDialog.hideModel($('div.add-box'));
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		},'json');
	});
}
/*
 * 获取列表数据 
 * te5l.com [K]
 */
function findListInfo() {
	var searchValue = $('input.searchValue').val();
	$.post('./mgr/training/findTrainingList', {searchValue : searchValue, page : page}, function(data) {
		dialog.close();
		var tbody = $('tbody.tbody-list').empty();
		if (!$.isSuccess(data)) return;
		$.each(data.body, function(i, v) {
			$('<tr></tr>')
			.append($('<td></td>').append(v.id))
			.append($('<td></td>').append(v.name))
			.append($('<td></td>').append(v.number))
			.append($('<td></td>').append(v.start))
			.append($('<td></td>').append(v.end))
			.append($('<td></td>').append(v.insertion ? "是" : "否"))
			.append($('<td></td>').append(v.stateKey))
			.append($('<td></td>').append(v.creator))
			.append($('<td></td>').append(analyzeBtns(v)))
			.appendTo(tbody);
		});
		findListPage();
	}, "json");
}
/*
 * 获取分页数据 
 * te5l.com [K]
 */
function findListPage() {
	var searchValue = $('input.searchValue').val();
	$.post('./mgr/training/findTrainingPage', {searchValue : searchValue, page : page}, function(data) {
		$.analysisPage(data.body);
	}, "json");
}
/*
 * 提示删除 
 * te5l.com [K]
 */
function hintDelete(id) {
	if (!id) return;
	BootstrapDialog.confirm("请确认是否删除该培训项目?<br /><span class='placeholder'>PS: 同时会删除所有员工相关的培训记录!</span>", function(result) {
		if (!result) return;
		$.getJSON('./mgr/training/delete', {id : id}, function(data) {
			if (!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}
/*
 * 显示详情 
 * te5l.com [K]
 */
function showDetails(id) {
	if (!id) return;
	dialog = BootstrapDialog.loading();
	$.getJSON('./mgr/training/findTrainingInfoById', {id : id}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		$('td.trainingitem-id').text(data.body.id);
		$('td.trainingitem-name').text(data.body.name);
		$('td.trainingitem-start').text(data.body.start);
		$('td.trainingitem-insertion').text(data.body.insertion ? "允许" : "不允许");
		$('td.trainingitem-end').text(data.body.end);
		$('td.trainingitem-create').text(data.body.time);
		$('td.trainingitem-description').text(data.body.description);
		$('td.trainingitem-state').text(data.body.stateKey);
		$('td.trainingitem-result').text(!data.body.trainingResult ? "无" : data.body.trainingResult);
		BootstrapDialog.showModel($('div.details-box'));
	});
}
/*
 * 显示编辑窗口
 *  te5l.com [K]
 */
function showModify(id) {
	$('.empty').removeClass('empty');
	$('input.modifyName').val("");
	$('input.modifyStartDate').val("");
	$('input.modifyEndDate').val("");
	$('textarea.modifyDesc').val("");
	if (!id) return;
	dialog = BootstrapDialog.loading();
	training.id = id;
	$.getJSON('./mgr/training/findTrainingInfoById', {id : id}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		$('input.modifyName').val(data.body.name);
		$('input.modifyStartDate').val(data.body.start);
		$('input.modifyEndDate').val(data.body.end);
		$('select.modifyInsertion').find('option').eq(data.body.insertion ? 0 : 1).prop('selected', true);
		$('textarea.modifyDesc').val(data.body.description);
		BootstrapDialog.showModel($('div.modify-box'));
	});
}
/*
 * 编辑数据 
 * te5l.com [K]
 */
function modifyTraining() {
	$.isSubmit = true;
	training.name = $.verifyForm($('input.modifyName'), true);
	training.startdate = $.verifyForm($('input.modifyStartDate'), true);
	training.enddate = $.verifyForm($('input.modifyEndDate'), true);
	training.insertion = $.verifyForm($('select.modifyInsertion'), true);
	training.description = $.verifyForm($('textarea.modifyDesc'), false);
	if (!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('./mgr/training/modifyTraining', {
		id : training.id,
		name : training.name,
		description : training.description,
		startTime : training.startdate,
		endTime : training.enddate,
		isInsertAttend : training.insertion
	}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		BootstrapDialog.hideModel($('div.modify-box'));
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		findListInfo();
	},'json');
}
/*
 * 解析操作按钮 
 * te5l.com [K]
 */
function analyzeBtns(v) {
	var btns = "";
	btns += secure.find ? "<button type='button' class='btn btn-info btn-xs' onclick='showDetails(" + v.id + ")'><span class='glyphicon glyphicon-menu-left'></span>详情</button>" : "";
	btns += secure.find ? "<button type='button' class='btn btn-success btn-xs' onclick='showRecord(" + v.id + ")'><span class='glyphicon glyphicon-paperclip'></span>记录</button>" : "";
	if (secure.modify) {
		btns += v.state == 0 ? "<button type='button' class='btn btn-warning btn-xs' onclick='start(" + v.id + ")'><span class='glyphicon glyphicon-play'></span>开始</button>" : "";
		btns += v.state == 1 ? "<button type='button' class='btn btn-danger btn-xs' onclick='writeStopNote(" + v.id + ", \"" + v.end + "\")'><span class='glyphicon glyphicon-pause'></span>结束</button>" : "";
		btns += v.state == 0 ? "<button type='button' class='btn btn-primary btn-xs' onclick='showModify(" + v.id + ")'><span class='glyphicon glyphicon-pencil'></span>编辑</button>" : "";
	}
	return btns;
}
/*
 * 响应搜索按钮
 * te5l.com [K]
 */
function search() {
	dialog = BootstrapDialog.loading();
	findListInfo();
}
/*
 * 开始项目 
 * te5l.com [K]
 */
function start(id) {
	if (!id) return;
	BootstrapDialog.confirm("请确认是否开始该培训项目!<br /><span class='placeholder'>PS: 培训一旦开始, 所有信息将不能修改, 开始前请确认培训信息是否有误! </span>", function(result) {
		if (!result) return;
		dialog = BootstrapDialog.loading();
		$.getJSON('./mgr/training/start', {id : id}, function(data) {
			if (!$.isSuccess(data)) return;
			dialog.close();
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findListInfo();
		});
	});
}
/*
 * 弹出窗口, 填写培训结果
 * te5l.com [K]
 */
function writeStopNote(id, end) {
	if (!id) return;
	training.id = id;
	$('textarea.stop-note').val('');
	$('input.stop-enddate').val(end);
	BootstrapDialog.showModel($('div.stop-box'));
}
/*
 * 停止培训项目 
 * te5l.com [K]
 */
function stop() {
	if (!training.id) return;
	$.isSubmit = true;
	training.enddate = $.verifyForm($('input.stop-enddate'), true);
	training.note = $.verifyForm($('textarea.stop-note'), true);
	if (!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('./mgr/training/stop', {
		id : training.id,
		note : training.note,
		enddate : training.enddate
	}, function(data) {
		dialog.close();
		if (!$.isSuccess(data)) return;
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		BootstrapDialog.hideModel($('div.stop-box'));
		findListInfo();
	},'json');
}
/*
 * 显示参加培训列表记录
 * te5l.com [K]
 */
function showRecord(id) {
	if (!id) return;
	training.id = id;
	dialog = BootstrapDialog.loading();
	$.getJSON('./mgr/training/findRecord', {trainingId : id},function(data) {
		dialog.close();
		training.state = data.body.state;
		training.attend = data.body.attend;
		$('button.add-empl-btn, table.table-record-list').addClass('hide');
		$('div.record-modal-body').find('div.notdate').remove();
		if (!$.isSuccess(data)) return;
		if(data.body.dtoList){
			if(data.body.state == 0 || (training.state == 1 && training.attend))
				$('button.add-empl-btn').removeClass('hide');
			$('table.table-record-list').removeClass('hide');
			var tbody = $('tbody.record-list').empty();
			$.each(data.body.dtoList, function(i,v){
				$('<tr></tr>')
				.append($('<td></td>').append(v.id))
				.append($('<td></td>').append(v.name))
				.append($('<td></td>').append(v.sex ? '女' : '男' )) 
				.append($('<td></td>').append(v.identity))
				.append($('<td></td>').append(v.time))
				.append($('<td></td>').append(v.status)) 
				.append($('<td></td>').append(analyzeApplyBtns(v)))
				.appendTo(tbody);
			});
			BootstrapDialog.showModel($('div.record-box'));
			return;
		}
		var body = $('div.record-modal-body');
		var button = "<button type='button' class='btn btn-success btn-xs' onclick='showAddEmplBox()'><span class='glyphicon glyphicon-plus'></span>点击这里添加员工</button>";
		if(data.body.state == 2)
			$("<div class='notdate'></div>").append("培训已经结束, 未发现报名参加的工作人员!").appendTo(body);
		else if(data.body.state == 1 && data.body.attend)
			$("<div class='notdate'></div>").append("培训已经开始, 如需报名请:"+button).appendTo(body);
		else if(data.body.state == 1 && !data.body.attend)
			$("<div class='notdate'></div>").append("培训已经开始且不能中途报名参加!").appendTo(body);
		else if(data.body.state == 0)
			$("<div class='notdate'></div>").append("该培训目前未发现已报名参加的工作人员, 如需报名请"+button).appendTo(body);
		BootstrapDialog.showModel($('div.record-box'));
	});
}
/*
 * 渲染培训记录操作按钮
 * te5l.com [K]
 */
function analyzeApplyBtns(v){
	var btns = "";
	console.log(training.state+"   "+training.attend+"    "+v.state);
	if(v.state == 1 && secure.del)
		btns += "<button type='button' class='btn btn-danger btn-xs' onclick='delEmployeesTraining(" + v.id + ")'><span class='glyphicon glyphicon-minus'></span>删除</button>" ;
	else if (v.state == 2 && secure.modify)
		btns += "<button type='button' class='btn btn-warning btn-xs' onclick='stopTraining(" + v.id + ")'><span class='glyphicon glyphicon-remove-circle'></span>停止</button>" ;
	else if (v.state == 3 && secure.find)
		btns += "<button type='button' class='btn btn-success btn-xs' onclick='evaluationTraining(" + v.id + ", \""+v.note+"\")'><span class='glyphicon glyphicon-bookmark'></span>评分</button>" ;
	else if (v.state == 4 && secure.find)
		btns += "<button type='button' class='btn btn-primary btn-xs' onclick='showNote(\"停止培训原因\",\""+v.note+"\")'><span class='glyphicon glyphicon-tag'></span>备注</button>" ;
	else if (v.state == 5 && secure.find)
		btns += "<button type='button' class='btn btn-info btn-xs' onclick='showNote(\"培训评分\",\""+v.note+"\")'><span class='glyphicon glyphicon-tag'></span>查看评分</button>" ;
	return btns;
}
/*
 * 关闭报名参加培训班员工列表窗口
 * te5l.com [K]
 */
function closeApplyRecord(){
	findListInfo();
	BootstrapDialog.hideModel($('div.record-box'));
}
/*
 * 显示员工列表框
 * te5l.com [K]
 */
function showAddEmplBox() {
	if (!training.id) return;
	findDepartment();
	$('tbody.empl-list-tboal').empty();
	BootstrapDialog.hideModel($('div.record-box'));
	BootstrapDialog.showModel($('div.add-empl-box'));
}
/*
 * 获取部门列表
 * te5l.com [K]
 */
function findDepartment(current){
	$.depar = $('select.select-department').empty().append('<option value=0>请选择部门</option>'); 
	$.getJSON('./mgr/findDepartment', function(data){
		if(!$.isSuccess(data)) return; 
		$.each(data.body, function(i,v){
			$('<option value='+v.id+'></option>').append(v.name).appendTo($.depar);
		});
	});
}
/*
 * 获取员工列表
 * te5l.com [K]
 */
function findEmployeesList(){
	if(!training.id) return;
	var deptId = $('select.select-department').val();
	var search = $('input.search-val').val();
	dialog = BootstrapDialog.loading();
	$.post('./mgr/training/findEmployeesList', {
		trainingId : training.id,
		deptId : deptId,
		searchVal : search
	}, function(data){
		dialog.close();
		var body = $('tbody.empl-list-tboal').empty();
		if(!$.isSuccess(data)) return;
		$.each(data.body, function(i,v){
			$('<tr></tr>')
			.append($('<td></td>').append(v.id))
			.append($('<td></td>').append(v.name))
			.append($('<td></td>').append(v.sex ? '女' : '男'))
			.append($('<td></td>').append(v.identity))
			.append($('<td></td>').append(v.department))
			.append($('<td></td>').append(v.birthday))
			.append($('<td></td>').append(analyzeAddTrainingBtns(v)))
			.appendTo(body);
		});
	}, 'JSON');
}
/*
 * 渲染添加培训记录按钮
 * te5l.com [K]
 */
function analyzeAddTrainingBtns(v){
	var btns = "";
	btns += secure.modify ? "<button type='button' class='btn btn-success btn-xs' onclick='addTrainingByEmplId("+v.id+", this)'><span class='glyphicon glyphicon-plus'></span>添加</button>" : "" ;
	return btns;
}
/*
 * 添加报名记录
 * te5l.com [K]
 */
function addTrainingByEmplId(emplId, obj){
	if(!emplId || !training.id) return;
	dialog = BootstrapDialog.loading();
	$.getJSON('./mgr/training/addTrainingByEmplId', {emplId : emplId, trainingId : training.id}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		$(obj).prop('disabled', true);
	}, 'json');
} 
/*
 * 关闭搜索员工列表窗口
 * te5l.com [K]
 */
function closeEmplListBox(){
	showRecord(training.id);
	BootstrapDialog.hideModel($('div.add-empl-box'));
}
/*
 * 删除员工报名记录
 * te5l.com [K]
 */
function delEmployeesTraining(id){
	BootstrapDialog.confirm('请确认是否删除该员工的报名记录?', function(result){
		if(!result) return;
		dialog = BootstrapDialog.loading();
		$.getJSON('./mgr/training/delEmployeesTrainingRecord', {
			id : id
		}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			showRecord(training.id);
		});
	});
}
/*
 * 退出培训班
 * te5l.com [K]
 */
function stopTraining(id){
	if(!id) return;
	training.logId = id;
	$('textarea.stop-training-text').val('');
	BootstrapDialog.showModel($('div.stop-training-box'));
}
/*
 * 退出培训班
 * te5l.com [K]
 */
function stopEmployeesTraining(){
	if(!training.logId) return;
	$.isSubmit = true;
	var note = $.verifyForm($('textarea.stop-training-text'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('./mgr/training/stopEmployeesTraining', {id:training.logId,note:note}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		BootstrapDialog.hideModel($('div.stop-training-box'));
		showRecord(training.id);
	}, 'json');
}
/*
 * 显示评分窗口
 * te5l.com [K]
 */
function evaluationTraining(id, note){
	if(!id) return;
	training.logId = id;
	$('textarea.evaluation-training-text').val(note);
	BootstrapDialog.showModel($('div.evaluation-training-box'));
}
/*
 * 为培训评分
 * te5l.com [K]
 */
function evaluationEmployeesTraining(){
	if(!training.logId) return;
	$.isSubmit = true;
	var note = $.verifyForm($('textarea.evaluation-training-text'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('./mgr/training/evaluationEmployeesTraining', {id:training.logId,note:note}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
		BootstrapDialog.hideModel($('div.evaluation-training-box'));
		showRecord(training.id);
	}, 'json');
}
/*
 * 显示备注
 * te5l.com [K]
 */
function showNote(title,message){
	new BootstrapDialog({
		title : title,
		message : message,
		type : BootstrapDialog.TYPE_PRIMARY,
		closeabled : true,
		buttons: [{label: '关闭',action: function(dialog) {dialog.close();}}]
	}).open();
}
/*
 * 提示并添加所有记录
 * te5l.com [K]
 */
function addAllTraining(){
	BootstrapDialog.confirm('请确认是否添加当前搜索结果中的所有员工!', function(result){
		if(!result) return;
		if(!training.id) return;
		var deptId = $('select.select-department').val();
		var search = $('input.search-val').val();
		dialog = BootstrapDialog.loading();
		$.post('./mgr/training/addAllTraining', {
			trainingId : training.id,
			deptId : deptId,
			searchVal : search
		}, function(data){
			dialog.close();
			if(!$.isSuccess(data)) return;
			BootstrapDialog.msg(data.body, BootstrapDialog.TYPE_SUCCESS);
			findEmployeesList();
		}, 'JSON');
	});
}