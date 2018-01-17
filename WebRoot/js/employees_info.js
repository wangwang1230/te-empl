var empl = {};	// 员工所有信息
var submitText = '提交信息失败, 请注意被标识为红色的部分!';

var moduleCode = $.getUrlParam('moduleCode');
moduleCode = !moduleCode ? '01001' : moduleCode;	// 获取url传递过来的模块编号, 默认为01001
empl.emplId = !$.getUrlParam('emplNo') ? 0 : $.getUrlParam('emplNo');	// 员工ID
// 动态生成url, 通过获取员工编号来决定
var url = !$.getUrlParam('emplNo') ? 'saveEmployeesInfo' : 'modifyEmployeesInfo';

var reg = {};	// 正则表达式
reg.identity = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;	// 身份证
reg.date = /^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1]))$/;	// 日期验证
reg.phone = /^1[3|4|5|8][0-9]\d{4,8}$/;	// 手机号码
reg.integer = /^[0-9]*[1-9][0-9]*$/;	// 正整数大于0
reg.double = /^\d+(\.\d+)?$/;	// 小数或整数, 含0
reg.number = /^[1-9]\d*|[0]{1,1}$/;	// 正整数, 含0

function initFun() {
	if(!secure.find)  $('div.edit-employees-box').remove();
	if(secure.find){
		dialog = BootstrapDialog.loading();
		$('div.edit-employees-box').removeClass('hide');
		if(empl.emplId){
			addBreadcrumb('修改实习员工信息');
			$.findEmployeesInfo(empl.emplId);
			return;
		}
		$('div.new-title').text("新增实习员工信息");
		addBreadcrumb('新增实习员工');
		findDepartment(0); // 获取所有部门
		findChoose(0); // 获取所有单选框
		$.findAllProvince(); // 获取所有省份
	}
}
/*
 * jQuery 扩展
 * te5l.com [K]
 */
(function($){
	var commpny = "<tr class='compnay-tr'>";
	commpny += "<td><input type='text' maxlengt='64' class='width180 text cp-name' placeholder='单位名称' /></td>";
	commpny += "<td><input type='text' class='width180 text date-before cp-state-date' placeholder='入职时间' /></td>";
	commpny += "<td><input type='text' class='width180 text date-before cp-end-date' placeholder='离职时间' /></td>";
	commpny += "<td><input type='text' maxlengt='64' class='width180 text cp-jobs' placeholder='岗位' /></td>";
	commpny += "<td><input type='text' maxlengt='512' class='width180 text cp-reason' placeholder='离职原因' /></td>";
	commpny += "<td><button type='button' class='btn btn-default btn-xs' onclick='$.delCompnay(this)'>移出</button></td>";
	commpny += "</tr>";
	commpny += "<script type='text/javascript'>$(function(){$('input.date-before').on('click', function(){WdatePicker({maxDate:'%y-%M-{%d}'});});});</script>";
	// 添加工作单位
	$.addCompany = function(){
		$('tbody.company-list').append(commpny);
	};
	// 删除工作单位
	$.delCompnay = function(obj){
		if($('tr.compnay-tr').length <= 1) return; // 如果当前只剩下一条工作单位, 则不删除
		 $(obj).parent().parent('tr.compnay-tr').remove();
	};
	// 验证文本框, 传入获取文本框元素的标识, 是否为空,  加正则匹配
	$.input = function(className, empty, regular){
		$(className).parent().prev().removeClass('data-empty');
		var val = $.removeTrim($(className).val());
		if(!empty) return val;
		if(empty && !regular && val) return val;
		if(regular != null && regular.test(val)) return val;
		$.isSubmit = false;
		$(className).parent().prev().addClass('data-empty');
	};
	// 验证下拉列表
	$.select = function(className, empty, regular){
		$(className).parent().prev().removeClass('data-empty');
		var val = $(className).val();
		if(!empty) return val;
		if(empty && !regular && val && val != 0) return val;
		if(regular != null && regular.test(val)) return val;
		$.isSubmit = false;
		$(className).parent().prev().addClass('data-empty');
	};
	// 获取单选按钮
	$.radio = function(className, empty, regular){
		$(className).parent().parent().prev().removeClass('data-empty');
		var val = $(className).filter(':checked').val();
		if(!empty) return val;
		if(empty && !regular && val) return val;
		if(regular != null && regular.test(val)) return val;
		$.isSubmit = false;
		$(className).parent().parent().prev().addClass('data-empty');
	};
	// 获取户籍地址
	$.findRegister = function(){
		$('td.text-register').removeClass('data-empty');
		var ads = {};
		ads.province = $('select.register-province').val(); 
		ads.city = $('select.register-city').val();
		ads.county = $('select.register-county').val();
		ads.township = $('select.register-township').val();
		ads.village = $('select.register-village').val();
		ads.detailed = $('input.register-detailed').val();
		if(!reg.integer.test(ads.province) || !reg.integer.test(ads.city) || !reg.integer.test(ads.county) || !reg.integer.test(ads.township)){
			$.isSubmit = false; 
			$('td.text-register').addClass('data-empty');
		}
		return ads;
	};
	// 获取现居住地址
	$.findCurrent = function(){
		$('td.text-current').removeClass('data-empty');
		var ads = {};
		ads.province = $('select.current-province').val(); 
		ads.city = $('select.current-city').val();
		ads.county = $('select.current-county').val();
		ads.township = $('select.current-township').val();
		ads.village = $('select.current-village').val();
		ads.detailed = $('input.current-detailed').val();
		if(!reg.integer.test(ads.province) || !reg.integer.test(ads.city) || !reg.integer.test(ads.county)){
			$.isSubmit = false; 
			$('td.text-current').addClass('data-empty');
		}
		return ads;
	};
})(jQuery);

