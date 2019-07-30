//选取图片的来源，拍照和相册  
function showActionSheet(conf) {
	var divid = conf.id;
	var inputid = conf.nextElementSibling.id
	var actionbuttons = [{
		title: "拍照"
	}, {
		title: "相册选取"
	}];
	var actionstyle = {
		title: "选择照片",
		cancel: "取消",
		buttons: actionbuttons
	};
	plus.nativeUI.actionSheet(actionstyle, function(e) {
		if(e.index == 1) {
			getImage(divid, inputid);
		} else if(e.index == 2) {
			galleryImg(divid, inputid);
		}
	});
}

//相册选取
function galleryImg(divid, inputid) {
	//console.log(divid)
	plus.gallery.pick(function(e) {
		//console.log(e.files.length);
		var zm = 0;
		setTimeout(file, 200);

		function file() {
			plus.io.resolveLocalFileSystemURL(e.files[zm], function(entry) {
				compressImage(entry.toLocalURL(), entry.name, divid, inputid);
			}, function(e) {
				plus.nativeUI.toast("读取拍照文件错误：" + e.message);
			});
			zm++;
			if(zm < e.files.length) {
				setTimeout(file, 200);
			}
		}
	}, function(e) {
		//console.log("取消选择图片");
	}, {
		filename: "_doc/camera/",
		filter: "image",
		multiple: true
	});
}
// 拍照  
function getImage(divid, inputid) {
	var cmr = plus.camera.getCamera();
	cmr.captureImage(function(p) {
		plus.io.resolveLocalFileSystemURL(p, function(entry) {
			//console.log(entry)
			compressImage(entry.toLocalURL(), entry.name, divid, inputid);
		}, function(e) {
			plus.nativeUI.toast("读取拍照文件错误：" + e.message);
		});
	}, function(e) {}, {
		filename: "_doc/camera/",
		index: 1
	});
}

//压缩图片  
function compressImage(url, filename, divid, inputid) {
	//console.log(divid)
	//console.log("111="+filename)
	var name = "_doc/upload/" + divid + "-" + filename; //_doc/upload/F_ZDDZZ-1467602809090.jpg  
	//console.log(name)
	plus.zip.compressImage({
			src: url, //src: (String 类型 )压缩转换原始图片的路径  
			dst: name, //压缩转换目标图片的路径  
			quality: 20, //quality: (Number 类型 )压缩图片的质量.取值范围为1-100  
			overwrite: true //overwrite: (Boolean 类型 )覆盖生成新文件  
		},
		function(event) {
			//console.log(event)
			//uploadf(event.target,divid);  
			var path = name; //压缩转换目标图片的路径  
			//event.target获取压缩转换后的图片url路  
			//filename图片名称  
			saveimage(event.target, divid, filename, path, inputid);
		},
		function(error) {
			plus.nativeUI.toast("压缩图片失败，请稍候再试");
		});
}


//保存信息到本地  
/**  
 *   
 * @param {Object} url  图片的地址  
 * @param {Object} divid  字段的名称  
 * @param {Object} name   图片的名称  
 */
function saveimage(url, divid, name, path, inputid) {
	//alert(url);//file:///storage/emulated/0/Android/data/io.dcloud...../doc/upload/F_ZDDZZ-1467602809090.jpg  
	//alert(path);//_doc/upload/F_ZDDZZ-1467602809090.jpg  
	var state = 0;
	var wt = plus.nativeUI.showWaiting();
	//  plus.storage.clear();   
	name = name.substring(0, name.indexOf(".")); //图片名称：1467602809090  
	//console.log(name)
	var id = document.getElementById(inputid).value; //429
	var itemname = id + "img-" + divid; //429img-F_CKJLB_1
   //  var itemname =divid;


	itemvalue = plus.storage.getItem(itemname);


	if(itemvalue == null) {
		itemvalue = "{" + name + "," + path + "," + url + "}";
		//console.log(itemvalue)
		//{IMG_20160704_112614,_doc/upload/F_ZDDZZ-IMG_20160704_112614.jpg,file:///storage/emulated/0/Android/data/io.dcloud...../doc/upload/F_ZDDZZ-1467602809090.jpg}
	} else {
		itemvalue = itemvalue + "{" + name + "," + path + "," + url + "}";
		//console.log(itemvalue)
	}
	plus.storage.setItem(itemname, itemvalue);

	var src = 'src="' + url + '"';
	//alert("itemvalue="+itemvalue);  
	//console.log(id)
	showImgDetail(name, divid, id, src, inputid, path);
	appendFile(path);
	wt.close();

}


//加载页面初始化需要加载的图片信息  
//或者相册IMG_20160704_112620.jpg  
//imgId:图片名称：1467602809090或者IMG_20160704_112620  
//imgkey:字段例如：F_ZDDZZ  
//ID：站点编号ID,例如429  
//src：src="file:///storage/emulated/0/Android/data/io.dcloud.HBuilder/.HBuilder/apps/HBuilder/doc/upload/F_ZDDZZ-1467602809090.jpg"  
//1
function showImgDetail(imgId, imgkey, id, src, inputid, path) {
	console.log("111=" + path) //F_CKJLB_1
	//console.log("111=" + imgkey) //F_CKJLB_1
	//console.log("222=" + inputid) //ckjl.id
	var html = "";
	html += '<div  id="Img' + imgId + imgkey + '" class="image-item">';
	html += '    <img id="picBig" data-preview-src="" data-preview-group="1" ' + src + '/>';
	html += '   <span class="del"  onclick="delImg(\'' + imgId + '\',\'' + imgkey + '\',' + id + ',\'' + path + '\');">';
	html += '        <div class="fa fa-times-circle"></div>';
	html += '    </span>';
	html += '</div>';

	$("#" + imgkey).parent().append(html);
	//$("#" + imgkey +'S').append(html);
	//$("#"+ 'F_CKJLBS')	
}
//删除图片  
//imgId:图片名称：IMG_20160704_112614  
//imgkey:字段，例如F_ZDDZZ  
//ID：站点编号ID，例如429  
function delImg(imgId, imgkey, id, path) {
	var bts = ["是", "否"];
	plus.nativeUI.confirm("是否删除图片？", function(e) {
		var i = e.index;
		if(i == 0) {
			var itemname = id + "img-" + imgkey; //429img-F_ZDDZZ  
			var itemvalue = plus.storage.getItem(itemname);
			//{IMG_20160704_112614,_doc/upload/F_ZDDZZ-IMG_20160704_112614.jpg,file:///storage/emulated/0/Android/data/io.dcloud...../doc/upload/F_ZDDZZ-1467602809090.jpg}  
			if(itemvalue != null) {
				var index = itemvalue.indexOf(imgId + ",");
				if(index == -1) { //没有找到  
					delImgfromint(imgId, imgkey, id, index);
				} else {
					delFile(path);
					delImgFromLocal(itemname, itemvalue, imgId, imgkey, index); //修改，加了一个index参数  
				}
			} else {
				delImgfromint(imgId, imgkey, id);
			}
		}
	}, bts);
}

function delImgFromLocal(itemname, itemvalue, imgId, imgkey, index) {
	var wa = plus.nativeUI.showWaiting();
	var left = itemvalue.substr(0, index - 1);
	var right = itemvalue.substring(index, itemvalue.length);
	var end = right.indexOf("}");
	right = right.substring(end + 1, right.length);
	var newitem = left + right;
	plus.storage.setItem(itemname, newitem);
	//myAlert("删除成功");  
	$("#Img" + imgId + imgkey).remove();
	wa.close();
}