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
					<div class="row-parts-head MT5" data-bind="css: { point-event-none: registrationModel.registrationState_hidden }">

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
										<span class="text-bold">Tỉnh/ Thành Phố <span class="red">(*)</span></span>
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
										<span class="text-bold">Quận/ Huyện <span class="red">(*)</span></span>
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
										<span class="text-bold">Xã/ Phường <span class="red">(*)</span></span>
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
				#if(!removed) { 
					 var checkState = viewRegistrationModel.registrationModel.registrationState; 
				#
					
					<div class="registrationTemplateIndex" 
						data-bind="events: {
							click: registrationFormsListView_toggleAlpacaToForm}" data-pk="#:id#" data-formno="#:formNo#">
						
						<div class="row-parts-head align-middle slide-toggle">
							<span class="text-bold MR5">#:itemIndex#.</span>
							<span class="hover-pointer"> #:formName# </span>
	
							<div class="actions">
								
								# if ( checkState ==0 || checkState ==3 ) {#
									#if(multiple) { #
										<a href="javascript:;"
											data-bind="events: {
												click: registrationFormsListView_addTemplate
											}"
											data-formno="#:formNo#">
											<i class="fa fa-plus-circle text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
										</a>

										<a href="javascript:;"
												data-bind="events: {
												click: registrationFormsListView_deleteTemplate
											}"
											data-referenceuid="#:referenceUid#" >
											<i class="fa fa-times text-light-gray" aria-hidden="true" style="font-size: 150%;"></i>
										</a>
									#}#
								
									
								#}#
		
								<a href="javascript:;" 
									data-bind="events: {
										click: registrationFormsListView_viewJasperTemplate
									}"
									title="Xem file Pdf" data-referenceuid="#:referenceUid#" data-formname="#:formName#">
									<span class="number-in-circle" >#:version#</span>
								</a>
								
								<a href="javascript:;" 
									data-bind="events: {
										click: registrationFormsListView_genAlpacaToForm
									}"
									title="Xem chi tiết" data-pk="#:id#" data-formno="#:formNo#">
									<span class="number-in-circle" > 
										<i class="fa fa-eye" aria-hidden="true"></i>
									</span>
								</a>
								
							</div>
						</div>

						<div class="collapse" id="collapseRegistrationPart#:id#">
							
							<div class="col-sm-12" style="height:450px; width:100%;overflow:auto;" >

								<form class="formAlpacaRegistration" id="formPartNo#:id#" 
									data-bind="css: { point-event-none: registrationModel.registrationState_hidden }" data-referenceuid="#:id#" data-formname="#:formName#">
								</form>
									
							</div>
							
							<div class="col-xs-12 col-sm-12 text-right">
								
								# if ( checkState ==0 || checkState ==3 ) {#
									<button id="btn-save-formalpaca#:id#" 
										class="btn btn-active MB10 MT10 MR20"
										data-bind="events: {
											click: registrationFormsListView_saveFormAlpaca
										}"
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
			data-bind="events: {
				click: saveRegistration}, css: { hidden: registrationModel.registrationState_hidden }"
			id="btn-save-registrations" type="button" 
			data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý...">
			<i class="fa fa-save"></i> Gửi
		</button>

		<button class="btn btn-active" 
			data-bind="events: {
				click: saveDraftsRegistration}, css: { hidden: registrationModel.registrationState_hidden }"
			id="btn-save-drafts-registrations" type="button" 
			data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang xử lý...">
			<i class="fa fa-save"></i> Lưu nháp
		</button>

		<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"  data-bind="css: { hidden: registrationModel.registrationState_hidden }">Thêm thành phần hồ sơ
			<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" data-template="registrationTemplateMultipleTemplate" data-bind="source: registrationTemplateMultiple">
			
		</ul>
		<script type="text/x-kendo-template" id="registrationTemplateMultipleTemplate">
			<li data-formno="#:formNo#" data-bind="click: onChangeAddRegistrationTemplate" class="hover-pointer">#:formName#</li>
		</script>
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