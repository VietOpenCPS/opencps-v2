function showWindowAlert(template, title, message, target) {

	 var dfd = new jQuery.Deferred();
	 var result = false;

	$("<div id='alertPopupWindow'></div>")
		 .appendTo(target)
		 .kendoWindow({
			width: "300px",
			modal: true,
			title: title,
			modal: true,
			resizable: false,
			visible: false,
			close: function(e) {
				this.destroy();
				dfd.resolve(result);
			 }
		}).data('kendoWindow').content($(template).html()).center().open();

	$('.popupMessage').html(message);

	$('#alertPopupWindow .close').val('Đóng');

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
			modal: true,
			resizable: false,
			visible: false,
			close: function(e) {
				this.destroy();
				dfd.resolve(result);
			 }
		}).data('kendoWindow').content($(template).html()).center().open();

	$('.popupMessage').html(message);

	$('#popupWindow .confirm_yes').val('Đồng ý');
	$('#popupWindow .confirm_no').val('Bỏ qua');

	$('#popupWindow .confirm_no').click(function() {
		 $('#popupWindow').data('kendoWindow').close();
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


function selectBoxEditable(target, value, dataSource, updateURL){
	var instance = $(target).editable({
		value: value,
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
		},
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			obj.docFileId = params.pk;
			
			return {
				query: JSON.stringify(obj)
			}
		},
		
		success: function(data) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thành công!"
			}, "success");
		},
		error: function(event, id, obj) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thất bại!"
			}, "error");
		}
	});	
	
	return instance;
};

function editable(target, value, dataSource, updateURL){
	var instance = $(target).editable({
		value: value,
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
		},
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			obj.docFileId = params.pk;
			
			return {
				query: JSON.stringify(obj)
			}
		},
		emptytext: "---",
		success: function(data) {
			
			notification.show({
				message: "Yêu cầu của bạn được xử lý thành công!"
			}, "success");
		},
		error: function(event, id, obj) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thất bại!"
			}, "error");
		}
	});	
	
	return instance;
};

function dateEditable(target, value, dataSource, updateURL){
	var d = new Date();
	var n = d.getFullYear();
	var instance = $(target).editable({
		value: value,
		source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
		},
		template: 'DD/MM/YYYY',
		format: 'YYYY/MM/DD',
		viewformat: 'DD/MM/YYYY',
		combodate: {
			minYear: n,
			maxYear: n + 2,
		},
		emptytext: "__/__/__",
		params: function(params) {
			var obj = {};
			obj[params.name] = params.value;
			obj.docFileId = params.pk;
			
			return {
				query: JSON.stringify(obj)
			}
		},
		success: function(data) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thành công!"
			}, "success");
		},
		error: function(event, id, obj) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thất bại!"
			}, "error");
		}
	});	
	
	return instance;
};

function select2BoxEditable(target, value, dataSource, updateURL){
	
	var instance = $(target).editable({
		//value: value,
		//source: dataSource,
		mode: 'inline',
		url: updateURL,
		ajaxOptions: {
			method: 'PUT',
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=utf-8",
		},
		select2: {
            placeholder: 'Chọn cơ quan ban hành ...',
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
			obj.docFileId = params.pk;
			
			return {
				query: JSON.stringify(obj)
			}
		},
		
		success: function(data) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thành công!"
			}, "success");
		},
		error: function(event, id, obj) {
			notification.show({
				message: "Yêu cầu của bạn được xử lý thất bại!"
			}, "error");
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
        panelBarId: "",
        dataSource: [],
        filterClass: "",
        filterId: "",
        doFilter: function(){}

      },

      kendoPanelBar: null,

      filterKeys: {

        dataExpand: []

      },

      init: function(options, e){
        
        this.options = options;
        this._initPanelBar();
        this._doFilter(e, options.doFilter);

      },

      createPanelBar: function(){

      },

      getFilterValue: function(){

        return this.filterKeys;

      },
      
      getPanelBar: function(){

        return this.kendoPanelBar;

      },

      _initPanelBar: function() {

        this.kendoPanelBar = $(this.options.panelBarId).data("kendoPanelBar");

      },

      render: function(callback){

        callback();

      },
      
      register: function(){

        return FilterMenu;

      },

      _doFilter: function(e, callback){
        
        $( e ).delegate( this.options.inputClass, "change", function(event) {
          
          event.preventDefault();
          event.stopPropagation();
          event.stopImmediatePropagation();

          var currentTarget = $(event.currentTarget);

          var name = currentTarget.attr('name');
          var value = currentTarget.val();

          FilterMenu.filterKeys[name] = value;

          if ( currentTarget.is('select') ) {

            var text = currentTarget.find("option:selected").text();
            FilterMenu.filterKeys[name + "_text"] = (value == "" || value == null )?"":text;

          }

          callback();

        });

      }

    }
    
    $.fn.filterMenu = function(options) {
      
      var filterMenu = Object.create(FilterMenu);

      filterMenu.init(options, this);
      options.doFilter;
      return filterMenu;

    };

  }
));