<script type="text/javascript">
	
	var manageRegistrations = new kendo.Router({
		
	});
	
	// Show màn hình thông báo chi tiết
	manageRegistrations.route("/", function(id){
		
		//show registration detail
		$('#registration-detail-wrapper').show();
		$('#registration-jasper-wrapper').hide();
	});
	
	manageRegistrations.route("/jasper", function(id){
		
		//show jasper
		$('#registration-jasper-wrapper').show();
		$('#registration-detail-wrapper').hide();
	});

	manageRegistrations.start();
</script>


<script>
	var registrationFormsListView_dataSource;
	var registrationsListView_dataSource;
	var registrationModelObj = {};
	var firstTime = false;
	var getLinkWindowUrl = function( options) {


				var xhttp = new XMLHttpRequest();
				var filename;
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
					
					var url = window.URL.createObjectURL(xhttp.response);
					
					//callback success
					options.success({url : url, status : xhttp.status});
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

		var registrationModelMap = function( model ){

			console.log("model==========",model);

			if(model.registrationState == 0 || model.registrationState == 3){
				model.registrationState_hidden = false;
			}else {
				model.registrationState_hidden = true;
			}
			var registrationTemplateMultiple = fnGetRegistrationTemplate();

			var formNoIfOne = "";

			if(registrationTemplateMultiple.length === 1){
				formNoIfOne = registrationTemplateMultiple[0].formNo;
				registrationTemplateMultiple = [];
			}

			model.registrationTemplateMultiple = registrationTemplateMultiple;

			registrationModelObj = model;

			$("#registrationId__hidden").val(model.registrationId);

			if(model.registrationState == 0){
				model.registrationState_text = "Lưu nháp";
			}else if (model.registrationState == 1) {
				model.registrationState_text = "Chờ duyệt";
			}else if (model.registrationState == 2) {
				model.registrationState_text = "Đã duyệt";
			}else{
				model.registrationState_text = "Yêu cầu sửa đổi";
			}

			$("#cityCode__hidden").val(model.cityCode);
			$("#districtCode__hidden").val(model.districtCode);
			$("#wardCode__hidden").val(model.wardCode);

			var viewModel = kendo.observable({
				registrationModel : model,
				addTemplateIfOne : function(e){
					if($(e.currentTarget).attr("data-formno") !== ""){
						onChangeAddRegistrationTemplate(e.currentTarget);
					}
				},
				formNoIfOne : formNoIfOne,
				styleIfHasButton : function(e){
					if(model.registrationState_hidden){
						return "";
					}

					return "position:absolute; top : 7px;";
				}

			});

			kendo.bind($("#registrationModel"), viewModel);

			getSourceAddress( model.cityCode, model.districtCode);
			$("#registration-forms-listview").getKendoListView().dataSource.read({
				registrationId : model.registrationId
			});

			$(".registrationsLogItem").find("span").removeClass("fa fa-arrow-right");
			$('.registrationsLogItem[data-pk='+ model.registrationId +']').find("span").addClass('fa fa-arrow-right');

		};

		var updateDossierBusiness = function (e) {
			addNewRegistration("");

		};

		var addNewRegistration = function( govAgencyCode ){
			var vm = this;

			$.ajax({
				url : "${api.server}/registrations",
				dataType : "json",
				type : "POST",
				headers : {"groupId": ${groupId}},
				async : false,
				data : {
					applicantName : "${(applicant.applicantName)!}",
					applicantIdType : "${(applicant.applicantIdType)!}",
					applicantIdNo : "${(applicant.applicantIdNo)!}",
					applicantIdDate : "${(applicant.applicantIdDate)!}",
					address : "${(applicant.address)!}",
					cityCode : "${(applicant.cityCode)!}",
					districtCode : "${(applicant.districtCode)!}",
					wardCode : "${(applicant.wardCode)!}",
					contactName :"${(applicant.contactName)!}",
					contactTelNo : "${(applicant.contactTelNo)!}",
					contactEmail : "${(applicant.contactEmail)!}",
					govAgencyCode : govAgencyCode,
					representativeEnterprise : "${(applicant.representativeEnterprise)!}"
				},
				success : function(result){
					firstTime = true;
					registrationModelMap(result);

				},
				error : function(result){
					notification.show({
						message: "Khởi tạo hồ sơ thất bại!"
					}, "error");
				}
			});

		};
	var registrationFormsListView_addTemplate = function(e){
			var formNo = $(e).attr("data-formno");

			$.ajax({
				url : "${api.server}/registrations/" + registrationModelObj.registrationId + "/forms/"+formNo,
				dataType : "json",
				type : "POST",
				headers: {
					"groupId": ${groupId},
					Accept : "application/json"
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
					registrationFormsListView_dataSource.read({
						registrationId : registrationModelObj.registrationId
					});
				},
				error : function(xhr){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}
			});

		};

		var registrationFormsListView_deleteTemplate = function(e){

			var referenceUid = $(e).attr("data-referenceuid");
			$.ajax({
				url : "${api.server}/registrations/" +registrationModelObj.registrationId+ "/forms/"+ referenceUid,
				type : "DELETE",
				dataType : "text",
				headers: {
					"groupId": ${groupId}
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
					registrationFormsListView_dataSource.read({
						registrationId : registrationModelObj.registrationId
					});

				},
				error : function(xhr){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}

			});
		};

		var registrationFormsListView_viewJasperTemplate = function(e){

			var referenceUid = $(e).attr("data-referenceuid");
			var formName = $(e).attr("data-formname");
			//vm.set("registrationFormsListView_formName", formName);

			var url = "${api.server}/registrations/" + registrationModelObj.registrationId + "/forms/"+ referenceUid +"/preview";

			getLinkWindowUrl({
				method : "GET",
				url : url,
				headers : {"groupId": ${groupId}},
				success: function(options){

					window.open(options.url);

				},
				error: function(){
					console.log("err get file");
				}
			});

		};

		var registrationFormsListView_genAlpacaToForm = function(e){

			var id = $(e).attr("data-pk");
			var formNo = $(e).attr("data-formno");

			_genAlpacaToForm(id, formNo);

		};

		var registrationFormsListView_toggleAlpacaToForm = function(e){

			console.log(e);
			var id = $(e).attr("data-pk");
			var formNo = $(e).attr("data-formno");

			if ( $("#formPartNo"+id).children().length == 0) {
				_genAlpacaToForm(id, formNo);
			}

		};

		var saveRegistration = function(){

			//MAP cac tham so cua formalpaca TTCDN vao registration
			fnMapFormTTCDNtoReg();

			var promise = new Promise(function(resolve, reject){
				registrationFormsListView_saveFormAlpacaTTCDN(resolve,reject);
			});

			promise.then(function(value){
				$("#btn-save-registrations").button('loading');
				var applicantValidator = $("#applicantInfo").kendoValidator().data("kendoValidator");

				if(applicantValidator.validate()){
					$.ajax({
						url  : '${api.server}/registrations/' + registrationModelObj.registrationId, 
						dataType : "json",
						type : 'PUT', 
						headers: {"groupId": ${groupId}},
						data : {

							applicantName : $("#applicantName").val(),
							applicantIdType : $("#applicantIdType").val(),
							applicantIdNo : $("#applicantIdNo").val(),
							applicantIdDate : kendo.toString($("#applicantIdDate").data("kendoDatePicker").value(), 'dd-MM-yyyy HH:mm:ss'),
							address : $("#address").val(),
							cityCode : $("#cityCode__hidden").val(),
							districtCode : $("#districtCode__hidden").val(),
							wardCode : $("#wardCode__hidden").val(),
							contactName : $("#contactName").val(),
							contactTelNo : $("#contactTelNo").val(),
							contactEmail : $("#contactEmail").val(),
							registrationState : 1,
							representativeEnterprise : $("#chuc_danh_dai_dien_doanh_nghiep").val()

						},
						success :  function(result){ 

							$("#btn-save-registrations").button('reset');
							notification.show({
								message: "Yêu cầu được thực hiện thành công"
							}, "success");
							registrationModelMap(result);
							registrationsListView_dataSource.pushUpdate(result);

						},
						error:function(xhr){

							$("#btn-save-registrations").button('reset'); 
							notification.show({
								message: "Xảy ra lỗi, xin vui lòng thử lại"
							}, "error");
						}	
					});
				}else {
					$("#btn-save-registrations").button('reset');
					notification.show({
						message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi lưu!"
					}, "error");
				}
			}).catch(function(reason){
				console.log(reason);
			});

		};

		var saveDraftsRegistration = function(){

			//MAP cac tham so cua formalpaca TTCDN vao registration
			fnMapFormTTCDNtoReg();


			//su dung promise luu thong tin form TTCDN, sau do moi luu lai du lieu registration
			var promise = new Promise(function(resolve, reject){
				registrationFormsListView_saveFormAlpacaTTCDN(resolve,reject);
			});

			promise.then(function(value){
				$("#btn-save-drafts-registrations").button('loading');
				var applicantValidator = $("#applicantInfo").kendoValidator().data("kendoValidator");

				if(applicantValidator.validate()){

					$.ajax({
						url  : '${api.server}/registrations/' + registrationModelObj.registrationId, 
						dataType : "json",
						type : 'PUT', 
						headers: {"groupId": ${groupId}},
						data : {

							applicantName : $("#applicantName").val(),
							applicantIdType : $("#applicantIdType").val(),
							applicantIdNo : $("#applicantIdNo").val(),
							applicantIdDate : kendo.toString($("#applicantIdDate").data("kendoDatePicker").value(), 'dd-MM-yyyy HH:mm:ss'),
							address : $("#address").val(),
							cityCode : $("#cityCode__hidden").val(),
							districtCode : $("#districtCode__hidden").val(),
							wardCode : $("#wardCode__hidden").val(),
							contactName : $("#contactName").val(),
							contactTelNo : $("#contactTelNo").val(),
							contactEmail : $("#contactEmail").val(),
							registrationState : 0,
							representativeEnterprise : $("#chuc_danh_dai_dien_doanh_nghiep").val()
						},
						success :  function(result){ 

							$("#btn-save-drafts-registrations").button('reset');
							notification.show({
								message: "Yêu cầu được thực hiện thành công"
							}, "success");

							registrationModelMap(result);
							registrationsListView_dataSource.pushUpdate(result);

						},
						error:function(xhr){

							$("#btn-save-drafts-registrations").button('reset'); 
							notification.show({
								message: "Xảy ra lỗi, xin vui lòng thử lại"
							}, "error");
						}	
					});

				}else {
					$("#btn-save-drafts-registrations").button('reset');
					notification.show({
						message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi lưu!"
					}, "error");
				}

			}).catch(function(reason){
				console.log(reason);
			});

			
		};

		var registrationFormsListView_saveFormAlpaca = function(e){

			fnMapFormTTCDNtoReg();

			var id = $(e).attr("data-pk");
			var referentUidFile = $(e).attr("data-referenceuid");

			var formType = $("#formPartNo"+referentUidFile+" .formType").val();
			var value ;

			if(formType !== "dklr"){
				value = $("#formPartNo"+referentUidFile).alpaca('get').getValue();

				var errorMessage = '';
				$("#formPartNo"+referentUidFile+' div[class*="has-error"] > label').each(function( index ) {

					errorMessage = "notValid";

				});

				if(errorMessage === '' && referentUidFile){
					$.ajax({
						url : "${api.server}/registrations/" +$('#__registrationId').text().trim() + "/forms/"+referentUidFile+"/formdata",
						dataType : "json",
						type : "PUT",
						headers: {
							"groupId": ${groupId},
							Accept : "application/json"
						},
						data : {
							formdata: JSON.stringify(value)
						},
						success : function(result){
							notification.show({
								message: "Yêu cầu được thực hiện thành công!"
							}, "success");
						},
						error : function(result){
							notification.show({
								message: "Thực hiện không thành công, xin vui lòng thử lại!"
							}, "error");
						}
					});
				}else {
					notification.show({
						message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi ghi lại!"
					}, "error");
				}
			}

		};

		var registrationFormsListView_saveFormAlpacaTTCDN = function(resolve, reject){

			var formTTCDN = $("form[data-pk=TTCDN]");
			var referentUidFile = formTTCDN.attr("data-referenceuid");

			var formType = $("#formPartNo"+referentUidFile+" .formType").val();
			var value ;

			if(formType !== "dklr"){
				value = $("#formPartNo"+referentUidFile).alpaca('get').getValue();

				var errorMessage = '';
				$("#formPartNo"+referentUidFile+' div[class*="has-error"] > label').each(function( index ) {

					errorMessage = "notValid";

				});

				if(errorMessage === '' && referentUidFile){
					$.ajax({
						url : "${api.server}/registrations/" +$('#__registrationId').text().trim() + "/forms/"+referentUidFile+"/formdata",
						dataType : "json",
						type : "PUT",
						headers: {
							"groupId": ${groupId},
							Accept : "application/json"
						},
						data : {
							formdata: JSON.stringify(value)
						},
						success : function(result){
							notification.show({
								message: "Yêu cầu được thực hiện thành công!"
							}, "success");
							resolve(true);

						},
						error : function(result){
							notification.show({
								message: "Thực hiện không thành công, xin vui lòng thử lại!"
							}, "error");
							resolve(false);
						}
					});
				}else {
					notification.show({
						message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi ghi lại!"
					}, "error");
					resolve(false);
				}
			}

		};

		var fnMapFormTTCDNtoReg = function(){
			$("#applicantName").val($("#ten_doanh_nghiep").val());
			$("#applicantIdType").val();
			$("#applicantIdNo").val($("#ma_so_doanh_nghiep").val());
			$("#address").val($("#dia_chi_chinh_doanh_nghiep").val());
			$("#contactName").val($("#nguoi_dai_dien_doanh_nghiep").val());
			$("#contactTelNo").val($("#so_dien_thoai_doanh_nghiep").val());
			$("#contactEmail").val($("#email_doanh_nghiep").val());

			$("#cityCode__hidden").val($("#tinh").val());
			$("#districtCode__hidden").val($("#huyen").val());
			$("#wardCode__hidden").val($("#xa").val());

		}

		var _genAlpacaToFormTTCDN = function(id, formNo){

			$.ajax({
				url : "${api.server}/registrations/" + registrationModelObj.registrationId + "/forms/"+id+"/formscript",
				dataType : "text",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					$("#formPartNo"+id).empty();
					console.log("${(applicant.representativeEnterprise)!}");
					if(result){

						var alpaca = eval("(" + result + ")");
						var formdata = _fnGetFormData(registrationModelObj.registrationId,id);
						if(firstTime || formdata.ten_doanh_nghiep.startsWith("_")){
							formdata.dia_chi_chinh_doanh_nghiep = "${(applicant.address)!}";
							formdata.email_doanh_nghiep = "${(applicant.contactEmail)!}";
							formdata.ten_doanh_nghiep = "${(applicant.applicantName)!}";
							formdata.ma_so_doanh_nghiep = "${(applicant.applicantIdNo)!}";
							formdata.so_dien_thoai_doanh_nghiep = "${(applicant.contactTelNo)!}";
							formdata.nguoi_dai_dien_doanh_nghiep = "${(applicant.contactName)!}";
							formdata.chuc_danh_dai_dien_doanh_nghiep = "${(applicant.representativeEnterprise)!}";
						}

						alpaca.data = formdata;

						$("#formPartNo"+id).alpaca(alpaca);

						firstTime = false;
					}
				},
				error : function(result){

				}
			});

		};

		var _genAlpacaToForm = function(id, formNo){

			$.ajax({
				url : "${api.server}/registrations/" + registrationModelObj.registrationId + "/forms/"+id+"/formscript",
				dataType : "text",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					$("#formPartNo"+id).empty();

					if(result){

						var alpaca = eval("(" + result + ")");
						var formdata = _fnGetFormData(registrationModelObj.registrationId,id);

						alpaca.data = formdata;

						$("#formPartNo"+id).alpaca(alpaca);
					}
				},
				error : function(result){

				}
			});

		};

		var _fnGetFormData = function(registrationId,referentUid){

			var value = null;
			if(registrationId && referentUid){
				$.ajax({
					url : "${api.server}/registrations/"+registrationId+"/forms/"+referentUid+"/formdata",
					type : "GET",
					dataType : "json",
					headers: {
						"groupId": ${groupId},
						Accept : "application/json"
					},
					async : false,
					success : function(result){
						value = result;

					},
					error : function(result){

					}

				});
			}

			return value;
		};

		function getSourceAddress( cityCode, districtCode){

			$("#districtCode").kendoComboBox({
				placeholder : "Chọn Quận/ Huyện",
				dataTextField : "itemName",
				dataValueField : "itemCode",
				noDataTemplate : "Không có dữ liệu",
				filter: "contains",
				dataSource : {
					transport : {
						read : function(options){
							var parent = cityCode;
							if(options.data.parent){
								parent = options.data.parent;
							}

							$.ajax({
								url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
								dataType : "json",
								type : "GET",
								headers: {"groupId": ${groupId}},
								data : {
									parent : parent
								},
								success : function(result){
									result["data"] = result.total == 0?[]: result["data"];
									options.success(result);
								},
								error : function(result){
									options.error(result);
								}
							});
						}
					},
					schema : {
						data : "data",
						total : "total"
					}
				},
				change : function(e){
					var value = this.value();
					if(value){
						$("#wardCode").data("kendoComboBox").dataSource.read({
							parent : value
						});
						$("#wardCode").data("kendoComboBox").select(-1);
					}

				}
			});

			$("#wardCode").kendoComboBox({
				placeholder : "Chọn Xã/ Phường",
				dataTextField : "itemName",
				dataValueField : "itemCode",
				noDataTemplate : "Không có dữ liệu",
				filter: "contains",
				dataSource : {
					transport : {
						read : function(options){
							var parent = districtCode;
							if(options.data.parent){
								parent = options.data.parent;
							}
							$.ajax({
								url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
								dataType : "json",
								type : "GET",
								headers: {"groupId": ${groupId}},
								data : {
									parent : parent
								},
								success : function(result){
									result["data"] = result.total == 0?[]: result["data"];
									options.success(result);
								},
								error : function(result){
									options.error(result);
								}
							});
						}
					},
					schema : {
						data : "data",
						total : "total"
					}
				}

			});
		}

		var onChangeAddRegistrationTemplate = function(e){
			console.log(e);
			var value = $(e).attr("data-formno");
			console.log(value);
			$.ajax({
				url : "${api.server}/registrations/"+registrationModelObj.registrationId+"/forms/"+value,
				dataType : "json",
				type : "POST",
				headers : {"groupId": ${groupId}},
				data : {

				},
				success : function(result){

					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
					registrationFormsListView_dataSource.read({
						registrationId : registrationModelObj.registrationId
					});
				},
				error : function(result){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}
			});
		};


	var viewRegistrationModel;
	document.addEventListener("DOMContentLoaded", function(event) {

		$(function(){
			console.log("--start registration--");
			$('#registration-jasper-wrapper').hide();

			viewRegistrationModel = kendo.observable({

				govAgency_dataSource : new kendo.data.DataSource({

					transport: {
						read: function(options) {

							$.ajax({
								url: "${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
								dataType: "json",
								type: 'GET',
								headers: {
									"groupId": ${groupId}
								},
								success: function(result) {
									result["data"] = result.total==0 ? []: result["data"];
									options.success(result);
								},
								error: function(xhr, textStatus, errorThrown) {
									options.success({total:0, data:[]});
								}
							});
						}
					},
					schema: {
						data: "data",
						total: "total"
					}
				}),
				govAgency_value: "default",
				govAgency_change : function(e){

					console.log("onchange co quan dk");
				},
				show: "false",
				registrationModelObj : registrationModelObj,
				registrationTemplateMultiple : registrationTemplateMultipleObj,

				registrationFormsListView_formName : "default",

			});

			var registrationTemplateMultipleObj = fnGetRegistrationTemplate();

		

		registrationsListView_dataSource = new kendo.data.DataSource({

			transport :{
				read : function(options){

					$.ajax({
						url : "${api.server}/registrations/",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data : {
							owner: true,
							sort : "modified"
						},
						success : function(result){
							result["data"] = result.total == 0?[]: result["data"];
							options.success(result);

							if ( result.total==0 ){

								addNewRegistration("");

							} else {

								registrationModelMap(result.data[0]);
							}

						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "registrationId"
				}
			}
		});

		$("#registrationsListView").kendoListView({
			dataSource: registrationsListView_dataSource,
			template: kendo.template($("#registrationsTemplate").html())
		});


		$(document).on("click",".registrationsLogItem",function(){
			var registrationId = $(this).attr("data-pk");
			console.log("registrationId ======",registrationId);

			var select = registrationsListView_dataSource.get(registrationId);
			console.log("select==========",select);
			registrationModelMap(select);
		});

		registrationFormsListView_dataSource = new kendo.data.DataSource({

			transport :{
				read : function(options){

					if (options.data.registrationId) {
						$.ajax({
							url : "${api.server}/registrations/" + options.data.registrationId + "/forms",
							dataType : "json",
							type : "GET",
							headers : {"groupId": ${groupId}},
							data : {

							},
							success : function(result){

								result["data"] = result.total == 0?[]: result["data"];

								result["data"] = result["data"].filter(function( obj ) {
									return ( obj.removed == false ) ;
								});

								var indexWorkshop = 0;
								for (index=0; index< result["data"].length; index++){

									if(result["data"][index]["formNo"] !== "TTCDN"){
										indexWorkshop ++ ;
									}
									result["data"][index]["itemIndex"] = indexWorkshop;

								}
								options.success(result);
							},
							error : function(result){
								options.error(result);
							}
						});
					} else {
						options.success({total:0, data:[]});
					}

				}
			},
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "referenceUid"
				}
			}
		});

		var indexWorkshop = 0;
		$("#registration-forms-listview").kendoListView({
			dataSource : registrationFormsListView_dataSource,
			template : kendo.template($("#registration-forms-template").html()),
			autoBind : false
		});

		$("#formAlpacaTTCDN").kendoListView({
			dataSource : registrationFormsListView_dataSource,
			template : kendo.template($("#registration-form-ttcdn").html()),
			autoBind : false
		});
		
		
		$("#cityCode").kendoComboBox({
			placeholder : "Chọn tên thành phố",
			dataTextField : "itemName",
			dataValueField : "itemCode",
			noDataTemplate : "Không có dữ liệu",
			filter: "contains",
			autoBind : true,
			dataSource : {
				transport : {
					read : function(options){
						$.ajax({
							url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							data : {
								parent : 0
							},
							success : function(result){
								result["data"] = result.total == 0?[]: result["data"];
								options.success(result);
							},
							error : function(result){
								options.error(result);
							}
						});
					}
				},
				schema : {
					data : "data",
					total : "total"
				}
			},
			change : function(e){
				var value = this.value();
				
				if(value){
					$("#districtCode").data("kendoComboBox").dataSource.read({
						parent : value
					});
					$("#districtCode").data("kendoComboBox").select(-1);
					$("#wardCode").data("kendoComboBox").select(-1);
				}

			}

		});
		
	});

});




var fnGetRegistrationTemplate = function(){
	var arrRes = new Array();
	$.ajax({
		url : "${api.server}/registrationtemplates",
		dataType : "json",
		type : "GET",
		headers: {"groupId": ${groupId}},
		data : {
		},
		async : false,
		success : function(result){
			if(result.data){
				for (var i = 0; i < result.data.length; i++) {
					if(result.data[i].multiple){
						arrRes.push(result.data[i]);
					}
				}
			}
		},
		error : function(result){
			
		}
	});
	return arrRes;
}
</script>