;( function( window ) {

	'use strict';

	/**
	* Extend obj function
	*
	* This is an object extender function. It allows us to extend an object
	* by passing in additional variables and overwriting the defaults.
	*/
	function extend( a, b ) {
	for( var key in b ) { 
		if( b.hasOwnProperty( key ) ) {
			a[key] = b[key];
		}
	}
	return a;
	}

	function MobilinkFormOnline( options ) {
	this.options = extend( {}, this.options );
	extend( this.options, options );
	this._init();
	}
 
	
	MobilinkFormOnline.prototype.options = {
	groupId: "default",
	dataValue : "default",
	dataText : "default",
	className: "default",
	dataPK: "default",
	container: "default",
	alpacaAPI: "default",
	alpacaDataAPI: "default",
	jasperAPI: "default",
	}
	
	MobilinkFormOnline.prototype._init = function() {
	// create element
	this.inputForm = document.createElement('div');
	
	var inputFormHTML = '';
				
	inputFormHTML +='<ul class="nav nav-tabs">';
	inputFormHTML +='<li class="active"><a data-toggle="tab" href="#alpacajs_input_form">Mã tạo giao diện nhập liệu</a></li>';
	
	if ( this.options.jasperAPI != "default") {
		
		inputFormHTML +='<li><a data-toggle="tab" href="#jasper_report_input_form">Mã tạo giao diện báo cáo</a></li>';
		
	}
	
	inputFormHTML +='</ul>';
	
	inputFormHTML +='<div class="tab-content">';
		
	inputFormHTML +='<div id="alpacajs_input_form" class="tab-pane fade in active panel-body">';
	inputFormHTML +='<div style="position: relative;">';
	inputFormHTML +='<textarea id="input_alpacajs_code" class="form-control" rows="8" ></textarea>';	
	inputFormHTML +='<div id="alpacajs_processing" class="lmask hidden"></div>';
	inputFormHTML +='</div>';
	inputFormHTML +='<br/>';
	inputFormHTML +='<button class="btn btn-primary image-preview-input submit-button MR15" id="submit-form-alpaca-script">'; 
	inputFormHTML +='<i class="fa fa-floppy-o" aria-hidden="true"></i> Ghi lại'; 
	inputFormHTML +='</button>';
	inputFormHTML +='<button class="btn btn-primary image-preview-input review-button" id="preview-alpaca-script" data-type="preview">'; 
	inputFormHTML +='<i class="fa fa-eye" aria-hidden="true"></i> Xem trước'; 
	inputFormHTML +='</button>';
	inputFormHTML +='</div>';
	
	if ( this.options.jasperAPI != "default") {
		
		inputFormHTML +='<div id="jasper_report_input_form" class="tab-pane fade panel-body">';
		inputFormHTML +='<textarea id="input_jasper_code" class="form-control" rows="8" ></textarea>';
		inputFormHTML +='<br/>';
		inputFormHTML +='<button class="btn btn-primary image-preview-input submit-button" id="submit-form-jasper-script">'; 
		inputFormHTML +='<i class="fa fa-floppy-o" aria-hidden="true"></i> Ghi lại'; 
		inputFormHTML +='</button>';
		inputFormHTML +='</div>';
		
	}
	
	inputFormHTML +='</div>';


	this.inputForm.innerHTML = inputFormHTML;
			
	this.showForm = document.createElement('div');
	
	var showFormHTML = '';
	
	showFormHTML +='<div id="alpacajs_input_form" class="tab-pane fade in active panel-body PT0">';
	showFormHTML +='<div style="position: relative;">';
	showFormHTML +='<div id="alpacajs_processing" class="lmask" style="min-height: 100px;"></div>';
	showFormHTML +='</div>';
	showFormHTML +='<div class="PL10 PR10">';
	showFormHTML +='<button class="btn btn-primary image-preview-input submit-button MR15" id="submit-form-alpaca-script-data">'; 
	showFormHTML +='<i class="fa fa-floppy-o" aria-hidden="true"></i> Ghi lại'; 
	showFormHTML +='</button>';
	
	if ( this.options.jasperAPI != "default") {
		
		showFormHTML +='<button class="btn btn-primary image-preview-input review-button" id="export-alpaca-script" >'; 
		showFormHTML +='<i class="fa fa-print" aria-hidden="true"></i> In ra'; 
		showFormHTML +='</button>';
		
	}
	
	showFormHTML +='</div>';
	showFormHTML +='</div>';


	this.showForm.innerHTML = showFormHTML;
	// run the events
	this._events();
		
	};
	
	MobilinkFormOnline.prototype._events = function() {
	
	
 }
	

	/**
	* MobilinkFormOnline show
	*
	* This function simply shows our Simple Alert by appending it
	* to the wrapper in question.
	*/
	MobilinkFormOnline.prototype.showInput = function() {
		
		$(this.options.container).html(this.inputForm);
		
		var groupId = this.options.groupId;
			var dataPK = this.options.dataPK;
			var alpacaAPI = this.options.alpacaAPI;
			var alpacaDataAPI = this.options.alpacaDataAPI;
			var jasperAPI = this.options.jasperAPI;
		
		//init alpacaJs data
		$.ajax({
			url: alpacaAPI + "/" + dataPK + "/inputform",
			headers: {
				"groupId": groupId
			},
			type: 'GET',
			dataType: 'text',
				success: function(result) {
				
				$('textarea#input_alpacajs_code').val(result);
				
				},
			error: function(xhr){
				
			}

		});	
		
		//events
		
		$(document).delegate('#submit-form-alpaca-script', "click", function(e) {
			
			e.preventDefault();
		
			submitAlpacaJsFormOnline(groupId, alpacaAPI, dataPK, $('textarea#input_alpacajs_code').val());
		
		});
		
		if ( this.options.jasperAPI != "default") {
			
			//init jasper data
			$.ajax({
				url: jasperAPI + "/" + dataPK + "/reportform",
				headers: {
					"groupId": groupId
				},
				type: 'GET',
				dataType: 'text',
					success: function(result) {
					
					$('textarea#input_jasper_code').val(result);
					
					},
				error: function(xhr){
					
					}

			});	
			
			//events
			
			$(document).delegate('#submit-form-jasper-script', "click", function(e) {
				
				e.preventDefault();
			
				submitJasperFormOnline(groupId, jasperAPI, dataPK, $('textarea#input_jasper_code').val());
			
			});
			
		}
		
		//events
		
		$(document).delegate('#preview-alpaca-script', "click", function(e) {
			
			e.preventDefault();
		
			var data_type = $('#preview-alpaca-script').attr('data-type');
			
			if(data_type == 'preview'){
				$('#preview-alpaca-script').attr('data-type', 'back');
				$('#preview-alpaca-script').html('<i class="fa fa-eye" aria-hidden="true"></i> Quay lại');
				$('#alpacajs_processing').removeClass('hidden');
			} else {
				$('#preview-alpaca-script').attr('data-type', 'preview');
				$('#preview-alpaca-script').html('<i class="fa fa-eye" aria-hidden="true"></i> Xem trước');
				$('#alpacajs_processing').addClass('hidden');
				$('#input_alpacajs_code').removeClass('hidden');
			}
			
			$.ajax({
				url: alpacaAPI + "/" + dataPK + "/inputform",
				headers: {
					"groupId": groupId
				},
				type: 'GET',
				dataType: 'text',
					success: function(result) {
					
					var alpacaSchemaJs = eval("(" + result + ")");
					
					$.ajax({
						url: alpacaDataAPI + "/" + dataPK + "/formdata",
						headers: {
							"groupId": groupId
						},
						type: 'GET',
						dataType: 'text',
							success: function(result) {
							
							var formData = eval("(" + result + ")");
							
							if(alpacaSchemaJs.options != 'undefined' && alpacaSchemaJs.schema != 'undefined'){

								if(formData != ''){
									alpacaSchemaJs.data = formData;
								}
							
							}
							$('#alpacajs_processing').removeClass('lmask');
							
							var el = $("#alpacajs_processing");
							Alpaca(el, alpacaSchemaJs);
							$('#input_alpacajs_code').toggle('hidden');
							
							},
						error: function(xhr){
							$('#alpacajs_processing').removeClass('lmask');
							$('#input_alpacajs_code').toggle('hidden');
							}
			
					});	
					
					},
				error: function(xhr){
					$('#alpacajs_processing').removeClass('lmask');
					$('#input_alpacajs_code').toggle('hidden');
					}
	
			});	
		
		});
		
	}

	MobilinkFormOnline.prototype.show = function() {
		
		
		$(this.options.container).html(this.showForm);
		
		var groupId = this.options.groupId;
			var dataPK = this.options.dataPK;
			var alpacaAPI = this.options.alpacaAPI;
			var alpacaDataAPI = this.options.alpacaDataAPI;
			var jasperAPI = this.options.jasperAPI;
		
		var className = this.options.className;
		
		var apiURL = '';
		var apiURLFormData = '';
			
			if(className == 'default'){
			
			apiURL = alpacaAPI + "/" + dataPK + "/inputform";
			apiURLFormData = alpacaDataAPI + "/" + dataPK + "/formdata";
			
			} else {
			
			apiURL = alpacaAPI + "/" + className + "/" + dataPK + "/inputform";
			apiURLFormData = alpacaDataAPI + "/" + className + "/" + dataPK + "/formdata";
			}
		
		$.ajax({
				url: apiURL,
				headers: {
					"groupId": groupId
				},
				type: 'GET',
				dataType: 'text',
					success: function(result) {
				
					var alpacaSchemaJs = eval("(" + result + ")");
					
					$.ajax({
						url: apiURLFormData,
						headers: {
							"groupId": groupId
						},
						type: 'GET',
						dataType: 'text',
							success: function(result) {
							
							var formData = eval("(" + result + ")");
							
							if(alpacaSchemaJs.options != 'undefined' && alpacaSchemaJs.schema != 'undefined'){

								if(formData != ''){
									alpacaSchemaJs.data = formData;
								}
								
								alpacaSchemaJs.postRender = function(control){

									$("#submit-form-alpaca-script-data").click(function(e) {
										var formData = control.getValue();
										
										submitAlpacaJsDataFormOnline(groupId, alpacaDataAPI, className, dataPK, JSON.stringify(formData));
									});
								}
					
							}
							
							$('#alpacajs_processing').removeClass('lmask');
							
							var el = $("#alpacajs_processing");
							
							Alpaca(el, alpacaSchemaJs);
							
							},
						error: function(xhr){
							$('#alpacajs_processing').removeClass('lmask');
							}
			
					});	
					
					},
				error: function(xhr){
					$('#alpacajs_processing').removeClass('lmask');
					}
	
			});
		
	}
		
window.MobilinkFormOnline = MobilinkFormOnline;

function submitAlpacaJsDataFormOnline (groupId, apiURL, className, pk, data) {
	
	var apiPutURL = '';
		
	if(className == 'default'){
			
		apiPutURL = apiURL + "/" + pk + "/formdata";
		
	} else {
			
		apiPutURL = apiURL + "/" + className + "/" + pk + "/formdata";
			
	}
	
	$.ajax({
		url: apiPutURL,
		type: 'PUT',
		headers: {
			"groupId": groupId
		},
		dataType: 'json',
		async: false,
		data:{
			formData: data
		},
		success: function(response) {
			showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
		},
		error: function(xhr, textStatus, errorThrown) {
								
			showMessageByAPICode(xhr.status);
								
		}
	});
	
}

function submitJasperFormOnline (groupId, apiURL, pk, data) {
	
	$.ajax({
		url: apiURL + '/' + pk + '/reportform',
		type: 'PUT',
		headers: {
			"groupId": groupId
		},
		dataType: 'json',
		async: false,
		data:{
			reportForm: data
		},
		success: function(response) {
			showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
		},
		error: function(xhr, textStatus, errorThrown) {
								
			showMessageByAPICode(xhr.status);
								
		}
	});
	
}

function submitAlpacaJsFormOnline (groupId, apiURL, pk, data) {
	
	$.ajax({
		url: apiURL + '/' + pk + '/inputform',
		type: 'PUT',
		headers: {
			"groupId": groupId
		},
		dataType: 'json',
		async: false,
		data:{
			inputForm: data
		},
		success: function(response) {
			showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
		},
		error: function(xhr, textStatus, errorThrown) {
								
			showMessageByAPICode(xhr.status);
								
		}
	});
	
}

})( window );