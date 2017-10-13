<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="">
	<form id="serviceprocesses_detail_form_action" name="fm" method="post">
		<div class="row">
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Tên thao tác
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="actionName" name="actionName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: actionName"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Mã thao tác
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="actionCode" name="actionCode" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: actionCode"/>
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
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="preCondition" name="preCondition" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: preCondition"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2 col-sm-2"></div>
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Kích hoạt sự kiện
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<select class="form-control" id="autoEvent" name="autoEvent" data-bind="value: autoEvent">
							<option value=""></option>
							<option value="submit">submit</option>
							<option value="timmer">timmer</option>
						</select>
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
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="preStepCode" name="preStepCode" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: preStepCode"/>
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
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="postStepCode" name="postStepCode" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: postStepCode"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-5">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="checkbox"> <input type="checkbox" id="allowAssignUser" name="allowAssignUser" data-bind="value: allowAssignUser"> <label>Phân công người xử lý</label> </div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<select class="form-control" id="assignUserId" name="assignUserId" data-bind="value: assignUserId">
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-5">
				<div class="checkbox"> <input type="checkbox" id="rollbackable" name="rollbackable" data-bind="checked: rollbackable"> <label>Cho phép rollback</label> </div>
			</div>
		</div>
		<div class="service-process-create-dossier-file-form-action-controls">
			<div class="service-process-create-dossier-file-form-action-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Tài liệu tạo mới</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-5">
						<select class="form-control" id="createDossierFiles" name="createDossierFiles" data-bind="value:createDossierFiles">
							<option value=""></option>

						</select>
					</div>
					<div class="col-xs-12 col-sm-3">
						<button class="btn btn-success btn-add-action-role-create-dossier-file" type="button">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="service-process-form-action-controls">
			<div class="service-process-return-dossier-file-form-action-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Kết quả trả về</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-5">
						<select class="form-control" id="returnDossierFiles" name="returnDossierFiles" data-bind="value:returnDossierFiles">
							<option value=""></option>

						</select>
					</div>
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
						<div class="checkbox"> <input type="checkbox" id="requestPayment" name="requestPayment" data-bind="value:requestPayment"> <label>Yêu cầu thanh toán</label> </div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input class="form-control" type="text" name="paymentFee" id="paymentFee" data-bind="value: paymentFee">
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">Mã đồng bộ</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input class="form-control" type="text" name="syncActionCode" id="syncActionCode" value="" data-bind="value: syncActionCode">
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
		currentEntry = $(this).parents('.service-process-return-dossier-file-form-action-entry:first'),
		newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');

		controlForm.find('.service-process-return-dossier-file-form-action-entry:not(:last) .btn-add-action-role')
		.removeClass('btn-add-action-role').addClass('btn-remove-action-role')
		.removeClass('btn-success').addClass('btn-danger')
		.html('<span class="glyphicon glyphicon-minus"></span>');
	}).on('click', '.btn-remove-action-role', function(e){
		$(this).parents('.service-process-return-dossier-file-form-action-entry:first').remove();
		e.preventDefault();
		return false;
	});

	$(document).on("click",".btn-add-action-role-create-dossier-file",function(e){
		e.preventDefault();

		var controlForm = $('.service-process-create-dossier-file-form-action-controls'),
		currentEntry = $(this).parents('.service-process-create-dossier-file-form-action-entry:first'),
		newEntry = $(currentEntry.clone()).appendTo(controlForm);

		newEntry.find('select').val('');

		controlForm.find('.service-process-create-dossier-file-form-action-entry:not(:last) .btn-add-action-role-create-dossier-file')
		.removeClass('btn-add-action-role-create-dossier-file').addClass('btn-remove-action-role-create-dossier-file')
		.removeClass('btn-success').addClass('btn-danger')
		.html('<span class="glyphicon glyphicon-minus"></span>');
	}).on('click', '.btn-remove-action-role-create-dossier-file', function(e){
		$(this).parents('.service-process-create-dossier-file-form-action-entry:first').remove();
		e.preventDefault();
		return false;
	});

	$("#autoEvent").kendoComboBox({
		filter: "contains"
	});

	$("#assignUserId").kendoComboBox({
		dataTextField: "fullName",
		dataValueField: "employeeId",
		filter: "contains",
		dataSource : {
			transport : {
				read : {
					url : "${api.server}/employees",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					success : function(result){

					},
					error : function(xhr){

					}
				}
			},
			schema: {
				data : "data",
				total : "total"
			}
		},
		noDataTemplate: 'Không có dữ liệu'
	});

</script>
