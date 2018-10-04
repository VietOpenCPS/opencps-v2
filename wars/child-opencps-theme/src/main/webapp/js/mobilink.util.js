function showWindowAlert(template, title, message, target) {

	var dfd = new jQuery.Deferred();
	var result = false;

	$("<div id='alertPopupWindow'></div>")
		.appendTo(target)
		.kendoWindow({
			width: "300px",
			modal: true,
			title: title,
			resizable: false,
			visible: false,
			close: function(e) {
				this.destroy();
				dfd.resolve(result);
			 }
		}).data('kendoWindow').content($(template).html()).center().open();

	$('.popupMessage').html(message);

	$('#alertPopupWindow .close').val('Đóng');
	
	$('#alertPopupWindow').parent().addClass("k-custom-window");

	$('#alertPopupWindow .close').click(function() {
		 $('#alertPopupWindow').data('kendoWindow').close();
	});

	return dfd.promise();
};

function showWindowConfirm(template, title, message, target) {

	var dfd = new jQuery.Deferred();
	var result = false;

	$("<div id='popupWindow'></div>")
		.appendTo(target)
		.kendoWindow({
			width: "300px",
			modal: true,
			title: title,
			resizable: false,
			visible: false,
			close: function(e) {
				this.destroy();
				dfd.resolve(result);
				
			}
		}).data('kendoWindow').content($(template).html()).center().open();

	$('.popupMessage').html(message);
	
	$('#popupWindow').parent().addClass("k-custom-window");
	
	$('#popupWindow .confirm_yes').val('Đồng ý');
	$('#popupWindow .confirm_no').val('Bỏ qua');

	$('#popupWindow .confirm_no').click(function() {
		result = false;
		$('#popupWindow').data('kendoWindow').close();
		//$('#popupWindow').data('kendoWindow').destroy();
	});

	 $('#popupWindow .confirm_yes').click(function() {
		result = true;
		$('#popupWindow').data('kendoWindow').close();
	});

	 return dfd.promise();
};

function makeid(length) {
	var text = "";
	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	for (var i = 0; i < length; i++){
		text += possible.charAt(Math.floor(Math.random() * possible.length));
	}
	return text;
}

function validation(target, errorTemplate){
	var validator = $(target).kendoValidator({
		validateOnBlur: true,
		//errorTemplate: errorTemplate,
		
		validate: function(e) {
			
		},
		validateInput: function(e) {
						
			var input = $(e.input);
			
			if(e.valid){
				input.parent().removeClass('has-error');
			}else{
				input.parent().addClass('has-error');
			}
		},
		messages: {
			required: "Bắt buộc!",
			datePicker: "Ngày tháng không hợp lệ!",
			fileSize: "Kích thước tệp tin vượt quá giới hạn cho phép!",
			fileType: "File không hợp lệ!",
			invalidCode: "Dữ liệu không hợp lệ!",
			maxLength: "Số ký tự vượt quá giới hạn cho phép!"
		},
		rules:{
			invalidCode: function(e){
				if ($(e).is("[data-invalidCode]")) {
					var value = $(e).val();
					if(value == null || value == ''){
						return false;
					}
				}

				return true;
			},
			maxLength: function(e){
				if ($(e).is("[data-maxLength]")) {
					var maxLength = $(e).attr("data-maxLength");
					var value = $(e).val();
					return value.replace(/<[^>]+>/g, "").length <= parseInt(maxLength);
				}
				return true;
			},
			minLength: function(e){
				if ($(e).is("[data-minLength]")) {
					var minLength = $(e).attr("data-minLength");
					var value = $(e).val();
					return value.replace(/<[^>]+>/g, "").length >= parseInt(minLength);
				}
				return true;
			},
			datePicker: function(e) {
				if ($(e).is("[data-datePicker]")) {
					
					if ($(e).val()==null || $(e).val()=="")
						return true;
					else
						return $(e).data("kendoDatePicker").value();
				} else {
					return true;
				}
			},
			fileSize: function(e){
				if ($(e).is("[data-fileSize]")) {
					var size = $(e).attr("data-fileSize");
					
					var files = $(e).prop("files");
					
					var isValid = true; 
					
					if(files){
						$.each(files, function(index, file){
							if(file.size > parseInt(size)){
								isValid = false;
							}
						});
						
						return isValid;
					}
				}
				return true;
			},
			fileType: function(e){
				if ($(e).is("[data-fileType]")) {
					var types = $(e).attr("accept").split(',');
					
					var files = $(e).prop("files");
					
					var isValid = true; 
					
					if(files){
						$.each(files, function(index, file){
							if(file.type == ""){
								isValid = false;
							}else{
								var extension = "." + file.name.split('.').pop();
								
								if($.inArray(extension, types) < 0){
									isValid = false;
								}
							}
						});
						
						return isValid;
					}
				}
				return true;
			},
		}
	});
	
	return validator;
}

