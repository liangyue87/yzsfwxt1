var common = {
	/**
	 * 服务应用名称
	 */
	app_name: "yzsfwxt1",
	/**
	 * 应用服务IP地址及端口号
	 * IP:端口
	 */
	ip: "http://192.168.1.117:8097",
	//ip: "http://localhost:8097",
	//ip: "http://172.20.2.42:8082", 
    //ip: "http://220.178.92.76:8082",//外网
     
};

/**
 * 跳转
 * @param {Object} url 跳转地址
 * @param {Object} obj 1：下级跳转，2：同级跳转
 */
function goto(url, obj) {
	if(obj == 1) {
		window.location.href = url; //location.href实现客户端页面的跳转  
	} else if(obj == 2) {
		//window.location.href = url;//location.href实现客户端页面的跳转  
		window.location.replace(url);
	}
}

function jbxx() {
	var xmid = localStorage.getItem("xmid");
	$.ajax({
		type: "get",
		url: common.ip + "/XkylxController/getXmxx",
		async: true,
		data: {
			"xmid": xmid,
		},
		success: function(res) {
			var jsonObject = eval("(" + res + ")");
			var xmxx = eval("(" + jsonObject.xmxx + ")");		
			$("#xmmc").text(xmxx.xmmc);
			$("#xmdz").text(xmxx.xmdz);
			$("#dwmc").text(xmxx.dwmc);
			$("#lxr").text(xmxx.lxr);
			$("#lxfs").text(xmxx.lxrsjh);
			$("#xmjl").text(xmxx.xmjl_str);
			$("#jl_lxfs").text(xmxx.xmjl_sjh);
		}
	});
}

function checkUpdate() {
	var nowVer = null;
	plus.runtime.getProperty(plus.runtime.appid, function(inf) {
		nowVer = inf.version;		
		console.log(nowVer);
		plus.nativeUI.showWaiting("检测更新...");
		$.ajax({
			type: 'post',
			url: common.ip + "/XkylxController/appUpdate",
			contentType: "application/json;charset=utf-8",
			cache: false,
			data: "",
			dataType: 'json',
			error: function() {
				plus.nativeUI.closeWaiting();
			},
			success: function(data) {
		
				if(data.apkCode > nowVer) {
					var btnArray = ['是', '否'];
					mui.confirm('有新版本[' + data.apkCode + ']可更新，是否更新？', '升级', btnArray, function(e) {
						if(e.index == 0) {
							//更新
							var apkUrl = data.apkUrl;
							downWgt(apkUrl);
						}
					})
				} else {
					//alert(newVer+"----"+nowVer);
				}
				plus.nativeUI.closeWaiting();
			}
		});
	});

	// 下载wgt文件
	//var updateUrl = localStorage.HDWURL+"/file/H5509211F.wgt";

}

function downWgt(apkUrl) {

	plus.nativeUI.showWaiting("下载更新文件...");
	//apkUrl="http://192.168.1.117:80/";
	//console.log(apkUrl);
	var dtask = plus.downloader.createDownload(apkUrl, {filename:"_doc/update/"}, function(d, status) {
		if(status == 200) {				
			var path = d.filename; //下载apk
			plus.runtime.install(path); // 自动安装apk文件  
		} else {
			plus.nativeUI.alert('版本更新失败:' + status);
		}
		plus.nativeUI.closeWaiting();
	});
	dtask.start();
}

/*function installWgt(path) {
	//alert("开始安装")
	console.log(path + '------------');
	plus.nativeUI.showWaiting("安装更新文件...");
	//alert(path+"");
	plus.runtime.install(path, {}, function() {
		plus.nativeUI.closeWaiting();
		//alert("安装成功！");
		plus.nativeUI.alert("应用资源更新完成！", function() {
			plus.runtime.restart();
		});
	}, function(e) {
		plus.nativeUI.closeWaiting();
		alert("安装失败：[" + e.code + "]：" + e.message);
	});
}
*/