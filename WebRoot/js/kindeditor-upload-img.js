KindEditor.ready(function(K) {
	var editor = K.editor({
		uploadJson : './mgr/uploadImg', // 服务端处理URL
		allowFileManager : false // 允许对上传的文件进行管理
	});
	K('img.empl-photo-image').click(function() {
		editor.loadPlugin('image', function() { // 加载图片上传插件
			editor.plugin.imageDialog({ // 显示图片上传的窗口
				showRemote : false, // 显示远程上传图片的tag标签
				clickFn : function(url, title, width, height, border, align) { // 上传完成后的回调
					editor.hideDialog(); // 隐藏弹窗
					console.log(url);
					$('img.empl-photo-image').attr('src', "."+url);
				}
			});
		});
	});
});