function allowFileSize(size, limit){
	if(parseInt(size) > parseInt(limit)){
		return false;
	}else{
		return true;
	}
}

function allowFileType(type, types){
	if($.inArray(type, types) < 0){
		return false;
	}else{
		return true;
	}
}

function convertFileSize(bytes) {
	var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
	if (bytes == 0) return '0 Byte';
	var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
	return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
};


function selectBoxEditable(target, dataSource, updateURL, callback){

	var value = "";
	var instance = $(target).editable({
		
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			headers: {
				"groupId": $(target).attr('data-groupId')
			}
		},
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			value = params.value;
			return obj;
		},
		
		success: function(data) {
			$(target).attr('data-value', value);
			callback(data);
		},
		error: function(xhr, id, obj) {
			showMessageByAPICode(xhr.status);
		}
	});	
	
	return instance;
};

function editable(target, dataSource, updateURL, callback){

	var value = "";
	var instance = $(target).editable({
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			headers: {
				"groupId": $(target).attr('data-groupId')
			}
		},
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			value = params.value;
			if ( params.name == "docfile-notation" ) {
				obj = params.value;
			} else if (params.name == "docfile-content") {
				obj["content"] = params.value;
			};
			
			return obj;
		},
		emptytext: "---",
		success: function(data) {

			$(target).attr('data-value', value);
			callback(data);
		},
		error: function(xhr, id, obj) {
			showMessageByAPICode(xhr.status);
		}
	});	
	
	return instance;
};

function dateEditable(target, dataSource, updateURL, callback){
	var d = new Date();
	var n = d.getFullYear();
	var value = "";
	var instance = $(target).editable({
		value: value,
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			headers: {
				"groupId": $(target).attr('data-groupId')
			}
		},
		template: 'DD/MM/YYYY',
		format: 'YYYYMMDD',
		viewformat: 'DD/MM/YYYY',
		combodate: {
			minYear: n,
			maxYear: n + 2,
		},
		emptytext: "__/__/__",
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			value = params.value;
			return obj;
		},
		success: function(data) {
			$(target).attr('data-value', value);
			callback(data);
		},
		error: function(xhr, id, obj) {

			showMessageByAPICode(xhr.status);
		}
	});	
	
	return instance;
};

function dateTimeEditable(target, dataSource, updateURL, callback){
	var d = new Date();
	var n = d.getFullYear();
	var value = "";
	var instance = $(target).editable({
		value: value,
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			headers: {
				"groupId": $(target).attr('data-groupId')
			}
		},
		template: 'HH:MM DD/MM/YYYY',
		format: 'YYYYMMDDHHmmss',
		viewformat: 'HH:MM DD/MM/YYYY',
		combodate: {
			minYear: n,
			maxYear: n + 2,
		},
		emptytext: "__/__/__",
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			value = params.value;
			return obj;
		},
		success: function(data) {
			$(target).attr('data-value', value);
			callback(data);
		},
		error: function(xhr, id, obj) {
			showMessageByAPICode(xhr.status);
		}
	});	
	
	return instance;
};

