<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="">
	<h4>Thêm mới thao tác xử lý</h4>
	<form class="" name="fm" method="post">
		<div class="row MT20">
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Tên thao tác
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="actionName" name="actionName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:actionName"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Kích hoạt sự kiện
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<select class="form-control" name="" data-bind="value:">
							<option value="">Chọn sự kiện</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Điều kiện kiểm tra
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="preCondition" name="preCondition" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:preCondition"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-5">
				<div class="row"></div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="checkbox"> <input type="checkbox" data-bind="value:"> <label>Là bước hoàn thành</label> </div>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Bước thực hiện thao tác
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="preProcessStepId" name="preProcessStepId" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:preProcessStepId"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Bước sau thực hiện thao tác
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="postProcessStepId" name="postProcessStepId" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:postProcessStepId"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="checkbox"> <input type="checkbox" data-bind="value:"> <label>Phân công người xử lý</label> </div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<select class="form-control" name="" data-bind="value:">
							<option value="">Chọn vai trò</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="service-process-create-dossier-file-form-action-controls">
			<div class="service-process-create-dossier-file-form-action-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Tài liệu tạo mới</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-5">
						<select class="form-control" id="createDossierFiles" name="createDossierFiles" data-bind="value:createDossierFiles">
							<option value=""></option>

						</select>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="checkbox"> <input type="checkbox" name="" data-bind="value:"> <label>Bắt buộc</label> </div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="checkbox"> <input type="checkbox" name="" data-bind="value:"> <label>Ký số</label> </div>
					</div>
					<#-- <div class="col-xs-12 col-sm-2">
						<div class="checkbox"> <input type="checkbox" name="" dât-bind="value:"> <label>Trả về</label> </div>
					</div> -->
					<div class="col-xs-12 col-sm-3">
						<button class="btn btn-success btn-add-action-role-create-dossier-file" type="button">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="service-process-form-action-controls">
			<div class="service-process-form-action-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Kết quả trả về</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-5">
						<select class="form-control" id="returnDossierFiles" name="returnDossierFiles" data-bind="value:returnDossierFiles">
							<option value=""></option>

						</select>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="checkbox"> <input type="checkbox" name="" data-bind="value:"> <label>Bắt buộc</label> </div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="checkbox"> <input type="checkbox" name="" data-bind="value:"> <label>Ký số</label> </div>
					</div>
					<#-- <div class="col-xs-12 col-sm-2">
						<div class="checkbox"> <input type="checkbox" name="" dât-bind="value:"> <label>Trả về</label> </div>
					</div> -->
					<div class="col-xs-12 col-sm-3">
						<button class="btn btn-success btn-add-action-role" type="button">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="checkbox"> <input type="checkbox" name="requestPayment" data-bind="value:requestPayment"> <label>Yêu cầu thanh toán</label> </div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input class="form-control" type="text" name="" value="" data-bind="value:stepName">
					</div>
				</div>
				<div class="row">
					<label>Mã đồng bộ</label>
					<div class="col-xs-12 col-sm-12">
						<input class="form-control" type="text" name="syncActionCode" id="syncActionCode" value="" data-bind="value:stepName">
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_service_process_action" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_service_process_action" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(document).on('click', '.btn-add-action-role', function(e){
		e.preventDefault();

		var controlForm = $('.service-process-form-action-controls'),
		currentEntry = $(this).parents('.service-process-form-action-entry:first'),
		newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');
		newEntry.find('input [type="radio"]').val('');

		controlForm.find('.service-process-form-action-entry:not(:last) .btn-add-action-role')
		.removeClass('btn-add-action-role').addClass('btn-remove-action-role')
		.removeClass('btn-success').addClass('btn-danger')
		.html('<span class="glyphicon glyphicon-minus"></span>');
	}).on('click', '.btn-remove-action-role', function(e){
		$(this).parents('.service-process-form-action-entry:first').remove();
		e.preventDefault();
		return false;
	});

	$(document).on("click",".btn-add-action-role-create-dossier-file",function(e){
		e.preventDefault();

		var controlForm = $('.service-process-create-dossier-file-form-action-controls'),
		currentEntry = $(this).parents('.service-process-create-dossier-file-form-action-entry:first'),
		newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');
		newEntry.find('input [type="radio"]').val('');

		controlForm.find('.service-process-create-dossier-file-form-action-entry:not(:last) .btn-add-action-role-create-dossier-file')
		.removeClass('btn-add-action-role-create-dossier-file').addClass('btn-remove-action-role-create-dossier-file')
		.removeClass('btn-success').addClass('btn-danger')
	}).on('click', '.btn-remove-action-role-create-dossier-file', function(e){
		$(this).parents('.service-process-create-dossier-file-form-action-entry:first').remove();
		e.preventDefault();
		return false;
	});

	$("#preProcessStepId").kendoComboBox({
		dataTextField: "preProcessStepId",
		dataValueField: "preProcessStepName",
		dataSource: "",
		filter: "contains"
	});

	$("#postProcessStepId").kendoComboBox({
		dataTextField: "postProcessStepId",
		dataValueField: "postProcessStepName",
		dataSource: "",
		filter: "contains"
	});

	// $("#autoEvent").kendoComboBox({
	// 	dataTextField: "activeStatusName",
	// 	dataValueField: "activeStatusCode",
	// 	dataSource: "",
	// 	filter: "contains"
	// });

	$("#assignUserId").kendoComboBox({
		dataTextField: "assignUserName",
		dataValueField: "assignUserId",
		dataSource: "",
		filter: "contains"
	});

	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});
</script>
