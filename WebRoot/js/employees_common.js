/*
 * 显示员工记录
 */
function record(id) {
	if (!id) return;
	$.getJSON('./mgr/findEmployeesRecord', {emplId : id}, function(data){
		if(!$.isSuccess(data)) return;
		BootstrapDialog.showModel($('div.employees-log-box'));
		var tbody = $('tbody.table-empoyees-box').empty();
		$.each(data.body, function(i,v){
			$('<tr></tr>')
			.append($('<td></td>').append(v.id))
			.append($('<td></td>').append(v.type))
			.append($('<td></td>').append(v.creator))
			.append($('<td></td>').append(v.time))
			.append($('<td></td>').append(v.note))
			.appendTo(tbody);
		});
	});
}