function select2BoxEditable(target, dataSource, updateURL, callback){
	
	var value = "";
	var instance = $(target).editable({
		//value: value,
		//source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			headers: {
				"groupId": $(target).attr('data-groupId')
			}
		},
		select2: {
			
			minimumInputLength: 0,
			id: function(item) {
				return item.id;
			},
			source: dataSource,
			formatResult: function(item) {
				return item.text;
			},
			formatSelection: function(item) {
				return item.text;
			},
			initSelection: function(element, callback) {
				
			}
		},
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			value = params.value;
			return obj;
		},
		
		success: function(data) {
			$(target).attr('data-value', value);
			callback(data);
		},
		error: function(xhr, id, obj) {
			showMessageByAPICode(xhr.status);
		}
	});	
	
	return instance;
};

function typeaheadjsEditable(target, dataSource, updateURL, callback){
	
	var instance = $(target).editable({
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
			headers: {
				"groupId": $(target).attr('data-groupId')
			}
		},
		typeahead: {
			name: 'language',
			local: dataSource,
			template: function(item) {
				
				return item.text; 
			},
			display: "text",
			val: "value"
		},
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			value = params.value;
			return obj;
		},
		
		success: function(data) {
			$(target).attr('data-value', value);
			callback(data);
		},
		error: function(xhr, id, obj) {
			showMessageByAPICode(xhr.status);
		}
	});
	
	return instance;
};

var messages = {
	"notifications":{
		"vi": {
			"default":{
				"success1": "Yêu cầu của bạn đã được xử lý thành công.",
				"error1": "Yêu cầu của bạn đã được xử lý không thành công!",
				"error2": "Bạn không có quyền thực hiện tác vụ này!",
				"error3": "Tài nguyên truy cập không tồn tại!",
				"warning1":"Một số thành phần dữ liệu không được tải về đầy đủ. Bạn hãy thử tải lại trang"
			},
			"voting":{
				"warning1": "Bạn chưa chọn ý kiến bình chọn."
			}, 
			"attachment": {
				
			}
		}
	}
}

function fileAttachmentDownload ( options) {
	
	// Use XMLHttpRequest instead of Jquery $ajax
	var xhttp = new XMLHttpRequest();
	var a,filename;
	var data = {};
	
	xhttp.onreadystatechange = function() {
		
		if (xhttp.readyState === 4 && xhttp.status === 200) {
		
			// check for a filename
			var disposition = xhttp.getResponseHeader('Content-Disposition');
			if (disposition && disposition.indexOf('attachment') !== -1) {
				var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
				var matches = filenameRegex.exec(disposition);
				if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
			}
			
			// Trick for making downloadable link
			a = document.createElement('a');
			a.href = window.URL.createObjectURL(xhttp.response);
			// Give filename you wish to download
			a.download = filename;
			a.style.display = 'none';
			document.body.appendChild(a);
			a.click();
			
			//callback success
			options.success(xhttp.status);
		} else if (xhttp.readyState === 4 && xhttp.status !== 200) {
			options.error(xhttp.status);
		}
		
	};
	
	// Post data to URL which handles post request
	xhttp.open(options.method, options.url);
	xhttp.setRequestHeader("Content-Type", "application/json");
	
	// others data header
	if (options.hasOwnProperty("headers")){
		Object.keys( options.headers ).map(function(objectKey, index) {
			var value = options.headers[objectKey];
			xhttp.setRequestHeader(objectKey, value);
		});
	}

	// You should set responseType as blob for binary responses
	if (options.hasOwnProperty("responseType")){
		xhttp.responseType = options.responseType;
	} else {
		xhttp.responseType = 'blob';
	}
	
	// Data to post
	if (options.hasOwnProperty("data")){
		data = options.data;
	}
	
	// excecute request
	xhttp.send(data);
	
};

