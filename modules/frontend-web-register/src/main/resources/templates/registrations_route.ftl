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
console.log(">>>>>>>>>>>>>>>>>>>>>>>DOM");
var viewRegistrationModel;
document.addEventListener("DOMContentLoaded", function(event) {
console.log(">>>>>>>>>>>>>>>>>>>>>>>DOMContentLoaded");
$(function(){
	console.log(">>>>>>>>>>>>>>>>>>>>>>>document ready");
	$('#registration-jasper-wrapper').hide();
	
	viewRegistrationModel = kendo.observable({

		// co quan dang ky

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
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			console.log("onchange co quan dk");
		},
		show: "false",
		updateDossierBusiness : function (e) {
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			viewRegistrationModel.addNewRegistration("");
		
		},
		
		registrationsListView_dataSource : new kendo.data.DataSource({
		
			transport :{
				read : function(options){
					
					$.ajax({
						url : "${api.server}/registrations/",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data : {
							owner: true
						},
						success : function(result){
							result["data"] = result.total == 0?[]: result["data"];
							
							if ( result.total==0 ){
								
								viewRegistrationModel.addNewRegistration("");
							
							} else {
								
								viewRegistrationModel.registrationModelMap(result.data[0]);
								options.success(result);
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
		}),
		registrationsListView_change: function(e){
			var vm = this;
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			
			var pk = $(e.target).closest("li").attr("data-pk");
			
			var obj = vm.registrationsListView_dataSource.data().filter(function( obj ) {
							return ( obj.registrationId == pk ) ;
						});
			
			vm.registrationModelMap(obj[0]);
			
		},
		registrationModel: {
			registrationId : "default",
			applicantName : "default",
			applicantIdType : "default",
			applicantIdNo : "default",
			applicantIdDate : "default",
			address : "default",
			cityCode : "default",
			districtCode : "default",
			wardCode : "default",
			contactName : "default",
			contactTelNo : "default",
			contactEmail : "default",
			govAgencyCode : "default",
			registrationState : "default"
		
		},
		registrationModelMap: function( model ){
			
			var vm = this;
			var registrationModel = {};
			
			for(var k in model) {
			
				if( k == "applicantIdDate" && model[k] != "" ){
					registrationModel[k] = kendo.toString(kendo.parseDate(model[k]), 'dd/MM/yyyy');
					
				} else if (k == "registrationState") {
					<#if constants.registrationStates?has_content>
						<#list constants.registrationStates as oRegistrationState>
							if (model[k] == "${oRegistrationState.value}") {
								registrationModel[k] = "${oRegistrationState.value}";
								registrationModel[k+"_text"] = "${oRegistrationState.text}";
							}
							if (model[k] == 0) {
								registrationModel[k+"_red"] = true;
							} else {
								registrationModel[k+"_red"] = false;
							}
							if (model[k] == 0 || model[k] == 3) {
								registrationModel[k+"_hidden"] = false;
							} else {
								registrationModel[k+"_hidden"] = true;
							}
						</#list>
					</#if>
					} else 
						registrationModel[k] = model[k]
			
			}
			
			getSourceAddress( registrationModel.cityCode, registrationModel.districtCode);
			vm.set("registrationModel", registrationModel);
			
			//vm.registrationFormsListView_dataSource.read();
			$("#registration-forms-listview").getKendoListView().dataSource.read();
			
			// thay doi trang thai check
			$(".registrationsLogItem>span").removeClass("fa fa-check");
			$('.registrationsLogItem[data-pk='+ registrationModel.registrationId +']>span').addClass('fa fa-check');
		
		},
			
		registrationFormsListView_dataSource : new kendo.data.DataSource({
	
			transport :{
				read : function(options){
					
					var vm = viewRegistrationModel;
					
					if (vm.registrationModel.registrationId != "default") {
						$.ajax({
							url : "${api.server}/registrations/" + vm.registrationModel.registrationId + "/forms",
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
								
								var index = 0;
								for (index=0; index< result["data"].length; index++){
									
									result["data"][index]["itemIndex"] = index+1;
								
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
		}),
		registrationFormsListView_addTemplate: function(e){
			var vm = this;
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			var formNo = $(e.currentTarget).attr("data-formno");

			$.ajax({
				url : "${api.server}/registrations/" + vm.registrationModel.registrationId + "/forms/"+formNo,
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
					vm.registrationFormsListView_dataSource.read();
					//$("#registration-forms-listview").getKendoListView().dataSource.read();
				},
				error : function(xhr){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}
			});

		},
		registrationFormsListView_deleteTemplate : function(e){
			var vm = this;
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			
			var referenceUid = $(e.currentTarget).attr("data-referenceuid");
			$.ajax({
				url : "${api.server}/registrations/" +vm.registrationModel.registrationId+ "/forms/"+ referenceUid,
				type : "DELETE",
				headers: {
					"groupId": ${groupId}
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
					vm.registrationFormsListView_dataSource.read();
					//$("#registration-forms-listview").getKendoListView().dataSource.read();
				},
				error : function(xhr){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}
		
			});
		},
		registrationFormsListView_viewJasperTemplate : function(e){
			var vm = this;
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			
			var referenceUid = $(e.currentTarget).attr("data-referenceuid");
			var formName = $(e.currentTarget).attr("data-formname");
			vm.set("registrationFormsListView_formName", formName);
			
			//TODO: chuyen huong man hinh
			var url = "${api.server}/registrations/" + vm.registrationModel.registrationId + "/forms/"+ referenceUid +"/preview";
			
			vm.getLinkWindowUrl({
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
			
		},
		registrationFormsListView_formName : "default",
			
		//--------------
		addNewRegistration: function( govAgencyCode ){
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
					govAgencyCode : govAgencyCode
				},
				success : function(result){
					
					vm.registrationsListView_dataSource.read();
					//$("#registrationsListView").getKendoListView().dataSource.read();
				},
				error : function(result){
					notification.show({
						message: "Khởi tạo hồ sơ thất bại!"
					}, "error");
				}
			});
		
		},
		saveRegistration : function(){
			var vm = this;
		
			$("#btn-save-registrations").button('loading');
			var applicantValidator = $("#applicantInfo").kendoValidator().data("kendoValidator");

			if(applicantValidator.validate()){
				$.ajax({
					url  : '${api.server}/registrations/' + vm.registrationModel.registrationId, 
					dataType : "json",
					type : 'PUT', 
					headers: {"groupId": ${groupId}},
					data : {

						applicantName : $("#applicantName").val(),
						applicantIdType : $("#applicantIdType").val(),
						applicantIdNo : $("#applicantIdNo").val(),
						applicantIdDate : kendo.toString($("#applicantIdDate").data("kendoDatePicker").value(), 'dd-MM-yyyy HH:mm:ss'),
						address : $("#address").val(),
						cityCode : $("#cityCode").val(),
						districtCode : $("#districtCode").val(),
						wardCode : $("#wardCode").val(),
						contactName : $("#contactName").val(),
						contactTelNo : $("#contactTelNo").val(),
						contactEmail : $("#contactEmail").val(),
						registrationState : 1

					},
					success :  function(result){ 
						
						$("#btn-save-registrations").button('reset');
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
						vm.registrationsListView_dataSource.read();
						//$("#registrationsListView").getKendoListView().dataSource.read();
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

		},
		getLinkWindowUrl ( options) {

			// Use XMLHttpRequest instead of Jquery $ajax
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
			
		},
		registrationFormsListView_genAlpacaToForm: function(e){
			var vm = this;
			
			var id = $(e.currentTarget).attr("data-pk");
			var formNo = $(e.currentTarget).attr("data-formno");
			console.log(">>>>>>>>>>>>>>>>>FORM NO>>"+formNo);
			console.log(">>>>>>>>>>>>>>>>>id>>"+id);
			
			$.ajax({
				url : "${api.server}/registrations/" + vm.registrationModel.registrationId + "/forms/"+id+"/formscript",
				dataType : "text",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					$("#formPartNo"+id).empty();
					
					if(result){
						console.log(">>>>>>>>>>>...GEN registration form script>>>>>>>>>");
						var alpaca = eval("(" + result + ")");
						var formdata = fnGetFormData(vm.registrationModel.registrationId,id);
						if(formdata){
							$("#validPart"+formNo).val("1");
						}
						alpaca.data = formdata;
	
						$("#formPartNo"+id).alpaca(alpaca);
					}
				},
				error : function(result){
		
				}
			});
		
		}

	});
	
	// apply the bindings
	kendo.bind($("#registrationModel"), viewRegistrationModel);
	
	$("#cityCode").kendoComboBox({
		placeholder : "Chọn tên thành phố",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
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
		
	function getSourceAddress( cityCode, districtCode){

		$("#districtCode").kendoComboBox({
			placeholder : "Chọn Quận/ Huyện",
			dataTextField : "itemName",
			dataValueField : "itemCode",
			noDataTemplate : "Không có dữ liệu",
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
	
});
	
});

</script>