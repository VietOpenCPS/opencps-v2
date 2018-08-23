<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="box" id="detailRegistrations">
	
	<input type="hidden" name="registrationId__hidden" id="registrationId__hidden">
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
						
						<span class="text-uppercase hover-pointer">THÔNG TIN CHUNG DOANH NGHIỆP</span> 
						<span class="red ML30">
							<i class="red" data-bind="text:registrationModel.registrationState_text"></i>
						</span>
						<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
					</div>
					
				</div>
				<div class="content-part collapse in " id="collapseDossierI">
					<div class="MT5" >

						<div class="row MT10" style="display: none;">
						
							<div class="col-sm-6 text-right">
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Tên tổ chức/ Doanh nghiệp (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.applicantName" id="applicantName" name="applicantName"> 
											<!-- <span data-for="applicantName" class="k-invalid-msg"></span> -->
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Mã số thuế (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.applicantIdNo" id="applicantIdNo" name="applicantIdNo"> 
						
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
												data-role="datepicker" data-format="dd/MM/yyyy"
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
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.contactName" id="contactName" name="contactName"> 
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Số điện thoại liên hệ</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.contactTelNo" id="contactTelNo" name="contactTelNo"> 
											<!-- <span data-for="contactTelNo" class="k-invalid-msg"></span> -->
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Địa chỉ email liên hệ (<span class="red">*</span>)</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.contactEmail" id="contactEmail" name="contactEmail"> 
											<!-- <span data-for="contactEmail" class="k-invalid-msg"></span> -->
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
												style="min-height:70px;">
											</textarea>
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Tỉnh/ Thành Phố <span class="red">(*)</span></span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.cityCode" id="cityCode" name="cityCode"> 
											<!-- <span data-for="cityCode" class="k-invalid-msg"></span> -->
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Quận/ Huyện <span class="red">(*)</span></span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.districtCode" id="districtCode" name="districtCode"> 
											<!-- <span data-for="districtCode" class="k-invalid-msg"></span> -->
										</div>
									</div>
								</div>
						
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Xã/ Phường <span class="red">(*)</span></span>
									</div>
									<div class="col-sm-7">
										<div class="form-group"> 
											<input type="text" class="form-control input-sm" data-bind="value: registrationModel.wardCode" id="wardCode" name="wardCode"> 
											<!-- <span data-for="wardCode" class="k-invalid-msg"></span> -->
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-sm-5">
										<span class="text-bold">Trạng thái</span>
									</div>
									<div class="col-sm-7">
										<div class="form-group text-left" > 
											<i class="red" data-bind="text:registrationModel.registrationState_text"></i>
										</div>
									</div>
								</div>
								
								<input type="hidden" name="cityCode__hidden" id="cityCode__hidden">
								<input type="hidden" name="districtCode__hidden" id="districtCode__hidden">
								<input type="hidden" name="wardCode__hidden" id="wardCode__hidden">

							</div>
						</div>

						<div class="col-sm-12" id="formAlpacaTTCDN" style="height:450px; width:100%;overflow:auto;" data-bind="css: { point-event-none: registrationModel.registrationState_hidden }">
							
						</div>

						<script type="text/x-kendo-template" id="registration-form-ttcdn">
							#if(formNo === "TTCDN"){#

							<form class="formAlpacaRegistration" id="formPartNo#:id#" 
							data-referenceuid="#:id#" data-formname="#:formName#" data-pk="TTCDN">
							
							#
								_genAlpacaToFormTTCDN(id,formNo);
							#

							#}#
						</script>
						
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="dossierFormSubmiting">
		<div class="dossier-parts">
			<div class="head-part align-middle PB5" >
				<div class="background-triangle-small hover-pointer">II</div> 
				<div class="col-sm-12 PL0">
					<span class="text-uppercase hover-pointer" data-bind="attr : { style : styleIfHasButton }" style="">Các xưởng lắp ráp</span> 
					<span class="hover-pointer pull-right">
						<!-- <i class="text-light-gray">
							Những thành phần hồ sơ có dấu 
						(<span class="red">*</span>) là thành phần bắt buộc
						</i> -->
						<button class="dropdown-toggle btn btn-active btn-sm" type="button" data-toggle="dropdown"  data-bind="css: { hidden: registrationModel.registrationState_hidden },
						events: {
							click: addTemplateIfOne
						},attr : { data-formno : formNoIfOne }">Thêm thành phần hồ sơ
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" data-template="registrationTemplateMultipleTemplate" data-bind="source: registrationModel.registrationTemplateMultiple">

						</ul>
						<script type="text/x-kendo-template" id="registrationTemplateMultipleTemplate">
							<li data-formno="#:formNo#" onclick="onChangeAddRegistrationTemplate(this)" class="hover-pointer">#:formName#</li>
						</script>
					</span>
				</div>
				
			</div>
			
			<!-- <div class="content-part collapse in " id="registration-forms-listview" 
				data-role="listview"
				data-template="registration-forms-template"
				
				data-bind="source: registrationFormsListView_dataSource,
				events: {
					change: registrationFormsListView_change
				}"
			>
			</div> -->
			<div class="content-part" id="registration-forms-listview" 
			>
			</div>
			
			<div id="__registrationId" data-bind="visible: false, text: registrationModel.registrationId"></div>

			<script type="text/x-kendo-template" id="registration-forms-template">
				#if(!removed && formNo !== "TTCDN") { 
					 var checkState = registrationModelObj.registrationState; 
					 console.log("checkState========",checkState);
				#
					
					<div class="registrationTemplateIndex" 
						onclick="registrationFormsListView_toggleAlpacaToForm(this)" data-pk="#:id#" data-formno="#:formNo#">
						
						<div class="row-parts-head align-middle slide-toggle">
							<span class="text-bold MR5">#:itemIndex#.</span>
							<span class="hover-pointer"> #:formName# (#:itemIndex#)</span>
	
							<div class="actions">
								
								# if ( checkState == 0 || checkState == 3 ) {#
									#if(multiple) { console.log("multiple========",multiple);#
										<a href="javascript:;" onclick="registrationFormsListView_addTemplate(this)" 
											data-formno="#:formNo#">
											<i class="fa fa-plus-circle text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
										</a>

										<a href="javascript:;"
												onclick="registrationFormsListView_deleteTemplate(this)"
											data-referenceuid="#:referenceUid#" >
											<i class="fa fa-times text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
										</a>
									#}#
								
									
								#}#
		
								<a href="javascript:;" 
									onclick="registrationFormsListView_viewJasperTemplate(this)" 
									title="Xem file Pdf" data-referenceuid="#:referenceUid#" data-formname="#:formName#">
									<span class="number-in-circle" >#:version#</span>
								</a>
								
								<a href="javascript:;" 
									onclick="registrationFormsListView_genAlpacaToForm(this)" 
									title="Xem chi tiết" data-pk="#:id#" data-formno="#:formNo#">
									<span class="number-in-circle" > 
										<i class="fa fa-eye" aria-hidden="true"></i>
									</span>
								</a>
								
							</div>
						</div>

						<div class="collapse" id="collapseRegistrationPart#:id#">
							
							<div class="col-sm-12" style="height:450px; width:100%;overflow:auto;" data-bind="css: { point-event-none:registrationModel.registrationState_hidden }">
			
								<form class="formAlpacaRegistration #if(checkState !== 0 && checkState !== 3){# point-event-none #}#" id="formPartNo#:id#" 
									 data-referenceuid="#:id#" data-formname="#:formName#" >
								</form>
									
							</div>
							
							<div class="col-xs-12 col-sm-12 text-right">
								
								# if ( checkState ==0 || checkState ==3 ) {#
									<button id="btn-save-formalpaca#:id#" 
										class="btn btn-active MB10 MT10 MR20"
										onclick="registrationFormsListView_saveFormAlpaca(this)" 
										type="button" data-pk="#:formNo#" data-referenceuid="#:id#">
										Ghi lại
									</button>
								#}#
								<input type="hidden" name="" id="dossierFileId#:id#" value="#:id#">
							</div>
							
						</div>
				
					</div>
				#}#
			</script>

		</div>
	</div>

</div>

<div class="button-row MT20">

		 <div class="dropdown">
		 	<button class="btn btn-active" 
			data-bind="css: { hidden: registrationModel.registrationState_hidden }"
			id="btn-save-registrations" type="button"  onclick="saveRegistration(this)"
			data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý...">
			<i class="fa fa-save"></i> Gửi
		</button>

		<button class="btn btn-active" 
			data-bind="css: { hidden: registrationModel.registrationState_hidden }"
			id="btn-save-drafts-registrations" type="button" onclick="saveDraftsRegistration(this)"
			data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý...">
			<i class="fa fa-save"></i> Lưu nháp
		</button>

		
	</div>
	
</div>

<style>

#applicantInfo .form-group {
	margin-bottom: 5px;
}

.point-event-none {
	pointer-events: none;
}
</style>