function getImageBlob(url, imgTarget, groupId){
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.setRequestHeader("groupId", groupId);
	xhr.responseType = "arraybuffer";
	xhr.onerror = function() {};
	xhr.onload = function () {
		if (this.readyState == 4 && this.status === 200) {
			//console.log(this.response, typeof this.response);
			//console.log(this.getResponseHeader('content-type'));
			var blob = new Blob([xhr.response], {type: this.getResponseHeader('content-type')});
			var objectUrl = URL.createObjectURL(blob);
			//window.open(objectUrl);
			imgTarget.attr("src", objectUrl);
		}
	};
	xhr.send();
}

function arrayToTree(arr, idKey, parentKey, childrenKey) {
	var tree = [],
	mappedArr = {},
	arrElem,
	mappedElem;

	//remove level cause kendo panelBar have a function level()
	arr = arr.map(function(item) {
		
		if (item.hasOwnProperty('level')) {
			item.treeLevel = item.level;
			delete item.level;
		}
		return item; 
	});

	idKey = (idKey==null || idKey=="" || idKey === undefined)?"id":idKey;
	parentKey = (parentKey==null || parentKey=="" || parentKey === undefined)?"parentId":parentKey;
	childrenKey = (childrenKey==null || childrenKey=="" || childrenKey === undefined)?"childrens":childrenKey; 

	// First map the nodes of the array to an object -> create a hash table.
	for(var i = 0, len = arr.length; i < len; i++) {
		arrElem = arr[i];
		mappedArr[arrElem[idKey]] = arrElem;
		mappedArr[arrElem[idKey]][childrenKey] = [];
	}


	for (var id in mappedArr) {
		
		if (mappedArr.hasOwnProperty(id)) {
			mappedElem = mappedArr[id];
			// If the element is not at the root level, add it to its parent array of children.
			if (mappedElem[parentKey]) {
				mappedArr[mappedElem[parentKey]][childrenKey].push(mappedElem);
			}
			// If the element is at the root level, add it to first level elements array.
			else {
				tree.push(mappedElem);
			}
		}
	}
	return tree;
};

function showMessageToastr(type, message){
	toastr.options = {
		"closeButton": true,
		"debug": false,
		"progressBar": true,
		"positionClass": "toast-top-right",
		"onclick": null,
		"showDuration": "400",
		"hideDuration": "1000",
		"timeOut": "4000",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	};
   toastr[type](message);
}

function showMessageByAPICode (code) {

	var message,status;

	switch (code) {

		case 200:
			message = "Yêu cầu của bạn được xử lý thành công!";
			status = "success";
			break;

		case 401:
			message = "Yêu cầu của bạn xử lý thất bại, chưa đăng nhập vào hệ thống!!!";
			status = "error";
			break;

		case 403:
			message = "Yêu cầu của bạn xử lý thất bại, không có quyền thay đổi dữ liệu!!!";
			status = "error";
			break;

		case 404:
			message = "Yêu cầu của bạn xử lý thất bại, không tìm thấy tài nguyên!!!";
			status = "error";
			break;

		case 405:
			message = "Yêu cầu không được phép xử lý!!!";
			status = "error";
			break;

		case 409:
			message = "Yêu cầu của bạn xử lý thất bại, xung đột dữ liệu";
			status = "error";
			break;

		case 500:
			message = "Yêu cầu của bạn xử lý thất bại, lỗi hệ thống";
			status = "error";
			break;

		default:
			message = "Lỗi kết nối!!!";
			status = "error";
			break;
	}

	showMessageToastr(status, message);

};

