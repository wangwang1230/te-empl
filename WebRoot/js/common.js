var secure = null;	// 权限参数
var page = 1;	// 默认页面
var dialog = null;

$(function() {
	if(moduleCode >= 0)
		findMenu(moduleCode, initFun);
	$('td.table-val').css('padding','5px');
	$('input.date-before').on('click', function(){
		WdatePicker({maxDate:'%y-%M-{%d}'});
	});
	$('input.date-after').on('click', function(){
		WdatePicker({minDate:'%y-%M-{%d}'});
	});
	$('input.date').on('click', function(){
		WdatePicker();
	});
});

/*
 * 获取权限信息, 传入模块编号及回调函数
 * te5l.com [K]
 */
function findModuleParameter(moduleCode, initFun) {
	if(!moduleCode) return;
	$.getJSON('mgr/findModuleParameter', {moduleCode : moduleCode}, function(data){
		if(!$.isSuccess(data)) return;
		$('a.acctInfo').append(data.body.acctount);
		secure = data.body;
		if(!secure.find){
			$('div.main').remove();	// 删除页面主要元素
			BootstrapDialog.msg("非法操作, 你没有当前页面的权限!", BootstrapDialog.TYPE_DANGER);
			return;
		}
		$('div.main').removeClass('hide');
		if("0" != moduleCode){
			var obj = $('ol.breadcrumb').empty();
			obj.append($("<li></li>").append(data.body.superModuleName));
			obj.append($("<li ckass='active'></li>").append(data.body.moduleName));
			$('.navbar-nav').find('.dropdown[name='+data.body.code+']').addClass('active');
			$('title').text("TE网络 | "+data.body.moduleName + ' - ' + data.body.superModuleName+" [www.te5l.com]");
		}
		initFun();
	});
}
/*
 * 退出登录
 * te5l.com [K]
 */
function exit(){
	BootstrapDialog.confirm("请确认是是否需要注销登录!", function(result){
		if(!result) return;
		BootstrapDialog.show({
			title : "加载中",
			closable : false,
			message: "正在加载, 请稍等..."
	    });
		$.getJSON('mgr/exit', function(data){
			if(!$.isSuccess(data)) return;
			window.location.href="./login.html"; 
		});
	});
}
/*
 * 根据当前帐号的权限获取导航菜单
 * te5l.com [K]
 */
function findMenu(moduleCode, initFun) {
	$.getJSON('mgr/findMenu', function(data) {
		if(!$.isSuccess(data)) return;
		var nav = $('ul#nav-box-ul').empty();
		$.each(data.body, function(i, v) {
			if(!v.moduleLevel){
				$('<li class=\'dropdown\' name=\''+v.moduleCode+'\'></li>')
				.append($("<a href='dropdown-toggle' data-toggle='dropdown' href='javascript:void(0)'></a>")
				.append(v.moduleName+"<span class='caret'></span>"))
				.append(analyzeMenu(v.moduleCode, data.body))
				.appendTo(nav);
			}
		});
		findModuleParameter(moduleCode, initFun);
	});
}
/*
 * 获取面包绡
 * te5l.com [K]
 */
function findBreadcrumb(){
	$.post('mgr/findBreadcrumb', {moduleCode : moduleCode}, function(data){
		var obj = $('ol.breadcrumb').empty();
		obj.append($("<li></li>").append(data.body.superName));
		obj.append($("<li ckass='active'></li>").append(data.body.name));
		$('title').text(data.body.name + ' - ' + data.body.superName);
		$('.navbar-nav').find('.dropdown[name='+data.body.code+']').addClass('active');
	}, 'json');
}
/*
 * 最佳面包绡
 * te5l.com [K]
 */
function addBreadcrumb(msg){
	$('ol.breadcrumb').find('.active').removeClass('active');
	$('ol.breadcrumb').append($("<li class='active'></li>").append(msg));
}
/*
 * 解析导航菜单
 * te5l.com [K]
 */
