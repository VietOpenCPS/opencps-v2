<#if (Request)??>
	<#include "init.ftl">
</#if>

<div>
	<h4>Thêm bước xử lý</h4>
	<form name="fm" class="" method="post">
		<div class="row MT20">
			<div class="col-xs-12 col-sm-6">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Tên bước
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="stepName" name="stepName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:stepName"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Số thứ tự
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="sequenceNo" name="sequenceNo" class="k-textbox form-control" data-bind="value:sequenceNo"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-6">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Trạng thái chính
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="dossierStatus" name="dossierStatus" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:dossierStatus"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Thời gian xử lý
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="durationCount" name="durationCount" type="number" class="k-textbox form-control" data-bind="value:durationCount"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-6">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Trạng thái phụ
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="dossierSubStatus" name="dossierSubStatus" class="form-control" data-bind="value:dossierSubStatus"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-4">
				<div class="checkbox"> <input type="checkbox"> <label>Cho phép sửa hồ sơ</label> </div>
			</div>
		</div>
		<div class="service-process-form-step-controls">
			<div class="service-process-form-step-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Vai trò xử lý</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-6">
						<select class="form-control" id="administration" name="administration" data-bind="value: administrationName">
							<option value="">Chọn vai trò</option>

						</select>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="radio-inline"> <input type="radio" name="stepAllowance" value="stepAllowanceViewOnly" data-bind="value:stepAllowanceViewOnly"> <label>Theo dõi</label> </div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="radio-inline"> <input type="radio" name="stepAllowance" value="stepAllowance" data-bind="value:stepAllowance"> <label>Chủ trì</label> </div>
					</div>
					<div class="col-xs-12 col-sm-1">
						<button class="btn btn-success btn-add-step-role" type="button">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="service-process-form-step-paper-result-controls">
			<div class="service-process-form-step-paper-result-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Giấy tờ kết quả kèm theo</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-6">
						<select class="form-control" id="administration" name="administration" data-bind="value: administrationName">
							<option value="">Chọn giấy tờ kết quả</option>

						</select>
					</div>
					<div class="col-xs-12 col-sm-1">
						<button class="btn btn-success btn-add-paper-result" type="button">
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
						Nhúng ajax của phần nghiệp vụ
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<textarea class="form-control" name="name" rows="4"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_service_process_step" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_service_process_step" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(document).on('click', '.btn-add-step-role', function(e){
		e.preventDefault();

		var controlForm = $('.service-process-form-step-controls'),
				currentEntry = $(this).parents('.service-process-form-step-entry:first'),
				newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');
		newEntry.find('input [type="radio"]').val('');

		controlForm.find('.service-process-form-step-entry:not(:last) .btn-add-step-role')
				.removeClass('btn-add-step-role').addClass('btn-remove-step-role')
				.removeClass('btn-success').addClass('btn-danger')
				.html('<span class="glyphicon glyphicon-minus"></span>');
		}).on('click', '.btn-remove-step-role', function(e){
			$(this).parents('.service-process-form-step-entry:first').remove();
			e.preventDefault();
			return false;
	});

	$(document).on('click', '.btn-add-paper-result', function(e){
		e.preventDefault();

		var controlForm = $('.service-process-form-step-paper-result-controls'),
				currentEntry = $(this).parents('.service-process-form-step-paper-result-entry:first'),
				newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');
		newEntry.find('input [type="radio"]').val('');

		controlForm.find('.service-process-form-step-paper-result-entry:not(:last) .btn-add-paper-result')
				.removeClass('btn-add-paper-result').addClass('btn-remove-paper-result')
				.removeClass('btn-success').addClass('btn-danger')
				.html('<span class="glyphicon glyphicon-minus"></span>');
		}).on('click', '.btn-remove-paper-result', function(e){
			$(this).parents('.service-process-form-step-paper-result-entry:first').remove();
			e.preventDefault();
			return false;
	});

	(function($){
		$("#dossierStatus").kendoComboBox({
			dataTextField: "dossierStatusName",
			dataValueField: "dossierStatusCode",
			dataSource: "",
			filter: "contains"
		});

		$("#dossierSubStatus").kendoComboBox({
			dataTextField: "dossierSubStatusName",
			dataValueField: "dossierSubStatusCode",
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

		$("#stepAllowance").kendoMultiSelect({
			placeholder: "Xử lý chính",
			dataTextField: "roleName",
			dataValueField: "roleId",
			autoClose: false,
			dataSource: {
				transport:{
					read:{
						url:"${api.server}/processsteps/id/stepallowances",
						dataType:"json",
						type:"GET"
					}
				},
				schema:{
					data:"data"
				}
			},
			filter: "contains"
		});

		$("#stepAllowanceViewOnly").kendoMultiSelect({
			placeholder: "Chỉ xem",
			dataTextField: "roleName",
			dataValueField: "roleId",
			autoClose: false,
			dataSource: {
				transport:{
					read:{
						url:"${api.server}/processsteps/id/stepallowances",
						dataType:"json",
						type:"GET"
					}
				},
				schema:{
					data:"data"
				}
			},
			filter: "contains"
		});

		$("#tabstrip_service_process_step_modal").kendoTabStrip({
				animation:  {
						open: {
								effects: "fadeIn"
						}
				}
		});

	})(jQuery);
</script>