(function($){
	// 解析地址
	$.analyzeAddress = function(current, eml, data){
		$.each(data, function(i,v){
			$('<option value='+v.id+' '+$.findOpeion(v.id, current)+' ></option>')
			.append(v.name).appendTo(eml);
		});
	};
	// 解析部门
	$.analyzeDepartmentOrPosition = function(current, eml, data){
		if(!data) return;
		$.each(data, function(i,v){
			$('<option value='+v.id+' '+$.findOpeion(v.id, current)+'></option>')
			.append(v.name).appendTo(eml);
		});
	};
	$.analyzeCompanie = function(companies){
		var companiebox = $('tbody.company-list').empty();
		$.each(companies, function(i,v){
			$("<tr class='compnay-tr'></tr>")
			.append($("<td></td>").append("<input type='text' maxlength='64' class='width180 text cp-name' placeholder='单位名称' value='"+v.comName+"' />"))
			.append($("<td></td>").append("<input type='text' class='width180 text date-before cp-state-date' placeholder='入职时间' value='"+v.comParticipateTime+"' />"))
			.append($("<td></td>").append("<input type='text' class='width180 text date-before cp-end-date' placeholder='离职时间' value='"+v.comLeaveTime+"' />"))
			.append($("<td></td>").append("<input type='text' maxlength='64' class='width180 text cp-jobs' placeholder='岗位' value='"+v.comPosition+"' />"))
			.append($("<td></td>").append("<input type='text' maxlength='512' class='width180 text cp-reason' placeholder='离职原因' value='"+v.comReason+"' />"))
			.append($("<td></td>").append("<button type='button' class='btn btn-default btn-xs' onclick='$.delCompnay(this)'>移出</button>"))
			.appendTo(companiebox);
		});
	};
})(jQuery);