function analyzeMenu(code, data){
	var ul = '';
	ul += "<ul class='dropdown-menu'>";
	$.each(data, function(i,v){
		if(v.moduleSuperCode == code) ul += "<li><a href='"+v.modulePage+"'>"+v.moduleName+"</a></li>"; 
	});
	ul += "</ul>";
	return ul;
}
BootstrapDialog.confirm = function(message, callback) {new BootstrapDialog({
        title: '提示信息',
        message: message,
        closable: false,
        data: {'callback': callback},
        buttons: [{
	        label: '取消',
	        action: function(dialog) {
	        	// 容易理解的写法 if(typeof dialog.getData('callback') === 'function') dialog.getData('callback')(false); // or callback(false);
	            typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(false);
	            dialog.close();
	        }
	    }, {
	        label: '确定',
	        cssClass: 'btn-primary',
	        action: function(dialog) {
	            typeof dialog.getData('callback') === 'function' && dialog.getData('callback')(true);
	            dialog.close();
	        }
	    }]
    }).open();
};
BootstrapDialog.alert = function(message, type){
	new BootstrapDialog({
		title : '提示信息',
		message : message,
		type : type,
		closeabled : true,
		buttons: [{label: '关闭',action: function(dialog) {dialog.close();}}]
	}).open();
};
BootstrapDialog.msg = function(message, type){
	new BootstrapDialog({
		title : '提示信息',
		message : message, 
		type : type,
		closeabled : false,
		backdrop : 'static'
	}).open();
};
BootstrapDialog.isSubmitted = function(){
	return BootstrapDialog.show({
		title : "正在提交",
		closable : false,
		message: "请稍等, 正在提交请求!"
    });
};
BootstrapDialog.loading = function(){
	return BootstrapDialog.show({
		title : "加载中",
		closable : false,
		message: "正在加载, 请稍等..."
    });
};
BootstrapDialog.hideModel = function(eml){
	eml.modal('hide');
};
BootstrapDialog.showModel = function(eml){
	eml.modal({backdrop : 'static', keyboard : false}).modal('show');
};
(function($) {
	// 获取传递的参数
	$.getUrlParam = function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	};
	// 获取项目根路径
	$.getRootPath = function(){
	    var curWwwPath=window.document.location.href; // 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname; // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pos=curWwwPath.indexOf(pathName);
	    var localhostPaht=curWwwPath.substring(0,pos); // 获取主机地址，如： http://localhost:8083
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); // 获取带"/"的项目名，如：/uimcardprj
	    return(localhostPaht+projectName);
	};
	// 删除空格
	$.removeTrim = function(str){
		return str.replace(/^\s+|\s+$/g,"");
	};
	// 分页插件
	$.analysisPage = function(data){
		$("#pagination ul").empty();
		if(data.totalPage < 2){
			$('nav#pagination').hide();
			return;
		}
		$("#pagination").pagy({
			totalPages: data.totalPage,
			currentPage: data.nowPage,
			innerWindow: 3,
			page : function(clickPage) {
				page = clickPage;
				if(page == data.nowPage) return false;
				dialog = BootstrapDialog.loading();
				findListInfo();
				return true;
			}
		});
	};
	$.isSubmit = true;	// 是否可提交
	$.verifyForm = function(eml, isEmpty){
		eml.removeClass('empty');
		if(!isEmpty) return eml.val();
		var val = eml.val();
		if(val < 1 || val.length < 1){
			$.isSubmit = false;
			eml.addClass('empty');
		}
		return val;
	};
	$.findChecked = function(val){
		return val ? " checked=true " : "" ;
	};
	$.findOpeion = function(id, current){
		return id == current ? " selected=true " : "" ;
	};
	// 点击搜索按钮, 弹出正在加载窗口, 调用findListInfo(); 函数获取列表数据!
	$.search = function(){
		dialog = BootstrapDialog.loading();
		findListInfo();
	};
	// 判断返回数据的JSON头是成功还是失败
	$.isSuccess = function(data) {
		if(data.head) return data.head;
		
		if (typeof data != 'object') {
			data = $.parseJSON(data);
		}
		
		if(!data.body) return;
		if((data.body == 'PERMISSION_DENIED' || data.body == 'UNLOGIN') && dialog != null){
			dialog.close();
			if(data.body == 'UNLOGIN'){
				BootstrapDialog.show({
					title : "错误",
					type : BootstrapDialog.TYPE_DANGER,
					message : data.body,
					onhide : function(dialog){window.location.href="./login.html"; }
				});
				return;
			}
		}
		BootstrapDialog.show({
			title : "错误",
			type : BootstrapDialog.TYPE_DANGER,
			message : data.body
		});
		return data.head;
	};
})(jQuery);


