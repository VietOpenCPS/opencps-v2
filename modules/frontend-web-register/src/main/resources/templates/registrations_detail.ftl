<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="box" id="detailRegistrations">

	<div class="row-header align-middle">
		<div class="background-triangle-big">Hồ sơ doanh nghiệp</div> 
		<span class="text-bold" data-bind="text: registrationModel.serviceName"></span>

	</div>

	<div class="dossier-general-info P15 MB15" style="display: none;">
		<div class="col-sm-12">
			Cơ quan thực hiện 
			<span class="text-bold" data-bind="text: registrationModel.govAgencyName"></span>
		</div>
	</div>

	<div class="row" id="applicantInfo">
		<div class="col-sm-12">
			<div class="dossier-parts">
				<div class="head-part align-middle slide-toggle">
					<div class="background-triangle-small">I</div> 
					<div class="col-sm-12 PL0">
						
						<span class="text-uppercase hover-pointer">THÔNG TIN TÀI KHOẢN DOANH NGHIỆP</span>
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.applicantName" id="applicantName" name="applicantName" required="required" validationMessage="Nhập tên tổ chức/ Doanh nghiệp"> 
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.applicantIdNo" id="applicantIdNo" name="applicantIdNo"
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
											<input type="text" class="form-control input-sm" 
												data-bind="value: registrationModel.applicantIdDate" 
												id="applicantIdDate" name="applicantIdDate"> 
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Người đại diện (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.contactName" id="contactName" name="contactName"
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.contactTelNo" id="contactTelNo" name="contactTelNo" required="required" validationMessage="Nhập số điện thoại liên hệ"> 
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.contactEmail" id="contactEmail" name="contactEmail" required="required" validationMessage="Nhập địa chỉ email"> 
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
											<textarea type="text" class="form-control input-sm" data-bind="value: registrationModel.address" id="address" name="address"
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.cityCode" id="cityCode" name="cityCode" required="required" validationMessage="Chọn Tỉnh/ Thành Phố"> 
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.districtCode" id="districtCode" name="districtCode" required="required" validationMessage="Chọn Quận/ Huyện"> 
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.wardCode" id="wardCode" name="wardCode" required="required" validationMessage="Chọn Xã/ Phường"> 
											<span data-for="wardCode" class="k-invalid-msg"></span>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Trạng thái</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group text-left" 
											data-bind="text: registrationModel.registrationState_text, css: { red: registrationModel.registrationState_red }"> 
											
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
			<div class="head-part align-middle PB5 slide-toggle" >
				<div class="background-triangle-small hover-pointer">II</div> 
				<div class="col-sm-12 PL0">
					<span class="text-uppercase hover-pointer">Thành phần hồ sơ</span> 
					<span class="hover-pointer pull-right">
						<i class="text-light-gray">
							Những thành phần hồ sơ có dấu 
						(<span class="red">*</span>) là thành phần bắt buộc
						</i>
						<span>
							<i class="fa fa-angle-down hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
						</span>
					</span>
				</div>
				
			</div>
			
			<div class="content-part collapse in " id="registration-forms-listview" 
				data-role="listview"
				data-template="registration-forms-template"
				
				data-bind="source: registrationFormsListView_dataSource,
				events: {
					change: registrationFormsListView_change
				}"
			>
			</div>
			
			<div id="__registrationId" data-bind="visible: false, text: registrationModel.registrationId"></div>

			<script type="text/x-kendo-template" id="registration-forms-template">
				#if(!removed) { #
					
					<div class="registrationTemplateIndex">
						<div class="row-parts-head align-middle slide-toggle">
							<span class="text-bold MR5">#:itemIndex#.</span>
							<span class="hover-pointer"> #:formName# </span>
	
							<div class="actions">
								
								#if(multiple) { #
									<a href="javascript:;"
										data-bind="events: {
											click: registrationFormsListView_addTemplate
										}"
										data-formno="#:formNo#">
										<i class="fa fa-plus-circle text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
									</a>
								#}#
								
								<a href="javascript:;"
									data-bind="events: {
										click: registrationFormsListView_deleteTemplate
									}"
									data-referenceuid="#:referenceUid#" >
									<i class="fa fa-times text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
								</a>
		
								<a href="javascript:;" 
									data-bind="events: {
										click: registrationFormsListView_viewJasperTemplate
									}"
									title="Số version" data-referenceuid="#:referenceUid#" data-formname="#:formName#">
									<span class="number-in-circle" >#:version#</span>
								</a>
								
								<a href="javascript:;" 
									data-bind="events: {
										click: registrationFormsListView_genAlpacaToForm
									}"
									data-pk="#:id#" data-formno="#:formNo#">
									<span class="number-in-circle" > 
										<i class="fa fa-eye" aria-hidden="true"></i>
									</span>
								</a>
								
							</div>
						</div>

						<div class="collapse" id="collapseRegistrationPart#:id#">
							<div class="col-xs-12 col-sm-12 text-right">
								
								# var checkState = viewRegistrationModel.registrationModel.registrationState; 
								if ( checkState ==0 || checkState ==3 ) {#
									<button id="btn-save-formalpaca#:id#" 
										class="btn btn-active MB10 MT10 MR20 saveFormAlpaca" 
										type="button" data-pk="#:formNo#" referenceUid="#:id#">
										Ghi lại
									</button>
								#}#
								<input type="hidden" name="" id="dossierFileId#:id#" value="#:id#">
							</div>
		
							<div class="col-sm-12" style="height:450px; width:100%;overflow:auto;" >
		
								<form id="formPartNo#:id#">

								</form>
		
							</div>
						</div>
				
					</div>
				#}#
			</script>

		</div>
	</div>

</div>

<div class="button-row MT20">

	<button class="btn btn-active" 
		data-bind="events: {
			click: saveRegistration}, css: { hidden: registrationModel.registrationState_hidden }"
		id="btn-save-registrations" type="button" 
		data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý...">
		<i class="fa fa-save"></i> Lưu
	</button>
	
</div>

<script type="text/javascript">
	
	var fnBack;
	var fnNext;
	$(function(){

		$("#applicantIdDate").kendoDatePicker({
			start: "month",
			depth: "year",
			format: "dd/MM/yyyy",
			dateInput: false
		});

	});


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
var getReferentUidFile = function(dossierId,dossierPartNo){
	var dossierFile;
	if(dossierId){
		$.ajax({
			type : 'GET', 
			dataType : "json",
			url  : '${api.server}/dossiers/' +$('#__registrationId').text().trim()+ '/files', 
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

var fnSaveForm = function(id, value){
	var current = $("#btn-save-formalpaca"+id);
	var referentUid = current.attr("referenceUid");
	
	if(referentUid){
		$.ajax({
			url : "${api.server}/registrations/" + $('#__registrationId').text().trim() + "/forms/"+referentUid+"/formdata",
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

</script>

<style>

#applicantInfo .form-group {
    margin-bottom: 5px;
}

</style>