(function($){
	// 获取所有省份
	$.findAllProvince = function(){
		$.getJSON('./mgr/findProvinceAll', function(data){
			if(!$.isSuccess(data)) return;
			$.analyzeAddress(0, $('select.current-province'), data.body);
			$.analyzeAddress(0, $('select.register-province'), data.body);
		});
	};
	// 根据省份获取市级
	$.findCity = function(provinceId, eml){
		if(provinceId == 0) return;
		$.getJSON('./mgr/findCityByProvinceId', {provinceId:provinceId}, function(data){
			if(!$.isSuccess(data)) return;
			$.analyzeAddress(0, eml, data.body);
		});
	};
	// 根据市级ID获取县级
	$.findCounty = function(cityId, eml){
		if(cityId == 0) return;
		$.getJSON('./mgr/findCountyByCityId', {cityId : cityId}, function(data){
			if(!$.isSuccess(data)) return;
			$.analyzeAddress(0, eml, data.body);
		});
	};
	// 根据县级ID获取乡村
	$.findTownship = function(countyId, eml){
		if(countyId == 0) return;
		$.getJSON('./mgr/findTownshipByCountyId', {countyId : countyId}, function(data){
			if(!$.isSuccess(data)) return;
			$.analyzeAddress(0, eml, data.body);
		});
	};
	// 根据乡村ID获取镇
	$.findVillage = function(vallageId, eml){
		if(vallageId == 0) return;
		$.getJSON('./mgr/findVillageByTownshipId', {vallageId : vallageId}, function(data){
			if(!$.isSuccess(data)) return;
			$.analyzeAddress(0, eml, data.body);
		});
	};
	// 清除地区下拉框列表
	$.clear = function(eml){
		return eml.empty().append($('<option value=0></option>').append('请选择'));
	};
})(jQuery);

$(function(){
	// 部门联动职位下拉框
	$('select.department').change(function(){
		var id = $(this).val();
		var eml = $('select.position').empty().append('<option value=0>请选择职位</option>');
		if(id == 0) return;
		$.getJSON('./mgr/findPositionByDeptId', {deptId:id}, function(data){
			if(!data.head) return;
			$.analyzeDepartmentOrPosition(0, eml, data.body);
		});
	});
	/*
	 * 现居住地址联动
	 * te5l.com [K]
	 */
	$('select.current-province, select.register-province').change(function(){
		if(!$(this).val()) return;
		$.clear($('select.current-county'));
		$.clear($('select.current-township'));
		$.clear($('select.current-village'));
		$.findCity($(this).val(), $.clear($(this).next('select')));
	});
	$('select.current-city, select.register-city').change(function(){
		if(!$(this).val()) return;
		$.clear($('select.current-township'));
		$.clear($('select.current-village'));
		$.findCounty($(this).val(), $.clear($(this).next('select')));
	});
	$('select.current-county, select.register-county').change(function(){
		if(!$(this).val()) return;
		$.clear($('select.current-village'));
		$.findTownship($(this).val(), $.clear($(this).next('select')));
	});
	$('select.current-township, select.register-township').change(function(){
		if(!$(this).val()) return;
		$.findVillage($(this).val(), $.clear($(this).next('select')));
	});
});
/*
 * 验证表单数据
 * te5l.com [K]
 */
function detectionForm(){
	$.isSubmit = true;	// 重置表单为可提交
	// 基本信息
	empl.fullName = $.input('.fullName', true, null);	// 姓名
	empl.sex = $.radio('.sex', true, null);	// 性别
	empl.photo = $('img.empl-photo-image').attr('src');	// 员工头像
	empl.identity = $.input('.identity', true, reg.identity);	// 身份证号码
	empl.birthday = $.input('.birthday', true, reg.date);	// 出生日期
	empl.participate = $.input('.participate', true, reg.date);	// 入职时间
	empl.socialSecurity = $.input('.social-security', false, reg.integer);	// 社保卡号
	empl.deparemtn = $.select('.department', true, reg.integer);	// 所属部门
	empl.position = $.select('.position', true, reg.integer);	// 员工职位
	empl.education = $.radio('.education', true, reg.integer);	// 文化程度
	empl.marriage = $.radio('.marriage', true, reg.integer);	// 婚姻状况
	empl.politics = $.radio('.politics', true, reg.integer);	// 政治面貌
	empl.national = $.radio('.national', true, reg.integer);	// 民族
	// 详细信息
	empl.phone = $.input('.phone', true, reg.phone);	// 手机号码
	empl.contact = $.input('.contact', true, null);	// 其他联系方式
	empl.emergencyContact = $.input('.emergencyContact', true, null);	// 紧急联系人
	empl.emergencyPhone = $.input('.emergencyPhone', true, null);	// 紧急联系方式
	empl.school = $.input('.school', false, null);	// 毕业院校
	empl.professional = $.input('.professional', false, null);	// 专业
	empl.graduationTime = $.input('.graduationTime', false, reg.date);	// 毕业时间
	empl.schooling = $.input('.schooling', false, null);	// 获得学历
	empl.degree = $.input('.degree', false, null);	// 获得学位
	empl.isPaySocialSecurity = $.radio('.isSocialSecurity', true, null);	// 是否缴纳社保
	empl.note = $.input('.note', false, null);	// 备注
	empl.company = findCompany();	// 单位信息
	empl.register = $.findRegister();	// 户籍地址
	empl.current = $.findCurrent(); 	// 现居住地址
	if(!$.isSubmit)
		BootstrapDialog.msg(submitText, BootstrapDialog.TYPE_DANGER);
	return $.isSubmit;
};
/*
 * 获取部门下拉框
 * te5l.com [K]
 */
