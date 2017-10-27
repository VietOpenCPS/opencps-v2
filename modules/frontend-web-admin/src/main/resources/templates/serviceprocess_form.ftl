<#if (Request)??>
<#include "init.ftl">
</#if>

<form id="fm_process_info">
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Số hiệu quy trình</div>
		<div class="col-xs-12 col-sm-9">
			<input id="processNo" name="processNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: processNo"/>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Tên quy trình</div>
		<div class="col-xs-12 col-sm-9">
			<input id="processName" name="processName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: processName"/>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Mô tả</div>
		<div class="col-xs-12 col-sm-9">
			<textarea id="description" name="description" class="k-textbox form-control" data-bind="value: description"></textarea>
		</div>
	</div>
	<div class="row MT10 align-middle-lg">
		<div class="col-xs-12 col-sm-3">Thời gian xử lý</div>
		<div class="col-xs-12 col-sm-9">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<input id="durationCount" name="durationCount" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: durationCount"/>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="row">
						<div class="col-xs-12 col-sm-6">
							<div class="radio-inline"> <input type="radio" name="durationUnit" value="0" data-bind="checked: durationUnit"> <label>Tính theo ngày</label> </div>
						</div>
						<div class="col-xs-12 col-sm-6">
							<div class="radio-inline"> <input type="radio" name="durationUnit" value="1" data-bind="checked: durationUnit"> <label>Tính theo giờ</label> </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="service-process-form-controls">
		<div class="service-process-form-entry">
			<div class="row MT10 align-middle-lg">
				<div class="col-xs-12 col-sm-3">Vai trò xử lý</div>
				<div class="col-xs-12 col-sm-9">
					<div class="row">
						<div class="col-xs-12 col-sm-6">
							<div class="row">
								<div class="col-xs-12 col-sm-12">
									<select class="form-control" name="roleId" data-bind="value: roleId">
										<option value=""></option>
									</select>
								</div>
							</div>
							<div class="row MT5">
								<div class="col-xs-12 col-sm-12">
									<div class="form-group"> <input name="condition" type="text" class="form-control" placeholder="Điều kiện phân công xử lý" data-bind="value: condition" title="Điều kiện phân công xử lý"> </div>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-sm-6">
							<div class="row">
								<div class="col-xs-12 col-sm-5">
									<div class="radio-inline"> <input type="radio" name="moderator_1" value="false" data-bind="checked: moderator"> <label>Theo dõi</label> </div>
								</div>
								<div class="col-xs-12 col-sm-5">
									<div class="radio-inline"> <input type="radio" name="moderator_1" value="true" data-bind="checked: moderator"> <label>Chủ trì</label> </div>
								</div>
								<div class="col-xs-12 col-sm-2 PL0">
									<button class="btn btn-success btn-add" type="button">
										<span class="glyphicon glyphicon-plus"></span>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3"></div>
		<div class="col-xs-12 col-sm-9">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="checkbox"> <input type="checkbox" name="generateDossierNo" id="generateDossierNo" data-bind="checked: generateDossierNo"> <label>Tạo số tiếp nhận hồ sơ</label> </div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="checkbox"> <input type="checkbox" name="generateDueDate" id="generateDueDate" data-bind="checked: generateDueDate"> <label>Sinh ngày hẹn trả hồ sơ</label> </div>
				</div>
			</div>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3"></div>
		<div class="col-xs-12 col-sm-9">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<input id="dossierNoPattern" name="dossierNoPattern" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: dossierNoPattern"/>
				</div>
				<div class="col-xs-12 col-sm-6">
					<input id="dueDatePattern" name="dueDatePattern" class="k-textbox form-control" data-bind="value: dueDatePattern"/>
				</div>
			</div>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3"></div>
		<div class="col-xs-12 col-sm-9">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<div class="checkbox"> <input type="checkbox" name="generatePassword" id="generatePassword" data-bind="checked: generatePassword"> <label>Sinh mật mã tra cứu hồ sơ</label> </div>
				</div>
				<div class="col-xs-12 col-sm-6">
					<div class="checkbox"> <input type="checkbox" name="directNotification" id="directNotification" data-bind="checked: directNotification"> <label>Gửi thông báo về cho chủ hồ sơ</label> </div>
				</div>
			</div>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Server đồng bộ</div>
		<div class="col-xs-12 col-sm-9">
			<input id="serverNo" name="serverNo" class="k-textbox form-control" data-bind="value: serverNo"/>
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

		var controlForm = $('.service-process-form-controls');
		var currentEntry = $(this).parents('.service-process-form-entry:first');

		var radioValue = currentEntry.find('input[type=radio]:checked').val();

		var newEntry = $(currentEntry.clone()).appendTo(controlForm);

		var index = controlForm.children().length;
		var name = 'moderator_' + index;
		newEntry.find('input[type=radio]').attr('name', name);

		newEntry.find('select').val('');
		newEntry.find('input[name=condition]').val('');
		newEntry.find('input[type=radio]:checked').prop('checked',false);
		currentEntry.find('input[type=radio][value=' + radioValue + ']').prop('checked',true);

		controlForm.find('.service-process-form-entry:not(:last) .btn-add')
		.removeClass('btn-add').addClass('btn-remove')
		.removeClass('btn-success').addClass('btn-danger')
		.html('<span class="glyphicon glyphicon-minus"></span>');
	}).on('click', '.btn-remove', function(e){
		$(this).parents('.service-process-form-entry:first').remove();
		e.preventDefault();
		return false;
	});

	$(document).ready(function(){
		$.ajax({
			url: "${api.server}" + "/jobpos",
			type: "GET",
			dataType: "json",
			headers: {"groupId": ${groupId}},
			async: false,
			success: function(result) {
				console.log(result);
				if (result && result.data && result.data.length > 0){
					result.data.forEach(function(jobpos){
						var newOpt = $(".service-process-form-entry select")[0].appendChild(document.createElement('option'));
						newOpt.value = jobpos.roleId;
						newOpt.text = jobpos.title;
					});
				}
			}
		});
	});

</script>
