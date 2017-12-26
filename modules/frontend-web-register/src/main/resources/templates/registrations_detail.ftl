<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="box" id="detailRegistrations">

	<div class="row-header align-middle">
		<div class="background-triangle-big">Hồ sơ doanh nghiệp</div> 
		<span class="text-bold" data-bind="text:serviceName"></span>
		<div class="pull-right group-icons">

			<a href="javascript:;" onclick="fnBack();">
				<i class="fa fa-reply" aria-hidden="true"></i>
				Quay lại
			</a>
		</div>
	</div>

	<div class="dossier-general-info P15 MB15" style="display: none;">
		<div class="col-sm-12">
			Cơ quan thực hiện <span class="text-bold" data-bind="text:govAgencyName"></span>
		</div>
	</div>

	<div class="row" id="applicantInfo">
		<div class="col-sm-12">
			<div class="dossier-parts">
				<div class="head-part align-middle" data-toggle="collapse" data-target="#collapseDossierI">
					<div class="background-triangle-small">I</div> 
					<div class="col-sm-12 PL0">
						
						<span class="text-uppercase hover-pointer">Thông tin chủ hồ sơ</span>
						<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
					</div>
					
				</div>
				<div class="content-part collapse in " id="collapseDossierI">
					<div class="row-parts-head MT5">

						<div class="row MT10">

							<div class="col-sm-6 text-right">
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Tên tổ chức/ Doanh nghiệp (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: applicantName" id="applicantName" name="applicantName" required="required" validationMessage="Nhập tên tổ chức/ Doanh nghiệp"> 
											<span data-for="applicantName" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Mã số thuế (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: applicantIdNo" id="applicantIdNo" name="applicantIdNo"
												required="required" validationMessage="Nhập mã số thuế"> 

										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Ngày cấp</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: applicantIdDate" id="applicantIdDate" name="applicantIdDate"> 
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Người đại diện (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: contactName" id="contactName" name="contactName"
												required="required" validationMessage="Nhập người đại diện"> 
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Số điện thoại liên hệ</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: contactTelNo" id="contactTelNo" name="contactTelNo" required="required" validationMessage="Nhập số điện thoại liên hệ"> 
											<span data-for="contactTelNo" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Địa chỉ email liên hệ (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: contactEmail" id="contactEmail" name="contactEmail" required="required" validationMessage="Nhập địa chỉ email"> 
											<span data-for="contactEmail" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>
							</div>

							<div class="col-sm-6 text-right">
								<div class="row">

									<div class="col-sm-5">
										<span class="text-bold">Địa chỉ (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<textarea type="text" class="form-control input-sm" data-bind="value: address" id="address" name="address"
												style="min-height:70px;" required="required" validationMessage="Nhập địa chỉ">
											</textarea>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Tỉnh/ Thành Phố</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: cityCode" id="cityCode" name="cityCode" required="required" validationMessage="Chọn Tỉnh/ Thành Phố"> 
											<span data-for="cityCode" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Quận/ Huyện</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: districtCode" id="districtCode" name="districtCode" required="required" validationMessage="Chọn Quận/ Huyện"> 
											<span data-for="districtCode" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Xã/ Phường</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: wardCode" id="wardCode" name="wardCode" required="required" validationMessage="Chọn Xã/ Phường"> 
											<span data-for="wardCode" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Trạng thái</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group text-left" data-bind="text: registrationState"> 
											
										</div>
									</div>
								</div>

							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="dossierFormSubmiting">
		<div class="dossier-parts">
			<div class="head-part align-middle PB5" data-toggle="collapse" data-target="#lsRegistrationtemplates">
				<div class="background-triangle-small hover-pointer">II</div> 
				<div class="col-sm-12 PL0">
					<span class="text-uppercase hover-pointer">Thành phần hồ sơ</span> 
					<span class="hover-pointer pull-right"><i class="text-light-gray">Những thành phần hồ sơ có dấu 
						(<span class="red">*</span>) là thành phần bắt buộc</i>
						<span>
							<i class="fa fa-angle-down hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
						</span>
					</span>
				</div>
				
			</div>
			<div class="content-part collapse in " id="lsRegistrationtemplates" >
				<#-- <#include "customer_dossier_online_form.ftl"> -->
			</div>

			<script type="text/x-kendo-template" id="templateRegistrationtemplates">
				# if(!removed) {#

				<div class="registrationTemplateIndex">
					<div class="row-parts-head align-middle" data-toggle="collapse" data-target="\\#collapseRegistrationPart#:id#">
						<span class="text-bold MR5">#:itemIndex#.</span>
						<span class="hover-pointer"> #:formName# 
						<#-- #
						if(required){
						#
						<span class="red">*</span>
						<input type="hidden" id="validPart#:id#" name="validPart#:id#" class="validPart" value="0">
						#}# -->
					</span>

					<div class="actions">
						
						<a href="javascript:;" class="registration-add-template" data-formno="#:formNo#" data-toggle="tooltip" data-placement="top">
							<i class="fa fa-plus-circle text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
						</a>
						
						<a href="javascript:;" class="registration-del-template" data-referenceuid="#:referenceUid#" >
							<i class="fa fa-times text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
						</a>

						<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số version" data-formno="#:formNo#" data-number="">
							<span class="number-in-circle" >#:version#</span>
						</a>
					</div>
				</div>

				
				<div class="collapse" id="collapseRegistrationPart#:id#">
					<div class="col-xs-12 col-sm-12 text-right">
						<button id="btn-save-formalpaca#:id#" class="btn btn-active MB10 MT10 MR20 saveForm saveFormAlpaca" 
						type="button" data-pk="#:formNo#" referenceUid="#:id#">Ghi lại</button>
						<input type="hidden" name="" id="dossierFileId#:id#" value="#:id#">
					</div>

					<div class="col-sm-12" style="height:450px; width:100%;overflow:auto;" >

						<form id="formPartNo#:id#">

						</form>

					#
						$.ajax({
						url : "${api.server}/registrations/${registrationId}/forms/"+id+"/formscript",
						dataType : "text",
						type : "GET",
						headers : {"groupId": ${groupId}},
						success : function(result){
							$("\\#formPartNo"+id).empty();
							
							if(result){
								var alpaca = eval("(" + result + ")");
								var formdata = fnGetFormData(${registrationId},id);
								if(formdata){
									$("\\#validPart"+formNo).val("1");
								}
								alpaca.data = formdata; 
			
								$("\\#formPartNo"+id).alpaca(alpaca);
							}
						},
						error : function(result){
				
						}
					});
				#

</div>
</div>

</div>
#}#
</script>

<script type="text/x-kendo-template" id="templateRegistrationtemplatesLogs">
	<div>
		<div class="row-parts-head align-middle" data-toggle="collapse" data-target="\\#collapseRegistrationPart#:id#">
			<span class="text-bold MR5">#:itemIndex#.</span>
			<span class="hover-pointer"> #:formName# </span>

			<div class="actions">
				<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số version" data-formno="#:formNo#" data-number="">
					<span class="number-in-circle" >0</span>
				</a>
			</div>
		</div>

		<div class="collapse" id="collapseRegistrationPart#:id#">
			<div class="col-xs-12 col-sm-12 text-right">
				<button id="btn-save-formalpaca#:id#" class="btn btn-active MB10 MT10 MR20 saveForm saveFormAlpaca" 
				type="button" data-pk="#:formNo#" referenceUid="#:id#">Ghi lại</button>
				<input type="hidden" name="" id="dossierFileId#:id#" value="#:id#">
			</div>

			<div class="col-sm-12" style="height:450px; width:100%;overflow:auto;" >

				<form id="formPartNo#:referenceUid#">

				</form>

				#
				try {
					if(formScript){
						var formScript = eval("(" + formScript + ")");
						if(formData){
							formScript.data = eval("(" + formData + ")");			
						}
						
						$("\\#formPartNo"+referenceUid).alpaca(formScript);
					}
				}catch(e){
					console.log(e);
				}
				
				#

			</div>
		</div>
	</div>
</script>
</div>
</div>

</div>

<div class="button-row MT20">
	<button class="btn btn-active" id="btn-back-registrations" type="button"><i class="fa fa-reply" aria-hidden="true"></i> Quay lại</button>
	<button class="btn btn-active" id="btn-save-registrations" type="button" data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý..."><i class="fa fa-save"></i> Lưu</button>
</div>

<script type="text/javascript">

	var funSaveRegistrations;
	var fnBack;
	var fnNext;
	var dataSourceRegistrationsTemplate;
	$(function(){

		$(document).off("click",".dossier-component-profile");
		$(document).on("click",".dossier-component-profile",function(){
			var partNo = $(this).attr("data-partno");
			var dossierId = "${(dossierId)!}";
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			
		});

		dataSourceRegistrationsTemplate = new kendo.data.DataSource({
			transport :{
				read : function(options){
					
					$.ajax({
						url : "${api.server}/registrations/${registrationId}/forms",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data : {

						},
						success : function(result){
							
							if (result.total == 0) {
								result["data"] = [];
							} else if (result.total == 1) {
								var dt = [result["data"]];
								result["data"] = dt;
							}
							options.success(result);
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
					id : "referenceUid"
				}
			}
		});

		var indexRegistrationsPart =0 ;
		$("#lsRegistrationtemplates").kendoListView({
			dataSource : dataSourceRegistrationsTemplate,
			change : function(){

			},
			template : function(data){

				if(!data.removed){
					indexRegistrationsPart ++;
					data.itemIndex = indexRegistrationsPart;
				}
				
				return kendo.template($("#templateRegistrationtemplates").html())(data);

			},
			dataBound : function(){
				indexRegistrationsPart = 0;
			}
		});

		$("#btn-save-registrations").click(function(){
			funSaveRegistrations();
		});

		$("#btn-back-registrations").click(function(){
			fnBack();
		});

		fnBack = function(){
			window.history.back();
		};

		funSaveRegistrations = function(){
			$("#btn-save-registrations").button('loading');
			var applicantValidator = $("#applicantInfo").kendoValidator().data("kendoValidator");

			if(applicantValidator.validate()){
				$.ajax({
					url  : '${api.server}/registrations/${registrationId}', 
					dataType : "json",
					type : 'PUT', 
					headers: {"groupId": ${groupId}},
					data : {

						applicantName : $("#applicantName").val(),
						applicantIdType : $("#applicantIdType").val(),
						applicantIdNo : $("#applicantIdNo").val(),
						applicantIdDate : $("#applicantIdDate").val(),
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
						console.log("success!");
						$("#btn-save-registrations").button('reset');             
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
						$("#lsRegistrationsLogs").getKendoListView().dataSource.read();
					},
					error:function(xhr){
						console.log(xhr);
						console.log("error!");
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

		}

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
				console.log("change");
				console.log(value);
				if(value){
					$("#districtCode").data("kendoComboBox").dataSource.read({
						parent : value
					});
					$("#districtCode").data("kendoComboBox").select(-1);
					$("#wardCode").data("kendoComboBox").select(-1);
				}

			}

		});

		$("#districtCode").kendoComboBox({
			placeholder : "Chọn Quận/ Huyện",
			dataTextField : "itemName",
			dataValueField : "itemCode",
			noDataTemplate : "Không có dữ liệu",
			dataSource : {
				transport : {
					read : function(options){
						var parent = "${(registration.cityCode)!}";
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
						var parent = "${(registration.districtCode)!}";
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

		$(function() {
			$("[data-role=combobox]").each(function() {
				var widget = $(this).getKendoComboBox();
				widget.input.on("focus", function() {
					widget.open();
				});
			});
		});

		var printDetailRegistrations = function(registrationId){
			if(registrationId){
				$.ajax({
					url : "${api.server}/registrations/"+registrationId,
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					success : function(result){
						
						/*23-12-2017 thanhnv: bo sung trang thai*/
						
						<#if constants.registrationStates?has_content>
							<#list constants.registrationStates as oRegistrationState>
								if (result.registrationState == "${oRegistrationState.value}") {
									result.registrationState = "${oRegistrationState.text}";
								}
							</#list>
						</#if>
						
						var viewModel = kendo.observable({

							registrationId : result.registrationId,
							applicantName : result.applicantName,
							applicantIdType : result.applicantIdType,
							applicantIdNo : result.applicantIdNo,
							applicantIdDate : result.applicantIdDate,
							address : result.address,
							cityCode : result.cityCode,
							districtCode : result.districtCode,
							wardCode : result.wardCode,
							contactName : result.contactName,
							contactTelNo : result.contactTelNo,
							contactEmail : result.contactEmail,
							govAgencyCode : result.govAgencyCode,
							registrationState : result.registrationState

						});
						kendo.bind($("#detailRegistrations"), viewModel);
					},
					error : function(result){

					}

				});
			}
		}

		printDetailRegistrations(${registrationId});

		var removeRegistrationsFile = function(registrationId, fileId){
			$.ajax({
				url : "${api.server}/registrations/"+registrationId+"/files/"+fileId,
				dataType : "json",
				type : "DELETE",
				headers : {"groupId": ${groupId}},
				success : function(result) {

				},
				error : function(result) {

				}
			});
		}


	});

var getReferentUidFile = function(dossierId,dossierPartNo){
	var dossierFile;
	if(dossierId){
		$.ajax({
			type : 'GET', 
			dataType : "json",
			url  : '${api.server}/dossiers/${registrationId}/files', 
			headers: {"groupId": ${groupId}},
			async : false,
			success :  function(result){ 
				if(result.data){
					for (var i = 0; i < result.data.length; i++) {
						if(result.data[i].eForm){
							if(dossierPartNo == result.data[i].dossierPartNo){
								dossierFile = result.data[i];
								return ;
							}

						}
					}
				}

			},
			error:function(result){

			}
		});
	}
	
	return dossierFile;
}

var fnGetFormData = function(registrationId,referentUid){
	var value = null;
	if(registrationId && referentUid){
		$.ajax({
			url : "${api.server}/registrations/"+registrationId+"/forms/"+referentUid+"/formdata",
			type : "GET",
			dataType : "json",
			async : false,
			success : function(result){
				value = result;

			},
			error : function(result){

			}

		});
	}

	return value;
}

var fnCheckValidTemplate = function(){
	
	var valid = true;
	try {

		$(".validPart").each(function(index){
			
			if($(this).val() === "0"){
				valid = false;
			}
		});

	}catch(e){
		valid = false;
	}

	return valid;
}

var fnSaveForm = function(id, value){
	var current = $("#btn-save-formalpaca"+id);
	var referentUid = current.attr("referenceUid");
	
	if(referentUid){
		$.ajax({
			url : "${api.server}/registrations/${registrationId}/forms/"+referentUid+"/formdata",
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
				
				$("#validPart"+id).val("1");
			},
			error : function(result){
				notification.show({
					message: "Thực hiện không thành công, xin vui lòng thử lại!"
				}, "error");
			}
		});
	}
}

$(document).off("click",".saveFormAlpaca");
$(document).on("click",".saveFormAlpaca",function(event){
	var id = $(this).attr("data-pk");
	var referentUidFile = $(this).attr("referenceUid");

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
				url : "${api.server}/registrations/${registrationId}/forms/"+referentUidFile+"/formdata",
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
					
					$("#validPart"+id).val("1");
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
});

/*$(document).ready(function(){
	$('html, body').animate({
		scrollTop: $("#dossierFormSubmiting").offset().top
	}, 700);
});*/

/*$("#numWorkshop").change(function(event){
	var numWorkshop = $(this).val();
	$(".registrationTemplateIndex").each(function(index){
		if(index > (numWorkshop - 1)){
			$(this).hide();
		}else {
			$(this).show();
		}
	});
});*/

$(document).on("click",".registration-add-template",function(){
	var formNo = $(this).attr("data-formno");

	$.ajax({
		url : "${api.server}/registrations/${registrationId}/forms/"+formNo,
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
			dataSourceRegistrationsTemplate.read();
		},
		error : function(xhr){
			notification.show({
				message: "Xảy ra lỗi, xin vui lòng thử lại"
			}, "error");
			dataSourceRegistrationsTemplate.read();
		}
	});
});

$(document).on("click",".registration-del-template",function(){
	var referenceUid = $(this).attr("data-referenceuid");

	$.ajax({
		url : "${api.server}/registrations/${registrationId}/forms/"+ referenceUid,
		type : "DELETE",
		headers: {
			"groupId": ${groupId}
		},
		success : function(result){
			notification.show({
				message: "Yêu cầu được thực hiện thành công"
			}, "success");
			dataSourceRegistrationsTemplate.read();
		},
		error : function(xhr){
			notification.show({
				message: "Xảy ra lỗi, xin vui lòng thử lại"
			}, "error");
		}

	});
});

</script>

<style>

#applicantInfo .form-group {
    margin-bottom: 5px;
}

<style>