function findDepartment(current){
	$('select.position').empty().append('<option value=0>请选择职位</option>'); // 设置默认职位
	$.depar = $('select.department').empty().append('<option value=0>请选择部门</option>');
	$.getJSON('./mgr/findDepartment', function(data){
		if(!$.isSuccess(data)) return;
		$.analyzeDepartmentOrPosition(current, $.depar, data.body);
	});
}
/*
 * 获取前工作单位信息
 * te5l.com [K]
 */
function findCompany(){
	var compnayLength = $('tr.compnay-tr').length;
	if(compnayLength < 1) return null;
	var compnayArray = new Array();	// 用来封装工作单位的数组
	for(var i = 0; i < compnayLength; i++){
		var compnay = {};	// 工作单位信息
		compnay.name = $.removeTrim($('input.cp-name').eq(i).val());
		compnay.state = $.removeTrim($('input.cp-state-date').eq(i).val());
		compnay.end = $.removeTrim($('input.cp-end-date').eq(i).val());
		compnay.jobs = $.removeTrim($('input.cp-jobs').eq(i).val());
		compnay.reason = $.removeTrim($('input.cp-reason').eq(i).val());
		if(compnay.name || compnay.state || compnay.end || compnay.jobs || compnay.reason) compnayArray.push(compnay);
	}
	return compnayArray;
}
/*
 * 遍历单选或多选项
 * te5l.com [K]
 */
function findChoose(current){
	$.getJSON('./mgr/employees/internship/findChoose', function(data){
		var educationList = $('td.educationList').empty();
		var marriageList = $('td.marriageList').empty();
		var nationalList = $('td.nationalList').empty();
		var politicListList = $('td.politicsList').empty();
		$.each(data.body.educationList, function(i,v){
			getLabelRadio(0, v.eduId, 'education', v.eduName).appendTo(educationList);
		});
		$.each(data.body.marriageList, function(i,v){
			getLabelRadio(0, v.marId, 'marriage', v.marName).appendTo(marriageList);
		});
		$.each(data.body.nationalList, function(i,v){
			getLabelRadio(0, v.natId, 'national', v.natName).appendTo(nationalList);
		});
		$.each(data.body.politicList, function(i,v){
			getLabelRadio(0, v.polId, 'politics', v.polName).appendTo(politicListList);
		});
		dialog.close();
	});
}
/*
 * 返回单选按钮
 * te5l.com [K]
 */
function getLabelRadio(current, id, name, text){
	return $("<label class='label'></label>")
	.append($("<input type='radio' class='"+name+"' value='"+id+"' name='"+name+"' "+ $.findChecked(current == id) +" />"))
	.append($("<span class='label-text float-left'></span>").append(text));
}
/*
 * 保存员工信息
 * te5l.com [K]
 */
function saveOrModifyEmployeesInfo(){ 
	detectionForm();
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.isSubmitted();
	$.post('./mgr/employees/internship/'+url,{data : JSON.stringify(empl)}, function(data){
		dialog.close();
		if(!$.isSuccess(data)) return;
		BootstrapDialog.show({
			title:"提示信息",
			type:BootstrapDialog.TYPE_SUCCESS,
			closable : true,
			message : "员工信息保存成功!",
			onhidden : function(dialogRef) {
				window.location.href = "employees_internship_info.html?moduleCode="+moduleCode+"&emplNo=" + data.body;
			}
		});
	}, 'json');
};
/*
 * 获取员工基本信息
 * te5l.com [K]
 */
