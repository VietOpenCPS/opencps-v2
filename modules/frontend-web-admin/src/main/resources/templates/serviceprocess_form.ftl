<#if (Request)??>
	<#include "init.ftl">
</#if>

<form id="fm_process_info">
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Số hiệu quy trình</div>
		<div class="col-xs-12 col-sm-9">
			<input id="processNo" name="processNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:processNo"/>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Tên quy trình</div>
		<div class="col-xs-12 col-sm-9">
			<input id="processName" name="processName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:processName"/>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Mô tả</div>
		<div class="col-xs-12 col-sm-9">
			<textarea id="description" name="description" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:description"></textarea>
		</div>
	</div>
	<div class="row MT10 align-middle-lg">
		<div class="col-xs-12 col-sm-3">Thời gian xử lý</div>
		<div class="col-xs-12 col-sm-3">
			<input id="durationCount" name="durationCount" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:durationCount"/>
		</div>
		<div class="col-xs-12 col-sm-3">
			<div class="radio-inline"> <input type="radio" name="durationUnit" value="day"> <label>Tính theo ngày</label> </div>
		</div>
		<div class="col-xs-12 col-sm-3">
			<div class="radio-inline"> <input type="radio" name="durationUnit" value="hour"> <label>Tính theo giờ</label> </div>
		</div>
	</div>
	<div class="service-process-form-controls">
		<div class="service-process-form-entry">
			<div class="row MT10 align-middle-lg">
				<div class="col-xs-12 col-sm-3">Vai trò xử lý</div>
				<div class="col-xs-12 col-sm-3">
					<select class="form-control" id="administration" name="administration" data-bind="value: administrationName">
						<option value="">Chọn vai trò</option>

					</select>
				</div>
				<div class="col-xs-12 col-sm-3">
					<div class="radio-inline"> <input type="radio" name="stepAllowance" value="stepAllowanceViewOnly"> <label>Theo dõi</label> </div>
				</div>
				<div class="col-xs-12 col-sm-2">
					<div class="radio-inline"> <input type="radio" name="stepAllowance" value="stepAllowance"> <label>Chủ trì</label> </div>
				</div>
				<div class="col-xs-12 col-sm-1">
					<button class="btn btn-success btn-add" type="button">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row MT10 text-center">
		<button id="btn_save_service_process" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
		<button id="btn_cancle_service_process" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
	</div>
</form>

<script type="text/javascript">
	$(document).on('click', '.btn-add', function(e){
		e.preventDefault();

		var controlForm = $('.service-process-form-controls'),
				currentEntry = $(this).parents('.service-process-form-entry:first'),
				newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');
		newEntry.find('input [type="radio"]').val('');

		controlForm.find('.service-process-form-entry:not(:last) .btn-add')
				.removeClass('btn-add').addClass('btn-remove')
				.removeClass('btn-success').addClass('btn-danger')
				.html('<span class="glyphicon glyphicon-minus"></span>');
		}).on('click', '.btn-remove', function(e){
			$(this).parents('.service-process-form-entry:first').remove();
			e.preventDefault();
			return false;
	});

	$(document).on("click", "#btn_save_service_process", function(event){
		 event.preventDefault();

	});

	$(document).on("click", "#btn_cancle_service_process", function(event){
		 event.preventDefault();

		 $("ul.nav.nav-tabs li").removeClass("disabled-tab");
		 $("#service_process_list_view li:first").addClass("k-state-selected");
	});
</script>