;(function(factory) {
	'use strict';
	if (typeof define === 'function' && define.amd) {
		define(['jquery'], factory);
	} else if (typeof exports !== 'undefined') {
		module.exports = factory(require('jquery'));
	} else {
		factory(jQuery);
	}

  }(function($) {

	'use strict';

	var FilterMenu = {

		options: {

			inputClass: "",
			keywordClass: "",
			panelBarId: "",
			dataSource: [],
			doFilter: function(){},
			createPanelBar: false,
			configPanelBar: {
				option: {},
				onSelect: function(){}
			},
			refreshSelected: function(){}

		},

		kendoPanelBar: null,

		filterKeys: {},

		init: function(options, e){
		
			this.options = options;
			this._doFilter(e, options.doFilter);

			if ( this.options.createPanelBar ) {

				this._createPanelBar(options.configPanelBar);

			} else {

				this._initPanelBar();

			}

		},

		_createPanelBar: function(config){

			FilterMenu.kendoPanelBar = $(this.options.panelBarId).kendoPanelBar(config).data("kendoPanelBar");

		},

		getFilterValue: function(){

			return this.filterKeys;

		},
		
		getPanelBar: function(){

			return this.kendoPanelBar;

		},

		_initPanelBar: function() {

			FilterMenu.kendoPanelBar = $(this.options.panelBarId).data("kendoPanelBar");

		},

		render: function(callback){

			callback();

		},
		
		_refreshSelected: function(){
			
			this.filterKeys = {};
			this.options.refreshSelected();
		},
		
		register: function(){
			FilterMenu.options = this.options;
			return FilterMenu;

		},

		_doFilter: function(e, callback){
			
			var that = this;
			
			// input class change
			$( e ).delegate( that.options.inputClass, "change", function(event) {
				
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();

				var currentTarget = $(event.currentTarget);

				var name = currentTarget.attr('name');
				var value = currentTarget.val();

				that.filterKeys[name] = value;

				if ( currentTarget.is('select') ) {

					var text = currentTarget.find("option:selected").text();
					that.filterKeys[name + "_text"] = (value == "" || value == null )?"":text;

				}
				
				//reset keywords
				$( that.options.keywordClass ).find('input[type=text]').filter(':visible:first').val("");
				that.filterKeys.keywords = "";
				
				callback(that.filterKeys);

			});
			
			// keyword input change
			$(document).delegate(that.options.keywordClass, "keyup",function(event){
				
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();

				var keywords = $(this).find('input[type=text]').filter(':visible:first');

				if( event.keyCode == 13 && keywords.val() != that.filterKeys[keywords.attr('name')] ) {
					
					that._refreshSelected();
					//reset
					that.filterKeys[keywords.attr('name')] = keywords.val();
					callback(that.filterKeys);
				}
				
			}).delegate(that.options.keywordClass, "click",function(event){
				
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();
				
				if ( !$(this).find('input[type=text]').is(":focus") ) {

					var keywords = $(this).find('input[type=text]').filter(':visible:first');
	
					that.filterKeys[keywords.attr('name')] = that.filterKeys[keywords.attr('name')] === undefined?"":FilterMenu.filterKeys[keywords.attr('name')];
	
					if( keywords.val() != that.filterKeys[keywords.attr('name')] ) {
						
						that._refreshSelected();
						//reset
						that.filterKeys[keywords.attr('name')] = keywords.val();
						callback(that.filterKeys);
					}
				}
				
			});
			
			$( e ).delegate( that.options.panelBarId, "click",function(event){ 
					
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();
				var currentTarget = $(event.currentTarget);
				var  params = currentTarget[0]["params"] ;
				
				that.filterKeys = (params!=null && typeof params === 'object')?params:{};;
				//reset keywords
				$( that.options.keywordClass ).find('input[type=text]').filter(':visible:first').val("");
				delete that.filterKeys["keywords"];
				
				callback(that.filterKeys);
	
	});

		}

	}
	
	$.fn.filterMenu = function(options) {
		
		var filterMenu = Object.create(FilterMenu);

		filterMenu.init(options, this);
		options.doFilter;
		return filterMenu;

	};

}));