$.findEmployeesInfo = function(emplId){
	if(!emplId) return;
	$.getJSON('./mgr/employees/internship/findEmployeesInfo',{emplId:emplId}, function(data){
		if(!$.isSuccess(data)) return;
		var info = data.body.info;
		$('input.fullName').val(info.name);
		$('div.new-title').text("修改 \""+info.name+"\" 信息");
		$('img.empl-photo-image').attr('src', info.photo ? info.photo : $('img.empl-photo-image').attr('src') );
		$('input.sex').eq(info.sex ? 1 : 0 ).prop('checked', true);
		$('input.identity').val(info.identity);
		$('input.birthday').val(info.birthday);
		$('input.social-security').val(info.socialSecurity);
		$('input.participate').val(info.participateTime);
		$('input.phone').val(info.phone);
		$('input.contact').val(info.contact);
		$('input.emergencyContact').val(info.emergencyContact);
		$('input.emergencyPhone').val(info.emergencyPhone);
		$('input.school').val(info.school);
		$('input.professional').val(info.professional);
		$('input.graduationTime').val(info.graduationTime);
		$('input.schooling').val(info.schooling);
		$('input.degree').val(info.degree);
		$('input.isSocialSecurity').eq(info.isSocialSecurity ? 0 : 1).prop('checked', true);
		$('input.note').val(info.note);
		// 户籍地址, 现居住地址
		var register = data.body.register;
		if(register){
			$.analyzeAddress(register.adsProvince, $('select.register-province'), data.body.province);
			$.analyzeAddress(register.adsCity, $('select.register-city'), data.body.registerCity);
			$.analyzeAddress(register.adsCounty, $('select.register-county'), data.body.registerCounty);
			$.analyzeAddress(register.adsTownship, $('select.register-township'), data.body.registerTownship);
			$.analyzeAddress(register.adsVillage, $('select.register-village'), data.body.registerVillage);
			$('input.register-detailed').val(register.adsDetailed);
		} else {
			$.analyzeAddress(0, $('select.register-province'), data.body.province);
		}
		var current = data.body.current;
		if(current){
			$.analyzeAddress(current.adsProvince, $('select.current-province'), data.body.province);
			$.analyzeAddress(current.adsCity, $('select.current-city'), data.body.currentCity);
			$.analyzeAddress(current.adsCounty, $('select.current-county'), data.body.currentCounty);
			$.analyzeAddress(current.adsTownship, $('select.current-township'), data.body.currentTownship);
			$.analyzeAddress(current.adsVillage, $('select.current-village'), data.body.currentVillage);
			$('input.current-detailed').val(current.adsDetailed);
		} else {
			$.analyzeAddress(0, $('select.current-province'), data.body.province);
		}
		$.analyzeCompanie(data.body.companies); // 工作经历 
		$.analyzeDepartmentOrPosition(info.deparemtn, $('select.department').empty().append('<option value=0>请选择部门</option>'), data.body.departments); // 部门
		$.analyzeDepartmentOrPosition(info.position, $('select.position').empty().append('<option value=0>请选择职位</option>'), data.body.positions); // 职位
		// 文化程度, 婚姻状况, 政治面貌, 民族,
		$.each(data.body.educations, function(i,v){
			getLabelRadio(info.education, v.eduId, 'education', v.eduName).appendTo($('td.educationList'));
		});
		$.each(data.body.marriages, function(i,v){
			getLabelRadio(info.marriage, v.marId, 'marriage', v.marName).appendTo($('td.marriageList'));
		});
		$.each(data.body.nationals, function(i,v){
			getLabelRadio(info.national, v.natId, 'national', v.natName).appendTo($('td.nationalList'));
		});
		$.each(data.body.politics, function(i,v){
			getLabelRadio(info.politics, v.polId, 'politics', v.polName).appendTo($('td.politicsList'));
		});
		dialog.close();
		if(info.state == 3 || info.state == 4){
			$('button.td-add-btn').remove();
			$('select, input, textarea').prop('disabled', true);
			BootstrapDialog.msg('该员工已 <b>离职</b> 或 <b>淘汰</b>, 所有信息不能编辑!', BootstrapDialog.TYPE_PRIMARY);
		}
	});
};