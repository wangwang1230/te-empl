var moduleCode = '-1';
function initFun(){};
$(function() {
	$('div#login-modal').modal({
		keyboard : false,
		backdrop : false
	});
	$('.verify, .username, .password').bind('keyup', function(event) {
		if (event.keyCode == "13") login();
	});
	$('img.login-verify-img').on('click', function(data){resetVerifyCode();});
});
function login() {
	$.isSubmit = true;
	var username = $.verifyForm($('input.username'), true);
	var password = $.verifyForm($('input.password'), true);
	var verify = $.verifyForm($('input.verify'), true);
	if(!$.isSubmit) return;
	dialog = BootstrapDialog.loading();
	$.post('mgr/0/acctLogin', {
		name : username,
		pass : password,
		verify : verify
	}, function(data) {
		dialog.close();
		if (data.head) {
			window.location.href = 'index.html';
			return;
		}
		$('td.hint-msg').text(data.body);
		resetVerifyCode();
	}, 'json');
	// 验证表单, 发送ajax请求, 用户登录, 页面盅
}
function reset() {
	$('input.username').val('');
	$('input.password').val('');
	$('input.verify').val('');
	// 重新请求验证码图片
	resetVerifyCode();
}
function resetVerifyCode(){
	$('img.login-verify-img').attr('src', './mgr/0/findVerifydCode?'+new Date().getTime());
}
